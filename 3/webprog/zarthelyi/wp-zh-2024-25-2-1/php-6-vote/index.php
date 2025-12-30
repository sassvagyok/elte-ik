<?php
require_once 'nations.php';
include_once "pointstorage.php";

$ps = new PointStorage();

if (isset($_GET['delete'])) {
    $ps->delete($_GET['delete']);
    header('Location: index.php');
    exit;
}

if (isset($_POST['nation']) && isset($_POST['vote'])) {
    $nation = $_POST['nation'];
    $vote = $_POST['vote'];
    
    $ps->add(
        [
            "nation" => $nation,
            "vote" => $vote
        ]
    );
    header('Location: index.php');
    exit;
}

$points = $ps->findAll();

$totalPoints = [];
foreach ($nations as $index => $nation) {
    $totalPoints[] = [
        'index' => $index,
        'total' => 0
    ];
}

foreach ($points as $p) {
    $nationIndex = $p["nation"];
    $vote = (int)$p["vote"];
    foreach ($totalPoints as &$tp) {
        if ($tp['index'] == $nationIndex) {
            $tp['total'] += $vote;
            break;
        }
    }
}

usort($totalPoints, function($a, $b) {
    return $b['total'] - $a['total'];
});
?>

<!DOCTYPE html>
<html lang="hu">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>6. feladat</title>
    <link rel="stylesheet" href="index.css" />
</head>

<body>
    <h1>6. Szavazás</h1>

    <form id="vote-form" method="post" action="vote_save.php">
        <select name="nation">
            <?php foreach ($nations as $index => $nation): ?>
                <option value="<?= $index ?>"><?= $nation['flag'] ?> <?= $nation['name'] ?></option>
            <?php endforeach ?>
        </select>
        <select name="vote">
            <option value="12">Legjobb</option>
            <option value="8">Nagyon jó</option>
            <option value="5">Jó</option>
        </select>
        <button type="submit">Küldés</button>
    </form>

    <div id="main">
        <div id="voting">
            <h2>Leadott szavazatok</h2>
            <?php foreach($points as $p): ?>
                <div class="vote-card">
                    <span><?= $nations[$p["nation"]]["flag"] ?> <?= $nations[$p["nation"]]["name"] ?> +<?= $p["vote"] ?> pont</span>
                    <a class="delete" href="index.php?delete=<?= $p["id"] ?>">🗑️</a>
                </div>
            <?php endforeach ?>
        </div>

        <div id="contestants">
            <h2>Fellépők</h2>
            <?php foreach($totalPoints as $tp): ?>
                <div class="vote-card"><?= $nations[$tp['index']]["flag"] ?> <?= $nations[$tp['index']]["name"] ?> – <?= $tp['total'] ?></div>
            <?php endforeach ?>
        </div>
    </div>
</body>


</html>