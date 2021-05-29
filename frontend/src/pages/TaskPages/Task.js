import { useParams } from "react-router-dom";
import useAuth from "../../authentication/hook/useAuth";
import CommentsPanel from "../../components/Comment/CommentsPanel";
import TaskDetails from "../../components/Task/TaskDetails";
import TwoComponentPageLayout from "../../components/TwoComponentPageLayout";

const Task = () => {
  let { taskId } = useParams();
  const [auth, authDispatch, history] = useAuth();

  return (
    <TwoComponentPageLayout
      main={
        <TaskDetails
          id={taskId}
          authDispatch={authDispatch}
          loggedInUser={auth.data}
        ></TaskDetails>
      }
      secondary={
        <CommentsPanel
          loggedInUser={auth.data}
          authDispatch={authDispatch}
          commentsSource={"task-comments/"}
          id={taskId}
        ></CommentsPanel>
      }
    ></TwoComponentPageLayout>
  );
};

export default Task;
