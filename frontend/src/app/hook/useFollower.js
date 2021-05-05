import { useEffect } from "react";
import { useState } from "react";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const useFollower = (programmerName) => {
  const [auth, authDispatch, history] = useAuth();
  const [followership, setFollowership] = useState();
  const [loading, setLoading] = useState(true);

  const getFollowership = async () => {
    try {
      let res = await axiosInstance(authDispatch, history).get(
        "/programmer/following/" + programmerName
      );
      setFollowership(res.data);
      setLoading(false);
    } catch (err) {
      authDispatch({
        type: authActions.LOGIN_ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  useEffect(() => {
    getFollowership();
  }, []);

  return [followership, setFollowership, loading];
};

export default useFollower;
