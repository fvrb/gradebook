import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import PredmetiSifrarnik from './components/PredmetiSifrarnik';
import PredmetForm from './components/PredmetForm';
import PopisRazreda from './components/PopisRazreda';
import RazredUcenici from './components/RazredUcenici';
import UcenikDetail from './components/UcenikDetail';
import OcjenaForm from './components/OcjenaForm';
import Navbar from './components/Navbar'


function App() {
  return (
    <Router>
      <Navbar>
        <Routes>
          <Route path="/razred" element={<PopisRazreda />} />
          <Route path="/razred/:id/ucenik" element={<RazredUcenici />} />
          <Route path="/razred/:id/ucenik/:ucenikId" element={<UcenikDetail />} />
          <Route path="/razred/:id/ucenik/:ucenikId/ocjena/:ocjenaId" element={<OcjenaForm />} />
          <Route path="/predmet" element={<PredmetiSifrarnik />} />
          <Route path="/predmet/novi" element={<PredmetForm />} />
          <Route path="/predmet/:id" element={<PredmetForm />} />
          <Route path="*" element={<Navigate to="/predmet" />} />
        </Routes>
      </Navbar>
    </Router>
  );
}

export default App;