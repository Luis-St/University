import type { Comment } from "../../types/Comment";

interface CommentListProps {
	comments: Comment[];
}

function CommentList({ comments }: CommentListProps) {
	if (comments.length === 0) {
		return <p>Noch keine Kommentare.</p>;
	}
	
	return (
		<div>
			<h4>Kommentare ({comments.length})</h4>
			<ul>
				{comments.map((c, index) => (
					<li key={index}>
						<span>{c.user}</span>
						<p>{c.comment}</p>
					</li>
				))}
			</ul>
		</div>
	);
}

export default CommentList;
