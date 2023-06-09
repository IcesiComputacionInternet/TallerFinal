import { NextPage } from "next";
import styles from '../styles/Index.module.css'
import { Button, TextField } from "@mui/material";
import { createTheme } from '@mui/material'
import { ThemeProvider } from '@mui/material/styles'
import Router from 'next/router'
import axios from 'axios';
import { useRef } from "react";

const theme = createTheme({
    palette: {
      primary: {
        main: "#1F7A8C"
      },
      secondary: {
        main: "#022b3a"
        
      }
    }
  })

export default function register() {
    const firstNameRef = useRef("");
    const lastNameRef = useRef("");
    const emailRef = useRef("");
    const passwordRef = useRef("");
    const phoneNumRef = useRef("");
    const addressRef = useRef("");
    const birthRef = useRef("");
    
    const handleRegister = () => {
        const firstName = firstNameRef.current.value;
        const lastName = lastNameRef.current.value;
        const email = emailRef.current.value;
        const password = passwordRef.current.value;
        const phoneNum = phoneNumRef.current.value;
        const address = addressRef.current.value;
        const birth = birthRef.current.value;
        axios.post("http://localhost:9090/user", {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            phoneNumber: phoneNum,
            address: address,
            birthday: birth
        }).then(response => {
            console.log(response.data);
            alert("You have successfully registered!");
            window.location.href = "/";
        }).catch(error => {
            console.log(error);
            alert("Some information is missing or incorrect. Please try again.");
        });
    }


    const handleCancel = () => {
        Router.push("/");
    }

    return (
        <div className={styles.flexContainer}>
            <div className={styles.sideImage}>
                
            </div>
                <div className={styles.formSection}>
                    <h1 style={{marginLeft:45}}>Register</h1>
                    <div style={{display:"flex",flexGrow:1,flexDirection:"row"}}> 
                        <div className={styles.formColumn}>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>First name</h4>
                                <TextField id="outlined-basic" variant="outlined" size="small" sx={{marginBottom:2}} inputRef={firstNameRef}/>
                            </div>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>Last name</h4>
                                <TextField id="outlined-basic" variant="outlined" size="small" sx={{marginBottom:2}} inputRef={lastNameRef}/>
                            </div>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>E-Mail</h4>
                                <TextField id="outlined-basic"  variant="outlined" size="small" sx={{marginBottom:2}} inputRef={emailRef}/>
                            </div>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>Password</h4>
                                <TextField id="outlined-basic" variant="outlined" size="small" sx={{marginBottom:2}} type="password" inputRef={passwordRef}/>
                            </div>
                        </div>
                        <div className={styles.formColumn}>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>Phone Number</h4>
                                <TextField id="outlined-basic" variant="outlined" size="small" sx={{marginBottom:2}} inputRef={phoneNumRef}/>
                            </div>
                        <div className={styles.inputNLabel}>
                            <h4 className={styles.label}>Address</h4>
                            <TextField id="outlined-basic" variant="outlined" size="small" sx={{marginBottom:2}} inputRef={addressRef}/>
                        </div>
                        <div className={styles.inputNLabel}>
                            <h4 className={styles.label}>Birthday</h4>
                            <TextField  size="small" type="date" sx={{marginBottom:2}} inputRef={birthRef}/>
                        </div>
                    </div> 
                </div>   
                <ThemeProvider theme={theme}>
                    <div className={styles.registerButtons}>
                        <Button variant="contained" sx={{marginRight:2}} onClick={handleRegister}>Register</Button>
                        <Button variant="contained" sx={{marginLeft:2}} color="secondary" onClick={handleCancel}>Cancel</Button>
                    </div>    
                </ThemeProvider>
                
            </div>

        </div>
    )
}

