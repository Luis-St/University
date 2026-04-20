import type { Comment } from "../../types/Comment";
import "./CommentList.css";

interface CommentListProps {
  comments: Comment[];
}

function CommentList({ comments }: CommentListProps) {
  if (comments.length === 0) {
    return <p className="comment-list__empty">Noch keine Kommentare.</p>;
  }

  return (
    <div className="comment-list">
      <h4>Kommentare ({comments.length})</h4>
      <ul>
        {comments.map((c, index) => (
          <li key={index} className="comment-list__item">
            <span className="comment-list__user">Benutzer</span>
            <p className="comment-list__text">{c.comment}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CommentList;
