import { useState } from 'react'
import { NavigateFunction, useNavigate} from "react-router-dom";

import { login } from '../../../services/login';

import { faUser,
        faLock } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface Props {
    setLogin: () => void;
    setInfoToast: (message: string, title: string) => void;
}

const Login = ({setLogin, setInfoToast}: Props) => {
    const [emailOrPhone, setEmailOrPhone] = useState("");
    const [password, setPassword] = useState("");
    const [emailOrPhoneError, setEmailOrPhoneError] = useState(false);
    const [passwordError, setPasswordError] = useState(false);

    const navigation: NavigateFunction = useNavigate();
    
    const handleSubmit = async (event: any) => {
        event.preventDefault();
        await login(emailOrPhone, password).then((result) => {
            if(result){
                setLogin();
                navigation("/");
                setInfoToast("You have successfully logged in!", "Success");
            }
        }).catch((error) => {
            setInfoToast(error.message, "Error");
        });
    };

    const handleEmailOrPhoneChange = (event: any) => {
        setEmailOrPhone(event.target.value);
    };

    const handlePasswordChange = (event: any) => {
        setPassword(event.target.value);
    };
        
    const handleEmailOrPhoneBlur = () => {
        if (emailOrPhone === "") {
            setEmailOrPhoneError(true);
        } else {
            setEmailOrPhoneError(false);
        }
    };

    const handlePasswordBlur = () => {
        if (password === "") {
            setPasswordError(true);
        } else {
            setPasswordError(false);
        }
    };

    return (
        <div className='container d-flex align-items-center'>
            <div className='row justify-content-center w-100'>
                <div className='card'>
                    <div className='card-body text-center'>
                        <h2 className='card-title'>Login</h2>
                        <form onSubmit={handleSubmit}>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faUser} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input 
                                            type="text"
                                            className={`form-control ${emailOrPhoneError ? 'is-invalid' : ''}`}
                                            placeholder='Email or Phone'
                                            value={emailOrPhone}
                                            onChange={handleEmailOrPhoneChange} 
                                            onBlur={handleEmailOrPhoneBlur}
                                        />
                                        <label>Email or Phone</label>
                                    </div>
                                </li>
                            </ul>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faLock} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input
                                            type="password"
                                            className={`form-control ${passwordError ? 'is-invalid' : ''}`}
                                            placeholder='Password'
                                            value={password}
                                            onChange={handlePasswordChange}
                                            onBlur={handlePasswordBlur}
                                        />
                                        <label>Password</label>
                                    </div>
                                </li>
                            </ul>
                            <button 
                                type='submit'
                                className='btn btn-primary btn-block'
                            >
                                Login
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;