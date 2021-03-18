import { Button, Card } from "react-bootstrap";
import {
  AiFillCheckCircle,
  AiOutlineCheckCircle,
  AiOutlineCode,
  AiOutlineSolution,
} from "react-icons/ai";
import Grade from "../Grade";

const TaskSnippet = ({ task }) => {
  debugger;
  return (
    <Card
      bg={"charcoal"}
      className="mb-2 mt-1 border border-rich-black text-baby-powder"
    >
      <Card.Body>{task.taskText}</Card.Body>
      <Card.Footer className="d-flex">
        <span className="border-right border-rich-black text-rich-black p-2 mr-1">
          <strong>{task.author.username}</strong>
        </span>
        <span className="border-right border-rich-black  p-1 mr-1">
          {task.averageGrade == null ? (
            <Grade grade={0}></Grade>
          ) : (
            <Grade grade={task.averageGrade}></Grade>
          )}
          {task.loggedInUserGrade && (
            <span>
              (<AiOutlineCheckCircle></AiOutlineCheckCircle>)
            </span>
          )}
        </span>
        <Button variant="outline-rich-black" className="ml-auto">
          {task.loggedInUserSolution && <AiFillCheckCircle></AiFillCheckCircle>}
          SOLVE
        </Button>
      </Card.Footer>
    </Card>
  );
};

export default TaskSnippet;
