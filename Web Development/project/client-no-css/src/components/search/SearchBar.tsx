
interface SearchBarProps {
	value: string;
	onChange: (value: string) => void;
	onSearch: () => void;
}

function SearchBar({ value, onChange, onSearch }: SearchBarProps) {
	const handleKeyDown = (e: React.KeyboardEvent) => {
		if (e.key === "Enter") onSearch();
	};
	
	return (
		<div>
			<input
				type="text"
				value={value}
				onChange={(e) => onChange(e.target.value)}
				onKeyDown={handleKeyDown}
				placeholder="Tags suchen (kommagetrennt)..."
			/>
			<button onClick={onSearch}>
				Suchen
			</button>
		</div>
	);
}

export default SearchBar;
