import { useState } from 'react';

import {faEnvelope,
        faPhone,
        faLock,
        faLocationDot,
        faCalendar,
        faAddressCard } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Register = () => {
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const [address, setAddress] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthDate, setBirthDate] = useState('');

    const handleSubmit = (event: any) => {
      event.preventDefault();
    };

    return (
        <div className='container d-flex align-items-center'>
            <div className='row justify-content-center w-100'>
                <div className='card'>
                    <div className='card-body text-center'>
                        <h3 className='card-title'>Register</h3>
                        <form onSubmit={handleSubmit}>
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
                                            type="text"
                                            className='form-control picker__input'
                                            placeholder='Birth Date'
                                            value={birthDate}
                                            onChange={(event) => setBirthDate(event.target.value)}
                                        />
                                        <label>Birth Date</label>
                                    </div>
                                </li>
                            </ul>
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faEnvelope} size='2x'/>
                                </li>
                                <li className="list-inline-item w-100">
                                    <div className='form-group form-floating'>
                                        <input 
                                            type="email"
                                            className='form-control'
                                            placeholder='Email'
                                            value={email}
                                            onChange={(event) => setEmail(event.target.value)} 
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
                                            className='form-control'
                                            placeholder='Phone'
                                            value={phone}
                                            onChange={(event) => setPhone(event.target.value)} 
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
                                            className='form-control'
                                            placeholder='Password'
                                            value={password}
                                            onChange={(event) => setPassword(event.target.value)}
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
                                            className='form-control'
                                            placeholder='Confirm Password'
                                            value={confirmPassword}
                                            onChange={(event) => setConfirmPassword(event.target.value)}
                                        />
                                        <label>Confirm Password</label>
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