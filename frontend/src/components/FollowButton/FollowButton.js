import { Button } from "react-bootstrap";
import useFollower from "../../app/hook/useFollower";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const FollowButton = ({ programmerName, programmerId }) => {
  const [auth, authDispatch, history] = useAuth();
  const [followership, setFollowership, loading] = useFollower(programmerName);

  const follow = async () => {
    try {
      let resFollow = await axiosInstance(authDispatch, history).get(
        "/programmer/follow/" + programmerId
      );
      setFollowership(resFollow.data);
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  const unfollow = async () => {
    try {
      await axiosInstance(authDispatch, history).delete(
        "/programmer/unfollow/" + followership.followerId
      );
      setFollowership({});
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  if (loading) {
    return (
      <Button variant="wine" block className="border border-rich-black">
        ...
      </Button>
    );
  }

  if (followership.following && !followership.pending) {
    return (
      <Button
        onClick={() => {
          unfollow();
        }}
        variant="wine"
        className="border border-rich-black"
        block
      >
        Unfollow
      </Button>
    );
  }
  if (followership.pending) {
    return (
      <Button
        onClick={() => {
          unfollow();
        }}
        variant="wine"
        className="border border-rich-black"
        block
      >
        Pending
      </Button>
    );
  }
  return (
    <Button
      onClick={() => {
        follow();
      }}
      variant="wine"
      className="border border-rich-black"
      block
    >
      Follow
    </Button>
  );
};

export default FollowButton;
