import React, { useEffect } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';
import { useSessionStore } from './store';
import LoadingScreen from './components/LoadingScreen';
import ManagementHubLayout from './components/ManagementHubLayout';
import Users from './pages/Users/Users';
import Repositories from './pages/Repositories/Repositories';
import OrganizationSettings from "./pages/OrganizationSettings/OrganizationSettings";

function App() {
  const user = useSessionStore(state => state.user);
  const loading = useSessionStore(state => state.loading);

  const getSession = useSessionStore(state => state.getSession);

  useEffect(() => {
    getSession();
  }, []);

  if (loading) {
    return <LoadingScreen />;
  }

  if (user) {
    return (
      <ManagementHubLayout>
        <Routes>
          <Route path={'/dashboard'} element={<Dashboard />} />
          <Route path={'/users'} element={<Users />} />
          <Route path={'/repositories'} element={<Repositories />} />
          <Route path={'/organizationSettings'} element={<OrganizationSettings />} />
          <Route path={'*'} element={<Navigate to="/dashboard" replace />} />
        </Routes>
      </ManagementHubLayout>
    );
  }

  return (
    <Routes>
      <Route path={'/login'} element={<LoginPage />} />
      <Route path={'*'} element={<Navigate to="/login" replace />} />
    </Routes>
  );
}

export default App;
