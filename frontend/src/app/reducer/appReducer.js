import appActions from "../actions/appActions";
import context from "../context";

const appReducer = (state, { type, payload }) => {
  switch (type) {
    case appActions.PAGE_CHANGE:
      return {
        ...state,
        page: payload.page,
      };
    default:
      return state;
  }
};

export default appReducer;
