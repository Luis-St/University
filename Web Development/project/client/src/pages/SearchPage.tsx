import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { searchRecipesThunk, setTags, setCategoryId, clearSearch } from "../store/slices/searchSlice";
import { fetchCategoriesThunk } from "../store/slices/categoriesSlice";
import SearchBar from "../components/search/SearchBar";
import CategoryFilter from "../components/search/CategoryFilter";
import RecipeGrid from "../components/recipe/RecipeGrid";
import LoadingSpinner from "../components/ui/LoadingSpinner";
import "./SearchPage.css";

function SearchPage() {
  const dispatch = useAppDispatch();
  const { results, tags, categoryId, loading } = useAppSelector((state) => state.search);
  const { categories } = useAppSelector((state) => state.categories);

  useEffect(() => {
    dispatch(fetchCategoriesThunk());
    return () => {
      dispatch(clearSearch());
    };
  }, [dispatch]);

  const handleSearch = () => {
    const params: { tags?: string; category?: string } = {};
    if (tags.trim()) params.tags = tags.trim();
    if (categoryId) params.category = categoryId;
    if (params.tags || params.category) {
      dispatch(searchRecipesThunk(params));
    }
  };

  const handleCategoryChange = (id: string) => {
    dispatch(setCategoryId(id));
  };

  return (
    <div className="search-page">
      <h1>Rezeptsuche</h1>
      <SearchBar
        value={tags}
        onChange={(val) => dispatch(setTags(val))}
        onSearch={handleSearch}
      />
      <CategoryFilter
        categories={categories}
        selectedCategory={categoryId}
        onChange={handleCategoryChange}
      />
      {loading ? <LoadingSpinner /> : <RecipeGrid recipes={results} />}
    </div>
  );
}

export default SearchPage;
