$begin = Read-Host "begin"
$increment = Read-Host "increment"
$n = Read-Host "n"

Write-Host $begin

for ($i = 1; $i -lt $n; $i++)
{
    Write-Host ([int]$begin += [int]$increment)
}