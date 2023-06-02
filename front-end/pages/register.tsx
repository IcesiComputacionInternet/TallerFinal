import { NextPage } from "next";
import styles from '../styles/Index.module.css'
import { Button, TextField } from "@mui/material";
import { createTheme } from '@mui/material'
import { ThemeProvider } from '@mui/material/styles'
import Router from 'next/router'

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

const Register:NextPage = () => {
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
                                <TextField id="outlined-basic" label="First Name" variant="outlined" size="small" sx={{marginBottom:2}}/>
                            </div>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>Last name</h4>
                                <TextField id="outlined-basic" label="Last Name" variant="outlined" size="small" sx={{marginBottom:2}}/>
                            </div>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>E-Mail</h4>
                                <TextField id="outlined-basic" label="E-Mail" variant="outlined" size="small" sx={{marginBottom:2}}/>
                            </div>
                        </div>
                        <div className={styles.formColumn}>
                            <div className={styles.inputNLabel}>
                                <h4 className={styles.label}>Phone Number</h4>
                                <TextField id="outlined-basic" label="Phone Number" variant="outlined" size="small" sx={{marginBottom:2}}/>
                            </div>
                        <div className={styles.inputNLabel}>
                            <h4 className={styles.label}>Address</h4>
                            <TextField id="outlined-basic" label="Address" variant="outlined" size="small" sx={{marginBottom:2}}/>
                        </div>
                        <div className={styles.inputNLabel}>
                            <h4 className={styles.label}>Birthday</h4>
                            <TextField  size="small" type="date" sx={{marginBottom:2}}></TextField>
                        </div>
                    </div> 
                </div>   
                <ThemeProvider theme={theme}>
                    <div className={styles.registerButtons}>
                        <Button variant="contained" sx={{marginRight:2}}>Register</Button>
                        <Button variant="contained" sx={{marginLeft:2}} color="secondary" onClick={handleCancel}>Cancel</Button>
                    </div>    
                </ThemeProvider>
                
            </div>

        </div>
    )
}

export default Register