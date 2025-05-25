import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  MenuItem
} from '@mui/material';

function UcenikDetail() {
  const { id: razredId, ucenikId } = useParams();
  const navigate = useNavigate();

  let novi = ucenikId === "novi";

  const [ime, setIme] = useState('');
  const [prezime, setPrezime] = useState('');
  const [email, setEmail] = useState('');
  const [telefon, setTelefon] = useState('');
  const [dob, setDob] = useState('');
  const [ocjene, setOcjene] = useState([]);

  const [razredi, setRazredi] = useState([]);
  const [trenutniRaz, setTrenutniRaz] = useState(0);

  useEffect(() => {
    if (!novi) {
      axios.get(`http://localhost:8080/ucenik/${ucenikId}`).then(res => {
        const u = res.data;
        setIme(u.ime);
        setPrezime(u.prezime);
        setEmail(u.email);
        setTelefon(u.telefon);
        setDob(u.dob);
        setOcjene(u.ocjene);
      });
    }

    axios.get("http://localhost:8080/razred").then(res => {
      setRazredi(res.data);
    });

    setTrenutniRaz(parseInt(razredId));

  }, [ucenikId]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = { ime, prezime, email, telefon, dob, razredId: trenutniRaz };
    if (novi)
      axios.post(`http://localhost:8080/ucenik`, data)
        .then(() => alert('Novi učenik je dodan!'));
    else
      axios.put(`http://localhost:8080/ucenik/${ucenikId}`, data)
        .then(() => alert('Podaci učenika su spremljeni!'));
  };

  const deleteOcjena = (id) => {
    if (window.confirm("Jeste li sigurni?")) {
      axios.delete(`http://localhost:8080/ocjena/${id}`).then(() => {
        setOcjene(ocjene.filter(o => o.id !== id));
      });
    }
  };

  return (
    <Box p={4} mx="auto">
      <Paper elevation={3} sx={{ p: 3, mb: 4 }}>
        <Typography variant="h6" gutterBottom>
          {novi ? "Dodaj učenika" : "Uredi učenika"}
        </Typography>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item size={6}>
              <TextField
                label="Ime"
                value={ime}
                onChange={e => setIme(e.target.value)}
                fullWidth
                required
              />
            </Grid>
            <Grid item size={6}>
              <TextField
                label="Prezime"
                value={prezime}
                onChange={e => setPrezime(e.target.value)}
                fullWidth
                required
              />
            </Grid>
            <Grid item size={1}>
              <TextField
                select
                label="Razred"
                value={trenutniRaz}
                onChange={e => setTrenutniRaz(e.target.value)}
                fullWidth
                required
              >
                {razredi.map(r => (
                  <MenuItem key={r.id} value={r.id}>
                    {r.oznaka}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item size={5}>
              <TextField
                label="Telefon"
                value={telefon}
                onChange={e => setTelefon(e.target.value)}
                fullWidth
              />
            </Grid>
            <Grid item size={6}>
              <TextField
                label="Email"
                value={email}
                onChange={e => setEmail(e.target.value)}
                fullWidth
                type="email"
              />
            </Grid>
            <Grid item size={3}>
              <TextField
                label="Datum rođenja"
                type="date"
                value={dob}
                onChange={e => setDob(e.target.value)}
                fullWidth
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
          </Grid>

          <Box display="flex" justifyContent="flex-end" gap={2} mt={3}>
            <Button type="submit" variant="contained" color="success">Spremi</Button>
            <Button variant="outlined" onClick={() => navigate(`/razred/${razredId}/ucenik`)}>Natrag</Button>
          </Box>
        </form>
      </Paper>

      {!novi &&
      <Box>
        <Typography variant="h6" gutterBottom>Ocjene učenika</Typography>
        <Box display="flex" alignItems="center" gap={2} mb={2}>
          <Button
            variant="contained"
            color="primary"
            onClick={() => navigate(`/razred/${razredId}/ucenik/${ucenikId}/ocjena/nova`)}
          >
            Nova ocjena
          </Button>
      </Box>
        <Paper elevation={3}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Predmet</TableCell>
                <TableCell>Ocjena</TableCell>
                <TableCell>Vrsta</TableCell>
                <TableCell>Komentar</TableCell>
                <TableCell>Nastavnik</TableCell>
                <TableCell>Datum</TableCell>
                <TableCell>Akcije</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {ocjene.map(o => (
                <TableRow key={o.id}>
                  <TableCell>{o.predmet.naziv}</TableCell>
                  <TableCell>{o.vrijednost}</TableCell>
                  <TableCell>{o.vrsta}</TableCell>
                  <TableCell>{o.komentar}</TableCell>
                  <TableCell>{o.nastavnik.ime} {o.nastavnik.prezime}</TableCell>
                  <TableCell>{new Date(o.datum).toLocaleDateString()}</TableCell>
                  <TableCell>
                    <Button
                      variant="outlined"
                      color="warning"
                      onClick={() => navigate(`/razred/${razredId}/ucenik/${ucenikId}/ocjena/${o.id}`)}
                      sx={{ mr: 1 }}
                    >
                      Uredi
                    </Button>
                    <Button
                      variant="outlined"
                      color="error"
                      onClick={() => deleteOcjena(o.id)}
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
      }
    </Box>
  );
}

export default UcenikDetail;
