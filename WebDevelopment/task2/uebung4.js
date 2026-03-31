// Aufgabe 2.3 - Promises

// --- Aufgabe 1 & 2: Callback Variante ---

let fs = require("fs");
let globaleDaten = "";

fs.readFile("text.txt", "utf-8", (err, data) => {
	if (err) {
		console.log("Fehler beim Lesen:", err);
		return;
	}
	console.log("Aufgabe 1 (Callback) - Gelesener Text:", data);
	globaleDaten = data;

	// Aufgabe 2: Daten in neue Datei schreiben
	fs.writeFile("output.txt", globaleDaten, (err) => {
		if (err) {
			console.log("Fehler beim Schreiben:", err);
			return;
		}
		console.log("Aufgabe 2 (Callback) - Datei erfolgreich geschrieben.");
	});
});

// --- Aufgabe 3: Promise Variante mit async/await ---

let fsPromises = require("fs").promises;

async function lesenUndSchreiben() {
	const data = await fsPromises.readFile("text.txt", "utf-8");
	console.log("Aufgabe 3 (Promise) - Gelesener Text:", data);

	await fsPromises.writeFile("output_promise.txt", data);
	console.log("Aufgabe 3 (Promise) - Datei erfolgreich geschrieben.");
}

lesenUndSchreiben();
