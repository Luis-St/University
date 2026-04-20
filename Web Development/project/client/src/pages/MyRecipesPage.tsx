import { useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { deleteRecipeThunk, fetchUserRecipesThunk } from "../store/slices/userRecipesSlice";
import RecipeGrid from "../components/recipe/RecipeGrid";
import LoadingSpinner from "../components/ui/LoadingSpinner";
import Modal from "../components/ui/Modal";
import Button from "../components/ui/Button";
import "./MyRecipesPage.css";

function MyRecipesPage() {
	const dispatch = useAppDispatch();
	const { recipes, loading } = useAppSelector((state) => state.userRecipes);
	const [deleteTarget, setDeleteTarget] = useState<string | null>(null);
	
	useEffect(() => {
		dispatch(fetchUserRecipesThunk());
	}, [dispatch]);
	
	const handleDelete = async () => {
		if (deleteTarget) {
			await dispatch(deleteRecipeThunk(deleteTarget));
			setDeleteTarget(null);
		}
	};
	
	if (loading) return <LoadingSpinner/>;
	
	return (
		<div className="my-recipes-page">
			<h1>Meine Rezepte</h1>
			<RecipeGrid
				recipes={recipes}
				renderActions={(recipe) => (
					<Button label="Löschen" variant="danger" onClick={() => setDeleteTarget(recipe._id)}/>
				)}
			/>
			<Modal isOpen={deleteTarget !== null} onClose={() => setDeleteTarget(null)}>
				<h3>Rezept löschen?</h3>
				<p>Möchten Sie dieses Rezept wirklich löschen?</p>
				<div className="my-recipes-page__modal-actions">
					<Button label="Abbrechen" variant="secondary" onClick={() => setDeleteTarget(null)}/>
					<Button label="Löschen" variant="danger" onClick={handleDelete}/>
				</div>
			</Modal>
		</div>
	);
}

export default MyRecipesPage;
