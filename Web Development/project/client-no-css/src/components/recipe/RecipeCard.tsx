import { Link } from "react-router-dom";
import type { Recipe } from "../../types/Recipe";
import { calculateAverageRating } from "../../utils/calculateAverageRating";
import { formatTime } from "../../utils/formatTime";
import StarRating from "../ui/StarRating";
import TagList from "./TagList";

interface RecipeCardProps {
	recipe: Recipe;
	actions?: React.ReactNode;
}

function RecipeCard({ recipe, actions }: RecipeCardProps) {
	const avgRating = calculateAverageRating(recipe.rating);
	
	return (
		<div>
			<Link to={`/recipe/${recipe._id}`}>
				{recipe.href && (
					<img
						src={recipe.href}
						alt={recipe.name}
					/>
				)}
				<div>
					<h3>{recipe.name}</h3>
					<div>
						<span>{formatTime(recipe.time)}</span>
						<StarRating rating={avgRating} readOnly/>
					</div>
					{recipe.tags.length > 0 && <TagList tags={recipe.tags.slice(0, 3)}/>}
				</div>
			</Link>
			{actions && <div>{actions}</div>}
		</div>
	);
}

export default RecipeCard;
