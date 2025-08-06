import React from 'react';
import { Outlet, Link, useNavigate } from 'react-router-dom';
import { removeToken } from '../utils/auth.jsx';

const Dashboard = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        removeToken();
        navigate('/');
    };

    return (
        <div className="d-flex">
            {/* Sidebar */}
            <div className="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style={{ width: '280px', height: '100vh' }}>
                <a href="#" className="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg className="bi pe-none me-2" width="40" height="32">
                        <use xlinkHref="#bootstrap" />
                    </svg>
                    <span className="fs-4">Sidebar</span>
                </a>
                <hr />
                <ul className="nav nav-pills flex-column mb-auto">
                    <li>
                        <Link to="/dashboard" className="nav-link text-white">
                            <i className="bi bi-house-door me-2"></i> Inicio
                        </Link>
                    </li>
                    <li>
                        <Link to="/dashboard/users" className="nav-link text-white">
                            <i className="bi bi-people me-2"></i> Customers
                        </Link>
                    </li>
                </ul>
                <hr />
                <div className="dropdown">
                    <a href="#" className="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="" width="32" height="32" className="rounded-circle me-2" />
                        <strong>mdo</strong>
                    </a>
                    <ul className="dropdown-menu dropdown-menu-dark text-small shadow">
                        <li><button className="dropdown-item" onClick={handleLogout}>Cerrar sesión</button></li>
                    </ul>
                </div>
            </div>

            {/* Contenido dinámico */}
            <div className="p-4" style={{ width: '100%' }}>
                <Outlet />
            </div>
        </div>
    );
};

export default Dashboard;
