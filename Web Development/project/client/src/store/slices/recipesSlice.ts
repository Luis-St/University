import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { fetchAllRecipes } from "../../api/fetchAllRecipes";
import type { Recipe } from "../../types/Recipe";

interface RecipesState {
  recipes: Recipe[];
  loading: boolean;
  error: string | null;
}

const initialState: RecipesState = {
  recipes: [],
  loading: false,
  error: null,
};

export const fetchAllRecipesThunk = createAsyncThunk(
  "recipes/fetchAll",
  async () => {
    return await fetchAllRecipes();
  }
);

const recipesSlice = createSlice({
  name: "recipes",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllRecipesThunk.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchAllRecipesThunk.fulfilled, (state, action) => {
        state.loading = false;
        state.recipes = action.payload;
      })
      .addCase(fetchAllRecipesThunk.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || "Fehler beim Laden der Rezepte";
      });
  },
});

export default recipesSlice.reducer;
