Param(
    [Parameter(Mandatory=$true)][string]$directory
)

foreach ($file in (Get-ChildItem $directory -File))
{
    $extension = [System.IO.Path]::GetExtension($file) # $file.Extension ugyanezt csinálja
    $targetDir = [System.IO.Path]::Combine($directory, $extension) # pl. C:\Users\Docs + \ + .txt

    if (-not (Test-Path $targetDir -PathType Container))
    {
        New-Item -Name $targetDir -ItemType Directory | Out-Null # nem jelenik meg a terminálban
    }

    Copy-Item $file ([System.IO.Path]::Combine($targetDir, $file))
}