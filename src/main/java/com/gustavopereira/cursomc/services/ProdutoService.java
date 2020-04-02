package com.gustavopereira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavopereira.cursomc.domain.Categoria;
import com.gustavopereira.cursomc.repositories.CategoriaRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElse(null);
	}
	
}
