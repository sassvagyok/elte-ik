<?php
    require_once "finalists_array.php";

    $qualified_count = count(array_filter($finalists, function($f) {
        return $f["method"] === "qualified";
    }));

    $big5_count = count(array_filter($finalists, function($f) {
        return $f["method"] === "big5";
    }));

    $host_count = count(array_filter($finalists, function($f) {
        return $f["method"] === "host";
    }));

    usort($finalists, function($a, $b) {
        return strcmp($a["country"], $b["country"]);
    });
?>

<!DOCTYPE html>
<html lang="hu">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>4. feladat</title>
    <link rel="stylesheet" href="index.css" />
</head>

<body>
    <h1>4. Döntő</h1>
    <div id="main">
        <div id="information">
            <div id="qualified">Bejutott: <?= $qualified_count ?></div>
            <div id="big5"><?= $big5_count === 5 ? "Minden Big5 jelen van." : "Hiányos Big5!"?></div>
            <div id="host"><?= $host_count === 1 ? "A rendező is fellép." : "A rendező nem indít versenyzőt!"?></div>
        </div>
        <div id="contestants">
            <?php foreach($finalists as $f): ?>
                <div class="card <?= $f["method"] ?>">
                    <span class="flag"><?= $f["flag"] ?></span>
                    <h2><?= $f["country"] ?></h2>
                    <span class="contestant"><?= $f["contestant"] ?></span>
                    <span class="method"><?= $f["method"] ?></span>
                </div>
            <?php endforeach ?>
        </div>
    </div>
</body>

</html>