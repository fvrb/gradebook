import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  MenuItem,
  Grid
} from '@mui/material';

function OcjenaForm() {
  const { id: razredId, ucenikId, ocjenaId } = useParams();
  const navigate = useNavigate();
  const novi = ocjenaId === "nova";

  const [vrijednost, setVrijednost] = useState('');
  const [vrsta, setVrsta] = useState('');
  const [komentar, setKomentar] = useState('');
  const [datum, setDatum] = useState('');
  const [predmetId, setPredmetId] = useState('');
  const [nastavnikId, setNastavnikId] = useState('');

  const [predmeti, setPredmeti] = useState([]);
  const [nastavnici, setNastavnici] = useState([]);

  useEffect(() => {
    if (!novi) {
      axios.get(`http://localhost:8080/ocjena/${ocjenaId}`).then(res => {
        const o = res.data;
        setVrijednost(o.vrijednost);
        setVrsta(o.vrsta);
        setKomentar(o.komentar);
        setDatum(o.datum);
        setPredmetId(o.predmet.id);
        setNastavnikId(o.nastavnik.id);
      });
    }

    axios.get('http://localhost:8080/predmet').then(res => setPredmeti(res.data));
    axios.get('http://localhost:8080/nastavnik').then(res => setNastavnici(res.data));
  }, [ocjenaId]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      vrijednost: parseInt(vrijednost),
      vrsta,
      komentar,
      datum,
      ucenikId: parseInt(ucenikId),
      predmetId: parseInt(predmetId),
      nastavnikId: parseInt(nastavnikId),
    };

    const request = novi
      ? axios.post('http://localhost:8080/ocjena', data)
      : axios.put(`http://localhost:8080/ocjena/${ocjenaId}`, data);

    request
        .then(() => alert("Ocjena uspješno spremljena!"))
        .catch((e) => alert("Kriteriji za spremanje ocjene nisu ispunjeni."));
  };

  return (
    <Box p={4} mx="auto">
      <Paper elevation={3} sx={{ p: 3 }}>
        <Typography variant="h6" gutterBottom>
          {novi ? 'Dodaj ocjenu' : 'Uredi ocjenu'}
        </Typography>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item size={6}>
              <TextField
                label="Predmet"
                select
                fullWidth
                required
                value={predmetId}
                onChange={(e) => setPredmetId(e.target.value)}
              >
                {predmeti.map(p => (
                  <MenuItem key={p.id} value={p.id}>{p.naziv}</MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item size={6}>
              <TextField
                label="Nastavnik"
                select
                fullWidth
                required
                value={nastavnikId}
                onChange={(e) => setNastavnikId(e.target.value)}
              >
                {nastavnici.map(n => (
                  <MenuItem key={n.id} value={n.id}>{n.ime} {n.prezime}</MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item size={1}>
              <TextField
                label="Vrijednost"
                type="number"
                inputProps={{ min: 1, max: 5 }}
                value={vrijednost}
                onChange={(e) => setVrijednost(e.target.value)}
                fullWidth
                required
              />
            </Grid>
            <Grid item size={11}>
              <TextField
                label="Vrsta"
                value={vrsta}
                onChange={(e) => setVrsta(e.target.value)}
                fullWidth
                required
              />
            </Grid>
            <Grid item size={12}>
              <TextField
                label="Komentar"
                value={komentar}
                onChange={(e) => setKomentar(e.target.value)}
                fullWidth
                multiline
              />
            </Grid>
            <Grid item size={3}>
              <TextField
                label="Datum"
                type="date"
                value={datum}
                onChange={(e) => setDatum(e.target.value)}
                fullWidth
                required
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
          </Grid>

          <Box display="flex" justifyContent="flex-end" gap={2} mt={3}>
            <Button type="submit" variant="contained" color="success">
              {novi ? 'Dodaj' : 'Spremi'}
            </Button>
            <Button variant="outlined" onClick={() => navigate(`/razred/${razredId}/ucenik/${ucenikId}`)}>
              Natrag
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
}

export default OcjenaForm;
