$ErrorActionPreference = 'Stop'
#------------------------------

Import-Module .\methods.psm1

$workspace = $PSScriptRoot
$solutionDir = Join-Path $workspace "../src"

$msbuild = Get-Env 'MSBUILD'
$nunitConsole = Get-Env 'NUNIT_CONSOLE'

Build $msbuild $solutionDir
Test $nunitConsole $solutionDir
