import useAuth from "../authentication/hook/useAuth";
import CommentsPanel from "../components/CommentsPanel";
import TwoComponentPageLayout from "../components/TwoComponentPageLayout";
import Spinner from "../components/Spinner";
import TaskDetails from "../components/Task/TaskDetails";
import { useParams } from "react-router-dom";

const Task = () => {
  debugger;
  let { id } = useParams();
  const [auth, authDispatch, checking] = useAuth();

  if (checking) {
    return <Spinner></Spinner>;
  }
  return (
    <TwoComponentPageLayout
      main={
        <TaskDetails
          id={id}
          authDispatch={authDispatch}
          loggedInUser={auth.data}
        ></TaskDetails>
      }
      secondary={
        <CommentsPanel
          authDispatch={authDispatch}
          commentsSource={"task-comments/"}
          id={id}
        ></CommentsPanel>
      }
    ></TwoComponentPageLayout>
  );
};

export default Task;
