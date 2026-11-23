# Semana 11 — Testes de integração com Spring Boot e JUnit 5

Projeto-base da disciplina ([teste_software_2026/aulas/SEMANA11/exercicios_integracao_springboot](https://github.com/JoaoChoma/teste_software_2026/tree/main/aulas/SEMANA11/exercicios_integracao_springboot)) com os métodos `TODO` das classes de teste completados.
Enunciado completo em [`ENUNCIADO.md`](ENUNCIADO.md).

**Tecnologias:** Java 17, Maven, Spring Boot 3.3, Spring Data JPA, H2 (em memória), JUnit 5 e MockMvc.

> Nenhum mock foi utilizado: `ProdutoService`, `ProdutoRepository` e o banco H2 são os componentes reais, injetados pelo Spring.

## Estrutura

```
src/main/java/br/edu/exemplo/
├── Aplicacao.java
├── controller/ProdutoController.java, ProdutoRequest.java
├── model/Produto.java
├── repository/ProdutoRepository.java
└── service/ProdutoService.java
src/test/java/br/edu/exemplo/
├── ProdutoServiceIntegrationTest.java     # Exercício 1 (CT01, CT02, CT03)
└── ProdutoControllerIntegrationTest.java  # Exercício 2 (CT04, CT05)
```

## Exercício 1 — Service + Repository + H2 (`ProdutoServiceIntegrationTest`)

| Caso | Método | O que o teste faz | Verificações |
|---|---|---|---|
| CT01 — Cadastro válido | `deveCadastrarERecuperarProduto` | Cadastra `Teclado` por `150.0`, sincroniza com o banco e consulta com `repository.findById()` | `assertNotNull` no ID; `assertEquals` no nome e no preço lidos do banco |
| CT02 — Nome inválido | `naoDevePersistirProdutoComNomeEmBranco` | Conta os registros, tenta cadastrar nome em branco por `80.0` | `assertThrows(IllegalArgumentException)` com a mensagem `Nome obrigatório`; `repository.count()` igual ao valor inicial |
| CT03 — Atualização (desafio) | `deveAtualizarPrecoPersistido` | Cadastra `Mouse` por `80.0`, atualiza para `95.0` e consulta de novo | Preço lido do banco igual a `95.0` |

Detalhe importante: antes de cada `findById()`, o teste chama `entityManager.flush()` e `entityManager.clear()`. O `flush` envia os `INSERT`/`UPDATE` pendentes ao H2 e o `clear` esvazia o cache do JPA. Assim, a consulta é feita **no banco de verdade**, e não apenas no objeto que já estava em memória.

## Exercício 2 — HTTP com MockMvc (`ProdutoControllerIntegrationTest`)

| Caso | Método | Requisição | Verificações |
|---|---|---|---|
| CT04 — Cadastro via API | `deveCadastrarProdutoPelaApi` | `POST /produtos` com `{"nome":"Mouse","preco":80.0}` | `status().isOk()` (200); JSON com `$.id` numérico, `$.nome = "Mouse"`, `$.preco = 80.0` |
| CT05 — Consulta via API | `deveBuscarProdutoPelaApi` | `POST /produtos`, extrai o ID com `ObjectMapper`/`JsonNode`, depois `GET /produtos/{id}` | `status().isOk()` (200); `id`, `nome` e `preco` do GET iguais aos do produto cadastrado |

## Execução e evidências

```bash
mvn test
```

Resultado esperado:

```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0 -- in br.edu.exemplo.ProdutoServiceIntegrationTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0 -- in br.edu.exemplo.ProdutoControllerIntegrationTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

<!-- Cole aqui um print do terminal com o resultado do `mvn test` -->

## Discussão

### Exercício 1

**Por que `repository.findById()` é uma evidência diferente do objeto retornado pelo serviço?**
O objeto devolvido por `service.cadastrar()` prova apenas que o método retornou algo com ID; ele está em memória e poderia ter os dados certos mesmo que nada fosse gravado. A consulta com `findById()` (feita após `flush()` e `clear()`) prova que o registro **foi persistido no H2** com nome e preço corretos, ou seja, que service, repository, mapeamento JPA e banco funcionam juntos. Esse é o objetivo do teste de integração.

**O que `@Transactional` faz ao término de cada teste?**
Cada método de teste roda dentro de uma transação que o Spring **desfaz (rollback)** automaticamente ao final. Assim, os produtos cadastrados em um teste não aparecem no seguinte: cada teste começa com o banco no mesmo estado e o resultado não depende da ordem de execução. Isso garante o **isolamento** entre os testes.

### Exercício 2

**Quais componentes são exercitados?**
O fluxo completo da aplicação: o `DispatcherServlet` do Spring MVC, a conversão JSON ↔ objeto (Jackson), o `ProdutoController`, o `ProdutoService` com suas validações, o `ProdutoRepository` (Spring Data JPA/Hibernate) e o banco H2.

**Por que o MockMvc não precisa iniciar um servidor HTTP real?**
O MockMvc chama o `DispatcherServlet` diretamente, dentro da mesma JVM, com objetos de requisição e resposta simulados (`MockHttpServletRequest`/`MockHttpServletResponse`). Não abre porta nem passa pela rede, mas executa toda a pilha do Spring MVC (rotas, conversores, controller). O teste fica mais rápido e, por rodar na mesma thread, participa da mesma transação do `@Transactional`.

**Diferença para um teste unitário do controller com dependências simuladas?**
Num teste unitário (`@WebMvcTest` + `@MockBean ProdutoService`), o service é falso: verifica-se só se o controller mapeia a rota e repassa os dados, e o resultado vem de um `when(...).thenReturn(...)`. Ele não detecta erros de validação, de mapeamento JPA ou de persistência. No teste de integração, nada é simulado: se o service, o repository ou o banco falharem, o teste falha. Ele é mais lento, mas dá mais confiança de que as partes funcionam juntas.

## Aluno

| Nome | R.A. | GitHub |
|---|---|---|
| Gabriel Santos Furlanette | 24000873-2 | [@GabSantos-pjt](https://github.com/GabSantos-pjt) |
