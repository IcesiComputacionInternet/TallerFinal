const authHeader = () => {
  const token = localStorage.getItem("token");
  if (token) {
    const parsedToken = JSON.parse(token);
    return {
      Authorization: `Bearer ${parsedToken.jwt}`,
      "Content-Type": "application/json",
    };
  } else {
    return {};
  }
};

export default authHeader;
