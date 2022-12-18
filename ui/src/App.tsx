import React from 'react';
import { Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';

function App() {
  return (
    <Routes>
      <Route path={'/login'} element={<LoginPage />} />
      <Route path={'/dashboard'} element={<Dashboard />} />
    </Routes>
  );
}

export default App;
