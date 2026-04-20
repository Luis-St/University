import type { Recipe } from "../../types/Recipe";
import RecipeCard from "./RecipeCard";
import "./RecipeGrid.css";

interface RecipeGridProps {
  recipes: Recipe[];
  renderActions?: (recipe: Recipe) => React.ReactNode;
}

function RecipeGrid({ recipes, renderActions }: RecipeGridProps) {
  if (recipes.length === 0) {
    return <p className="recipe-grid__empty">Keine Rezepte gefunden.</p>;
  }

  return (
    <div className="recipe-grid">
      {recipes.map((recipe) => (
        <RecipeCard
          key={recipe._id}
          recipe={recipe}
          actions={renderActions?.(recipe)}
        />
      ))}
    </div>
  );
}

export default RecipeGrid;
