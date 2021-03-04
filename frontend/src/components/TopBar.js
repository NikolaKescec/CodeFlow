import { Button } from "react-bootstrap";
import { Nav, Navbar } from "react-bootstrap";

import logo from "../assets/processor.png";

const TopBar = () => {
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
      <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
        {/* <Nav>
          <Nav.Item className="ml-2">
            <Button variant="wine" onClick={() => signOut()}>
              Log out
            </Button>
          </Nav.Item>
        </Nav> */}
      </Navbar.Collapse>
    </Navbar>
  );
};

export default TopBar;
