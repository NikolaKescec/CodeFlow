export const isLoggedIn = () => {
  return localStorage.token !== undefined;
};
