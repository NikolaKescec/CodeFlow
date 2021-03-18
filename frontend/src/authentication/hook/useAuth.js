import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthProvider";
import axiosInstance from "../../utils/axiosInstance";
import { useHistory } from "react-router";

const useAuth = () => {
  const { auth, authDispatch } = useContext(AuthContext);
  const [checking, setChecking] = useState(true);
  const history = useHistory();

  useEffect(() => {
    axiosInstance(authDispatch, history)
      .get("/check")
      .then((res) => {
        setChecking(false);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return [auth, authDispatch, checking];
};

export default useAuth;
