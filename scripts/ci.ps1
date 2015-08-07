Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Import-Module '.\modules\msbuild.psm1' -DisableNameChecking
Import-Module '.\modules\nunit.psm1' -DisableNameChecking

$configuration = 'Release'
$solutionDir = Join-Path $PSScriptRoot "../src"
$solution = Join-Path $solutionDir 'Winium.Elements.sln'
$testFile = Join-Path $solutionDir "Tests\Winium.Elements.Desktop.Tests\bin\$configuration\Winium.Elements.Desktop.Tests.dll"

$msbuildProperties = @{
    'Configuration' = $configuration
}

Invoke-MSBuild $solution $msbuildProperties -Verbose
Invoke-NUnit $testFile -Verbose
