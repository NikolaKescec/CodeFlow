import { useContext, useEffect, useState } from "react";
import {
  Button,
  Dropdown,
  DropdownButton,
  NavDropdown,
  NavItem,
  NavLink,
} from "react-bootstrap";
import { Nav, Navbar } from "react-bootstrap";
import { useHistory } from "react-router";
import { AuthContext } from "../authentication/context/AuthProvider";
import logo from "../assets/processor.png";
import profile from "../assets/profile.png";
import logout from "../authentication/actions/logout";
import { Link } from "react-router-dom";

const TopBar = () => {
  const history = useHistory();
  const { auth, dispatch } = useContext(AuthContext);
  const [path, setPath] = useState();

  useEffect(() => {
    if (auth.loading) {
      setPath("/home");
    }
  });

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
      {auth.data && (
        <NavDropdown
          title={
            <img alt="" src={profile} width="30" height="30" className="" />
          }
          id="nav-dropdown"
          className="rounded-pill border border-rich-black bg-wine ml-auto"
          alignRight
        >
          {path !== "/home" && (
            <NavDropdown.Item
              className="text-left"
              as={Link}
              to="/home"
              onClick={() => setPath("/home")}
            >
              Home
            </NavDropdown.Item>
          )}
          {path !== "/profile" && (
            <NavDropdown.Item
              className="text-left"
              as={Link}
              to="/profile"
              onClick={() => setPath("/profile")}
            >
              Profile
            </NavDropdown.Item>
          )}
          <NavDropdown.Divider />
          <NavDropdown.Item onClick={() => logout(history)(dispatch)}>
            Logout
          </NavDropdown.Item>
        </NavDropdown>
      )}
    </Navbar>
  );
};

export default TopBar;
