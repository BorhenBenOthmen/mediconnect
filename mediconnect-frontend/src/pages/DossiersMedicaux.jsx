// ==================== src/pages/DossiersMedicaux.jsx ====================
import { useState, useEffect } from 'react';
import { dossierAPI, patientAPI } from '../services/api';
import Navbar from '../components/Navbar';
import '../styles/Patients.css';

export default function DossiersMedicaux() {
  const [dossiers, setDossiers] = useState([]);
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedPatientId, setSelectedPatientId] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    patientId: '',
    medecinId: '',
    diagnostic: '',
    traitement: '',
    notes: '',
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

  const loadDossiers = async (patientId) => {
    if (!patientId) return;
    try {
      const response = await dossierAPI.getByPatient(patientId);
      setDossiers(response.data);
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors du chargement des dossiers');
    }
  };

  const handlePatientChange = (e) => {
    const patientId = e.target.value;
    setSelectedPatientId(patientId);
    loadDossiers(patientId);
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
      await dossierAPI.create({
        ...formData,
        patientId: parseInt(selectedPatientId),
      });
      setFormData({
        patientId: '',
        medecinId: '',
        diagnostic: '',
        traitement: '',
        notes: '',
      });
      setShowForm(false);
      loadDossiers(selectedPatientId);
      alert('Dossier créé avec succès !');
    } catch (error) {
      console.error('Erreur:', error);
      alert('Erreur lors de la création du dossier');
    }
  };

  return (
    <>
      <Navbar />
      <div className="patients-container">
        <h2>Dossiers Médicaux</h2>

        <div className="form-row" style={{ marginBottom: '2rem' }}>
          <select
            value={selectedPatientId}
            onChange={handlePatientChange}
            style={{
              padding: '0.75rem',
              border: '1px solid #ddd',
              borderRadius: '5px',
              fontSize: '1rem',
            }}
          >
            <option value="">Sélectionner un patient</option>
            {patients.map((p) => (
              <option key={p.id} value={p.id}>
                {p.prenom} {p.nom}
              </option>
            ))}
          </select>
          <button
            onClick={() => setShowForm(!showForm)}
            className="btn btn-primary"
            disabled={!selectedPatientId}
          >
            {showForm ? 'Annuler' : '➕ Ajouter un dossier'}
          </button>
        </div>

        {showForm && selectedPatientId && (
          <form onSubmit={handleSubmit} className="patient-form">
            <h3>Nouveau Dossier Médical</h3>

            <div className="form-row">
              <input
                type="number"
                name="medecinId"
                placeholder="ID Médecin"
                value={formData.medecinId}
                onChange={handleChange}
                required
              />
            </div>

            <textarea
              name="diagnostic"
              placeholder="Diagnostic"
              value={formData.diagnostic}
              onChange={handleChange}
              style={{
                width: '100%',
                padding: '0.75rem',
                marginBottom: '1rem',
                borderRadius: '5px',
                border: '1px solid #ddd',
                minHeight: '80px',
              }}
              required
            />

            <textarea
              name="traitement"
              placeholder="Traitement"
              value={formData.traitement}
              onChange={handleChange}
              style={{
                width: '100%',
                padding: '0.75rem',
                marginBottom: '1rem',
                borderRadius: '5px',
                border: '1px solid #ddd',
                minHeight: '80px',
              }}
              required
            />

            <textarea
              name="notes"
              placeholder="Notes"
              value={formData.notes}
              onChange={handleChange}
              style={{
                width: '100%',
                padding: '0.75rem',
                marginBottom: '1rem',
                borderRadius: '5px',
                border: '1px solid #ddd',
                minHeight: '80px',
              }}
            />

            <button type="submit" className="btn btn-success">
              Créer Dossier
            </button>
          </form>
        )}

        <h3>Dossiers du Patient</h3>
        {!selectedPatientId ? (
          <p>Sélectionnez un patient pour voir ses dossiers</p>
        ) : loading ? (
          <p>Chargement...</p>
        ) : dossiers.length === 0 ? (
          <p>Aucun dossier trouvé pour ce patient</p>
        ) : (
          <table className="patients-table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Diagnostic</th>
                <th>Traitement</th>
                <th>Notes</th>
              </tr>
            </thead>
            <tbody>
              {dossiers.map((dossier) => (
                <tr key={dossier.id}>
                  <td>{new Date(dossier.dateConsultation).toLocaleDateString()}</td>
                  <td>{dossier.diagnostic}</td>
                  <td>{dossier.traitement}</td>
                  <td>{dossier.notes}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
}
