Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

$mvnPath = ''
if (Get-Command 'mvn' -ErrorAction SilentlyContinue) 
{
    $mvnPath = 'mvn'
}
else
{
    Write-Output 'Unable to find mvn in your PATH'
    Exit 1
}

$projectDir = Join-Path $PSScriptRoot "../"

# Compile & Test
& $mvnPath -f $projectDir test
if ($LASTEXITCODE -ne 0)
{
    Write-Output "Compile or test failed. See output"
    Exit $LASTEXITCODE
}
