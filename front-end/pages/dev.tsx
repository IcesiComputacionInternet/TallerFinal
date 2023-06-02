import Router from "next/router";

export default function Dev() {
    const handleAdmin = () => {
        localStorage.setItem('role', 'admin');
        Router.push('/home');
    }
    const handleUser = () => {
        localStorage.setItem('role', 'user');
        Router.push('/home');
    }
    const handleClean = () => {
        localStorage.clear();
        Router.push('/home');
    }
    return (
        <div>
        <h1>Dev</h1>
        <button onClick={handleAdmin}>Admin</button>
        <button onClick={handleUser}>User</button>
        <button onClick={handleClean}>Clean</button>
        </div>
    );
}