Param(
    [Parameter(Mandatory=$true)][int]$elso,
    [Parameter(Mandatory=$true)][int]$masodik,
    [Parameter(Mandatory=$true)][int]$harmadik
)

$ketszeres = 2 * $elso

if ($masodik -lt $harmadik)
{
    if ($ketszeres -gt $masodik -and $ketszeres -lt $harmadik)
    {
        Write-Host igen
    }
    else
    {
        Write-Host nem
    }
}

if ($masodik -gt $harmadik)
{
    if ($ketszeres -lt $masodik -and $ketszeres -gt $harmadik)
    {
        Write-Host igen
    }
    else
    {
        Write-Host nem
    }
}