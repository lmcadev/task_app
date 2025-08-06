import React, { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import { getToken } from '../../../../../client_frontend/client/src/utils/auth.jsx';

const UsersPage = () => {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await api.get('/users/all', {
                    headers: {
                        Authorization: `Bearer ${getToken()}`
                    }
                });
                setUsers(response.data);
            } catch (err) {
                console.error('Error al obtener usuarios:', err);
                setError('Error al cargar los usuarios.');
            }
        };

        fetchUsers();
    }, []);

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Listado de Usuarios</h2>
            {error && <div className="alert alert-danger">{error}</div>}

            <table className="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Rol</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.nombre}</td>
                        <td>{user.email}</td>
                        <td>{user.rol}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UsersPage;
