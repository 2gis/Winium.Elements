Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Import-Module '.\ProjectData.ps1'
Import-Module '.\modules\versioning.psm1'
Import-Module '.\modules\changelog.psm1'
Import-Module '.\modules\msbuild.psm1'
Import-Module '.\modules\nunit.psm1'
Import-Module '.\modules\nuget.psm1'
Import-Module '.\modules\git.psm1'
Import-Module '.\modules\github.psm1'

$version = $env:release_version
$description = $env:release_description

# Update AssembyInfo
Update-AssemblyInfo $assemblyInfoPath $version -Verbose

# Update CHANGELOG.md
Update-Changelog $changelogPath $version $description -Verbose

# Update Nuspec file
Update-Nuspec $nuspecPath $version $description -Verbose

# Build
Invoke-MSBuild $solution $msbuildProperties -Verbose

# Test
Invoke-NUnit $testFile -Verbose

# Create nuget-package
New-Item -ItemType directory -Path $releaseDir | Out-Null
Invoke-NuGetPack $project $configuration $releaseDir -Verbose

# Git checkout master
Invoke-Git ('checkout', 'master') -Verbose

# Git add changes
Invoke-Git ('add', $assemblyInfoPath) -Verbose
Invoke-Git ('add', $changelogPath) -Verbose
Invoke-Git ('add', $nuspecPath) -Verbose

# Git commit and push
Invoke-GitCommit "Version $version" -Verbose
Invoke-Git ('push', 'origin', 'master') -Verbose

# Git tag and push
$buildUrl = $env:BUILD_URL
Invoke-GitTag "Version '$version'. Build url '$buildUrl'." "v$version" -Verbose
Invoke-Git ('push', 'origin', 'master', "v$version") -Verbose

# Push nuget-package
$package = Join-Path $releaseDir '*.nupkg'
Invoke-NuGetPush $package -Verbose

# Create github release
Invoke-CreateGitHubRelease '2gis' $githubProjectName $version $description $releaseDir -Verbose
