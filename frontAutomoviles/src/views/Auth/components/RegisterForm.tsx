import { useState } from 'react';

import {faEnvelope,
        faPhone,
        faLock,
        faLocationDot,
        faCalendar,
        faAddressCard } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { register } from '../../../services/register';

interface Props {
    setViewLogin: () => void;
}

const Register = ({setViewLogin} : Props) => {
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const [userCredError, setUserCredError] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const [confirmPasswordError, setConfirmPasswordError] = useState(false);

    const [address, setAddress] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthDate, setBirthDate] = useState('');

    const handleSubmit = async (event: any) => {
        event.preventDefault();
       
        const userInfo = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            address: address,
            password: password,
          };
        register(userInfo).then((result) => {
            if(result){
                console.log('Registered successfully');
                setViewLogin();
            }
        }).catch((error) => {
            console.log(error);
        });
    };

    const handleEmailChange = (event: any) => {
        setEmail(event.target.value);
    };

    const handleUserCredentialBlur = () => {
        if(email === '' && phone === ''){
            setUserCredError(true);
        }else{
            setUserCredError(false);
        }
    };

    const handlePasswordBlur = () => {
        if(password === ''){
            setPasswordError(true);
        }else{
            setPasswordError(false);
        }
    };

    const handleConfirmPasswordBlur = () => {
        if(confirmPassword === '' || password !== confirmPassword){
            setConfirmPasswordError(true);
        }else{
            setConfirmPasswordError(false);
        }
    }

    return (
        <div className='container d-flex align-items-center'>
            <div className='row justify-content-center w-100'>
                <div className='card'>
                    <div className='card-body text-center'>
                        <h2 className='card-title'>Register</h2>
                        <h6>Can register with email or/and phone</h6>
                        <form onSubmit={handleSubmit}>
                        <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faEnvelope} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input 
                                            type="email"
                                            className={`form-control ${userCredError ? 'is-invalid' : ''}`}
                                            placeholder='Email'
                                            value={email}
                                            onChange={handleEmailChange}
                                            onBlur={handleUserCredentialBlur}
                                        />
                                        <label>Email</label>
                                    </div>
                                </li>
                            </ul>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faPhone} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input 
                                            type="phone"
                                            className={`form-control ${userCredError ? 'is-invalid' : ''}`}
                                            placeholder='Phone'
                                            value={phone}
                                            onChange={(event) => setPhone(event.target.value)}
                                            onBlur={handleUserCredentialBlur}
                                        />
                                        <label>Phone</label>
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
                                            onChange={(event) => setPassword(event.target.value)}
                                            onBlur={handlePasswordBlur}
                                        />
                                        <label>Password</label>
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
                                            className={`form-control ${confirmPasswordError ? 'is-invalid' : ''}`}
                                            placeholder='Confirm Password'
                                            value={confirmPassword}
                                            onChange={(event) => setConfirmPassword(event.target.value)}
                                            onBlur={handleConfirmPasswordBlur}
                                        />
                                        <label>Confirm Password</label>
                                    </div>
                                </li>
                            </ul>

                            <h5 className='card-title'>Aditional Information</h5>

                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faAddressCard} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input
                                            type="text"
                                            className='form-control'
                                            placeholder='First Name'
                                            value={firstName}
                                            onChange={(event) => setFirstName(event.target.value)}
                                        />
                                        <label>First Name</label>
                                    </div>
                                </li>
                            </ul>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faAddressCard} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input
                                            type="text"
                                            className='form-control'
                                            placeholder='Last Name'
                                            value={lastName}
                                            onChange={(event) => setLastName(event.target.value)}
                                        />
                                        <label>Last Name</label>
                                    </div>
                                </li>
                            </ul>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faLocationDot} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input
                                            type="text"
                                            className='form-control'
                                            placeholder='Address'
                                            value={address}
                                            onChange={(event) => setAddress(event.target.value)}
                                        />
                                        <label>Address</label>
                                    </div>
                                </li>
                            </ul>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faCalendar} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input
                                            type="date"
                                            className='form-control picker__input'
                                            placeholder='Birth Date'
                                            value={birthDate}
                                            onChange={(event) => setBirthDate(event.target.value)}
                                        />
                                        <label>Birth Date</label>
                                    </div>
                                </li>
                            </ul>
                            <button type="submit" className="btn btn-primary btn-block">
                                Register
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Register;