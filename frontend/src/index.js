import React from "react";
import ReactDOM from "react-dom";
import App from "./App";

import { createMuiTheme, ThemeProvider } from "@material-ui/core/styles";

const CodeFlowTheme = createMuiTheme({
  palette: {
    primary: {
      main: "#912f40",
    },
    secondary: {
      main: "#912f40",
    },
    text: {
      primary: "#FFFFFF",
      secondary: "#FFFFFF",
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
