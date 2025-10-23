<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
    // function filter($x, callable $fn) {
    //     $result=[];
    //     foreach ($x as $e) {
    //         if ($fn($e)) {
    //             $result[]=$e;
    //         }
    //     }

    //     return $result;
    // }
    
    // $negative=filter($numbers, function($e) {
    //     return $e < 0;
    // });
    
    $limit=2;
    $numbers=[1, -8, 3, 4, -6, 9];
    $negative=array_filter($numbers, function($e) use ($limit) {
        return $e < $limit;
    });

    // print("<pre>");
    // print_r($negative);
    // print("</pre>");
    ?>
    <ul>
        <?php foreach ($negative as $number): ?>
        <li><?= $number ?></li>
        <?php endforeach ?>
    </ul>
</body>
</html>