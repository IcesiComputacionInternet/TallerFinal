import { useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import helpHttp from "../helpers/helpHttp.js";

interface Props {
  setLogin: (value: boolean) => void;
}

const Login = ({ setLogin }: Props) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigation: NavigateFunction = useNavigate();

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const handleSubmit = async (e: any) => {
    e.preventDefault();
    const data = { username, password };
    const options = {
      body: data,
    };
    const api = helpHttp();
    api.post(`/token`, options).then((res: { token: string }) => {
      console.log(options);
      console.log(res);
      if (res.token) {
        localStorage.setItem("jwt", res.token);
        setLogin(true);
        navigation("/");
      } else {
        alert("Invalid username or password");
      }
    });
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title">Login</h3>
              <form onSubmit={handleSubmit}>
                <div className="form-group mb-3">
                  <label htmlFor="username" className="form-label">
                    Usuario
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="jhondoe@example.com"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>
                <div className="form-group mb-3">
                  <label htmlFor="password" className="form-label">
                    Password
                  </label>
                  <input
                    type="password"
                    className="form-control"
                    placeholder="************"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <button type="submit" className="btn btn-primary">
                  Login
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
