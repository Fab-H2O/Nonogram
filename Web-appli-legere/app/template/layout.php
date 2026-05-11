<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Big+Shoulders:opsz,wght@10..72,100..900&family=Open+Sans:ital,wght@0,300..800;1,300..800&family=Oswald:wght@200..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/picross.css">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/stylenonogram.css">
    <?php echo $scripts ?>
    <title><?= $title ?></title>
</head>
<body>
    <div class="window centered">
        <?= $content ?>
    </div>
</body>
</html>