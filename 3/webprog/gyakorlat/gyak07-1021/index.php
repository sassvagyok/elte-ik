<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
        $name_o="Máté";
        echo "<h1>Hello $name_o</h1>";

        //http://localhost:3000/index.php/?name2=asd
        $name=$_GET["name2"]?? "Vendég";
        echo "<h1>Hello $name</h1>";
    ?>
    <?=$name?>
</body>
</html>