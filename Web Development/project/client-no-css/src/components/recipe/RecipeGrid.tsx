import type { Recipe } from "../../types/Recipe";
import RecipeCard from "./RecipeCard";

interface RecipeGridProps {
	recipes: Recipe[];
	renderActions?: (recipe: Recipe) => React.ReactNode;
}

function RecipeGrid({ recipes, renderActions }: RecipeGridProps) {
	if (recipes.length === 0) {
		return <p>Keine Rezepte gefunden.</p>;
	}
	
	return (
		<div>
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
