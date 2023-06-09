// @ts-ignore
import React, { useState } from "react";
import '../style/form.css'
import { NavigateFunction, useNavigate } from "react-router-dom";
import axios from "axios";


interface RequestUserDTO {
    firstName ?: string;
    lastName ?: string;
    email: string;
    password: string;
    phoneNumber: string;
    address ?: string;
    birthday ?: string;
    role: string;
}


const baseUrl = "http://localhost:8080";

const SignUp = () => {
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [address, setAddress] = useState("");
    const [birthday, setBirthday] = useState("");
    const [password, setPassword] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        try {
            const requestUserDTO: RequestUserDTO = {
                firstName: name,
                lastName: lastName,
                email: email,
                password: password,
                phoneNumber: phoneNumber,
                address: address,
                birthday: birthday,
                role: "USER",
            };

            console.log(requestUserDTO)

            const userCtx = (requestUserDTO: RequestUserDTO) => {
                return axios.post(baseUrl + "/users/create", requestUserDTO, {
                headers: {
                    "Access-Control-Allow-Origin": baseUrl,
                    "MediaType": "application/json"
                }
            });
        }

        const response = await userCtx(requestUserDTO);
            console.log(response.data)
            if (response.status == 200) {
                alert("Success");
                navigation("/");
            }

        } catch (error) {
            alert("Error signing up");
        }
    };

    const cancelSignUp = () => {
        localStorage.removeItem("jwt");
        localStorage.removeItem("userEmail");
        localStorage.setItem("logged_user", JSON.stringify(false));
        navigation("/");
    }

    return (
        <div className="signUp-container">
            <form onSubmit={handleSubmit} className='signup-form'>
                <h3>Sign Up</h3>
                <div className="input-container">
                    <input required
                           type="text"
                           className="input-field"
                           placeholder="Name"
                           value={name}
                           onChange={(event) => setName(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Name</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <input required
                           type="text"
                           className="input-field"
                           placeholder="Last Name"
                           value={lastName}
                           onChange={(event) => setLastName(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Last Name</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <input required
                           type="email"
                           className="input-field"
                           placeholder="Email"
                           value={email}
                           onChange={(event) => setEmail(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Email</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <input required
                           type="tel"
                           className="input-field"
                           placeholder="Phone Number"
                           value={phoneNumber}
                           onChange={(event) => setPhoneNumber(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Phone Number</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <input required
                           type="date"
                           className="input-field"
                           placeholder="Birthdate"
                           value={birthday}
                           onChange={(event) => setBirthday(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Birth Date</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <input required
                           type="text"
                           className="input-field"
                           placeholder="Address"
                           value={address}
                           onChange={(event) => setAddress(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Address</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <input required
                           type="password"
                           className="input-field"
                           placeholder="Password"
                           value={password}
                           onChange={(event) => setPassword(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Password</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="button-container">
                    <button className="btn btn-primary form-button" type="button" onClick={handleSubmit}>Sign Up</button>
                    <button className="btn btn-outline-danger form-button" onClick={cancelSignUp}>Cancel</button>
                </div>
            </form>
        </div>
    );
};
export default SignUp;