import type { NextPage } from 'next'
import styles from '../styles/Index.module.css'
import myImage from '../resources/logo.png'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Divider  from '@mui/material/Divider'
import { createTheme } from '@mui/material'
import { ThemeProvider } from '@mui/material/styles'
import Router from 'next/router'

const theme = createTheme({
  palette: {
    primary: {
      main: "#022b3a"
    },
    secondary: {
      main: "#1F7A8C"
    }
  }
})


const Home: NextPage = () => {
  //const navigation : NavigateFunction = useNavigate(); 
  const handleRegister = () => {
    Router.push("/register");
  }
  const handleLogin = () => {
    Router.push("/home");
  }

  return (
    <div className={styles.flexContainer}>
      <ThemeProvider theme={theme}>
      <div className={styles.section1}>
        <div>
          <h1>Bienvenido</h1>
        </div>
        <div className={styles.form}>
          <div>
            <h4 style={{margin:0}}>Username</h4>
              <TextField id="outlined-basic" 
              label="Username" 
              variant="outlined" 
              sx={{maxWidth: 250}} 
              size='small' 
              />
          </div>
          <div>
            <h4 style={{margin:0}}>Password</h4>
            <TextField 
            id="outlined-basic" 
            label="Password" 
            variant="outlined" 
            sx={{maxWidth: 250}} 
            size='small'             
            type='password'/>
          </div>
            
            
        </div>
        <Divider sx={{marginTop: 2, marginBottom: 2}}/>
        <div className={styles.buttons}>  
          <Button 
            variant="contained" 
            sx={{maxWidth: 170, minWidth: 100}} 
            color="primary"
            onClick={handleLogin}>Log In</Button>
          <Button 
            variant="outlined" 
            sx={{maxWidth: 170, marginLeft:5, minWidth: 100}} 
            color="secondary"
            onClick={handleRegister}>Register</Button>
        </div>
        <button onClick={() => window.location.href = "/dev"}>dev</button>
      </div>
      <div className={styles.section2}>
        <div className={styles.logo}>
          <img src={myImage.src} alt="Logo" width={150} height={150}/>
        </div>
        <div>
          <h1>TV-EShop.com</h1>
        </div>
      </div>
      </ThemeProvider>
    </div>
    
  )
}

export default Home
