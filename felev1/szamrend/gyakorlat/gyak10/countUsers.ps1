$name = Read-Host "Adja meg a nevet"

(Get-Content users.txt | 
    ForEach-Object { $_.Split(':')[4] } |
    Select-String $name |
    Measure-Object).Count