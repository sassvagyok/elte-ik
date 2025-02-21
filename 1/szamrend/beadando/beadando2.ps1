Param(
    [Parameter(Mandatory=$true)][int]$k
)

$adatok = Get-Content meresek.txt

Write-Host 2. feladat:

foreach ($sor in $adatok)
{
    $splitSor = $sor.Split(' ', [StringSplitOptions]::RemoveEmptyEntries)
    if ($k -ge $splitSor[3])
    {
        Write-Host $splitSor[0] $splitSor[1]
    }
}