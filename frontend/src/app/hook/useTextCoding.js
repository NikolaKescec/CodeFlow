const useTextCoding = () => {
  const encode = (str) => {
    return btoa(unescape(encodeURIComponent(str || "")));
  };

  const decode = (bytes) => {
    var escaped = escape(atob(bytes || ""));
    try {
      return decodeURIComponent(escaped);
    } catch {
      return unescape(escaped);
    }
  };

  return [encode, decode];
};

export default useTextCoding;
