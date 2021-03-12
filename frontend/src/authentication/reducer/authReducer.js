import authActions from "../actions/authActions";
import authState from "../state";

const authReducer = (state, { type, payload }) => {
  switch (type) {
    case authActions.LOGIN_LOADING:
    case authActions.REGISTER_LOADING:
      return {
        ...state,
        ...state.auth,
        error: false,
        loading: true,
      };
    case authActions.LOGIN_SUCCESS:
    case authActions.REGISTER_SUCCESS:
      return {
        ...state,
        ...state.auth,
        data: payload,
        message: undefined,
        error: undefined,
        loading: false,
      };
    case authActions.REGISTER_ERROR:
    case authActions.LOGIN_ERROR:
      return {
        ...state,
        ...state.auth,
        error: payload,
        loading: false,
      };
    case authActions.LOGOUT:
      return {
        ...authState,
      };
    case authActions.REMOVE_ERROR:
      return {
        ...state,
        ...state.auth,
        error: undefined,
      };
    case authActions.REMOVE_MESSAGE:
      return {
        ...state,
        ...state.auth,
        message: undefined,
      };
    default:
      return state;
  }
};

export default authReducer;
