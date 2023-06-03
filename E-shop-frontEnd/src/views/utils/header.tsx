import Logo from "../../assets/logo_transparent_min.png";
import "./styles.css";

const Header = () => {
  return (
    <div className="header">
      <img src={Logo} alt="Logo" className="logo" />
    </div>
  );
};

export default Header;
