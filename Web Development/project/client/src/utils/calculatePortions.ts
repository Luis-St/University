import type { Ingredient } from "../types/Ingredient";

export const calculatePortions = (
  ingredients: Ingredient[],
  originalPortion: number,
  desiredPortion: number
): Ingredient[] => {
  const factor = desiredPortion / originalPortion;
  return ingredients.map((ing) => ({
    ...ing,
    amount: Math.round(ing.amount * factor * 100) / 100,
  }));
};
