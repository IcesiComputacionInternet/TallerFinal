import React, { useState } from 'react';
import { AppBar, Toolbar, Typography, Button, IconButton, Badge } from '@mui/material';
import { AccountCircle, ShoppingCart } from '@mui/icons-material';
import UserProfile from './UserProfile';

function Navbar() {
    
    const [showProfile, setShowProfile] = useState(false);
    
    const user = {
        firstName: "John",
        lastName: "Doe",
        email: "john@example.com",
        phoneNumber: "+123456789",
        address: "123 Main St, City, Country",
        birthday: new Date(1990, 0, 1), // January 1, 1990
        isLoggedIn: true
      };

    const handleLogout = () => {
        console.log('Logged out');
    };

    const handleProfile = () => {
        setShowProfile(true)
    };

    const handleCart = () => {
        console.log('Go to shopping cart');
    };

    return (
        <AppBar position="static">
        <Toolbar>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                Book Shop
            </Typography>

            {user.isLoggedIn ? (
            <div>
                <Button color="inherit" startIcon={<AccountCircle />} onClick={handleProfile}>
                    {user.firstName + ' ' + user.lastName}
                </Button>
                {showProfile && <UserProfile user={user} isVisible={setShowProfile}/>}
                <Button color="inherit" onClick={handleLogout}>
                    Logout
                </Button>
            </div>
            ) : (
            <Button color="inherit" startIcon={<AccountCircle />} onClick={handleProfile}>
                Login
            </Button>
            )}

            <IconButton color="inherit" onClick={handleCart}>
            <Badge badgeContent={1} color="error">
                <ShoppingCart />
            </Badge>
            </IconButton>
        </Toolbar>
        </AppBar>
    );
}

export default Navbar;