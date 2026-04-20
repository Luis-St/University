import { useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { fetchAllRecipesThunk } from "../store/slices/recipesSlice";
import { filterRecipesByCategory } from "../utils/filterRecipesByCategory";
import RecipeGrid from "../components/recipe/RecipeGrid";
import CategoryFilter from "../components/search/CategoryFilter";
import LoadingSpinner from "../components/ui/LoadingSpinner";
import "./HomePage.css";

function HomePage() {
  const dispatch = useAppDispatch();
  const { recipes, loading } = useAppSelector((state) => state.recipes);
  const { categories } = useAppSelector((state) => state.categories);
  const [selectedCategory, setSelectedCategory] = useState("");

  useEffect(() => {
    dispatch(fetchAllRecipesThunk());
  }, [dispatch]);

  const filtered = filterRecipesByCategory(recipes, selectedCategory);

  if (loading) return <LoadingSpinner />;

  return (
    <div className="home-page">
      <h1>Alle Rezepte</h1>
      <CategoryFilter
        categories={categories}
        selectedCategory={selectedCategory}
        onChange={setSelectedCategory}
      />
      <RecipeGrid recipes={filtered} />
    </div>
  );
}

export default HomePage;
