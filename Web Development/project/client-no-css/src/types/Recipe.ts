import type { Ingredient } from "./Ingredient";
import type { NutritionalValue } from "./NutritionalValue";
import type { Comment } from "./Comment";

export interface Recipe {
	_id: string;
	name: string;
	ingredients: Ingredient[];
	portion: number;
	nutritionalValues: NutritionalValue[];
	directions: string;
	tags: string[];
	href: string;
	time: number;
	comments: Comment[];
	rating: number[];
	categories: string[];
	user: string;
}
