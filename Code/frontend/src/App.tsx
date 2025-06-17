import React from 'react';
import { Routes, Route } from 'react-router-dom';

import IndexPage from './pages/IndexPage';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';

const App: React.FC = () => {
  return (
    <Routes>
      <Route path="/" element={<IndexPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/sign-up" element={<SignUpPage />} />

    </Routes>
  );
};

export default App;
