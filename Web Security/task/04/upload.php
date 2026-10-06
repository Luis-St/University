<?php
$target_dir = "uploads/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));



if ($_FILES["fileToUpload"]["size"] > 5000000) {
  echo "Die Datei ist zu groß.";
  $uploadOk = 0;
}


if ($uploadOk == 0) {
  echo "Datei nicht hochgeladen.";
} else {
  if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
    echo "Datei ". htmlspecialchars( basename( $_FILES["fileToUpload"]["name"])). " hochgeladen.";
  } else {
    echo "Das Schreiben der Datei hat zu einem Fehler geführt.";
  }
}
?>

<a href="/"> zurück </a>