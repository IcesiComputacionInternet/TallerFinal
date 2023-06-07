import { useState } from 'react'
import { NavigateFunction, useNavigate} from "react-router-dom";

import { faUser,
        faLock } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface Props {
    setLogin: () => void;
}

const Login = ({setLogin}: Props) => {
    const [emailPhone, setEmailPhone] = useState("");
    const [password, setPassword] = useState("");

    const navigation: NavigateFunction = useNavigate();
    
    const handleSubmit = async (event: any) => {
        event.preventDefault();
        setLogin();
        navigation("/home");
    };

    return (
        <div className='container d-flex align-items-center'>
            <div className='row justify-content-center w-100'>
                <div className='card'>
                    <div className='card-body text-center'>
                        <h3 className='card-title'>Login</h3>
                        <form onSubmit={handleSubmit}>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faUser} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input 
                                            type="email"
                                            className='form-control'
                                            placeholder='Email or Phone'
                                            value={emailPhone}
                                            onChange={(event) => setEmailPhone(event.target.value)} 
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
                                            className='form-control'
                                            placeholder='Password'
                                            value={password}
                                            onChange={(event) => setPassword(event.target.value)}
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