let dog = {
	art: "Hund",
	gewicht: 30,
	rasse: "Schäferhund",
	name: "Peter"
};

let array = [10, 20, 30, 13, 50, 60, 5];

// Aufgabe 1
function sum(a, b) {
	return a + b;
}

const e1 = sum(15, 27);
console.log("Aufgabe 1:", e1);

// Aufgabe 2
function toArray(...args) {
	const result = [];
	for (const arg of args) {
		result.push(arg);
	}
	return result;
}

// Alternative mit Array.from:
// function toArray(...args) {
//     return Array.from(args);
// }

// Alternative direkt:
// function toArray(...args) {
//     return args;
// }

const e2 = toArray(1, "Hallo", true, 42, "Welt");
console.log("Aufgabe 2:", e2);

// Aufgabe 3
const modifyArray = (array, operator, step) => {
	return array.map(wert => {
		if (operator === "+") {
			return wert + step;
		}
		if (operator === "-") {
			return wert - step;
		}
		throw new Error(`Unbekannter Operator: ${operator}`);
	});
};

const e3a = modifyArray(array, "+", 5);
const e3b = modifyArray(array, "-", 3);
console.log("Aufgabe 3 (+ 5):", e3a);
console.log("Aufgabe 3 (- 3):", e3b);

// Aufgabe 4
function pureFunction(obj) {
	return {
		...obj,
		name: "Max",
		gewicht: obj.gewicht + 5
	};
}

const e4 = pureFunction(dog);
console.log("Aufgabe 4 (Original):", dog);
console.log("Aufgabe 4 (Verändert):", e4);

// Aufgabe 5
function smaller(a, b) {
	return a < b ? a : b;
}

function findSmallest(array, compareFunction) {
	let smallest = array[0];
	for (let i = 1; i < array.length; i++) {
		smallest = compareFunction(smallest, array[i]);
	}
	return smallest;
}

const e5 = findSmallest(array, smaller);
console.log("Aufgabe 5:", array, ":", e5);
