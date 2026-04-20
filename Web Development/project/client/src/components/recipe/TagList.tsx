import "./TagList.css";

interface TagListProps {
  tags: string[];
}

function TagList({ tags }: TagListProps) {
  return (
    <div className="tag-list">
      {tags.map((tag) => (
        <span key={tag} className="tag-list__tag">
          {tag}
        </span>
      ))}
    </div>
  );
}

export default TagList;
