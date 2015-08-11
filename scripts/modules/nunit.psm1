Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

$NUnitVersion = '2.6.4'
$NUnitConsolePath = "${Env:ProgramFiles(x86)}\NUnit $NUnitVersion\bin\nunit-console.exe"
if ($env:NUNIT_CONSOLE)
{
    $NUnitConsolePath = $env:NUNIT_CONSOLE
}

Function Invoke-NUnit()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$testFile,

        [string]$resultFile = 'nunit-result.xml'
    )
    
    $arguments = @(
        $testFile
        "/xml=$resultFile"
        '/labels'
    )

    Write-Output '---> Run tests.'
    Write-Verbose "nunit-console path: $NUnitConsolePath"
    Write-Verbose "nunit-console arguments: $arguments"
    & $NUnitConsolePath $arguments
    if ($LASTEXITCODE -ne 0)
    {
        throw "Tests failed. See $resultFile"
    }
    
    Write-Output '---> Tests succeeded.'
}

Export-ModuleMember -Function Invoke-NUnit
