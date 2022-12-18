import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';
import { userSessionStore } from './store';
import LoadingScreen from './components/LoadingScreen';

function App() {
  const user = userSessionStore(state => state.user);
  const loading = userSessionStore(state => state.loading);

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
