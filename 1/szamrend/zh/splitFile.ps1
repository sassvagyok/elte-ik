Param(
    [Parameter(Mandatory=$true)][string]$bemenet,
    [Parameter(Mandatory=$true)][int]$elso,
    [Parameter(Mandatory=$true)][int]$masodik
)

if (Test-Path -Path $bemenet -PathType Leaf)
{
    if (-not (Test-Path -Path ($bemenet + "-split") -PathType Container))
    {
        New-Item -Name ($bemenet + "-split") -ItemType Directory | Out-Null
    }

    $file = Get-Content $bemenet
    
    for ($i = 0; $i -lt $file.Length; $i++)
    {
        if(($i + 1) -eq $elso -or ($i + 1) -eq $masodik)
        {
            $fullPath = ($bemenet + "-split" + "\" + [string]($i + 1) + ".txt")

            Add-Content -Path $fullPath -Value $file[$i]
        }
    }
}
else
{
    Write-Host "Létező fájlt adj meg!"
}