import React, { useEffect } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';
import { useSessionStore } from './store';
import LoadingScreen from './components/LoadingScreen';

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
      <Routes>
        <Route path={'/dashboard'} element={<Dashboard />} />
      </Routes>
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
