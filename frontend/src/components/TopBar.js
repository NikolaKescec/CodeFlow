import { useContext } from "react";
import { Button } from "react-bootstrap";
import { Nav, Navbar } from "react-bootstrap";
import { useHistory } from "react-router";
import { AuthContext } from "../authentication/context/AuthProvider";
import logo from "../assets/processor.png";
import logout from "../authentication/actions/logout";
import { Link } from "react-router-dom";

const TopBar = () => {
  const history = useHistory();
  const { auth, dispatch } = useContext(AuthContext);

  return (
    <Navbar
      bg="dark"
      variant="dark"
      sticky="top"
      expand="lg"
      className="border-bottom border-wine"
    >
      <Navbar.Brand className="text-red-violet">
        <img
          alt=""
          src={logo}
          width="30"
          height="30"
          className="d-inline-block align-top"
        />
        <span> CodeFlow</span>
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav"></Navbar.Toggle>
      {auth.data && (
        <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
          <Nav>
            <Nav.Item className="ml-2">
              <Link to="/profile">
                <Button variant="wine">Profile</Button>
              </Link>
            </Nav.Item>
            <Nav.Item className="ml-2">
              <Button variant="wine" onClick={() => logout(history)(dispatch)}>
                Logout
              </Button>
            </Nav.Item>
          </Nav>
        </Navbar.Collapse>
      )}
    </Navbar>
  );
};

export default TopBar;
