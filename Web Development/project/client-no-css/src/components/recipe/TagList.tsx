
interface TagListProps {
	tags: string[];
}

function TagList({ tags }: TagListProps) {
	return (
		<div>
			{tags.map((tag) => (
				<span key={tag}>
            		{tag}
        		</span>
			))}
		</div>
	);
}

export default TagList;
