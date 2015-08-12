Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

$GitHubApiKey = $env:GITHUB_API_KEY

Function Invoke-CreateGitHubRelease()
{
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$True)]
        [string]$owner,
        
        [Parameter(Mandatory=$True)]
        [string]$repo,
        
        [Parameter(Mandatory=$True)]
        [string]$version,
        
        [Parameter(Mandatory=$True)]
        [string]$description,
        
        [string]$releaseDir
    )
    
    $name = "v$version"
    
    $headers = @{
        Authorization = "token $GitHubApiKey"
    }
    
    $releaseData = @{
        tag_name = $name
        target_commitish = 'master'
        name = $name
        body = $description
    }
    
    $releaseParams = @{
        Uri = "https://api.github.com/repos/$owner/$repo/releases"
        Method = 'POST'
        Headers = $headers
        ContentType = 'application/json'
        Body = (ConvertTo-Json $releaseData -Compress)
    }
    
    try
    {
        Write-Output '---> Create GitHub release.'
        Write-Verbose "Release name: $name"
        $result = Invoke-RestMethod @releaseParams
    
        if (!$releaseDir)
        {
            return
        }
        
        $uploadUrl = $result | Select -ExpandProperty upload_url
        $artifacts = Get-ChildItem -Path $releaseDir
        foreach ($artifact in $artifacts)
        {
            $uploadUri = $uploadUrl -replace '\{\?name\}', "?name=$artifact"
            $uploadFile = Join-Path -path $releaseDir -childpath $artifact

            $uploadParams = @{
                Uri = $uploadUri
                Method = 'POST'
                Headers = $headers
                ContentType = 'application/zip'
                InFile = $uploadFile
            }
            
            Invoke-RestMethod @uploadParams | Out-Null
        }
        
        Write-Output '---> Create GitHub release succeeded.'
    }
    catch
    {
        Write-Output $_.Exception.Message
        Exit 1
    }
}

Export-ModuleMember -Function Invoke-CreateGitHubRelease
