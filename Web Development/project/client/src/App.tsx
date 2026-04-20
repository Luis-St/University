import { Routes, Route } from "react-router-dom";
import Layout from "./components/layout/Layout";
import ProtectedRoute from "./components/auth/ProtectedRoute";
import HomePage from "./pages/HomePage";
import RecipeDetailPage from "./pages/RecipeDetailPage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import CreateRecipePage from "./pages/CreateRecipePage";
import MyRecipesPage from "./pages/MyRecipesPage";
import SavedRecipesPage from "./pages/SavedRecipesPage";
import SearchPage from "./pages/SearchPage";

function App() {
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/recipe/:recipeId" element={<RecipeDetailPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route element={<ProtectedRoute />}>
          <Route path="/create" element={<CreateRecipePage />} />
          <Route path="/my-recipes" element={<MyRecipesPage />} />
          <Route path="/saved" element={<SavedRecipesPage />} />
          <Route path="/search" element={<SearchPage />} />
        </Route>
      </Route>
    </Routes>
  );
}

export default App;
