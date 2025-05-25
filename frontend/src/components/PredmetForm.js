import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper
} from '@mui/material';

function PredmetForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [oznaka, setOznaka] = useState('');
  const [naziv, setNaziv] = useState('');

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:8080/predmet/${id}`).then(res => {
        setOznaka(res.data.oznaka);
        setNaziv(res.data.naziv);
      });
    }
  }, [id]);

  const handleSubmit = e => {
    e.preventDefault();
    const data = { oznaka, naziv };
    const request = id
      ? axios.put(`http://localhost:8080/predmet/${id}`, data)
      : axios.post('http://localhost:8080/predmet', data);

    request.then(() => navigate('/predmet'));
  };

  return (
    <Box p={4} maxWidth={500} mx="auto">
      <Paper elevation={3} sx={{ p: 3 }}>
        <Typography variant="h6" gutterBottom>
          {id ? 'Uredi predmet' : 'Dodaj predmet'}
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Oznaka"
            value={oznaka}
            onChange={e => setOznaka(e.target.value)}
            fullWidth
            required
            margin="normal"
          />
          <TextField
            label="Naziv"
            value={naziv}
            onChange={e => setNaziv(e.target.value)}
            fullWidth
            required
            margin="normal"
          />
          <Box display="flex" justifyContent="flex-end" gap={2} mt={2}>
            <Button type="submit" variant="contained" color="success">
              {id ? 'Spremi' : 'Dodaj'}
            </Button>
            <Button
              type="button"
              variant="outlined"
              onClick={() => navigate('/predmet')}
            >
              Natrag
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
}

export default PredmetForm;
