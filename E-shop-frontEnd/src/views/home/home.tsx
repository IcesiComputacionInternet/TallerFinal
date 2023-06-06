import OptionsAdmin from "../admin/optionsAdmin";
import OptionsStore from "../store/optionStore";
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
        <></>
      )}
      {role === '"Store"' ? (
        <>
          <OptionsStore></OptionsStore>
        </>
      ) : (
        <>
          <div>No sos ni admin ni tienda chota</div>
        </>
      )}
      <Footer />
    </>
  );
};

export default Home;
