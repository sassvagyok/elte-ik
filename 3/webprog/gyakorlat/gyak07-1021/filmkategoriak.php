<?php
    $categories=[
        [
            "id"=>1,
            "category"=>"Akció"
        ],
        [
            "id"=>2,
            "category"=>"Dráma"
        ],
        [
            "id"=>13,
            "category"=>"Vígjáték"
        ]
    ];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="" method="post">
        <select name="category">
            <?php foreach ($categories as $c): ?>
            <option value=<?= $c["id"] ?>>
                <?= $c["category"] ?>
            </option>
            <?php endforeach ?>
        </select>
        <button>Küldés</button>
        <?php foreach ($categories as $c): ?>
            <input type="checkbox" id="category" name="category">
            <label for="category" id=<?= $c["id"] ?>>
                <?= $c["category"] ?>
            </label>
        <?php endforeach ?>
        <?php foreach ($categories as $c): ?>
            <input type="radio" id="category" name="category">
            <label for="category" id=<?= $c["id"] ?>>
                <?= $c["category"] ?>
            </label>
        <?php endforeach ?>
    </form>
</body>
</html>