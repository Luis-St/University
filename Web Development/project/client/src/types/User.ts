import type { Recipe } from "./Recipe";

export interface User {
  _id: string;
  firstname: string;
  lastname: string;
  street: string;
  postcode: string;
  city: string;
  country: string;
  phone: string;
  email: string;
  savedRecipes: string[] | Recipe[];
}
