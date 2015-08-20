Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Import-Module '.\project-consts.ps1'
Import-Module '.\modules\msbuild.psm1'
Import-Module '.\modules\nunit.psm1'

# Build
Invoke-MSBuild $solution $msbuildProperties -Verbose

# Test
Invoke-NUnit $testFile -Verbose
