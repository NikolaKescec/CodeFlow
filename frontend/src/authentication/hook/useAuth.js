import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthProvider";
import axiosInstance from "../../utils/axiosInstance";
import { useHistory } from "react-router";

const useAuth = () => {
  const { auth, dispatch } = useContext(AuthContext);
  const [checking, setChecking] = useState(true);
  const history = useHistory();

  useEffect(() => {
    axiosInstance(dispatch, history)
      .get("/check")
      .then((res) => {
        setChecking(false);
      });
  }, []);

  return [auth, dispatch, checking];
};

export default useAuth;
