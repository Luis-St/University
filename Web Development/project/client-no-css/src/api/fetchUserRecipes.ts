import axiosInstance from "./axiosInstance";
import type { Recipe } from "../types/Recipe";

export const fetchUserRecipes = async (): Promise<Recipe[]> => {
	const response = await axiosInstance.get("/cookbook/user/recipe");
	return response.data;
};
