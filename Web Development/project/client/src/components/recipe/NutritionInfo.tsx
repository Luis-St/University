import type { NutritionalValue } from "../../types/NutritionalValue";
import "./NutritionInfo.css";

interface NutritionInfoProps {
  nutritionalValues: NutritionalValue[];
}

function NutritionInfo({ nutritionalValues }: NutritionInfoProps) {
  if (nutritionalValues.length === 0) return null;

  const values = nutritionalValues[0];

  return (
    <div className="nutrition-info">
      <h4>Nährwerte</h4>
      <div className="nutrition-info__grid">
        <div className="nutrition-info__item">
          <span className="nutrition-info__label">Kalorien</span>
          <span className="nutrition-info__value">{values.kcal}</span>
        </div>
        <div className="nutrition-info__item">
          <span className="nutrition-info__label">Protein</span>
          <span className="nutrition-info__value">{values.protein}</span>
        </div>
        <div className="nutrition-info__item">
          <span className="nutrition-info__label">Fett</span>
          <span className="nutrition-info__value">{values.fat}</span>
        </div>
        <div className="nutrition-info__item">
          <span className="nutrition-info__label">Kohlenhydrate</span>
          <span className="nutrition-info__value">{values.carbohydrates}</span>
        </div>
      </div>
    </div>
  );
}

export default NutritionInfo;
