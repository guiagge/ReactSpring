import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import RegistrationForm from './RegistrationForm';
import AnimalSearch from './AnimalSearch';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<RegistrationForm />} />
                <Route path="/index.html" element={<RegistrationForm />} />
                <Route path="/search.html" element={<AnimalSearch />} />
            </Routes>
        </BrowserRouter>
      );
};

export default App;
