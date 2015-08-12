Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

$MSBuildVersion = '12.0'
$MSBuildPath = "${Env:ProgramFiles(x86)}\MSBuild\$MSBuildVersion\Bin\MSBuild.exe"
if ($env:MSBUILD)
{
    $MSBuildPath = $env:MSBUILD
}

Function Invoke-MSBuild()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$buildFileName,
        
        [hashtable]$properties
    )
    
    $arguments = @(
        $buildFileName
        '/consoleloggerparameters:ErrorsOnly'
        '/t:Clean,Build'
    )

    foreach ($property in $properties.GetEnumerator())
    {
        $arguments += "/p:$($property.Key)=$($property.Value)"
    }

    Write-Output '---> Start build.'
    Write-Verbose "MSBuild path: $MSBuildPath"
    Write-Verbose "Build arguments: $arguments"
    & $MSBuildPath $arguments
    if ($LASTEXITCODE -ne 0)
    {
        Write-Output "Build failed with exit code $lastExitCode"
        Exit $LASTEXITCODE
    }
    
    Write-Output '---> Build succeeded.'
}

Export-ModuleMember -Function Invoke-MSBuild
