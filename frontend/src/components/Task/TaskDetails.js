import { useState } from "react";
import { Container } from "react-bootstrap";
import { useParams } from "react-router";
const TaskDetails = () => {
  const { id } = useParams();
  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);
  return <Container fluid>TASK</Container>;
};

export default TaskDetails;
