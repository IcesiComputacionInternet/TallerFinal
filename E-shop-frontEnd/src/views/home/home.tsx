const Home = () => {
  const role = localStorage.getItem("role");
  return <>{role === "ADMIN" ? <p>Hola</p> : <p>No sos Admin chota</p>}</>;
};

export default Home;
