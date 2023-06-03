import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "../views/login/login";
import Home from "../views/home/home";
import CreateUser from "../views/user/createUser";

interface Props {
  children: React.ReactComponentElement<any>;
}

const ProtectedRoutes = ({ children }: Props) => {
  if (localStorage.getItem("token") === null) {
    return <Navigate to="/login" />;
  } else {
    return children;
  }
};

const IsNotLoggedRoutes = ({ children }: Props) => {
  if (localStorage.getItem("token") === null) {
    return children;
  } else {
    return <Navigate to="/" />;
  }
};

const ThemeRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/login"
          element={
            <IsNotLoggedRoutes>
              <Login></Login>
            </IsNotLoggedRoutes>
          }
        />
        <Route
          path="/"
          element={
            <ProtectedRoutes>
              <Home></Home>
            </ProtectedRoutes>
          }
        />
        <Route
          path="/sing_up"
          element={
            <IsNotLoggedRoutes>
              <CreateUser />
            </IsNotLoggedRoutes>
          }
        />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
};

export default ThemeRoutes;
