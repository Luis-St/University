<?php
$target_dir = "uploads/";
$uploadOk = 1;

$orig = basename($_FILES["fileToUpload"]["name"]);
$ext  = strtolower(pathinfo($orig, PATHINFO_EXTENSION));
$allowed = ["jpg", "jpeg", "png", "gif", "webp"];

$info = @getimagesize($_FILES["fileToUpload"]["tmp_name"]);
if ($info === false) {
  echo "Abgelehnt: Datei ist kein gueltiges Bild."; $uploadOk = 0;
}

$mime_to_ext = [
  "image/jpeg" => ["jpg","jpeg"], "image/png" => ["png"],
  "image/gif" => ["gif"], "image/webp" => ["webp"],
];
if ($uploadOk && (!in_array($ext, $allowed) ||
    !isset($mime_to_ext[$info["mime"]]) ||
    !in_array($ext, $mime_to_ext[$info["mime"]]))) {
  echo "Abgelehnt: Endung passt nicht zum Bildtyp."; $uploadOk = 0;
}

if ($uploadOk && $_FILES["fileToUpload"]["size"] > 5000000) {
  echo "Die Datei ist zu gross."; $uploadOk = 0;
}

if ($uploadOk) {
  $safe = bin2hex(random_bytes(8)) . "." . $ext;
  if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_dir . $safe)) {
    echo "Datei " . htmlspecialchars($safe) . " hochgeladen.";
  } else {
    echo "Das Schreiben der Datei hat zu einem Fehler gefuehrt.";
  }
}
?>
<a href="/"> zurueck </a>
