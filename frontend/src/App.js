import React from "react";
import CodeFlowRouter from "./router/index";
import { AuthProvider } from "./authentication/context/AuthProvider";

import "./styles/globals.css";
import "./styles/sass/App.scss";

function App() {
  return (
    <AuthProvider>
      <CodeFlowRouter></CodeFlowRouter>
    </AuthProvider>
  );
}

export default App;
