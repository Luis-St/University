import axiosInstance from "./axiosInstance";

export const createRecipe = async (recipeData: {
  name: string;
  ingredients: { ingredient: string; amount: number; unit: string }[];
  portion: number;
  nutritionalValues: { kcal: string; protein: string; fat: string; carbohydrates: string }[];
  directions: string;
  tags: string[];
  href: string;
  time: number;
  categories: string[];
}) => {
  const response = await axiosInstance.post("/cookbook/recipe", recipeData);
  return response.data;
};
