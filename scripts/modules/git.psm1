Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

Function Invoke-Git()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string[]]$arguments
    )
    
    Write-Output '---> Run Git command.'
    Invoke-GitPrivate $arguments
    Write-Output '---> Git command succeeded.'
}

Function Invoke-GitCommit()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$message
    )
    
    $arguments = @(
        'commit'
        '-m'
        """$message"""
    )

    Write-Output '---> Run Git commit command.'
    Invoke-GitPrivate $arguments
    Write-Output '---> Git commit command succeeded.'
}

Function Invoke-GitTag()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$amsg,
        
        [Parameter(Mandatory=$True)]
        [string]$tagName
    )
    
    $arguments = @(
        'tag'
        '-a'
        '-m'
        """$amsg"""
        $tagName
    )

    Write-Output '---> Run Git tag command.'
    Invoke-GitPrivate $arguments
    Write-Output '---> Git tag command succeeded.'
}

Function Invoke-GitPrivate($arguments)
{
    if ((Get-Command 'git.exe' -ErrorAction SilentlyContinue) -eq $null) 
    {
        Write-Output 'Unable to find git.exe in your PATH'
        Exit 1
    }
    
    Write-Verbose "Command arguments: $arguments"
    & git $arguments
    if ($LASTEXITCODE -ne 0)
    {
        Write-Output "Git command failed with exit code $LASTEXITCODE"
        Exit $LASTEXITCODE
    }
}

Export-ModuleMember -Function Invoke-Git, Invoke-GitCommit, Invoke-GitTag
