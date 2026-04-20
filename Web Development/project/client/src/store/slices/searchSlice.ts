import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { searchRecipes } from "../../api/searchRecipes";
import type { Recipe } from "../../types/Recipe";

interface SearchState {
  results: Recipe[];
  tags: string;
  categoryId: string;
  loading: boolean;
  error: string | null;
}

const initialState: SearchState = {
  results: [],
  tags: "",
  categoryId: "",
  loading: false,
  error: null,
};

export const searchRecipesThunk = createAsyncThunk(
  "search/search",
  async (params: { tags?: string; category?: string }) => {
    return await searchRecipes(params);
  }
);

const searchSlice = createSlice({
  name: "search",
  initialState,
  reducers: {
    setTags: (state, action) => {
      state.tags = action.payload;
    },
    setCategoryId: (state, action) => {
      state.categoryId = action.payload;
    },
    clearSearch: (state) => {
      state.results = [];
      state.tags = "";
      state.categoryId = "";
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(searchRecipesThunk.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(searchRecipesThunk.fulfilled, (state, action) => {
        state.loading = false;
        state.results = action.payload;
      })
      .addCase(searchRecipesThunk.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || "Suche fehlgeschlagen";
      });
  },
});

export const { setTags, setCategoryId, clearSearch } = searchSlice.actions;
export default searchSlice.reducer;
