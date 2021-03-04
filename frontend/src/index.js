import React from "react";
import ReactDOM from "react-dom";
import App from "./App";

import { createMuiTheme, ThemeProvider } from "@material-ui/core/styles";

const CodeFlowTheme = createMuiTheme({
  palette: {
    secondary: {
      main: "#ebebf2",
    },
    primary: {
      main: "#912f40",
    },
  },
});

ReactDOM.render(
  <React.StrictMode>
    <ThemeProvider theme={CodeFlowTheme}>
      <App />
    </ThemeProvider>
  </React.StrictMode>,
  document.getElementById("root")
);
