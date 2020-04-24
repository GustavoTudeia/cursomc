package com.gustavopereira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gustavopereira.cursomc.domain.Categoria;
import com.gustavopereira.cursomc.domain.Produto;
import com.gustavopereira.cursomc.repositories.CategoriaRepository;
import com.gustavopereira.cursomc.repositories.ProdutoRepository;
import com.gustavopereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repoCategoria;

	public Produto find(Integer id) {
		Optional<Produto> produto = repo.findById(id);
		return produto.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, int page, int size, Direction direction, String properties) {
		try {
			Pageable  pageResquest = PageRequest.of(page, size, direction, properties);
			List<Categoria> categorias = repoCategoria.findAllById(ids);
			return repo.search(nome, categorias, pageResquest);
		}catch (InvalidDataAccessApiUsageException e) {
			throw new InvalidDataAccessApiUsageException("A categoria nao pode ser nula!", e.getCause());
		}
	}
}
