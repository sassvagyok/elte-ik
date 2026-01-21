<?php
    session_start();
    include('auth.php');
    include('userstorage.php');
    include('utils.php');

    $user_storage = new UserStorage();
    $auth = new Auth($user_storage);

    $auth->logout();
    redirect("index.php");
?>