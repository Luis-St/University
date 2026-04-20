import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { fetchCategoriesThunk } from "../store/slices/categoriesSlice";
import { createRecipe } from "../api/createRecipe";
import InputField from "../components/ui/InputField";
import Button from "../components/ui/Button";
import "./CreateRecipePage.css";

function CreateRecipePage() {
	const navigate = useNavigate();
	const dispatch = useAppDispatch();
	const { categories } = useAppSelector((state) => state.categories);
	const [error, setError] = useState("");
	
	const [name, setName] = useState("");
	const [href, setHref] = useState("");
	const [time, setTime] = useState("");
	const [portion, setPortion] = useState("");
	const [directions, setDirections] = useState("");
	const [tagsInput, setTagsInput] = useState("");
	const [selectedCategories, setSelectedCategories] = useState<string[]>([]);
	const [ingredients, setIngredients] = useState([{ ingredient: "", amount: "", unit: "" }]);
	const [nutritionalValues, setNutritionalValues] = useState({ kcal: "", protein: "", fat: "", carbohydrates: "" });
	
	useEffect(() => {
		dispatch(fetchCategoriesThunk());
	}, [dispatch]);
	
	const handleIngredientChange = (index: number, field: string, value: string) => {
		const updated = ingredients.map((ing, i) =>
			i === index ? { ...ing, [field]: value } : ing,
		);
		setIngredients(updated);
	};
	
	const addIngredient = () => {
		setIngredients([...ingredients, { ingredient: "", amount: "", unit: "" }]);
	};
	
	const removeIngredient = (index: number) => {
		setIngredients(ingredients.filter((_, i) => i !== index));
	};
	
	const toggleCategory = (catId: string) => {
		setSelectedCategories((prev) =>
			prev.includes(catId) ? prev.filter((id) => id !== catId) : [...prev, catId],
		);
	};
	
	const handleSubmit = async (e: React.FormEvent) => {
		e.preventDefault();
		setError("");
		
		const tags = tagsInput
			.split(",")
			.map((t) => t.trim())
			.filter((t) => t.length > 0);
		
		const parsedIngredients = ingredients.map((ing) => ({
			ingredient: ing.ingredient,
			amount: Number(ing.amount),
			unit: ing.unit,
		}));
		
		try {
			await createRecipe({
				name,
				href,
				time: Number(time),
				portion: Number(portion),
				directions,
				tags,
				categories: selectedCategories,
				ingredients: parsedIngredients,
				nutritionalValues: [nutritionalValues],
			});
			navigate("/");
		} catch {
			setError("Fehler beim Erstellen des Rezepts.");
		}
	};
	
	return (
		<div className="create-recipe-page">
			<form className="create-recipe-page__form" onSubmit={handleSubmit}>
				<h1>Rezept erstellen</h1>
				{error && <p className="create-recipe-page__error">{error}</p>}
				
				<InputField label="Name" name="name" value={name} onChange={(e) => setName(e.target.value)} required/>
				<InputField label="Bild-URL" name="href" value={href} onChange={(e) => setHref(e.target.value)} required/>
				<div className="create-recipe-page__row">
					<InputField label="Zeit (Minuten)" name="time" type="number" value={time} onChange={(e) => setTime(e.target.value)} required/>
					<InputField label="Portionen" name="portion" type="number" value={portion} onChange={(e) => setPortion(e.target.value)} required/>
				</div>
				
				<div className="create-recipe-page__section">
					<h3>Zutaten</h3>
					{ingredients.map((ing, index) => (
						<div key={index} className="create-recipe-page__ingredient-row">
							<InputField label="Zutat" name={`ing-${index}`} value={ing.ingredient} onChange={(e) => handleIngredientChange(index, "ingredient", e.target.value)} required/>
							<InputField label="Menge" name={`amount-${index}`} type="number" value={ing.amount} onChange={(e) => handleIngredientChange(index, "amount", e.target.value)} required/>
							<InputField label="Einheit" name={`unit-${index}`} value={ing.unit} onChange={(e) => handleIngredientChange(index, "unit", e.target.value)} required/>
							{ingredients.length > 1 && (
								<Button label="X" variant="danger" onClick={() => removeIngredient(index)}/>
							)}
						</div>
					))}
					<Button label="+ Zutat hinzufügen" variant="secondary" onClick={addIngredient}/>
				</div>
				
				<div className="create-recipe-page__section">
					<h3>Nährwerte</h3>
					<div className="create-recipe-page__row">
						<InputField label="Kalorien" name="kcal" value={nutritionalValues.kcal} onChange={(e) => setNutritionalValues({ ...nutritionalValues, kcal: e.target.value })}/>
						<InputField label="Protein" name="protein" value={nutritionalValues.protein} onChange={(e) => setNutritionalValues({ ...nutritionalValues, protein: e.target.value })}/>
					</div>
					<div className="create-recipe-page__row">
						<InputField label="Fett" name="fat" value={nutritionalValues.fat} onChange={(e) => setNutritionalValues({ ...nutritionalValues, fat: e.target.value })}/>
						<InputField label="Kohlenhydrate" name="carbs" value={nutritionalValues.carbohydrates} onChange={(e) => setNutritionalValues({ ...nutritionalValues, carbohydrates: e.target.value })}/>
					</div>
				</div>
				
				<div className="create-recipe-page__section">
					<label>Zubereitung</label>
					<textarea
						className="create-recipe-page__textarea"
						value={directions}
						onChange={(e) => setDirections(e.target.value)}
						rows={5}
						required
					/>
				</div>
				
				<InputField label="Tags (kommagetrennt)" name="tags" value={tagsInput} onChange={(e) => setTagsInput(e.target.value)}/>
				
				<div className="create-recipe-page__section">
					<h3>Kategorien</h3>
					<div className="create-recipe-page__categories">
						{categories.map((cat) => (
							<label key={cat._id} className="create-recipe-page__category">
								<input
									type="checkbox"
									checked={selectedCategories.includes(cat._id)}
									onChange={() => toggleCategory(cat._id)}
								/>
								{cat.name}
							</label>
						))}
					</div>
				</div>
				
				<Button label="Rezept erstellen" type="submit"/>
			</form>
		</div>
	);
}

export default CreateRecipePage;
