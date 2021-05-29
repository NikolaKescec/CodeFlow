import { Button, Card } from "react-bootstrap";
import { Link } from "react-router-dom";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const Notification = ({ notification, removeNotification }) => {
  const [auth, authDispatch, history] = useAuth();

  const acceptFollow = async () => {
    try {
      let res = await axiosInstance(authDispatch, history).get(
        `/programmer/accept-followership/${notification.notificationId}/${notification.notificatorUsername}`
      );
      removeNotification(notification.notificationId);
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  const denyFollow = async () => {
    try {
      let res = await axiosInstance(authDispatch, history).get(
        `/programmer/deny-followership/${notification.notificationId}/${notification.notificatorUsername}`
      );
      removeNotification(notification.notificationId);
    } catch (err) {
      removeNotification(notification.notificationId);
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  const deleteNotification = async () => {
    try {
      let res = await axiosInstance(authDispatch, history).delete(
        `/notification/remove-notification/${notification.notificationId}`
      );
      removeNotification(notification.notificationId);
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  return (
    <Card className="p-0 border-rich-black text-rich-black">
      <Card.Body>
        <Link
          className="text-red-violet"
          to={"/programmer/" + notification.notificatorUsername}
        >
          {notification.notificationText}
        </Link>
      </Card.Body>
      {notification.type === "followership" && (
        <Card.Footer>
          <div className="d-flex justify-content-end">
            <Button variant="rich-black" onClick={acceptFollow}>
              Allow
            </Button>
            <Button variant="rich-black" className="ml-2" onClick={denyFollow}>
              Reject
            </Button>
          </div>
        </Card.Footer>
      )}
      {notification.type === "info" && (
        <Card.Footer>
          <Button
            variant="rich-black"
            className="ml-2"
            onClick={deleteNotification}
          >
            OK
          </Button>
        </Card.Footer>
      )}
    </Card>
  );
};

export default Notification;
