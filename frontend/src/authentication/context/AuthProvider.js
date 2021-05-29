import React, { createContext, useEffect, useReducer } from "react";
import authReducer from "../reducer/authReducer";
import authState from "../state";

export const AuthContext = createContext({});

//destrukturiranje djece ovog komponenta
export const AuthProvider = ({ children }) => {
  const [auth, dispatch] = useReducer(authReducer, authState, () => {
    const auth =
      localStorage.auth !== undefined
        ? JSON.parse(localStorage.auth)
        : authState;
    return auth;
  });

  useEffect(() => {
    localStorage.auth = JSON.stringify(auth);
  }, [auth]);

  return (
    <AuthContext.Provider value={{ auth, authDispatch: dispatch }}>
      {children}
    </AuthContext.Provider>
  );
};
