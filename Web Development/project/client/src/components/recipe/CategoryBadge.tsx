import { useAppSelector } from "../../store/hooks";
import "./CategoryBadge.css";

interface CategoryBadgeProps {
	categoryId: string;
}

function CategoryBadge({ categoryId }: CategoryBadgeProps) {
	const category = useAppSelector((state) =>
		state.categories.categories.find((c) => c._id === categoryId),
	);
	
	if (!category) return null;
	
	return <span className="category-badge">{category.name}</span>;
}

export default CategoryBadge;
