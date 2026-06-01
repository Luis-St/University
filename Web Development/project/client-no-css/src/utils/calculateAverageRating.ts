export const calculateAverageRating = (ratings: number[]): number => {
	if (ratings.length === 0) return 0;
	const sum = ratings.reduce((acc, val) => acc + val, 0);
	return Math.round((sum / ratings.length) * 10) / 10;
};
