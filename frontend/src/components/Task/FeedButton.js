import "../../styles/feed.css";
import { HiOutlineSelector } from "react-icons/hi";

const FeedButton = ({ text, selectFunction, activeElement, middle }) => {
  const isActive = () => {
    if (text === activeElement) {
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
      onClick={() => selectFunction(text)}
    >
      {isActive() && <HiOutlineSelector></HiOutlineSelector>}
      {text}
      {isActive() && <HiOutlineSelector></HiOutlineSelector>}
    </div>
  );
};

export default FeedButton;
