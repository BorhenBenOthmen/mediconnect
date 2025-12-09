// ==================== src/pages/Patients.jsx ====================
import { useState, useEffect } from 'react';
import { patientAPI } from '../services/api';
import Navbar from '../components/Navbar';
import '../styles/Patients.css';

export default function Patients() {
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    nom: '',
    prenom: '',
    dateNaissance: '',
    telephone: '',
    email: '',
    adresse: '',
    numeroSecuriteSociale: '',
  });

  useEffect(() => {
    loadPatients();
  }, []);

  const loadPatients = async () => {
    try {
      const response = await patientAPI.getAll();
      setPatients(response.data);
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors du chargement des patients');
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
      await patientAPI.create(formData);
      setFormData({
        nom: '',
        prenom: '',
        dateNaissance: '',
        telephone: '',
        email: '',
        adresse: '',
        numeroSecuriteSociale: '',
      });
      setShowForm(false);
      loadPatients();
      alert('Patient créé avec succès !');
    } catch (error) {
      alert('Erreur lors de la création du patient');
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer ce patient ?')) {
      try {
        await patientAPI.delete(id);
        loadPatients();
        alert('Patient supprimé avec succès !');
      } catch (error) {
        alert('Erreur lors de la suppression');
      }
    }
  };

  return (
    <>
      <Navbar />
      <div className="patients-container">
        <h2>Gestion des Patients</h2>

        <button onClick={() => setShowForm(!showForm)} className="btn btn-primary">
          {showForm ? 'Annuler' : '➕ Ajouter un patient'}
        </button>

        {showForm && (
          <form onSubmit={handleSubmit} className="patient-form">
            <h3>Nouveau Patient</h3>
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
                type="date"
                name="dateNaissance"
                value={formData.dateNaissance}
                onChange={handleChange}
                required
              />
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-row">
              <input
                type="tel"
                name="telephone"
                placeholder="Téléphone"
                value={formData.telephone}
                onChange={handleChange}
              />
              <input
                type="text"
                name="numeroSecuriteSociale"
                placeholder="N° Sécurité Sociale"
                value={formData.numeroSecuriteSociale}
                onChange={handleChange}
              />
            </div>

            <input
              type="text"
              name="adresse"
              placeholder="Adresse"
              value={formData.adresse}
              onChange={handleChange}
              style={{ width: '100%', marginBottom: '1rem' }}
            />

            <button type="submit" className="btn btn-success">
              Créer Patient
            </button>
          </form>
        )}

        <h3>Liste des Patients</h3>
        {loading ? (
          <p>Chargement...</p>
        ) : patients.length === 0 ? (
          <p>Aucun patient trouvé</p>
        ) : (
          <table className="patients-table">
            <thead>
              <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Téléphone</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {patients.map((patient) => (
                <tr key={patient.id}>
                  <td>{patient.nom}</td>
                  <td>{patient.prenom}</td>
                  <td>{patient.email}</td>
                  <td>{patient.telephone}</td>
                  <td>
                    <button
                      onClick={() => handleDelete(patient.id)}
                      className="btn btn-danger btn-sm"
                    >
                      Supprimer
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
}
