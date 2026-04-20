import type { Ingredient } from "../../types/Ingredient";
import { calculatePortions } from "../../utils/calculatePortions";
import "./IngredientList.css";

interface IngredientListProps {
  ingredients: Ingredient[];
  originalPortion: number;
  desiredPortion: number;
}

function IngredientList({ ingredients, originalPortion, desiredPortion }: IngredientListProps) {
  const scaled = calculatePortions(ingredients, originalPortion, desiredPortion);

  return (
    <div className="ingredient-list">
      <h4>Zutaten</h4>
      <ul>
        {scaled.map((ing, index) => (
          <li key={index} className="ingredient-list__item">
            <span className="ingredient-list__amount">
              {ing.amount} {ing.unit}
            </span>
            <span className="ingredient-list__name">{ing.ingredient}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default IngredientList;
