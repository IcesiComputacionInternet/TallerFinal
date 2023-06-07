// @ts-ignore
import React, { useState } from "react";
import '../style/form.css'
import { NavigateFunction, useNavigate } from "react-router-dom";

const SignUp = () => {
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [address, setAddress] = useState("");
    const [birthday, setBirthday] = useState("");
    // const [rol, setRol] = useState("");
    const [password, setPassword] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: any) => {
        event.preventDefault();
    };

    const cancelSignUp = () => {
        navigation("/");
    }

    return (
        <div className="container">
            <form onSubmit={handleSubmit} className='signup-form'>
                <h3>Sign Up</h3>
                <div className="form-group">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Name"
                        value={name}
                        onChange={(event) => setName(event.target.value)}
                    />
                    </div>
                    <div className="form-group">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Last Name"
                            value={lastName}
                            onChange={(event) => setLastName(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <input required
                            type="email"
                            className="form-control"
                            placeholder="Email"
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <input required
                            type="tel"
                            className="form-control"
                            placeholder="Phone Number"
                            value={phoneNumber}
                            onChange={(event) => setPhoneNumber(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="date"
                            className="form-control"
                            placeholder="Birthdate"
                            value={birthday}
                            onChange={(event) => setBirthday(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Address"
                            value={address}
                            onChange={(event) => setAddress(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <input required
                            type="password"
                            className="form-control"
                            placeholder="Password"
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                        />
                    </div>
                    <div className="button-container">
                        <button className="btn btn-primary">Sign Up</button>
                        <button className="btn btn-outline-danger" onClick={cancelSignUp}>Cancel</button>
                    </div>
            </form>
        </div>
    );
};
export default SignUp;