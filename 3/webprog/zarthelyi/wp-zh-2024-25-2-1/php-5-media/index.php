<?php
    require_once 'nations.php';

    function validate($get, &$data, &$errors): bool {
        global $nations;
        $data = $get;
        $big_5 = ["United Kingdom", "Germany", "France", "Spain", "Italy"];

        // name
        $company = trim($get['company'] ?? '');
        if ($company === '') {
            $errors['company'] = 'A cégnév nem lehet üres!';
        } else if (strlen($get["company"]) < 3) {
            $errors['company'] = 'A cég neve legalább 3 betű legyen!';
        } else {
            $data['company'] = filter_var($company, FILTER_SANITIZE_SPECIAL_CHARS);
        }

        // nation
        $nation = $get['nation'] ?? '';
        if (!isset($nations[$nation])) {
            $errors['nation'] = 'Az ország nem létezik a listában!';
        } else {
            $data['nation'] = $nation;
        }

        // reporters
        $reporters = trim($get['reporters'] ?? '');
        if ($reporters === '') {
            $errors['reporters'] = 'Az újságírók száma nem lehet üres!';
        } else if (filter_var($reporters, FILTER_VALIDATE_INT) === false) {
            $errors['reporters'] = 'Az újságírók száma egész szám legyen!';
        } else if (in_array($nations[$data["nation"]]["name"], $big_5)) {
            if ($reporters < 1 || $reporters > 20)
            $errors['reporters'] = 'Az országod cégei 1-20 újságíróval érkezhetnek!';
        } else if ($reporters < 1 || $reporters > 10) {
            $errors['reporters'] = 'Az országod cégei 1-10 újságíróval érkezhetnek!';
        } else {
            $data['reporters'] = intval($reporters);
        }

        // contract
        $contract = $get['contract'] ?? '';
        if ($contract === '') {
            $errors['contract'] = 'A szerződés típusát kötelező kiválasztani!';    
        } else if (!in_array($contract, ['subsidiary', 'independent'])) {
            $errors['contract'] = 'Ismeretlen szerződéstípus!';
        } else if (in_array($nations[$data["nation"]]["name"], $big_5) && $contract !== "subsidiary") {
            $errors['contract'] = 'Big5 csak alvállalkozóként érkezhet!';
        } else {
            $data['contract'] = $contract;
        }

        // emails
        // $raw = $get['emails'] ?? null;

        // if (!is_array($raw)) {
        //     $errors['emails'] = 'Az email nem tömb!';
        // } else {
        //     // trim minden elemre
        //     $trimmed = array_map('trim', $raw);

        //     // üres stringek kiszűrése
        //     $nonEmpty = array_values(array_filter($trimmed, function ($e) {
        //         return $e !== '';
        //     }));

        //     if ($nonEmpty === []) {
        //         $errors['emails'] = 'Legalább 1 email megadása kötelező!';
        //     } else {
        //         // email formátum ellenőrzése
        //         $valid = array_values(array_filter($nonEmpty, function ($e) {
        //             return filter_var($e, FILTER_VALIDATE_EMAIL);
        //         }));
        //         //A sor azt ellenőrzi, hogy a megtisztított nem-üres email címek közül mindegyik érvényes volt-e. Ha nem, akkor hibaüzenetet ad hozzá az $errors tömbhöz.
        //         if (count($valid) !== count($nonEmpty)) {
        //             $errors['emails'] = 'Valamelyik email rossz formátumú!';
        //         } else {
        //             // minden rendben → valid adatok
        //             $data['emails'] = $valid;
        //         }
        //     }
        // }

        return count($errors) === 0;
    }

    $data=[];
    $errors=[];
    $success=false;

    if (count($_GET) > 0) {
        if (validate($_GET, $data, $errors)) {
            $company=$data["company"];
            $reporters=$data["reporters"];
            $nation = $data["nation"];
            $contract = $data["contract"];
            $success = true;
        }
    }
?>

<!DOCTYPE html>
<html lang="hu">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>5. feladat</title>
    <link rel="stylesheet" href="index.css" />
</head>

<body>
    <h1>5. Média</h1>
    <div id="main">
        <form>
            <label>
                Cég neve
                <input name="company" value="<?=$_GET['company'] ?? '' ?>">
            </label>
            <label>
                Ország
                <select name="nation">
                    <?php foreach ($nations as $nation): ?>
                        <!-- Országkódot fog továbbküldeni, pl. HUN -->
                        <option value="<?= $nation['id'] ?>">
                            <?= $nation['flag'] ?> <?= $nation['name'] ?>
                        </option>
                    <?php endforeach ?>
                </select>
            </label>
            <label>
                Újságírók száma
                <input name="reporters" value="<?=$_GET['reporters'] ?? '' ?>">
            </label>
            <div>
                Szerződés
                <label><input type="radio" name="contract" value="subsidiary" <?= ($_GET['contract'] ?? '') === 'subsidiary' ? 'checked' : '' ?>> EBU Alvállalkozó</label>
                <label><input type="radio" name="contract" value="independent" <?= ($_GET['contract'] ?? '') === 'independent' ? 'checked' : '' ?>> Független</label>
            </div>
            <input type="submit">
        </form>

        <?php if($success): ?>
            <div id="success">Sikeres regisztráció!</div>
        <?php endif ?>
        <?php if (!empty($errors)): ?>
            <div id="errors">
                <ul>
                    <?php foreach($errors as $e): ?>
                        <li><?= htmlspecialchars($e) ?></li>
                    <?php endforeach ?>
                </ul>
            </div>
        <?php endif ?>
    </div>



    <hr>

    <div>
        Ha GET kérést használtál egy checks.php oldalon keresztül, akkor ezekkel a linkekkel tudod tesztelni az űrlapon keresztül körülményesen előidézhető hibákat:
        <ul><a href="check.php?nation=XXX">Nem létező ország (check.php?nation=XXX)</a></ul>
        <ul><a href="check.php?nation=DEU&reporters=25">Big5 túl sok újságíró (check.php?nation=DEU&reporters=25)</a></ul>
        <ul><a href="check.php?contract=taxevasion">Nem létező szerződés típus (check.php?contract=taxevasion)</a></ul>
        <ul><a href="check.php?nation=DEU&contract=independent">Big5 rossz szerződés (check.php?nation=DEU&contract=independent)</a></ul>
    </div>
    <div>
        Ha GET kérést használtál helyben, akkor ezekkel a linkekkel tudod tesztelni az űrlapon keresztül körülményesen előidézhető hibákat:
        <ul><a href="index.php?nation=XXX">Nem létező ország (index.php?nation=XXX)</a></ul>
        <ul><a href="index.php?nation=DEU&reporters=25">Big5 túl sok újságíró (index.php?nation=DEU&reporters=25)</a></ul>
        <ul><a href="index.php?contract=taxevasion">Nem létező szerződés típus (index.php?contract=taxevasion)</a></ul>
        <ul><a href="index.php?nation=DEU&contract=independent">Big5 rossz szerződés (index.php?nation=DEU&contract=independent)</a></ul>
    </div>
</body>

</html>