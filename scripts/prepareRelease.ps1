Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Function CleanDirectory($releaseDir)
{
    if (Test-Path $releaseDir)
    {
        Remove-Item $releaseDir -Force -Recurse
    }

    New-Item -ItemType directory -Path $releaseDir | Out-Null
}

$releaseDir = Join-Path $PSScriptRoot '../Release'
CleanDirectory $releaseDir

Import-Module '.\ci.ps1'
Import-Module '.\modules\nuget.psm1'

$project = Join-Path $solutionDir 'Winium.Elements.Desktop\Winium.Elements.Desktop.csproj'
Invoke-NuGetPack $project $configuration $releaseDir -Verbose
