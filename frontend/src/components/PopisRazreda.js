import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Box,
  Button,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
  Paper
} from '@mui/material';

function PopisRazreda() {
  const [razredi, setRazredi] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/razred').then(res => setRazredi(res.data));
  }, []);

  return (
    <Box p={4}>
      <Typography variant="h5" gutterBottom>Popis razreda</Typography>

      <Paper>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Oznaka</TableCell>
              <TableCell>Naziv</TableCell>
              <TableCell>Akcije</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {razredi.map(razred => (
              <TableRow key={razred.id}>
                <TableCell>{razred.oznaka}</TableCell>
                <TableCell>{razred.naziv}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => navigate(`/razred/${razred.id}/ucenik`)}
                  >
                    Pregled
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </Box>
  );
}

export default PopisRazreda;
