<?php
  session_start();
  include('auth.php');
  include_once("userstorage.php");
  include('utils.php');

  function validate($post, &$data, &$errors): bool
  {
    $data = [
      'username' => trim($post['username'] ?? ''),
      'password' => $post['password'] ?? '',
    ];

    if ($data['username'] === '') {
      $errors['username'] = 'A felhasználónév megadása kötelező!';
    }

    if ($data['password'] === '') {
      $errors['password'] = 'A jelszó megadása kötelező!';
    }

    return count($errors) === 0;
  }

  $user_storage = new UserStorage();
  $auth = new Auth($user_storage);
  $data = [];
  $errors = [];

  if (count($_POST) > 0) {
    if (validate($_POST, $data, $errors)) {
      $auth_user = $auth->authenticate($data['username'], $data['password']);
      if (!$auth_user) {
        $errors['global'] = "A usernév-jelszó páros nem megfelelő";
      } else {
        $auth->login($auth_user);
        if (isset($_GET["redirect_url"])) {
          redirect($_GET["redirect_url"]);
        } else {
          redirect('index.php');
        }
      }
    }
  }
?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bejelentkezés - Budapesti Közösségi Költségvetés</title>
  <style>
    input~span {
      color: red;
      font-size: smaller;
    }
  </style>
</head>

<body>
  <a href="index.php">Főoldal</a>
  <h1>Budapesti Közösségi Költségvetés</h1>
  <h2>Bejelentkezés</h2>
  <?php if (isset($errors['global'])) : ?>
    <p><span class="error"><?= $errors['global'] ?></span></p>
  <?php endif; ?>
  <form action="" method="post">
    <label for="username">Felhasználónév: </label><br>
    <input type="text" name="username" id="username" value="<?= $_POST['username'] ?? "" ?>">
    <?php if (isset($errors['username'])) : ?>
      <span class="error"><?= $errors['username'] ?></span>
    <?php endif; ?>
    <br>
    <label for="password">Jelszó: </label><br>
    <input type="password" name="password" id="password">
    <?php if (isset($errors['password'])) : ?>
      <span class="error"><?= $errors['password'] ?></span>
    <?php endif; ?>
    <br>
    <br>
    <button type="submit">Bejelentkezés</button>
  </form>
  <p>
    <a href="register.php">Regisztráció</a>
  </p>
</body>

</html>