import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import Login from '../views/login/login'

interface Props {
    children: React.ReactComponentElement<any>;
}

const ProtectedRoutes = ({children} : Props) => {
    if(localStorage.getItem('token') === null) {
        return <Navigate to="/login" />
    } else {
        return children;
    }
}

const ThemeRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<Login></Login>} />
            </Routes>
        </BrowserRouter>
    )
}

export default ThemeRoutes;

