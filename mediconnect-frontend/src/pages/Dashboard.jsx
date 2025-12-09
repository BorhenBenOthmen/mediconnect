
// ==================== src/pages/Dashboard.jsx ====================
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import '../styles/Dashboard.css';

export default function Dashboard() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user') || '{}');

  const cards = [
    { title: '👥 Patients', route: '/patients', color: '#3498db' },
    { title: '👨‍⚕️ Médecins', route: '/medecins', color: '#2ecc71' },
    { title: '📅 Rendez-vous', route: '/rendez-vous', color: '#e74c3c' },
    { title: '📋 Dossiers', route: '/dossiers', color: '#f39c12' },
  ];

  return (
    <>
      <Navbar />
      <div className="dashboard-container">
        <h1>Bienvenue {user.prenom} ! 👋</h1>
        <p>Gérez votre système médical depuis ce tableau de bord</p>

        <div className="cards-grid">
          {cards.map((card, idx) => (
            <div
              key={idx}
              className="card"
              style={{ borderTop: `4px solid ${card.color}` }}
              onClick={() => navigate(card.route)}
            >
              <h3>{card.title}</h3>
              <p>Cliquez pour accéder</p>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
