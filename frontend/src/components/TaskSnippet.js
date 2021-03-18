import { Card } from "react-bootstrap";

const TaskSnippet = ({ task }) => {
  debugger;
  return (
    <Card className="mb-2 mt-1">
      <Card.Title>{task.author.username}</Card.Title>
      <Card.Body>{task.taskText}</Card.Body>
      <Card.Footer>
        Grade: {task.averageGrade == null ? "ungraded" : task.averageGrade}
      </Card.Footer>
    </Card>
  );
};

export default TaskSnippet;
