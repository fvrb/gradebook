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
  TextField,
  Typography,
  Paper
} from '@mui/material';

function PredmetiSifrarnik() {
  const [predmeti, setPredmeti] = useState([]);
  const [filter, setFilter] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/predmet').then(res => setPredmeti(res.data));
  }, []);

  const filteredPredmeti = predmeti.filter(p =>
    p.oznaka.toLowerCase().includes(filter.toLowerCase()) ||
    p.naziv.toLowerCase().includes(filter.toLowerCase())
  );

  const deletePredmet = (id) => {
    if (window.confirm("Jeste li sigurni?")) {
      axios.delete(`http://localhost:8080/predmet/${id}`).then(() => {
        setPredmeti(predmeti.filter(p => p.id !== id));
      });
    }
  };

  return (
    <Box p={4}>
      <Typography variant="h5" gutterBottom>Šifrarnik predmeta</Typography>

      <Box display="flex" alignItems="center" gap={2} mb={2}>
        <TextField
          label="Filtriraj"
          value={filter}
          onChange={e => setFilter(e.target.value)}
          variant="outlined"
          size="small"
        />
        <Button variant="contained" color="primary" onClick={() => navigate('/predmet/novi')}>
          Dodaj predmet
        </Button>
      </Box>

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
            {filteredPredmeti.map(predmet => (
              <TableRow key={predmet.id}>
                <TableCell>{predmet.oznaka}</TableCell>
                <TableCell>{predmet.naziv}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="warning"
                    onClick={() => navigate(`/predmet/${predmet.id}`)}
                    sx={{ mr: 1 }}
                  >
                    Uredi
                  </Button>
                  <Button
                    variant="outlined"
                    color="error"
                    onClick={() => deletePredmet(predmet.id)}
                  >
                    Obriši
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

export default PredmetiSifrarnik;
