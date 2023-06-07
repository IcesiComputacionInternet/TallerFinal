// @ts-ignore
import React, { useState } from "react";
import '../style/form.css'
import { NavigateFunction, useNavigate } from "react-router-dom";

const CreateItem = () => {
    const [cellname, setCellName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [brand, setBrand] = useState("");
    const [storage, setStorage] = useState("");
    const [ram, setRam] = useState("");
    const [os, setOs] = useState("");
    const [frontCamera, setFrontCamera] = useState("");
    const [mainCamera, setMainCamera] = useState("");
    const [screenSize, setScreenSize] = useState("");
    const [stock, setStock] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: any) => {
        event.preventDefault();
    };

    const cancelNewItem = () => {
        navigation("/");
    }

    return (
        <body>
        <form className="new-item">
            <div className="row g-3">
                <h3>New Item</h3>
                <div className="col-md-6">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Name"
                        value={cellname}
                        onChange={(event) => setCellName(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Brand"
                        value={brand}
                        onChange={(event) => setBrand(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="1"
                        className="form-control"
                        placeholder="Price"
                        value={price}
                        onChange={(event) => setPrice(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="64"
                        className="form-control"
                        placeholder="Storage"
                        value={storage}
                        onChange={(event) => setStorage(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Operating System"
                        value={os}
                        onChange={(event) => setOs(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="2"
                        className="form-control"
                        placeholder="RAM"
                        value={ram}
                        onChange={(event) => setRam(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="1"
                        className="form-control"
                        placeholder="Front Camera"
                        value={frontCamera}
                        onChange={(event) => setFrontCamera(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="1"
                        className="form-control"
                        placeholder="Main Camera"
                        value={mainCamera}
                        onChange={(event) => setMainCamera(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="1"
                        className="form-control"
                        placeholder="Screen Size"
                        value={screenSize}
                        onChange={(event) => setScreenSize(event.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        type="number"
                        min="1"
                        className="form-control"
                        placeholder="Stock"
                        value={stock}
                        onChange={(event) => setStock(event.target.value)}
                    />
                </div>
                <div className="col-md-11">
                    <textarea
                        className="form-control"
                        placeholder="Description"
                        value={description}
                        onChange={(event) => setDescription(event.target.value)}
                    />
                </div>
                <div className="col-md-5">
                    <select id="inputState" className="form-select">
                        <option selected>Category</option>
                        <option>...</option>
                    </select>
                </div>
                </div>
            <div className="button-cont-item">
                <button className="btn btn-primary">Done</button>
                <button className="btn btn-outline-danger" onClick={cancelNewItem}>Cancel</button>
        </div>
        </form>
        </body>
    );
};
export default CreateItem;