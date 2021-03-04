import { useContext, useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import { AuthContext } from "../authentication/context/AuthProvider";
import logout from "../authentication/actions/logout";
import { useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";

const Home = () => {
  const { auth, dispatch } = useContext(AuthContext);
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
    getUsers();
  }, []);

  return (
    <Container fluid>
      <Button variant="wine" onClick={() => logout(history)(dispatch)}>
        Logout
      </Button>
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
