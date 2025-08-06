import React, { useState } from 'react';
import api from '../api/axiosConfig.jsx';
import { saveToken } from '../utils/auth.jsx';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post('/api/V1/auth/login',
                {
                    username: email, // o "email" si tu backend espera "email"
                    password: password
                },
                {
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    withCredentials: true, // opcional, si usas cookies
                }
            );
            const token = response.data;
            saveToken(token);
            navigate('/dashboard');
        } catch (err) {
            console.error('Error en login:', err.response?.data || err.message);
            setError('Credenciales inválidas');
        }
    };


    return (
        <form onSubmit={handleSubmit} className="p-4 shadow rounded bg-white">
            <h3>Iniciar Sesión</h3>
            {error && <div className="alert alert-danger">{error}</div>}
            <div className="mb-3">
                <label>Email</label>
                <input type="email" className="form-control"
                       value={email} onChange={(e) => setEmail(e.target.value)} required />
            </div>
            <div className="mb-3">
                <label>Contraseña</label>
                <input type="password" className="form-control"
                       value={password} onChange={(e) => setPassword(e.target.value)} required />
            </div>
            <button type="submit" className="btn btn-primary">Ingresar</button>
        </form>
    );
};

export default LoginForm;
