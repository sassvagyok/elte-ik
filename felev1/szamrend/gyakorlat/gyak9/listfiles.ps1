$files = Get-ChildItem

foreach ($f in $files)
{
    #Write-Host $f.Length # hány bites
    if (Test-Path -Path $f -PathType Leaf) #csak fájlok
    {
        Write-Host $f
    }
}

for ($i = 0; $i -lt $files.Length; $i++)
{
    #Write-Host $f.Length # hány bites
    if (Test-Path -Path $files[$i] -PathType Leaf) #csak fájlok
    {
        Write-Host $files[$i]
    }
}