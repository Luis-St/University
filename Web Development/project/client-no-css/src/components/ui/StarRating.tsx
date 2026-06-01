
interface StarRatingProps {
	rating: number;
	onRate?: (n: number) => void;
	readOnly?: boolean;
}

function StarRating({ rating, onRate, readOnly = false }: StarRatingProps) {
	const stars = [1, 2, 3, 4, 5];
	
	return (
		<div>
			{stars.map((star) => (
				<span
					key={star}
					onClick={() => !readOnly && onRate?.(star)}
				>
          			★
        		</span>
			))}
			<span>{rating > 0 ? rating.toFixed(1) : ""}</span>
		</div>
	);
}

export default StarRating;
