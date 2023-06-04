import { useState } from 'react'
import { NavigateFunction, useNavigate} from "react-router-dom";

interface Props {
    setLogin: () => void;
}

const Login = ({setLogin}: Props) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigation: NavigateFunction = useNavigate();
    
    const handleSubmit = async (event: any) => {
        event.preventDefault();
        setLogin();
        navigation("/home");
    };

    return (
        <div className='container d-flex align-items-center' style={{ height: "78vh"}}>
            <div className='row justify-content-center w-100'>
                <div className='col-md-6'>
                    <div className='card'>
                        <div className='card-body text-center'>
                            <h3 className='card-title'>Login</h3>
                            <form onSubmit={handleSubmit}>
                                <div className='form-group my-2'>
                                    <input 
                                        type="text"
                                        className='form-control'
                                        placeholder='Username'
                                        value={username}
                                        onChange={(event) => setUsername(event.target.value)} 
                                    />
                                </div>
                                <div className='form-group my-2'>
                                    <input
                                        type="password"
                                        className='form-control'
                                        placeholder='Password'
                                        value={password}
                                        onChange={(event) => setPassword(event.target.value)}
                                    />
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
        </div>
    );
};

export default Login;