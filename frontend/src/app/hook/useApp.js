import { useContext } from "react";
import { AppContext } from "../AppProvider";
import appActions from "../actions/appActions";

const useApp = () => {
  const { app, appDispatch } = useContext(AppContext);

  const navigation = {
    changePage: (newPage) => {
      appDispatch({
        type: appActions.PAGE_CHANGE,
        payload: { page: newPage },
      });
    },
  };

  return [app, navigation];
};

export default useApp;
