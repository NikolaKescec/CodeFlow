import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import TaskSnippet from "./TaskSnippet";

import "../../styles/spinner.css";
import "../../styles/feed.css";
import axiosInstance from "../../utils/axiosInstance";

const Feed = ({ text, loggedInUser }) => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);

  const getTasks = async () => {
    debugger;
    try {
      let res = await axiosInstance().get(`/task/${text}`);
      setTasks([...res.data]);
      setLoading(false);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getTasks();
  }, [text]);

  return (
    <Container fluid>
      {loading && <div class="loader">Loading...</div>}
      {!loading &&
        tasks.map((task) => {
          return (
            <TaskSnippet
              key={task.taskId}
              task={task}
              loggedInUser={loggedInUser}
            ></TaskSnippet>
          );
        })}
    </Container>
  );
};

export default Feed;
