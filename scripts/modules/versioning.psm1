Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Function Update-AssemblyInfo()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$assemblyInfoPath,

        [Parameter(Mandatory=$True)]
        [string]$version
    )
    
    $fileVersionPattern = 'AssemblyFileVersion\(".*"\)'
    $infoVersionPattern = 'AssemblyInformationalVersion\(".*"\)'
    
    $fileVersion = 'AssemblyFileVersion("' + $version + '.*")';
    $infoVersion = 'AssemblyInformationalVersion("' + $version + '")';

    Write-Output "---> Updating '$assemblyInfoPath' -> $version"
    Write-Verbose "Set $fileVersion"
    Write-Verbose "Set $infoVersion"
    (Get-Content $assemblyInfoPath) | ForEach-Object {
        % {$_ -replace $fileVersionPattern, $fileVersion } |
        % {$_ -replace $infoVersionPattern, $infoVersion }
    } | Out-File $assemblyInfoPath -Encoding UTF8 -Force

    Write-Output '---> Update succeeded.'
}

Export-ModuleMember -Function Update-AssemblyInfo
