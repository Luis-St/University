import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { clearSearch, searchRecipesThunk, setCategoryId, setTags } from "../store/slices/searchSlice";
import { fetchCategoriesThunk } from "../store/slices/categoriesSlice";
import { fetchSavedRecipesThunk, saveRecipeThunk, unsaveRecipeThunk } from "../store/slices/savedRecipesSlice";
import SearchBar from "../components/search/SearchBar";
import CategoryFilter from "../components/search/CategoryFilter";
import RecipeGrid from "../components/recipe/RecipeGrid";
import Button from "../components/ui/Button";
import LoadingSpinner from "../components/ui/LoadingSpinner";

function SearchPage() {
	const dispatch = useAppDispatch();
	const { results, tags, categoryId, loading } = useAppSelector((state) => state.search);
	const { categories } = useAppSelector((state) => state.categories);
	const { isAuthenticated } = useAppSelector((state) => state.auth);
	const { recipes: savedRecipes = [] } = useAppSelector((state) => state.savedRecipes);
	
	useEffect(() => {
		dispatch(fetchCategoriesThunk());
		if (isAuthenticated) {
			dispatch(fetchSavedRecipesThunk());
		}
		return () => {
			dispatch(clearSearch());
		};
	}, [dispatch, isAuthenticated]);
	
	const handleSearch = () => {
		const params: { tags?: string; category?: string } = {};
		if (tags.trim()) params.tags = tags.trim();
		if (categoryId) params.category = categoryId;
		if (params.tags || params.category) {
			dispatch(searchRecipesThunk(params));
		}
	};
	
	const handleCategoryChange = (id: string) => {
		dispatch(setCategoryId(id));
	};
	
	return (
		<div>
			<h1>Rezeptsuche</h1>
			<SearchBar
				value={tags}
				onChange={(val) => dispatch(setTags(val))}
				onSearch={handleSearch}
			/>
			<CategoryFilter
				categories={categories}
				selectedCategory={categoryId}
				onChange={handleCategoryChange}
			/>
			{loading ? <LoadingSpinner/> : (
				<RecipeGrid
					recipes={results}
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
			)}
		</div>
	);
}

export default SearchPage;
