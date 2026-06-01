import { useState } from "react";
import StarRating from "../ui/StarRating";
import Button from "../ui/Button";

interface CommentFormProps {
	onSubmit: (comment: string, rating?: number) => void;
}

function CommentForm({ onSubmit }: CommentFormProps) {
	const [comment, setComment] = useState("");
	const [rating, setRating] = useState(0);
	
	const handleSubmit = () => {
		if (!comment.trim() && rating === 0) return;
		onSubmit(comment.trim(), rating > 0 ? rating : undefined);
		setComment("");
		setRating(0);
	};
	
	return (
		<div>
			<h4>Bewerten & Kommentieren</h4>
			<StarRating rating={rating} onRate={setRating}/>
			<textarea
				value={comment}
				onChange={(e) => setComment(e.target.value)}
				placeholder="Kommentar schreiben..."
				rows={3}
			/>
			<Button label="Absenden" onClick={handleSubmit} disabled={!comment.trim() && rating === 0}/>
		</div>
	);
}

export default CommentForm;
