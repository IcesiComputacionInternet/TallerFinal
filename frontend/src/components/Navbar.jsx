import React from 'react';
import { AppBar, Toolbar, Typography, Button, IconButton, Badge } from '@mui/material';
import { AccountCircle, ShoppingCart } from '@mui/icons-material';

function Navbar() {
  // Sample user identification
  const user = {
    name: 'John Doe',
    isLoggedIn: true
  };

  const handleLogout = () => {
    console.log('Logged out');
  };

  const handleProfile = () => {
    console.log('Go to profile');
  };

  const handleCart = () => {
    console.log('Go to shopping cart');
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          My App
        </Typography>

        {user.isLoggedIn ? (
          <div>
            <Button color="inherit" startIcon={<AccountCircle />} onClick={handleProfile}>
              {user.name}
            </Button>
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
          <Badge badgeContent={3} color="error">
            <ShoppingCart />
          </Badge>
        </IconButton>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;