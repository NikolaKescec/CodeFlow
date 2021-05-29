import React, { createContext, useReducer } from "react";
import context from "./context";
import appReducer from "./reducer/appReducer";

export const AppContext = createContext({});

export const AppProvider = ({ children }) => {
  const [app, dispatch] = useReducer(appReducer, context, () => {
    const context = {
      page: window.location.pathname,
    };
    return context;
  });

  return (
    <AppContext.Provider value={{ app, appDispatch: dispatch }}>
      {children}
    </AppContext.Provider>
  );
};
