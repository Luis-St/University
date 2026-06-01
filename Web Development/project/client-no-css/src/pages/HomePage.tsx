import { useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { fetchAllRecipesThunk } from "../store/slices/recipesSlice";
import { fetchSavedRecipesThunk, saveRecipeThunk, unsaveRecipeThunk } from "../store/slices/savedRecipesSlice";
import { filterRecipesByCategory } from "../utils/filterRecipesByCategory";
import RecipeGrid from "../components/recipe/RecipeGrid";
import CategoryFilter from "../components/search/CategoryFilter";
import Button from "../components/ui/Button";
import LoadingSpinner from "../components/ui/LoadingSpinner";

function HomePage() {
	const dispatch = useAppDispatch();
	const { recipes, loading } = useAppSelector((state) => state.recipes);
	const { categories } = useAppSelector((state) => state.categories);
	const { isAuthenticated } = useAppSelector((state) => state.auth);
	const { recipes: savedRecipes = [] } = useAppSelector((state) => state.savedRecipes);
	const [selectedCategory, setSelectedCategory] = useState("");
	
	useEffect(() => {
		dispatch(fetchAllRecipesThunk());
		if (isAuthenticated) {
			dispatch(fetchSavedRecipesThunk());
		}
	}, [dispatch, isAuthenticated]);
	
	const filtered = filterRecipesByCategory(recipes, selectedCategory);
	
	if (loading) return <LoadingSpinner/>;
	
	return (
		<div>
			<h1>Alle Rezepte</h1>
			<CategoryFilter
				categories={categories}
				selectedCategory={selectedCategory}
				onChange={setSelectedCategory}
			/>
			<RecipeGrid
				recipes={filtered}
				renderActions={isAuthenticated ? (recipe) => {
					const isSaved = savedRecipes.some((r) => r._id === recipe._id);
					return (
						<Button
							label={isSaved ? "Gemerkt" : "Merken"}
							variant={isSaved ? "secondary" : "primary"}
							onClick={() => isSaved
								? dispatch(unsaveRecipeThunk(recipe._id))
								: dispatch(saveRecipeThunk(recipe))
							}
						/>
					);
				} : undefined}
			/>
		</div>
	);
}

export default HomePage;
