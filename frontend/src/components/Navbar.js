import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';

function Navbar({ children }) {
  return (
    <Box>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Gradebook
          </Typography>
          <Button color="inherit" component={Link} to="/razred" sx={{ mr: 2 }}>
            Popis razreda
          </Button>
          <Button color="inherit" component={Link} to="/predmet" sx={{ mr: 2 }}>
            Predmeti
          </Button>
        </Toolbar>
      </AppBar>
      <Box p={3}>{children}</Box>
    </Box>
  );
}

export default Navbar;
