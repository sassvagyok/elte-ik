<?php 
    include('projectstorage.php');
    include('utils.php');

    if (!isset($_GET["id"])) {
        redirect("index.php");
    }

    $id = $_GET["id"];
    $ps = new ProjectStorage();
    $project = $ps->findById($id);

    if (!$project) {
        redirect("index.php");
    }

    $categories = [
        'small' => 'Helyi kis projekt',
        'large' => 'Helyi nagy projekt',
        'opportunity' => 'Esélyteremtő Budapest',
        'green' => 'Zöld Budapest'
    ];
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?= $project["name"] ?> - Budapesti Közösségi Költségvetés</title>
    <style>
        li {
            white-space: pre-wrap;
            word-wrap: break-word;
        }
    </style>
</head>
<body>
    <a href="index.php">Főoldal</a>
    <h1>Projektek</h1>
    <h2>Részletek</h2>
    <dl>
        <h3>Név</h3>
        <ul>
            <li><?= $project["name"] ?></li>
        </ul>
        <h3>Leírás</h3>
        <ul>
            <li><?= $project["description"] ?></li>
        </ul>
        <h3>Irányítószám</h3>
        <ul>
            <li><?= $project["postcode"] ?></li>
        </ul>
        <h3>Kategória</h3>
        <ul>
            <li><?= $categories[$project["category"]] ?></li>
        </ul>
        <h3>Hozzáadva</h3>
        <ul>
            <li><?= $project["date"] ?></li>
        </ul>
        <h3>Hozzáadta</h3>
        <ul>
            <li><?= $project["user"] ?></li>
        </ul>
    </dl>
</body>
</html>