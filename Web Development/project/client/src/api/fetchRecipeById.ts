import axiosInstance from "./axiosInstance";
import type { Recipe } from "../types/Recipe";

export const fetchRecipeById = async (recipeId: string): Promise<Recipe> => {
  const response = await axiosInstance.get(`/cookbook/recipe/${recipeId}`);
  return response.data;
};
