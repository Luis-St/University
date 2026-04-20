import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { fetchSavedRecipesThunk, unsaveRecipeThunk } from "../store/slices/savedRecipesSlice";
import RecipeGrid from "../components/recipe/RecipeGrid";
import LoadingSpinner from "../components/ui/LoadingSpinner";
import Button from "../components/ui/Button";
import "./SavedRecipesPage.css";

function SavedRecipesPage() {
  const dispatch = useAppDispatch();
  const { recipes, loading } = useAppSelector((state) => state.savedRecipes);

  useEffect(() => {
    dispatch(fetchSavedRecipesThunk());
  }, [dispatch]);

  const handleUnsave = (recipeId: string) => {
    dispatch(unsaveRecipeThunk(recipeId));
  };

  if (loading) return <LoadingSpinner />;

  return (
    <div className="saved-recipes-page">
      <h1>Gemerkte Rezepte</h1>
      <RecipeGrid
        recipes={recipes}
        renderActions={(recipe) => (
          <Button label="Entfernen" variant="secondary" onClick={() => handleUnsave(recipe._id)} />
        )}
      />
    </div>
  );
}

export default SavedRecipesPage;
