import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { fetchCategories } from "../../api/fetchCategories";
import type { Category } from "../../types/Category";

interface CategoriesState {
	categories: Category[];
	loading: boolean;
}

const initialState: CategoriesState = {
	categories: [],
	loading: false,
};

export const fetchCategoriesThunk = createAsyncThunk(
	"categories/fetchAll",
	async () => {
		return await fetchCategories();
	},
);

const categoriesSlice = createSlice({
	name: "categories",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(fetchCategoriesThunk.pending, (state) => {
				state.loading = true;
			})
			.addCase(fetchCategoriesThunk.fulfilled, (state, action) => {
				state.loading = false;
				state.categories = action.payload;
			})
			.addCase(fetchCategoriesThunk.rejected, (state) => {
				state.loading = false;
			});
	},
});

export default categoriesSlice.reducer;
