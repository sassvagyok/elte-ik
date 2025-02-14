Param(
    [Parameter(Mandatory=$true)][string]$directory
    )

function GetDirectorySize([string]$dir)
{
    $size = 0

    if (Test-Path $dir -PathType Container)
    {
      $files = Get-ChildItem $dir
      foreach ($child in $files)
      {
        if(Test-Path $child -PathType Container)
        {
            $size += GetDirectorySize($child)
        }
        else
        {
            $size += $child.Length
        }
      }
    }

    return $size
}

GetDirectorySize($directory)