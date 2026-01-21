<?php
    session_start();
    include_once("userstorage.php");
    include_once("auth.php");
    include_once("utils.php");

    function validate($post, &$data, &$errors): bool {
        $data = [
            "username" => trim($post["username"] ?? ""),
            "email" => $post["email"] ?? "",
            "password1" => $post["password1"] ?? "",
            "password2" => $post["password2"] ?? "",
            "role" => "user"
        ];

        if ($data["username"] === "") {
            $errors["username"] = "A felhasználónév megadás kötelező!";
        } else if (strlen($data["username"]) < 8) {
            $errors["username"] = "A felhasználónévnek legalább 8 karakternek kell lennie!";
        } else if (strpos($data["username"], ' ') !== false) {
            $errors["username"] = "A felhasználónév nem tartalmazhat szóközt!";
        }

        if ($data["email"] === "") {
            $errors['emails'] = 'Email cím megadása kötelező!';
        } else if (!filter_var($data["email"], FILTER_VALIDATE_EMAIL)) {
            $errors['email'] = 'Az email cím rossz formátumú!';
        }

        if ($data["password1"] === "") {
            $errors["password1"] = "A jelszó megadása kötelező!";
        } else if (strlen($data["password1"]) < 8) {
            $errors["password1"] = "A jelszónak legalább 8 karakternek kell lennie!";
        } else if (!preg_match('/[a-z]/', $data["password1"])) {
            $errors["password1"] = "A jelszónak tartalmaznia kell kisbetűt!";
        } else if (!preg_match('/[A-Z]/', $data["password1"])) {
            $errors["password1"] = "A jelszónak tartalmaznia kell nagybetűt!";
        } else if (!preg_match('/[0-9]/', $data["password1"])) {
            $errors["password1"] = "A jelszónak tartalmaznia kell számjegyet!";
        }

        if ($data["password2"] === "") {
            $errors["password2"] = "Add meg újra a jelszót!";
        }

        if ($data["password1"] !== $data["password2"]) {
            $errors["password1"] = "A jelszavak nem egyeznek!";
        }

        if (count($errors) === 0) {
            $data["password"] = $data["password1"];
        }

        return count($errors) === 0;
    }

    $user_storage = new UserStorage();
    $auth = new Auth($user_storage);
    $data=[];
    $errors=[];

    if (count($_POST) > 0) {
        if (validate($_POST, $data, $errors)) {
            $auth_user = $auth->user_exists($data["username"]);
            if (!$auth_user) {
                $auth->register($data);

                if (isset($_GET["redirect_url"])) {
                    redirect($_GET["redirect_url"]);
                } else {
                    redirect("index.php");
                }
            } else {
                $errors["global"] = "Ez a felhasználónév már foglalt!";
            }            
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Regisztráció - Budapesti Közösségi Költségvetés</title>
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
    <h2>Regisztráció</h2>
    <form action="" method="post" novalidate>
        Felhasználónév:<br>
        <input type="text" name="username" value="<?=$_POST['username'] ?? '' ?>" required>
        <?php if(isset($errors["username"])) : ?>
            <span><?= $errors["username"] ?></span>
        <?php endif ?>
        <br>
        Email:<br>
        <input type="text" name="email" value="<?=$_POST['email'] ?? '' ?>" required>
        <?php if(isset($errors["email"])) : ?>
            <span><?= $errors["email"] ?></span>
        <?php endif ?>
        <br>
        Jelszó:<br>
        <input type="password" name="password1" required>
        <?php if(isset($errors["password1"])) : ?>
            <span><?= $errors["password1"] ?></span>
        <?php endif ?>
        <br>
        Jelszó újra:<br>
        <input type="password" name="password2" required>
        <?php if(isset($errors["password2"])) : ?>
            <span><?= $errors["password2"] ?></span>
        <?php endif ?>
        <br>
        <br>
        <button>Regisztráció</button>
        <?php if(isset($errors["global"])) : ?>
            <span><?= $errors["global"] ?></span>
        <?php endif ?>
    </form>
</body>
</html>