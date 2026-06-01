import axiosInstance from "./axiosInstance";
import type { Recipe } from "../types/Recipe";

export const fetchAllRecipes = async (): Promise<Recipe[]> => {
	const response = await axiosInstance.get("/cookbook/recipes");
	return response.data;
};
