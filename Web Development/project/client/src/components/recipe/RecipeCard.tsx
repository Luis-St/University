import { Link } from "react-router-dom";
import type { Recipe } from "../../types/Recipe";
import { calculateAverageRating } from "../../utils/calculateAverageRating";
import { formatTime } from "../../utils/formatTime";
import StarRating from "../ui/StarRating";
import TagList from "./TagList";
import "./RecipeCard.css";

interface RecipeCardProps {
  recipe: Recipe;
  actions?: React.ReactNode;
}

function RecipeCard({ recipe, actions }: RecipeCardProps) {
  const avgRating = calculateAverageRating(recipe.rating);

  return (
    <div className="recipe-card">
      <Link to={`/recipe/${recipe._id}`} className="recipe-card__link">
        {recipe.href && (
          <img
            className="recipe-card__image"
            src={recipe.href}
            alt={recipe.name}
          />
        )}
        <div className="recipe-card__content">
          <h3 className="recipe-card__title">{recipe.name}</h3>
          <div className="recipe-card__meta">
            <span className="recipe-card__time">{formatTime(recipe.time)}</span>
            <StarRating rating={avgRating} readOnly />
          </div>
          {recipe.tags.length > 0 && <TagList tags={recipe.tags.slice(0, 3)} />}
        </div>
      </Link>
      {actions && <div className="recipe-card__actions">{actions}</div>}
    </div>
  );
}

export default RecipeCard;
