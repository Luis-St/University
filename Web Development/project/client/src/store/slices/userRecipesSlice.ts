import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { fetchUserRecipes } from "../../api/fetchUserRecipes";
import { deleteRecipe } from "../../api/deleteRecipe";
import type { Recipe } from "../../types/Recipe";

interface UserRecipesState {
  recipes: Recipe[];
  loading: boolean;
  error: string | null;
}

const initialState: UserRecipesState = {
  recipes: [],
  loading: false,
  error: null,
};

export const fetchUserRecipesThunk = createAsyncThunk(
  "userRecipes/fetchAll",
  async () => {
    return await fetchUserRecipes();
  }
);

export const deleteRecipeThunk = createAsyncThunk(
  "userRecipes/delete",
  async (recipeId: string) => {
    await deleteRecipe(recipeId);
    return recipeId;
  }
);

const userRecipesSlice = createSlice({
  name: "userRecipes",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUserRecipesThunk.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchUserRecipesThunk.fulfilled, (state, action) => {
        state.loading = false;
        state.recipes = action.payload;
      })
      .addCase(fetchUserRecipesThunk.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || "Fehler beim Laden";
      })
      .addCase(deleteRecipeThunk.fulfilled, (state, action) => {
        state.recipes = state.recipes.filter((r) => r._id !== action.payload);
      });
  },
});

export default userRecipesSlice.reducer;
