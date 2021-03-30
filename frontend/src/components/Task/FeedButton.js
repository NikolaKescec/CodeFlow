import "../../styles/feed.css";
import { HiOutlineSelector } from "react-icons/hi";

const FeedButton = ({ name, link, selectFunction, activeElement, middle }) => {
  const isActive = () => {
    if (link === activeElement) {
      return true;
    }
    return false;
  };

  const setClass = () => {
    if (isActive()) {
      return "feed-button-active";
    }
    return "feed-button-inactive";
  };

  const isMiddle = () => {
    if (middle) {
      return "feed-button-middle";
    }
  };

  return (
    <div
      className={"feed-button flex-fill " + setClass() + " " + isMiddle()}
      onClick={() => selectFunction(link)}
    >
      {isActive() && <HiOutlineSelector></HiOutlineSelector>}
      {name.toUpperCase()}
      {isActive() && <HiOutlineSelector></HiOutlineSelector>}
    </div>
  );
};

export default FeedButton;
