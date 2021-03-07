export const isLoggedIn = () => {
  debugger;
  if (localStorage.auth) {
    let auth = JSON.parse(localStorage.auth);
    return auth.data !== undefined;
  } else return false;
};
