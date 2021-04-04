import useAuth from "../authentication/hook/useAuth";
import CommentsPanel from "../components/CommentsPanel";
import TwoComponentPageLayout from "../components/TwoComponentPageLayout";
import Spinner from "../components/Spinner";
import TaskDetails from "../components/Task/TaskDetails";

const Task = () => {
  debugger;
  const [auth, authDispatch, checking] = useAuth();

  if (checking) {
    return <Spinner></Spinner>;
  }
  return (
    <TwoComponentPageLayout
      main={<TaskDetails></TaskDetails>}
      side={<CommentsPanel commentsSource={"task-comments/"}></CommentsPanel>}
    ></TwoComponentPageLayout>
  );
};

export default Task;
