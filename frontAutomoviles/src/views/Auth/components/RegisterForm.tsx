import { useState } from 'react';

const RegisterForm = () => {
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event: any) => {
    event.preventDefault();
  };

  return (
    <div className="container d-flex align-items-center">
      <div className="row justify-content-center w-100">
          <div className="card">
            <div className="card-body text-center">
              <h3 className="card-title">Register</h3>
              <form onSubmit={handleSubmit}>
                <div className="form-group my-2 form-floating">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                  />
                  <label htmlFor='email'>Email</label>
                </div>
                <div className="form-group my-2 form-floating">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Phone"
                    value={phone}
                    onChange={(event) => setPhone(event.target.value)}
                  />
                  <label htmlFor='phone'>Phone</label>
                </div>
                <div className="form-group my-2">
                  <input
                    type="password"
                    className="form-control"
                    placeholder="Password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                  />
                </div>
                <button type="submit" className="btn btn-primary btn-block my-2">
                  Register
                </button>
              </form>
            </div>
          </div>
      </div>
    </div>
  );
};

export default RegisterForm;
