import type { Category } from "../../types/Category";

interface CategoryFilterProps {
	categories: Category[];
	selectedCategory: string;
	onChange: (id: string) => void;
}

function CategoryFilter({ categories, onChange }: CategoryFilterProps) {
	return (
		<div>
			<button
				onClick={() => onChange("")}
			>
				Alle
			</button>
			{categories.map((cat) => (
				<button
					key={cat._id}
					onClick={() => onChange(cat._id)}
				>
					{cat.name}
				</button>
			))}
		</div>
	);
}

export default CategoryFilter;
