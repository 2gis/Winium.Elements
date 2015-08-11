Function Build()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$msbuild,
        
        [Parameter(Mandatory=$True)]
        [string]$solutionDir,

        [string]$configuration = 'Release'
    )

    $solutionPath = Join-Path $solutionDir 'Winium.Elements.sln'
    &$msbuild ($solutionPath, '/v:minimal', "/p:configuration=$configuration", '/t:Clean,Build')
    if ($LASTEXITCODE -ne 0)
    {
        CustomExit "Build failed." 1
    }
}

Function Test()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$nunitConsole,
        
        [Parameter(Mandatory=$True)]
        [string]$solutionDir,

        [string]$configuration = 'Release',

        [string]$resultFile = 'nunit-result.xml'
    )

    $testFile = Join-Path $solutionDir "Tests\Winium.Elements.Desktop.Tests\bin\$configuration\Winium.Elements.Desktop.Tests.dll"
    &$nunitConsole ($testFile, "/xml=$resultFile", '/labels')
    if ($LASTEXITCODE -ne 0)
    {
        CustomExit "Tests failed. See $resultFile" 2
    }
}

Function Get-Env()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$envName
    )

    $envValue = Get-Item env:$envName -ErrorAction SilentlyContinue
    if (!$envValue)
    {
        CustomExit "Environment variable $envName not set" 1
    }
    
    return $envValue.Value
}

Function CustomExit()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$errorText,
        
        [Parameter(Mandatory=$True)]
        [int]$exitCode
    )

    Write-Host $errorText -ForegroundColor Red
    Exit $exitCode
}

Export-ModuleMember -Function Build, Test, Get-Env
