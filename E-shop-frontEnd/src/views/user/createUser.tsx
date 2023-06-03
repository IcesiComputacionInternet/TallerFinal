import UtilServices from "../../services/utilServices";
import Header from "../utils/header";
import Footer from "../utils/footer";
import UserForm from "./userForm";

const CreateUser = () => {
  UtilServices.setTitlePage("E-Shop | Login");
  return (
    <div className="main">
      <Header />
      <div className="container">
        <UserForm />
      </div>
      <Footer />
    </div>
  );
};

export default CreateUser;
