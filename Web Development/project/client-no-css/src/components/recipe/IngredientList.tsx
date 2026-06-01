import type { Ingredient } from "../../types/Ingredient";
import { calculatePortions } from "../../utils/calculatePortions";

interface IngredientListProps {
	ingredients: Ingredient[];
	originalPortion: number;
	desiredPortion: number;
}

function IngredientList({ ingredients, originalPortion, desiredPortion }: IngredientListProps) {
	const scaled = calculatePortions(ingredients, originalPortion, desiredPortion);
	
	return (
		<div>
			<h4>Zutaten</h4>
			<ul>
				{scaled.map((ing, index) => (
					<li key={index}>
            			<span>
              				{ing.amount} {ing.unit}
            			</span>
						<span>{ing.ingredient}</span>
					</li>
				))}
			</ul>
		</div>
	);
}

export default IngredientList;
