import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import Dashboard from './pages/Dashboard';
import UsersPage from './pages/UserPage.jsx';
import { getToken } from './utils/auth.jsx';

const ProtectedRoute = ({ children }) => {
    const token = getToken();
    return token ? children : <Navigate to="/" />;
};

const App = () => (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/dashboard" element={
                <ProtectedRoute>
                    <Dashboard />
                </ProtectedRoute>
            } />
            <Route path="/users" element={
                <ProtectedRoute>
                    <UsersPage />
                </ProtectedRoute>
            } />
        </Routes>
    </BrowserRouter>
);

export default App;
