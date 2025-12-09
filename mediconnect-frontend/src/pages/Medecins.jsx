// ==================== src/pages/Medecins.jsx ====================
import { useState, useEffect } from 'react';
import { medecinAPI } from '../services/api';
import Navbar from '../components/Navbar';
import '../styles/Patients.css';

export default function Medecins() {
  const [medecins, setMedecins] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    nom: '',
    prenom: '',
    specialite: '',
    telephone: '',
    email: '',
    numeroOrdre: '',
  });

  useEffect(() => {
    loadMedecins();
  }, []);

  const loadMedecins = async () => {
    try {
      const response = await medecinAPI.getAll();
      setMedecins(response.data);
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors du chargement des médecins');
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await medecinAPI.create(formData);
      setFormData({
        nom: '',
        prenom: '',
        specialite: '',
        telephone: '',
        email: '',
        numeroOrdre: '',
      });
      setShowForm(false);
      loadMedecins();
      alert('Médecin créé avec succès !');
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors de la création du médecin');
    }
  };

  return (
    <>
      <Navbar />
      <div className="patients-container">
        <h2>Gestion des Médecins</h2>

        <button onClick={() => setShowForm(!showForm)} className="btn btn-primary">
          {showForm ? 'Annuler' : '➕ Ajouter un médecin'}
        </button>

        {showForm && (
          <form onSubmit={handleSubmit} className="patient-form">
            <h3>Nouveau Médecin</h3>
            <div className="form-row">
              <input
                type="text"
                name="nom"
                placeholder="Nom"
                value={formData.nom}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="prenom"
                placeholder="Prénom"
                value={formData.prenom}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-row">
              <input
                type="text"
                name="specialite"
                placeholder="Spécialité"
                value={formData.specialite}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="numeroOrdre"
                placeholder="N° Ordre"
                value={formData.numeroOrdre}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-row">
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
              />
              <input
                type="tel"
                name="telephone"
                placeholder="Téléphone"
                value={formData.telephone}
                onChange={handleChange}
              />
            </div>

            <button type="submit" className="btn btn-success">
              Créer Médecin
            </button>
          </form>
        )}

        <h3>Liste des Médecins</h3>
        {loading ? (
          <p>Chargement...</p>
        ) : medecins.length === 0 ? (
          <p>Aucun médecin trouvé</p>
        ) : (
          <table className="patients-table">
            <thead>
              <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Spécialité</th>
                <th>Email</th>
                <th>Téléphone</th>
              </tr>
            </thead>
            <tbody>
              {medecins.map((medecin) => (
                <tr key={medecin.id}>
                  <td>{medecin.nom}</td>
                  <td>{medecin.prenom}</td>
                  <td>{medecin.specialite}</td>
                  <td>{medecin.email}</td>
                  <td>{medecin.telephone}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
}

