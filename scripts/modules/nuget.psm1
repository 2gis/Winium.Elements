Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Function Invoke-NuGetPack()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$packTarget,
        
        [Parameter(Mandatory=$True)]
        [string]$configuration,

        [string]$outputDirectory = '.'
    )
    
    $nugetPath = Get-NuGetPath
    $arguments = @(
        'pack'
        $packTarget
        '-IncludeReferencedProjects'
        '-Properties'
        "Configuration=$configuration"
        '-OutputDirectory'
        $outputDirectory
    )

    Write-Output '---> Run NuGet pack.'
    Write-Verbose "NuGet path: $nugetPath"
    Write-Verbose "NuGet arguments: $arguments"
    & $nugetPath $arguments
    if ($LASTEXITCODE -ne 0)
    {
        throw "NuGet pack failed with exit code $LASTEXITCODE"
    }
    
    Write-Output '---> NuGet pack succeeded.'
}

Function Get-NuGetPath()
{
    if ($env:NUGET)
    {
        return $env:NUGET
    }
    
    Write-Verbose 'Environment variable NUGET not set'
    if (Get-Command 'nuget.exe' -ErrorAction SilentlyContinue) 
    {
        return 'nuget'
    }
    
    Write-Verbose 'Unable to find nuget.exe in your PATH'
    throw 'Unable to find path to nuget.exe. See more with -Verbose'
}

Export-ModuleMember -Function Invoke-NuGetPack
