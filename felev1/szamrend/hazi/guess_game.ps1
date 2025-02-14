$rand = Get-Random -Minimum 1 -Maximum 100

$guess = Read-Host "Adj meg egy számot"

while (-not ($rand -eq $guess))
{
    if ($rand -lt $guess)
    {
        Write-Host "A kitalálandó szám kisebb!"
    }
    else
    {
        Write-Host "A kitalálandó szám nagyobb!"
    }

    $guess = Read-Host "Adj meg egy számot!"
}

Write-Host "Helyes!"