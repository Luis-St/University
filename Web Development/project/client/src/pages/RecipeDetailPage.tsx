import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { fetchRecipeByIdThunk, clearRecipeDetail } from "../store/slices/recipeDetailSlice";
import { saveRecipeThunk, unsaveRecipeThunk, fetchSavedRecipesThunk } from "../store/slices/savedRecipesSlice";
import { commentRecipe } from "../api/commentRecipe";
import { rateRecipe } from "../api/rateRecipe";
import { commentAndRateRecipe } from "../api/commentAndRateRecipe";
import { calculateAverageRating } from "../utils/calculateAverageRating";
import { formatTime } from "../utils/formatTime";
import IngredientList from "../components/recipe/IngredientList";
import NutritionInfo from "../components/recipe/NutritionInfo";
import PortionScaler from "../components/recipe/PortionScaler";
import TagList from "../components/recipe/TagList";
import CategoryBadge from "../components/recipe/CategoryBadge";
import CommentList from "../components/recipe/CommentList";
import CommentForm from "../components/recipe/CommentForm";
import StarRating from "../components/ui/StarRating";
import Button from "../components/ui/Button";
import LoadingSpinner from "../components/ui/LoadingSpinner";
import "./RecipeDetailPage.css";

function RecipeDetailPage() {
  const { recipeId } = useParams<{ recipeId: string }>();
  const dispatch = useAppDispatch();
  const { recipe, loading } = useAppSelector((state) => state.recipeDetail);
  const { isAuthenticated } = useAppSelector((state) => state.auth);
  const { recipes: savedRecipes } = useAppSelector((state) => state.savedRecipes);
  const [desiredPortion, setDesiredPortion] = useState(0);

  useEffect(() => {
    if (recipeId) {
      dispatch(fetchRecipeByIdThunk(recipeId));
      if (isAuthenticated) {
        dispatch(fetchSavedRecipesThunk());
      }
    }
    return () => {
      dispatch(clearRecipeDetail());
    };
  }, [dispatch, recipeId, isAuthenticated]);

  useEffect(() => {
    if (recipe) {
      setDesiredPortion(recipe.portion);
    }
  }, [recipe]);

  if (loading || !recipe) return <LoadingSpinner />;

  const avgRating = calculateAverageRating(recipe.rating);
  const isSaved = savedRecipes.some((r) => r._id === recipe._id);

  const handleSaveToggle = () => {
    if (isSaved) {
      dispatch(unsaveRecipeThunk(recipe._id));
    } else {
      dispatch(saveRecipeThunk(recipe._id));
    }
  };

  const handleCommentSubmit = async (comment: string, rating?: number) => {
    if (comment && rating) {
      await commentAndRateRecipe(recipe._id, comment, rating);
    } else if (comment) {
      await commentRecipe(recipe._id, comment);
    } else if (rating) {
      await rateRecipe(recipe._id, rating);
    }
    dispatch(fetchRecipeByIdThunk(recipe._id));
  };

  return (
    <div className="recipe-detail">
      {recipe.href && (
        <img className="recipe-detail__image" src={recipe.href} alt={recipe.name} />
      )}
      <div className="recipe-detail__header">
        <h1>{recipe.name}</h1>
        <div className="recipe-detail__meta">
          <span>{formatTime(recipe.time)}</span>
          <StarRating rating={avgRating} readOnly />
          <span>({recipe.rating.length} Bewertungen)</span>
        </div>
      </div>

      <div className="recipe-detail__badges">
        {recipe.categories.map((catId) => (
          <CategoryBadge key={catId} categoryId={catId} />
        ))}
        {recipe.tags.length > 0 && <TagList tags={recipe.tags} />}
      </div>

      {isAuthenticated && (
        <Button
          label={isSaved ? "Gemerkt - Entfernen" : "Merken"}
          variant={isSaved ? "secondary" : "primary"}
          onClick={handleSaveToggle}
        />
      )}

      <div className="recipe-detail__section">
        <PortionScaler portion={desiredPortion} onChange={setDesiredPortion} />
        <IngredientList
          ingredients={recipe.ingredients}
          originalPortion={recipe.portion}
          desiredPortion={desiredPortion}
        />
      </div>

      <div className="recipe-detail__section">
        <NutritionInfo nutritionalValues={recipe.nutritionalValues} />
      </div>

      <div className="recipe-detail__section">
        <h3>Zubereitung</h3>
        <p className="recipe-detail__directions">{recipe.directions}</p>
      </div>

      <div className="recipe-detail__section">
        <CommentList comments={recipe.comments} />
      </div>

      {isAuthenticated && (
        <div className="recipe-detail__section">
          <CommentForm onSubmit={handleCommentSubmit} />
        </div>
      )}
    </div>
  );
}

export default RecipeDetailPage;
