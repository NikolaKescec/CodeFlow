import { Card } from "react-bootstrap";
import ReactMarkdown from "react-markdown";

const Comment = ({ comment, loggedInUser }) => {
  return (
    <Card
      bg={"charcoal"}
      className="mb-2 mt-1 border border-rich-black text-baby-powder"
    >
      <Card.Header>
        <span>
          <strong>{comment.commenter.username}</strong>
        </span>
      </Card.Header>
      <Card.Body>
        <span>
          <ReactMarkdown>{comment.comment}</ReactMarkdown>
        </span>
      </Card.Body>
    </Card>
  );
};

export default Comment;
