package com.gustavopereira.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gustavopereira.cursomc.domain.Categoria;
import com.gustavopereira.cursomc.domain.Cidade;
import com.gustavopereira.cursomc.domain.Cliente;
import com.gustavopereira.cursomc.domain.Endereco;
import com.gustavopereira.cursomc.domain.Estado;
import com.gustavopereira.cursomc.domain.Produto;
import com.gustavopereira.cursomc.domain.enums.TipoCliente;
import com.gustavopereira.cursomc.repositories.CategoriaRepository;
import com.gustavopereira.cursomc.repositories.CidadeRepository;
import com.gustavopereira.cursomc.repositories.ClienteRepository;
import com.gustavopereira.cursomc.repositories.EnderecoRepository;
import com.gustavopereira.cursomc.repositories.EstadoRepository;
import com.gustavopereira.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoria;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private EstadoRepository estado;
	
	@Autowired
	private CidadeRepository cidade;
	
	@Autowired
	private ClienteRepository cliente;
	
	@Autowired
	EnderecoRepository endereco;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));	
		
				
		categoria.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade cd1 = new Cidade(null, "Uberlandia", est1);
		Cidade cd2 = new Cidade(null, "Sao Paulo", est2);
		Cidade cd3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));
		
		estado.saveAll(Arrays.asList(est1, est2));
		cidade.saveAll(Arrays.asList(cd1, cd2, cd3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "04937959603", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3332256560", "33999797225"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Ap301", "Esplanada", "39830000", cli1, cd1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "30", "sala 05", "Centro", "32420000", cli1, cd2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		cliente.save(cli1);
		endereco.saveAll(Arrays.asList(e1, e2));
		
	}

}
