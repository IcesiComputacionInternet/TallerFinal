import LoginForm from "./components/LoginForm";

interface Props {
    setLogin: () => void;
}

function AuthView ({setLogin}: Props) {
    return (
        
        <div>
            <header className="p-3 bg-dark text-white" style={{height:'12vh'}}>
                <div className="container d-flex align-items-center justify-content-center">
                    <span className="text-center display-6"><strong>eShop Automoviles</strong></span>
                </div>
            </header>
            <LoginForm setLogin={setLogin}/>
            
        </div>
    )
}

export default AuthView;