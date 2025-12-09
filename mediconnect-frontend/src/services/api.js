// ==================== src/services/api.js ====================
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export const authAPI = {
  login: (email, password) => api.post('/auth/login', { email, password }),
  logout: () => localStorage.removeItem('token'),
};

export const patientAPI = {
  getAll: () => api.get('/patients'),
  getById: (id) => api.get(`/patients/${id}`),
  create: (data) => api.post('/patients', data),
  update: (id, data) => api.put(`/patients/${id}`, data),
  delete: (id) => api.delete(`/patients/${id}`),
};

export const medecinAPI = {
  getAll: () => api.get('/medecins'),
  getById: (id) => api.get(`/medecins/${id}`),
  create: (data) => api.post('/medecins', data),
};

export const rendezVousAPI = {
  getAll: () => api.get('/rendez-vous'),
  getById: (id) => api.get(`/rendez-vous/${id}`),
  create: (data) => api.post('/rendez-vous', data),
};

export const dossierAPI = {
  getByPatient: (patientId) => api.get(`/dossiers-medicaux/patient/${patientId}`),
  create: (data) => api.post('/dossiers-medicaux', data),
};

export default api;

