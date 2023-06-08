// @ts-ignore
import React, { useState } from "react";
// import axios from "axios";
// import { NavigateFunction, useNavigate } from "react-router-dom";
import '../style/form.css'

const baseUrl = "http://localhost:8080";


const Roles = () => {
    const [roleName, setRoleName] = useState("");
    const [roleDescription, setRoleDescription] = useState("");
    // const navigation : NavigateFunction = useNavigate();

    // const handleSubmit = async (event: any) => {
    //     event.preventDefault();
    //
    //     try {
    //         const { data } = await axios.post(
    //             baseUrl + "/token",
    //             {
    //                 username,
    //                 password,
    //             },
    //             {
    //                 headers: {
    //                     "Access-Control-Allow-Origin": baseUrl,
    //                 },
    //             }
    //         );
    //         if (data.token) {
    //             localStorage.setItem("jwt", data.token);
    //             localStorage.setItem("userEmail", username);
    //             setLogin();
    //             navigation("/");
    //         }
    //     } catch (error) {
    //         alert("Invalid username or password")
    //         navigation("/*");
    //     }
    //
    // };

    return (
        <div className="signUp-container">
            <form className="signup-form" style={{marginTop: "100PX"}}>
                <h3>New role</h3>
                <div className="input-container" >
                    <input required
                           type="text"
                           className="input-field"
                           placeholder="Name"
                           value={roleName}
                           onChange={(event) => setRoleName(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Name</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="input-container">
                    <textarea required
                           className="input-field"
                              placeholder="Description"
                              value={roleDescription}
                              onChange={(event) => setRoleDescription(event.target.value)}
                    />
                    <label htmlFor="input-field" className="input-label">Description</label>
                    <span className="input-highlight"></span>
                </div>

                <div className="button-container">
                    <button className="btn btn-primary form-button">Done</button>
                    <button className="btn btn-outline-danger form-button" >Cancel</button>
                </div>
            </form>
        </div>
    );
};



export default Roles;