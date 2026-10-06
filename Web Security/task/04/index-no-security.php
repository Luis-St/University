<!DOCTYPE html>
<html>
<body>
<h1> Bitte laden Sie ein Bild hoch </h1>
<form action="upload.php" method="post" enctype="multipart/form-data">
  Bitte Datei auswählen:
  <input type="file" name="fileToUpload" id="fileToUpload">
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

</body>
</html>