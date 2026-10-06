<!DOCTYPE html>
<html>
<body>
<h1> Bitte laden Sie ein Bild hoch </h1>
<h2> Neu: Jetzt mit IT-Security!!!11 </h2>
<form id="uploadForm" action="upload.php" method="post" enctype="multipart/form-data">
  Bitte Datei auswählen:
  <input type="file" name="fileToUpload" id="fileToUpload" accept="image/*" required>
  <input type="submit" value="Upload Image" name="submit">
</form>

<h3> Aktuell gespeicherte Bilder: </h3>
<?php

$dir = "./uploads/";

if (is_dir($dir)){
  if ($dh = opendir($dir)){
	while (($file = readdir($dh)) !== false){
		if ($file != "." && $file != "..") {
          echo "<a href=\"uploads/$file\"> $file </a><br>";
		}
    }
  closedir($dh);
  }

}
?>

<p id="error" style="color:red;"></p>

<script>
document.getElementById("uploadForm").addEventListener("submit", function(e) {
    const fileInput = document.getElementById("fileToUpload");
    const file = fileInput.files[0];
    const error = document.getElementById("error");

    if (!file) {
        error.textContent = "Bitte Datei auswählen.";
        e.preventDefault();
        return;
    }

    if (!file.type.startsWith("image/")) {
        error.textContent = "Nur Bilddateien erlaubt!";
        e.preventDefault();
        return;
    }

    const allowedExtensions = ["jpg", "jpeg", "png", "gif", "webp"];
    const ext = file.name.split(".").pop().toLowerCase();

    if (!allowedExtensions.includes(ext)) {
        error.textContent = "Ungültige Dateiendung!";
        e.preventDefault();
        return;
    }
});
</script>

</body>
</html>