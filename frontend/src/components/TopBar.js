import { useContext, useEffect, useState } from "react";
import {
  Button,
  Container,
  Dropdown,
  DropdownButton,
  NavDropdown,
  NavItem,
  NavLink,
} from "react-bootstrap";
import { Nav, Navbar } from "react-bootstrap";
import { useHistory } from "react-router";
import { AuthContext } from "../authentication/context/AuthProvider";
import useApp from "../app/hook/useApp";
import appActions from "../app/actions/appActions";
import logo from "../assets/processor.png";
import profile from "../assets/profile.png";
import logout from "../authentication/actions/logout";
import { Link } from "react-router-dom";
import { RiNotification2Fill } from "react-icons/ri";
import axios from "axios";
import axiosInstance from "../utils/axiosInstance";
import Notification from "./Notification/Notification";

const TopBar = ({ path }) => {
  const history = useHistory();
  const { auth, authDispatch } = useContext(AuthContext);
  const [notifications, setNotifications] = useState();
  const [selected, setSelected] = useState(false);

  const getNotifications = async () => {
    let resNotifications = await axiosInstance(authDispatch, history).get(
      "/notification/receive"
    );
    setNotifications(resNotifications.data);
  };

  const removeNotification = (notificationId) => {
    debugger;
    let filtered = notifications.filter((notification) => {
      return notification.notificationId !== notificationId;
    });
    setNotifications([...filtered]);
  };

  const handleNotificationDropdown = (isOpen, ev, metadata) => {
    if (metadata.source === "select") {
      setSelected(true);
      return;
    }
    setSelected(isOpen);
  };

  useEffect(() => {
    if (auth.data) {
      getNotifications();
    }
  }, [path]);

  debugger;

  return (
    <Navbar
      bg="dark"
      variant="dark"
      sticky="top"
      expand="lg"
      className="border-bottom border-rich-black"
    >
      <Navbar.Brand
        as={Link}
        to="/home"
        className="text-red-violet rounded p-1 bg-charcoal"
      >
        <img
          alt=""
          src={logo}
          width="30"
          height="30"
          className="d-inline-block align-top"
        />
        <span className="font-weight-bold"> CodeFlow</span>
      </Navbar.Brand>
      {auth.data && (
        <>
          <NavDropdown
            title={<RiNotification2Fill></RiNotification2Fill>}
            id="notification-dropdown"
            className="rounded-pill border border-rich-black bg-wine ml-auto mr-2"
            onToggle={handleNotificationDropdown}
            show={selected}
            alignRight
          >
            {notifications &&
              notifications.map((notification, index) => {
                return (
                  <div
                    key={
                      notification.notificationId +
                      "-" +
                      notification.notifiedUsername +
                      "-" +
                      notification.notificatorUsername
                    }
                  >
                    <NavDropdown.Item
                      as={Container}
                      className="bg-white"
                      eventKey={index}
                    >
                      <Notification
                        notification={notification}
                        removeNotification={removeNotification}
                      ></Notification>
                    </NavDropdown.Item>
                    <NavDropdown.Divider />
                  </div>
                );
              })}
          </NavDropdown>
          <NavDropdown
            title={
              <>
                <img alt="" src={profile} width="30" height="30" className="" />
              </>
            }
            id="navigation-dropdown"
            className="rounded-pill border border-rich-black bg-wine"
            alignRight
          >
            <NavDropdown.Header>
              <span className="text-wine"> {auth.data.username} </span>
            </NavDropdown.Header>
            <NavDropdown.Divider />
            {path !== "/create-task" && (
              <NavDropdown.Item
                className="text-left"
                as={Link}
                to="/create-task"
              >
                Create task
              </NavDropdown.Item>
            )}
            <NavDropdown.Divider />
            {path !== "/home" && (
              <NavDropdown.Item className="text-left" as={Link} to="/home">
                Home
              </NavDropdown.Item>
            )}
            {path !== "/profile" && (
              <NavDropdown.Item className="text-left" as={Link} to="/profile">
                Profile
              </NavDropdown.Item>
            )}
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={() => logout(history)(authDispatch)}>
              Logout
            </NavDropdown.Item>
          </NavDropdown>
        </>
      )}
    </Navbar>
  );
};

export default TopBar;
