// @ts-ignore
import React, { useState } from "react";

const SignUp = () => {
    const [emailOrphoneNumber, setEmailOrphoneNumber] = useState("");
    // const [rol, setRol] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        // console.log(name, email, password);
    };

    return (
            <div className="container">
                <div className="row justify-content-center">
                    <div className= "col-md-6">
                        <div className= "card text-center mb-3">
                            <div className="card-body">
                                <h3 className="card-title">Sign Up</h3>
                                <form onSubmit={handleSubmit}>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="Email or Phone Number"
                                            value={emailOrphoneNumber}
                                            onChange={(event) => setEmailOrphoneNumber(event.target.value)}
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
                    </div>
                </div>
            </div>
    );
};
export default SignUp;