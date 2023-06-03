import OptionsAdmin from "../admin/optionsAdmin";
import Header from "../utils/header";
import Footer from "../utils/footer";

const Home = () => {
  var role: string | null = localStorage.getItem("role");

  return (
    <>
      <Header />
      {role === '"ADMIN"' ? (
        <>
          <OptionsAdmin />
        </>
      ) : (
        <p>No sos Admin chota</p>
      )}
      <Footer />
    </>
  );
};

export default Home;
