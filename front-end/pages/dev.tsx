import Router from "next/router";

export default function Dev() {
    const handleAdmin = () => {
        localStorage.setItem('role', 'admin');
        window.location.href = '/home';
    }
    const handleUser = () => {
        localStorage.setItem('role', 'user');
        window.location.href = '/home';
    }
    const handleClean = () => {
        localStorage.clear();
        window.location.href = '/';
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