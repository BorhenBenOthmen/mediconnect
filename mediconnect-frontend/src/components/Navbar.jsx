
// ==================== src/components/Navbar.jsx ====================
import { useNavigate } from 'react-router-dom';
import { authAPI } from '../services/api';
import '../styles/Navbar.css';

export default function Navbar() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user') || '{}');

  const handleLogout = () => {
    authAPI.logout();
    localStorage.removeItem('user');
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <div className="navbar-logo">
          <h1>🏥 MediConnect</h1>
        </div>

        <ul className="nav-menu">
          <li><a href="/dashboard">Dashboard</a></li>
          <li><a href="/patients">Patients</a></li>
          <li><a href="/medecins">Médecins</a></li>
          <li><a href="/rendez-vous">Rendez-vous</a></li>
        </ul>

        <div className="nav-user">
          <span>Bienvenue {user.prenom} {user.nom}</span>
          <button onClick={handleLogout} className="logout-btn">
            Déconnexion
          </button>
        </div>
      </div>
    </nav>
  );
}

