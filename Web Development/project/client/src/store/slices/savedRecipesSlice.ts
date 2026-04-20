import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { fetchSavedRecipes } from "../../api/fetchSavedRecipes";
import { saveRecipe } from "../../api/saveRecipe";
import { unsaveRecipe } from "../../api/unsaveRecipe";
import type { Recipe } from "../../types/Recipe";

interface SavedRecipesState {
	recipes: Recipe[];
	loading: boolean;
	error: string | null;
}

const initialState: SavedRecipesState = {
	recipes: [],
	loading: false,
	error: null,
};

export const fetchSavedRecipesThunk = createAsyncThunk(
	"savedRecipes/fetchAll",
	async () => {
		const user = await fetchSavedRecipes();
		return user.savedRecipes as Recipe[];
	},
);

export const saveRecipeThunk = createAsyncThunk(
	"savedRecipes/save",
	async (recipeId: string) => {
		await saveRecipe(recipeId);
		return recipeId;
	},
);

export const unsaveRecipeThunk = createAsyncThunk(
	"savedRecipes/unsave",
	async (recipeId: string) => {
		await unsaveRecipe(recipeId);
		return recipeId;
	},
);

const savedRecipesSlice = createSlice({
	name: "savedRecipes",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(fetchSavedRecipesThunk.pending, (state) => {
				state.loading = true;
				state.error = null;
			})
			.addCase(fetchSavedRecipesThunk.fulfilled, (state, action) => {
				state.loading = false;
				state.recipes = action.payload;
			})
			.addCase(fetchSavedRecipesThunk.rejected, (state, action) => {
				state.loading = false;
				state.error = action.error.message || "Fehler beim Laden";
			})
			.addCase(unsaveRecipeThunk.fulfilled, (state, action) => {
				state.recipes = state.recipes.filter((r) => r._id !== action.payload);
			});
	},
});

export default savedRecipesSlice.reducer;
