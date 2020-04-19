package com.gustavopereira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gustavopereira.cursomc.domain.Cliente;
import com.gustavopereira.cursomc.domain.enums.TipoCliente;
import com.gustavopereira.cursomc.dto.ClienteDTO;
import com.gustavopereira.cursomc.repositories.ClienteRepository;
import com.gustavopereira.cursomc.services.exceptions.DataIntegrityException;
import com.gustavopereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> categoria = repo.findById(id);
		return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	

	public Cliente insert(Cliente fromDTO) {
		throw new UnsupportedOperationException();
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		
		if(obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
		
		if(obj.getCpdOuCnpj() != null) {
			newObj.setCpdOuCnpj(obj.getCpdOuCnpj());
		}
		
		if(obj.getTipoCliente() != null) {
			newObj.setTipoCliente(obj.getTipoCliente());
		}
	}


	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não possível excluir cliente com pedido(s) ou endereco(s) associado(s)! ", e.getCause());
		}
		
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(int page, int size, Direction direction, String properties) {
		PageRequest pageResquest = PageRequest.of(page, size, direction, properties);
		return repo.findAll(pageResquest);
	}
	
	public Cliente fromDTO(ClienteDTO cliDTO) {
		return new Cliente(cliDTO.getId(), cliDTO.getNome(), cliDTO.getEmail(), cliDTO.getCpdOuCnpj(), TipoCliente.toEnum(cliDTO.getTipoCliente()));
	}
}
