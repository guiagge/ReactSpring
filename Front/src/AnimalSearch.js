import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { formataData } from './utils';

const AnimalSearch = () => {
    const [animals, setAnimals] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    // Gets the animal list when creating the component
    const fetchData = async () => {
        try {
            const response = await axios.get('animal');
            setAnimals(response.data);
        } catch (error) {
            alert('O servidor não parece estar respondendo...');
            console.error('Network error:', error);
        }
    };
    
    useEffect(() => {
        fetchData();
    }, []);

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    const handleChange = async (animalId, newStatus) => {
        console.log('Teste!', animalId, newStatus);
        try {
            const response = await fetch('animal', {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: animalId, status: newStatus })
            });
            if (!response.ok) 
                throw new Error('Error updating animal status:' + response.statusText);
            fetchData();    // Reloads data
        } catch (error) {
          console.error('Error updating animal status:', error);
        }
    };

    const filteredAnimals = animals.filter((animal) => {
        const searchTextLower = searchTerm.toLowerCase();
        return (
            animal.name.toLowerCase().includes(searchTextLower) ||
            animal.description.toLowerCase().includes(searchTextLower) ||
            animal.category.toLowerCase().includes(searchTextLower)
        );
    });

    return (
        <div className="App">
            <h2 class> Busca de Animais </h2>
            <div>
                <aside class="sidenav">
                    <p><a href="index.html"><i class="fas fa-home" aria-hidden="true"></i> Cadastro de Animais </a></p>
                    <p><a href="search.html"><i class="fas fa-ghost" aria-hidden="true"></i> Busca de Animais </a></p>
                </aside>
                <div class="grid">
                    <input type="text" placeholder="Buscar por nome, descrição ou categoria"
                        value={searchTerm} onChange={handleSearchChange} />
                    <table>
                        <thead>
                            <tr>
                            <th>Imagem</th>
                            <th>Nome</th>
                            <th>Descrição</th>
                            <th>Categoria</th>
                            <th>Data de Nascimento</th>
                            <th>Idade</th>
                            <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredAnimals.map((animal) => (
                            <tr key={animal.id}>
                                <td><img src={animal.imageUrl} alt={animal.name} /></td>
                                <td>{animal.name}</td>
                                <td>{animal.description}</td>
                                <td>{animal.category}</td>
                                <td>{formataData(animal.dateOfBirth)}</td>
                                <td>{animal.age}</td>
                                <td>
                                    <div class="adotabox">
                                        <input type="radio" id={'disponivel-' + animal.id} 
                                        name={'status-'+ animal.id} onChange={() => handleChange(animal.id, false)}
                                        checked={animal.status == false} />
                                        <label for={'disponivel-' + animal.id}> Disponível </label>
                                    </div>
                                    <div class="adotabox">
                                        <input type="radio" id={'adotado-' + animal.id} 
                                        name={'status-' + animal.id} onChange={() => handleChange(animal.id, true)}
                                        checked={animal.status == true} />
                                        <label for={'adotado-' + animal.id}> Adotado </label>
                                    </div>
                                </td>
                            </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
            <footer>
				<p><a href="index.html"><i class="fas fa-home" aria-hidden="true"></i> Cadastro de Animais </a></p>
				<p><a href="search.html"><i class="fas fa-ghost" aria-hidden="true"></i> Busca de Animais </a></p>
			</footer>
        </div>
    );
};

export default AnimalSearch;
