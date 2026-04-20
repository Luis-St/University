import "./StarRating.css";

interface StarRatingProps {
  rating: number;
  onRate?: (n: number) => void;
  readOnly?: boolean;
}

function StarRating({ rating, onRate, readOnly = false }: StarRatingProps) {
  const stars = [1, 2, 3, 4, 5];

  return (
    <div className={`star-rating ${readOnly ? "star-rating--readonly" : ""}`}>
      {stars.map((star) => (
        <span
          key={star}
          className={`star-rating__star ${star <= Math.round(rating) ? "star-rating__star--filled" : ""}`}
          onClick={() => !readOnly && onRate?.(star)}
        >
          ★
        </span>
      ))}
      <span className="star-rating__value">{rating > 0 ? rating.toFixed(1) : ""}</span>
    </div>
  );
}

export default StarRating;
