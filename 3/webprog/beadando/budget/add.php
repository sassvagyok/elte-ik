<?php
    session_start();
    include_once("projectstorage.php");
    include_once("userstorage.php");
    include_once("auth.php");
    include('utils.php');

    $user_storage = new UserStorage();
    $auth = new Auth($user_storage);

    if (!$auth->is_authenticated()) {
        redirect("login.php");
    }

    function validate($post, &$data, &$errors): bool {
        $data = $post;

        $name = trim($post['name'] ?? '');
        if ($name === '') {
            $errors['name'] = 'Név megadása kötelező!';
        } else if (strlen($data["name"]) < 10) {
            $errors["name"] = "A névnek legalább 10 karakternek kell lennie!";
        }

        if ($data["description"] === "") {
            $errors['description'] = 'Leírás megadása kötelező!';
        } else if (strlen($data["description"]) < 150) {
            $errors['description'] = 'A leírásnak legalább 150 karakternek kell lennie!';
        }

        $postcode = $post['postcode'] ?? '';
        if ($postcode === '') {
            $errors['postcode'] = 'Az irányítószám megadása kötelező!';
        } else if (!is_numeric($postcode)) {
            $errors['postcode'] = 'Az irányítószámnak számnak kell lennie!';
        } else if ($postcode < 1000 || $postcode > 9999) {
            $errors['postcode'] = 'Helytelen formátum!';
        } else {
            $data['postcode'] = (int)$postcode;
        }

        $category = $post['category'] ?? '';
        if ($category === '') {
            $errors['category'] = 'A projekt típusát kötelező kiválasztani!';    
        } else if (!in_array($category, ['small', 'large', 'opportunity', 'green'])) {
            $errors['category'] = 'Ismeretlen kategória!';
        } else {
            $data['category'] = $category;
        }

        return count($errors) === 0;
    }

    $data=[];
    $errors=[];

    if (count($_POST) > 0) {
        if (validate($_POST, $data, $errors)) {
            $name=$data["name"];
            $description=$data["description"];
            $postcode = $data["postcode"];
            $category=$data["category"];

            $user = $auth->authenticated_user();
            $ps = new ProjectStorage();
            $ps->add(
                [
                    "name"=>$name,
                    "description"=>$description,
                    "user" => $user['username'],
                    "postcode" => $postcode,
                    "category" => $category,
                    "date" => date('Y-m-d'),
                    "status" => "approved"
                ]
            );
            redirect("index.php");
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Új projekt - Budapesti Közösségi Költségvetés</title>
    <style>
        input ~ span {
        color: red;
        font-size: smaller;
        }
    </style>
</head>
<body>
    <a href="index.php">Főoldal</a>
    <h1>Budapesti Közösségi Költségvetés</h1>
    <h2>Új projekt</h2>
    <form action="" method="post" novalidate>
        Név:
        <br>
        <input type="text" name="name" value="<?=$_POST['name'] ?? '' ?>" required> <br>
        <?php if(isset($errors["name"])) : ?>
            <span><?= $errors["name"] ?></span>
        <?php endif ?>
        <br>
        Leírás:
        <br>
        <textarea name="description" rows="15" cols="50" required><?=$_POST['description'] ?? '' ?></textarea> <br>
        <?php if(isset($errors["description"])) : ?>
            <span><?= $errors["description"] ?></span>
        <?php endif ?>
        <br>
        Irányítószám:
        <br>
        <input type="number" name="postcode" value="<?=$_POST['postcode'] ?? '' ?>" required> <br>
        <?php if(isset($errors["postcode"])) : ?>
            <span><?= $errors["postcode"] ?></span>
        <?php endif ?>
        <br>
        Kategória:
        <br>
        <label><input type="radio" name="category" value="small" <?= ($_POST['category'] ?? '') === 'small' ? 'checked' : '' ?>> Helyi kis projekt</label> <br>
        <label><input type="radio" name="category" value="large" <?= ($_POST['category'] ?? '') === 'large' ? 'checked' : '' ?>> Helyi nagy projekt</label> <br>
        <label><input type="radio" name="category" value="opportunity" <?= ($_POST['category'] ?? '') === 'opportunity' ? 'checked' : '' ?>> Esélyteremtő Budapest</label> <br>
        <label><input type="radio" name="category" value="green" <?= ($_POST['category'] ?? '') === 'green' ? 'checked' : '' ?>> Zöld Budapest</label> <br>
        <?php if(isset($errors["category"])) : ?>
            <span><?= $errors["category"] ?></span>
        <?php endif ?>
        <br>
        <button>Hozzáadás</button>
        </form>
</body>
</html>