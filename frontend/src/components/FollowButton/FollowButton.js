import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const FollowButton = ({ programmerName, programmerId, followed }) => {
  const [auth, authDispatch, history] = useAuth();
  const [following, setFollowing] = useState();
  const [pending, setPending] = useState();
  const [followershipId, setFollowershipId] = useState();

  useEffect(() => {
    for (let i = 0; i < followed.length; i++) {
      if (followed[i].programmer === programmerName) {
        if (!followed[i].pending) setFollowing(true);
        else setPending(true);
        setFollowershipId(followed[i].followerId);
        break;
      }
    }
  }, []);

  const follow = async () => {
    try {
      debugger;
      await axiosInstance(authDispatch, history).get(
        "/programmer/follow/" + programmerId
      );
      setPending(true);
    } catch (err) {
      alert(err.message);
    }
  };

  const unfollow = async () => {
    try {
      await axiosInstance(authDispatch, history).delete(
        "/programmer/unfollow/" + followershipId
      );
      setFollowing(false);
      setPending(false);
    } catch (err) {
      alert(err.message);
    }
  };

  if (following) {
    return (
      <Button
        onClick={() => {
          unfollow();
        }}
        variant="wine"
        className="border border-rich-black"
      >
        Unfollow
      </Button>
    );
  }
  if (pending) {
    return (
      <Button
        onClick={() => {
          unfollow();
        }}
        variant="wine"
        className="border border-rich-black"
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
    >
      Follow
    </Button>
  );
};

export default FollowButton;
