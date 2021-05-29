import { useEffect, useState } from "react";
import { Container, Jumbotron } from "react-bootstrap";
import useAuth from "../../authentication/hook/useAuth";
import "../../styles/feed.css";
import "../../styles/spinner.css";
import axiosInstance from "../../utils/axiosInstance";
import Spinner from "../Spinner";
import TaskSnippet from "./TaskSnippet";

const Feed = ({ text, loggedInUser }) => {
  const [auth, authDispatch, history] = useAuth();
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);

  const getTasks = async () => {
    try {
      let res = await axiosInstance(authDispatch, history).get(`/task/${text}`);
      setTasks([...res.data]);
      setLoading(false);
    } catch (e) {}
  };

  useEffect(() => {
    getTasks();
  }, [text]);

  if (loading) {
    return <Spinner></Spinner>;
  }

  if (!loading && tasks.length === 0)
    return (
      <Container fluid>
        <Jumbotron className="bg-dark text-center text-white mt-2">
          Seems there are no tasks to show here!
        </Jumbotron>
      </Container>
    );

  return (
    <Container fluid>
      {tasks.map((task, index) => {
        return (
          <TaskSnippet
            key={task.taskId + task.author + index}
            task={task}
            loggedInUser={loggedInUser}
          ></TaskSnippet>
        );
      })}
    </Container>
  );
};

export default Feed;
