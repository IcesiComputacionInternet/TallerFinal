import OptionsAdmin from "../admin/optionsAdmin";
import OptionsStore from "../store/optionStore";
import Header from "../utils/header";
import Footer from "../utils/footer";
import ViewUser from "./viewUser";

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
        <></>
      )}
      <ViewUser />
      <Footer />
    </>
  );
};

export default Home;
