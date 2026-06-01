import type { NutritionalValue } from "../../types/NutritionalValue";

interface NutritionInfoProps {
	nutritionalValues: NutritionalValue[];
}

function NutritionInfo({ nutritionalValues }: NutritionInfoProps) {
	if (nutritionalValues.length === 0) return null;
	
	const values = nutritionalValues[0];
	
	return (
		<div>
			<h4>Nährwerte</h4>
			<div>
				<div>
					<span>Kalorien</span>
					<span>{values.kcal}</span>
				</div>
				<div>
					<span>Protein</span>
					<span>{values.protein}</span>
				</div>
				<div>
					<span>Fett</span>
					<span>{values.fat}</span>
				</div>
				<div>
					<span>Kohlenhydrate</span>
					<span>{values.carbohydrates}</span>
				</div>
			</div>
		</div>
	);
}

export default NutritionInfo;
