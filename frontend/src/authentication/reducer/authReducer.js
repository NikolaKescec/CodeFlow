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
    case authActions.MODIFY_PROGRAMMER:
    case authActions.LOGIN_SUCCESS:
    case authActions.REGISTER_SUCCESS:
      return {
        ...state,
        ...state.auth,
        data: payload,
        error: undefined,
        loading: false,
      };
    case authActions.ERROR:
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
    default:
      return state;
  }
};

export default authReducer;
