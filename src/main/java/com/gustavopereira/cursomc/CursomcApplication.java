package com.gustavopereira.cursomc;

import java.text.SimpleDateFormat;
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
import com.gustavopereira.cursomc.domain.ItemPedido;
import com.gustavopereira.cursomc.domain.Pagamento;
import com.gustavopereira.cursomc.domain.PagamentoComBoleto;
import com.gustavopereira.cursomc.domain.PagamentoComCartao;
import com.gustavopereira.cursomc.domain.Pedido;
import com.gustavopereira.cursomc.domain.Produto;
import com.gustavopereira.cursomc.domain.enums.EstadoPagamento;
import com.gustavopereira.cursomc.domain.enums.TipoCliente;
import com.gustavopereira.cursomc.repositories.CategoriaRepository;
import com.gustavopereira.cursomc.repositories.CidadeRepository;
import com.gustavopereira.cursomc.repositories.ClienteRepository;
import com.gustavopereira.cursomc.repositories.EnderecoRepository;
import com.gustavopereira.cursomc.repositories.EstadoRepository;
import com.gustavopereira.cursomc.repositories.ItemPedidoRepository;
import com.gustavopereira.cursomc.repositories.PagamentoRepository;
import com.gustavopereira.cursomc.repositories.PedidoRepository;
import com.gustavopereira.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade cd1 = new Cidade(null, "Uberlandia", est1);
		Cidade cd2 = new Cidade(null, "Sao Paulo", est2);
		Cidade cd3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "04937959603", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3332256560", "33999797225"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Ap301", "Esplanada", "39830000", cli1, cd1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "30", "sala 05", "Centro", "32420000", cli1, cd2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 10:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido it1 = new ItemPedido(prod1, ped1, 0.00, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(prod3, ped1, 0.00, 2, 80.00);
		ItemPedido it3 = new ItemPedido(prod2, ped2, 100.00, 1, 800.00);
		
		prod1.getItems().add(it1);
		prod2.getItems().add(it3);
		prod3.getItems().add(it2);
		
		ped1.getItems().addAll(Arrays.asList(it1, it2));
		ped2.getItems().add(it3);
		
		itemPedidoRepository.saveAll(Arrays.asList(it1, it2, it3));
		
		
		
		
	}

}
