import { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import logout from "../authentication/actions/logout";
import { useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";
import useAuth from "../authentication/hook/useAuth";

const Profile = () => {
  const [auth, dispatch, checking] = useAuth();
  const [user, setUser] = useState(null);
  const history = useHistory();

  const getUser = async () => {
    debugger;
    try {
      let res = await axiosInstance(dispatch, history).get(
        "/user/" + auth.data.username
      );
      setUser(res.data);
    } catch (err) {}
  };

  useEffect(() => {
    if (!checking) getUser();
  }, [checking]);

  return (
    <Container fluid>
      <Button variant="wine" onClick={() => logout(history)(dispatch)}>
        Logout
      </Button>
      <Container fluid className="bg-baby-powder">
        <p className="overflow-auto">{JSON.stringify(auth)}</p>
      </Container>
      <Container fluid className="bg-baby-powder">
        <p>Logged in user:</p>
        <p>Username: {auth.data.username}</p>
        <p>Id: {auth.data.id}</p>
      </Container>
      {user && (
        <Container fluid className="bg-baby-powder">
          <p>Logged in user (with useEffect):</p>
          <p>Username: {user.username}</p>
          <p>Id: {user.id}</p>
        </Container>
      )}
    </Container>
  );
};

export default Profile;
