import type { Category } from "../../types/Category";
import "./CategoryFilter.css";

interface CategoryFilterProps {
  categories: Category[];
  selectedCategory: string;
  onChange: (id: string) => void;
}

function CategoryFilter({ categories, selectedCategory, onChange }: CategoryFilterProps) {
  return (
    <div className="category-filter">
      <button
        className={`category-filter__btn ${selectedCategory === "" ? "category-filter__btn--active" : ""}`}
        onClick={() => onChange("")}
      >
        Alle
      </button>
      {categories.map((cat) => (
        <button
          key={cat._id}
          className={`category-filter__btn ${selectedCategory === cat._id ? "category-filter__btn--active" : ""}`}
          onClick={() => onChange(cat._id)}
        >
          {cat.name}
        </button>
      ))}
    </div>
  );
}

export default CategoryFilter;
