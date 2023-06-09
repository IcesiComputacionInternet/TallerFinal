// @ts-ignore
import React, { useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import '../style/form.css'
import axios from "axios";
// import {NavigateFunction, useNavigate} from "react-router-dom";

const baseUrl = "http://localhost:8080";
interface RoleDTO {
    roleName : string;
    description : string;
}

const Roles = () => {
    const [roleName, setRoleName] = useState("");
    const [roleDescription, setRoleDescription] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        try {
            const roleDTO: RoleDTO = {
                roleName : roleName,
                description : roleDescription
            };

            const roleCtx = (roleDTO: RoleDTO) => {
                return axios.post(baseUrl + "/roles/create", roleDTO, {
                    headers: {
                        "Access-Control-Allow-Origin": baseUrl,
                        "MediaType": "application/json",
                        "Authorization": "Bearer " + localStorage.getItem('jwt')
                    }
                });
            }

            const response = await roleCtx(roleDTO);
            console.log(response.data)
            if (response.status == 200) {
                alert("Success");
                navigation("/store");
            }

        } catch (error) {
            alert("Error creating new role");
        }
    };

    const cancelNewRole = () => {
        localStorage.removeItem("jwt");
        localStorage.removeItem("userEmail");
        localStorage.setItem("logged_user", JSON.stringify(false));
        navigation("/");
    }

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
                    <button className="btn btn-primary form-button" onClick={handleSubmit}>Done</button>
                    <button className="btn btn-outline-danger form-button" onClick={cancelNewRole}>Cancel</button>
                </div>
            </form>
        </div>
    );
};

export default Roles;