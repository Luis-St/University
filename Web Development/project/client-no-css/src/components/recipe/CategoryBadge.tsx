import { useAppSelector } from "../../store/hooks";

interface CategoryBadgeProps {
	categoryId: string;
}

function CategoryBadge({ categoryId }: CategoryBadgeProps) {
	const category = useAppSelector((state) =>
		state.categories.categories.find((c) => c._id === categoryId),
	);
	
	if (!category) return null;
	
	return <span>{category.name}</span>;
}

export default CategoryBadge;
