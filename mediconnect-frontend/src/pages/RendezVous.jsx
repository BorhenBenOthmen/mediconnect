// ==================== src/pages/RendezVous.jsx ====================
import { useState, useEffect } from 'react';
import { rendezVousAPI, patientAPI } from '../services/api';
import Navbar from '../components/Navbar';
import '../styles/Patients.css';

export default function RendezVous() {
  const [rdvs, setRdvs] = useState([]);
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    patientId: '',
    medecinId: '',
    dateRendezVous: '',
    motif: '',
    statut: 'PLANIFIE',
  });

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      const rdvResponse = await rendezVousAPI.getAll();
      const patientResponse = await patientAPI.getAll();
      setRdvs(rdvResponse.data);
      setPatients(patientResponse.data);
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors du chargement');
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
      await rendezVousAPI.create(formData);
      setFormData({
        patientId: '',
        medecinId: '',
        dateRendezVous: '',
        motif: '',
        statut: 'PLANIFIE',
      });
      setShowForm(false);
      loadData();
      alert('Rendez-vous créé avec succès !');
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors de la création du RDV');
    }
  };

  return (
    <>
      <Navbar />
      <div className="patients-container">
        <h2>Gestion des Rendez-vous</h2>

        <button onClick={() => setShowForm(!showForm)} className="btn btn-primary">
          {showForm ? 'Annuler' : '➕ Ajouter un RDV'}
        </button>

        {showForm && (
          <form onSubmit={handleSubmit} className="patient-form">
            <h3>Nouveau Rendez-vous</h3>
            <div className="form-row">
              <select
                name="patientId"
                value={formData.patientId}
                onChange={handleChange}
                required
              >
                <option value="">Sélectionner un patient</option>
                {patients.map((p) => (
                  <option key={p.id} value={p.id}>
                    {p.prenom} {p.nom}
                  </option>
                ))}
              </select>
              <input
                type="text"
                name="medecinId"
                placeholder="ID Médecin"
                value={formData.medecinId}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-row">
              <input
                type="datetime-local"
                name="dateRendezVous"
                value={formData.dateRendezVous}
                onChange={handleChange}
                required
              />
              <select
                name="statut"
                value={formData.statut}
                onChange={handleChange}
              >
                <option value="PLANIFIE">Planifié</option>
                <option value="CONFIRME">Confirmé</option>
                <option value="ANNULE">Annulé</option>
              </select>
            </div>

            <input
              type="text"
              name="motif"
              placeholder="Motif"
              value={formData.motif}
              onChange={handleChange}
              style={{ width: '100%', marginBottom: '1rem', padding: '0.75rem' }}
              required
            />

            <button type="submit" className="btn btn-success">
              Créer RDV
            </button>
          </form>
        )}

        <h3>Liste des Rendez-vous</h3>
        {loading ? (
          <p>Chargement...</p>
        ) : rdvs.length === 0 ? (
          <p>Aucun rendez-vous trouvé</p>
        ) : (
          <table className="patients-table">
            <thead>
              <tr>
                <th>Patient ID</th>
                <th>Médecin ID</th>
                <th>Date</th>
                <th>Motif</th>
                <th>Statut</th>
              </tr>
            </thead>
            <tbody>
              {rdvs.map((rdv) => (
                <tr key={rdv.id}>
                  <td>{rdv.patientId}</td>
                  <td>{rdv.medecinId}</td>
                  <td>{new Date(rdv.dateRendezVous).toLocaleString()}</td>
                  <td>{rdv.motif}</td>
                  <td>{rdv.statut}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
}
