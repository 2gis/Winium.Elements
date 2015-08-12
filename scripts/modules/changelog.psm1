Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

$nl = [Environment]::NewLine

Function Update-Changelog()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$changelogPath,

        [Parameter(Mandatory=$True)]
        [string]$version,

        [Parameter(Mandatory=$True)]
        [string]$description
    )
    
    $additionalContent = "## v$version" + "$nl$nl" + $description + "$nl$nl$nl"
    
    $fileContent = [IO.File]::ReadAllText($changelogPath)
    $insertIndex = $fileContent.IndexOf('## v')
    if ($insertIndex -eq '-1')
    {
        $insertIndex = $fileContent.Length - 1
    }
    
    Write-Output "---> Updating '$changelogPath'"
    Write-Verbose "Content:$nl$additionalContent"
    $fileContent.Insert($insertIndex, $additionalContent) | 
        Out-File $changelogPath -Encoding UTF8 -Force

    Write-Output '---> Update succeeded.'
}

Export-ModuleMember -Function Update-Changelog
