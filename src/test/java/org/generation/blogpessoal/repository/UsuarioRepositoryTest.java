package org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com", "usuario123", "https://i.pinimg.com/736x/63/16/d5/6316d53995d9e8f07ebe351d1f8326b0.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela Lima", "manu@email.com", "usuario123", "https://i.pinimg.com/736x/74/fd/6c/74fd6cc79212cf2a7d118efc2e4cc8ae.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana Lima", "drica@email.com", "usuario123", "https://i.pinimg.com/564x/31/77/85/317785f40d3aaaf68ce2971dbe48decb.jpg"));

		usuarioRepository.save(new Usuario(0L, "Antonio Carlos Lima", "antonio@email.com", "usuario123", "https://i.pinimg.com/564x/7f/10/43/7f10439e1b5f5c8a72c0b8c2395eb7df.jpg"));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Lima");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Manuela Lima"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Adriana Lima"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Antonio Carlos Lima"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
}
