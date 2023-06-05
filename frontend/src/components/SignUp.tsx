// @ts-ignore
import React, { useState } from "react";

const SignUp = () => {
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [address, setAddress] = useState("");
    const [birthday, setBirthday] = useState("");
    // const [rol, setRol] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event: any) => {
        event.preventDefault();
    };

    return (
            <div className="container">
                            <div className="card-body">
                                <h3 className="card-title">Sign Up</h3>
                                <form onSubmit={handleSubmit}>
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
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="Email"
                                            value={email}
                                            onChange={(event) => setEmail(event.target.value)}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input
                                            type="text"
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
                                        <input
                                            type="password"
                                            className="form-control"
                                            placeholder="Password"
                                            value={password}
                                            onChange={(event) => setPassword(event.target.value)}
                                        />
                                    </div>
                                    <button type="submit" className="btn btn-primary" >
                                        Done
                                    </button>
                                </form>
                            </div>
                        </div>
    );
};
export default SignUp;