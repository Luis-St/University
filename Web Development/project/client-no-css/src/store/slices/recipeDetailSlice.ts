import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { fetchRecipeById } from "../../api/fetchRecipeById";
import type { Recipe } from "../../types/Recipe";

interface RecipeDetailState {
	recipe: Recipe | null;
	loading: boolean;
	error: string | null;
}

const initialState: RecipeDetailState = {
	recipe: null,
	loading: false,
	error: null,
};

export const fetchRecipeByIdThunk = createAsyncThunk(
	"recipeDetail/fetch",
	async (recipeId: string) => {
		return await fetchRecipeById(recipeId);
	},
);

const recipeDetailSlice = createSlice({
	name: "recipeDetail",
	initialState,
	reducers: {
		clearRecipeDetail: (state) => {
			state.recipe = null;
			state.error = null;
		},
	},
	extraReducers: (builder) => {
		builder
			.addCase(fetchRecipeByIdThunk.pending, (state) => {
				state.loading = true;
				state.error = null;
			})
			.addCase(fetchRecipeByIdThunk.fulfilled, (state, action) => {
				state.loading = false;
				state.recipe = action.payload;
			})
			.addCase(fetchRecipeByIdThunk.rejected, (state, action) => {
				state.loading = false;
				state.error = action.error.message || "Fehler beim Laden des Rezepts";
			});
	},
});

export const { clearRecipeDetail } = recipeDetailSlice.actions;
export default recipeDetailSlice.reducer;
