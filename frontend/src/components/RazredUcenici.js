import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
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

function RazredUcenici() {
  const { id: razredId } = useParams();
  const navigate = useNavigate();
  const [naslov, setNaslov] = useState({oznaka: "", naziv: ""});
  const [ucenici, setUcenici] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/razred/${razredId}`).then(res => {
    setNaslov({oznaka: res.data.oznaka, naziv: res.data.naziv});
    setUcenici(res.data.ucenici || []);
    });
  }, [razredId]);

  const deleteUcenik = (id) => {
    if (window.confirm("Jeste li sigurni?")) {
      axios.delete(`http://localhost:8080/ucenik/${id}`).then(() => {
        setUcenici(ucenici.filter(u => u.id !== id));
      });
    }
  };

  return (
    <Box p={4}>
      <Typography variant="h5" gutterBottom>Učenici razreda {naslov.naziv} ({naslov.oznaka})</Typography>

      <Box display="flex" alignItems="center" gap={2} mb={2}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate(`/razred/${razredId}/ucenik/novi`)}
        >
          Dodaj učenika
        </Button>
        <Button variant="outlined" onClick={() => navigate('/razred')}>
          Natrag na popis razreda
        </Button>
      </Box>

      <Paper>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Ime</TableCell>
              <TableCell>Prezime</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Telefon</TableCell>
              <TableCell>Datum rođenja</TableCell>
              <TableCell>Akcije</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {ucenici.map(ucenik => (
              <TableRow key={ucenik.id}>
                <TableCell>{ucenik.ime}</TableCell>
                <TableCell>{ucenik.prezime}</TableCell>
                <TableCell>{ucenik.email}</TableCell>
                <TableCell>{ucenik.telefon}</TableCell>
                <TableCell>{ucenik.dob}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="warning"
                    onClick={() => navigate(`/razred/${razredId}/ucenik/${ucenik.id}`)}
                    sx={{ mr: 1 }}
                  >
                    Pregled
                  </Button>
                  <Button
                    variant="outlined"
                    color="error"
                    onClick={() => deleteUcenik(ucenik.id)}
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

export default RazredUcenici;
