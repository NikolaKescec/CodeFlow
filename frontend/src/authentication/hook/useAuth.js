import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthProvider";
import { useHistory } from "react-router-dom";

const useAuth = () => {
  const { auth, authDispatch } = useContext(AuthContext);
  const history = useHistory();

  return [auth, authDispatch, history];
};

export default useAuth;
