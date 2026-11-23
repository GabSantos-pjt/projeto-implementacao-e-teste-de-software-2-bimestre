package br.edu.exemplo;
import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
class ProdutoServiceIntegrationTest {
 @Autowired ProdutoService service;
 @Autowired ProdutoRepository repository;
 @Autowired EntityManager entityManager;

 /** Envia os comandos pendentes ao H2 e limpa o cache do JPA,
  *  obrigando a próxima consulta a ler do banco de verdade. */
 private void sincronizarComBanco() {
  entityManager.flush();
  entityManager.clear();
 }

 @Test void deveCadastrarERecuperarProduto(){
  // CT01 — Cadastro válido
  Produto cadastrado = service.cadastrar("Teclado", 150.0);
  assertNotNull(cadastrado.getId(), "O produto deve receber um ID ao ser salvo");

  sincronizarComBanco();

  Produto doBanco = repository.findById(cadastrado.getId()).orElseThrow();
  assertEquals("Teclado", doBanco.getNome());
  assertEquals(150.0, doBanco.getPreco(), 0.001);
 }

 @Test void naoDevePersistirProdutoComNomeEmBranco(){
  // CT02 — Nome inválido
  long antes = repository.count();

  IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
          () -> service.cadastrar("   ", 80.0));
  assertEquals("Nome obrigatório", erro.getMessage());

  assertEquals(antes, repository.count(), "Nenhum registro deve ser criado");
 }

 @Test void deveAtualizarPrecoPersistido(){
  // CT03 — Atualização (desafio)
  Produto mouse = service.cadastrar("Mouse", 80.0);
  sincronizarComBanco();

  service.atualizarPreco(mouse.getId(), 95.0);
  sincronizarComBanco();

  Produto doBanco = repository.findById(mouse.getId()).orElseThrow();
  assertEquals(95.0, doBanco.getPreco(), 0.001);
  assertEquals("Mouse", doBanco.getNome());
 }
}
