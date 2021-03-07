import { useEffect, useState } from "react";
import useAuth from "../authentication/hook/useAuth";
import { Button, Container } from "react-bootstrap";
import { AuthContext } from "../authentication/context/AuthProvider";
import logout from "../authentication/actions/logout";
import { Link, useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";

const Home = () => {
  debugger;
  const [auth, dispatch, checking] = useAuth();
  const [users, setUsers] = useState([]);
  const history = useHistory();

  const getUsers = async () => {
    debugger;
    try {
      let res = await axiosInstance.get("/user");
      setUsers(res.data);
    } catch (err) {}
  };

  useEffect(() => {
    if (!checking) getUsers();
  }, [checking]);

  return (
    <Container fluid>
      <Button variant="wine" onClick={() => logout(history)(dispatch)}>
        Logout
      </Button>
      <Link to="/profile">
        <Button variant="wine">Profile</Button>
      </Link>
      <Container fluid className="bg-baby-powder">
        <p className="overflow-auto">{JSON.stringify(auth)}</p>
      </Container>
      <Container>
        {users.map((user) => {
          return (
            <div className="overflow-auto bg-baby-powder" key={user.id}>
              <p>{user.id}</p>
              <p>{user.username}</p>
            </div>
          );
        })}
      </Container>
    </Container>
  );
};

export default Home;
