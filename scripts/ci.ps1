Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
#------------------------------

$root = $PWD

# .NET
cd ..\dotnet\scripts
& .\ci.ps1
Copy-Item nunit-result.xml -Destination $root
