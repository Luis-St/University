// Aufgabe 2.2 - Klassen

class Kraftfahrzeug {
	constructor(maxGeschw, istAn, richtung, aktuelleGeschw) {
		this.maxGeschw = maxGeschw;
		this.istAn = istAn;
		this.richtung = richtung;
		this.aktuelleGeschw = aktuelleGeschw;
	}

	an() {
		this.istAn = true;
		console.log("Kraftfahrzeug ist an.");
	}

	aus() {
		this.istAn = false;
		this.aktuelleGeschw = 0;
		console.log("Kraftfahrzeug ist aus.");
	}

	gasgeben() {
		if (this.aktuelleGeschw < this.maxGeschw) {
			this.aktuelleGeschw += 10;
		}
		console.log("Gas gegeben. Aktuelle Geschwindigkeit:", this.aktuelleGeschw);
	}

	bremsen() {
		if (this.aktuelleGeschw > 0) {
			this.aktuelleGeschw -= 10;
		}
		console.log("Gebremst. Aktuelle Geschwindigkeit:", this.aktuelleGeschw);
	}

	richtungWechseln(r) {
		this.richtung = r;
		console.log("Richtung geändert zu:", this.richtung);
	}

	print() {
		console.log("Max. Geschwindigkeit:", this.maxGeschw);
		console.log("Ist an:", this.istAn);
		console.log("Richtung:", this.richtung);
		console.log("Aktuelle Geschwindigkeit:", this.aktuelleGeschw);
	}
}

class Auto extends Kraftfahrzeug {
	constructor(maxGeschw, istAn, richtung, aktuelleGeschw, tueren, hatDachfenster) {
		super(maxGeschw, istAn, richtung, aktuelleGeschw);
		this.tueren = tueren;
		this.hatDachfenster = hatDachfenster;
	}

	printTueren() {
		console.log("Türen:", this.tueren);
	}

	printHatDachfenster() {
		console.log("Hat Dachfenster:", this.hatDachfenster);
	}

	printMaxGeschw() {
		console.log("Max. Geschwindigkeit:", this.maxGeschw);
	}

	printIstAn() {
		console.log("Ist an:", this.istAn);
	}

	printRichtung() {
		console.log("Richtung:", this.richtung);
	}

	printAktuelleGeschw() {
		console.log("Aktuelle Geschwindigkeit:", this.aktuelleGeschw);
	}

	print() {
		super.print();
		console.log("Türen:", this.tueren);
		console.log("Hat Dachfenster:", this.hatDachfenster);
	}
}

const meinAuto = new Auto(200, false, "Norden", 0, 4, true);
console.log("--- Alle Eigenschaften ---");
meinAuto.print();

console.log("\n--- Funktionen aufrufen ---");
meinAuto.an();
meinAuto.gasgeben();
meinAuto.gasgeben();
meinAuto.richtungWechseln("Osten");
meinAuto.bremsen();
meinAuto.aus();
