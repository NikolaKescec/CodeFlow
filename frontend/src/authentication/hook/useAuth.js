import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthProvider";
import axiosInstance from "../../utils/axiosInstance";

const useAuth = () => {
  const { auth, dispatch } = useContext(AuthContext);
  const [checking, setChecking] = useState(true);

  useEffect(() => {
    axiosInstance.get("/check").then((res) => {
      setChecking(false);
    });
  }, []);

  return [auth, dispatch, checking];
};

export default useAuth;
