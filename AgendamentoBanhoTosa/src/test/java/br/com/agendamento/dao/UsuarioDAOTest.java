package br.com.agendamento.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.agendamento.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore // teste sendo ignorado
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setBairro("centro");
		usuario.setCep("18860-000");
		usuario.setCidade("Timburi");
		usuario.setCpf("000.000.000-00");
		// usuario.setDataNascimento(19990920);
		usuario.setEstado("S�o Paulo");
		usuario.setFuncao("Gerente");
		usuario.setNivelUsuario("Administrador");
		usuario.setNome("Lucas Oliveira");
		usuario.setNumero(45);
		usuario.setRg("43223444");
		usuario.setRua("Rua Sao Paulo");
		usuario.setSenha("teste");
		usuario.setTelefone("(14)2343-5553");
		usuario.setUsuario("locrz");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}

	@Test
	
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		List<Usuario> resultado = usuarioDAO.listar();

		// mostrando total de usuarios
		System.out.println("Total de Usu�rios Encontrados: " + resultado.size());

		// listando usuarios
		for (Usuario usuario : resultado) {
			System.out.println(usuario.getCodigo() + " - " + usuario.getUsuario() + " - " + usuario.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;// L de long int referente ao codigo 3

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhum usu�rio encontrado!");
		} else {
			System.out.println("Usu�rio encontrado:");
			System.out.println(usuario.getCodigo() + " - " + usuario.getUsuario() + " - " + usuario.getNome());
		}

	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		if(usuario == null) {
			System.out.println("Nenhum usu�rio encontrado!");
		}else {
			usuarioDAO.excluir(usuario);
			System.out.println("Usu�rio Removido:");
			System.out.println(usuario.getCodigo() + " - " + usuario.getUsuario() + " - " + usuario.getNome());
			
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		if(usuario == null) {
			System.out.println("Usu�rio n�o encontrado!");
		}else {
			usuario.setUsuario("feliz70");
			usuario.setSenha("20102010");
			usuarioDAO.editar(usuario);
			System.out.println("Usu�rio Alterado!");
			System.out.println(usuario.getCodigo() + " - " + usuario.getUsuario() + " - " + usuario.getNome());
		}
	}
}
