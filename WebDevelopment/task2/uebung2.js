// Aufgabe 2.1 - Destructuring

let haus = {
	geschosse: 2,
	fenster: 10,
	tueren: 2,
	garage: 1,
	raeume: 8
};

const { geschosse, raeume } = haus;
console.log("Geschosse:", geschosse);
console.log("Räume:", raeume);
