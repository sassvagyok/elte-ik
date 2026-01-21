<?php
    session_start();
    include('auth.php');
    include('userstorage.php');
    include('projectstorage.php');
    include('utils.php');

    $user_storage = new UserStorage();
    $auth = new Auth($user_storage);

    $ps = new ProjectStorage();
    $projects = $ps->findAll();

    $selected_category = $_GET['category'] ?? '';
    if ($selected_category !== '') {
        $projects = array_filter($projects, function($project) use ($selected_category) {
            return $project['category'] === $selected_category;
        });
    }

    $categories = [
        'small' => 'Helyi kis projekt',
        'large' => 'Helyi nagy projekt',
        'opportunity' => 'Esélyteremtő Budapest',
        'green' => 'Zöld Budapest'
    ];
?>

<!DOCTYPE html>
<html lang="hu">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Budapesti Közösségi Költségvetés</title>
</head>
<body>
    <?php if($auth->is_authenticated()): ?>
        <a href="logout.php">Kijelentkezés</a>
        <br>
        <a href="add.php">Projekt hozzáadása</a>
    <?php else: ?>
        <a href="login.php">Bejelentkezés</a>
    <?php endif ?>

    <h1>Budapesti Közösségi Költségvetés</h1>
    
    <form method="get">
        Projekt kategória:
        <select name="category">
            <option value="">Összes</option>
            <option value="small" <?= $selected_category === 'small' ? 'selected' : '' ?>>Helyi kis projekt</option>
            <option value="large" <?= $selected_category === 'large' ? 'selected' : '' ?>>Helyi nagy projekt</option>
            <option value="opportunity" <?= $selected_category === 'opportunity' ? 'selected' : '' ?>>Esélyteremtő Budapest</option>
            <option value="green" <?= $selected_category === 'green' ? 'selected' : '' ?>>Zöld Budapest</option>
        </select>
        <button type="submit">Szűrés</button>
    </form>

    <ul>
        <?php foreach ($projects as $id => $project): ?>
            <li>
                <a href="details.php?id=<?= $id ?>"><?= $project['name'] ?></a> - <?= $categories[$project["category"]] ?>
            </li>
        <?php endforeach ?>
    </ul>
</body>
</html>