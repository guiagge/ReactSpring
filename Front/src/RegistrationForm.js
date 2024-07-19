import React, { useState } from 'react';
import axios from 'axios';

const RegistrationForm = () => {
  	const [name, setName] = useState('');
	const [description, setDescription] = useState('');
	const [image, setImage] = useState('');
  	const [category, setCategory] = useState('');
  	const [dateOfBirth, setDateOfBirth] = useState(0);
  	const [status, setStatus] = useState('Disponível');
	const [errors, setErrors] = useState({});
	
	const validateForm = () => {
		const newErrors = {};
		if (!name) newErrors.name = 'Nome é obrigatório';
		if (description.length < 3) newErrors.description = 'Descrição precisa ter no mínimo 3 caracteres';
		if (!category) newErrors.category = 'Categoria é obrigatória';
		if (!dateOfBirth) newErrors.dateOfBirth = 'Data de nascimento é obrigatória';
		setErrors(newErrors);
		return Object.keys(newErrors).length === 0; // Returns true if no errors
	};

  	const handleSubmit = async (event) => {
    	event.preventDefault();
		if (!validateForm()) {
			const errorMessages = Object.values(errors).join('\n');
			alert(`Erro(s):\n${errorMessages}`);
			return;
		  }
    	const animalData = {
    	  	name: name,
			description: description,
			image: image,
			category: category,
    	  	dateOfBirth: dateOfBirth,
    	  	status: status,
    	};
    	try {
    	  	await axios.post('http://localhost:8080/animal', animalData);
    	  	alert('Animal registrado com sucesso!');
    	  	// Clears the form after registration
    	  	setName('');
			setDescription('');
			setImage('');
    	  	setCategory('');
    	  	setDateOfBirth('');
    	  	setStatus('Disponível');
    	} catch (error) {
    	  	console.error(error);
    	  	alert('Falha ao registrar animal.');
    	}
  };

  return (
	<div className="App">
        <h1> Cadastro de Animais </h1>
		<div>
			<aside class="sidenav">
				<p><a href="index.html"><i class="fas fa-home" aria-hidden="true"></i> Cadastro de Animais </a></p>
				<p><a href="search.html"><i class="fas fa-ghost" aria-hidden="true"></i> Busca de Animais </a></p>
			</aside>
			<form onSubmit={handleSubmit}>
				<label htmlFor="name"> Nome: </label>
				<input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} />
				<label htmlFor="description"> Descrição: </label>
				<input type="text" id="description" value={description} onChange={(e) => setDescription(e.target.value)} />
				<label htmlFor="image"> Imagem (URL): </label>
				<input type="text" id="category" value={image} onChange={(e) => setImage(e.target.value)} />
				<label htmlFor="category"> Categoria: </label>
				<input type="text" id="category" value={category} onChange={(e) => setCategory(e.target.value)} />
				<label htmlFor="dateOfBirth"> Data de Nascimento: </label>
				<input type="date" id="dateOfBirth" value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} />
				<label>Status:</label>
				<div class="flexbox">
					<input type="radio" id="disponivel" name="status" value="Disponível"
						checked={status == false}
						onChange={(e) => setStatus(false)} />
					<label for="disponivel"> Disponível </label>
				</div>
				<label></label>
				<div class="flexbox">
					<input type="radio" id="adotado" name="status" value="Adotado"
						checked={status == true}
						onChange={(e) => setStatus(true)} />
					<label for="adotado"> Adotado </label>
				</div>
				<label></label>
				<button type="submit"> Registrar Animal </button>
			</form>
			<footer>
				<p><a href="index.html"><i class="fas fa-home" aria-hidden="true"></i> Cadastro de Animais </a></p>
				<p><a href="search.html"><i class="fas fa-ghost" aria-hidden="true"></i> Busca de Animais </a></p>
			</footer>
		</div>
	</div>
  );
};

export default RegistrationForm;