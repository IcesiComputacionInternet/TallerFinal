import { useState } from 'react'
import { NavigateFunction, useNavigate} from "react-router-dom";

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
                            <div className='form-group my-2 form-floating'>
                                <input 
                                    type="email"
                                    className='form-control'
                                    placeholder='Email'
                                    value={emailPhone}
                                    onChange={(event) => setEmailPhone(event.target.value)} 
                                />
                                <label>Email</label>
                            </div>
                            <div className='form-group my-2 form-floating'>
                                <input
                                    type="password"
                                    className='form-control'
                                    placeholder='Password'
                                    value={password}
                                    onChange={(event) => setPassword(event.target.value)}
                                />
                                <label>Password</label>
                            </div>
                            <button 
                                type='submit'
                                className='btn btn-primary btn-block my-2'
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