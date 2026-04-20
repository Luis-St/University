import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./slices/authSlice";
import recipesReducer from "./slices/recipesSlice";
import recipeDetailReducer from "./slices/recipeDetailSlice";
import userRecipesReducer from "./slices/userRecipesSlice";
import savedRecipesReducer from "./slices/savedRecipesSlice";
import categoriesReducer from "./slices/categoriesSlice";
import searchReducer from "./slices/searchSlice";

export const store = configureStore({
	reducer: {
		auth: authReducer,
		recipes: recipesReducer,
		recipeDetail: recipeDetailReducer,
		userRecipes: userRecipesReducer,
		savedRecipes: savedRecipesReducer,
		categories: categoriesReducer,
		search: searchReducer,
	},
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
