import { Card, Container, Dropdown } from "react-bootstrap";
import ReactMarkdown from "react-markdown";

const Comment = ({ comment, loggedInUser }) => {
  return (
    <Card
      bg={"charcoal"}
      className="mb-2 mt-1 border border-rich-black text-baby-powder"
    >
      <Card.Header>
        <Container fluid className="p-0">
          <span>
            <strong>{comment.commenter.username}</strong>
          </span>
          {loggedInUser.username === comment.commenter.username && (
            <Dropdown className="d-inline float-right">
              <Dropdown.Toggle
                variant="charcoal"
                className="text-wine btn-sm border border-rich-black"
                id="dropdown-basic"
                split={true}
              ></Dropdown.Toggle>

              <Dropdown.Menu>
                <Dropdown.Item href="#/action-1">Edit</Dropdown.Item>
                <Dropdown.Item href="#/action-2">Delete</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          )}
        </Container>
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
