import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import PrivateRoute from './components/PrivateRoute';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Patients from './pages/Patients';
import Medecins from './pages/Medecins';
import RendezVous from './pages/RendezVous';
import DossiersMedicaux from './pages/DossiersMedicaux';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        {/* Route publique */}
        <Route path="/login" element={<Login />} />

        {/* Routes protégées */}
        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <Dashboard />
            </PrivateRoute>
          }
        />
        <Route
          path="/patients"
          element={
            <PrivateRoute>
              <Patients />
            </PrivateRoute>
          }
        />
        <Route
          path="/medecins"
          element={
            <PrivateRoute>
              <Medecins />
            </PrivateRoute>
          }
        />
        <Route
          path="/rendez-vous"
          element={
            <PrivateRoute>
              <RendezVous />
            </PrivateRoute>
          }
        />
        <Route
          path="/dossiers"
          element={
            <PrivateRoute>
              <DossiersMedicaux />
            </PrivateRoute>
          }
        />

        {/* Redirection par défaut */}
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        <Route path="*" element={<Navigate to="/dashboard" replace />} />
      </Routes>
    </Router>
  );
}

export default App;