package br.edu.exemplo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
@SpringBootTest @AutoConfigureMockMvc @Transactional
class ProdutoControllerIntegrationTest {
 @Autowired MockMvc mockMvc;
 @Autowired ObjectMapper objectMapper;

 private static final String MOUSE_JSON = "{\"nome\":\"Mouse\",\"preco\":80.0}";

 @Test void deveCadastrarProdutoPelaApi() throws Exception {
  // CT04 — Cadastro via API
  mockMvc.perform(post("/produtos")
          .contentType(MediaType.APPLICATION_JSON)
          .content(MOUSE_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").isNumber())
      .andExpect(jsonPath("$.nome").value("Mouse"))
      .andExpect(jsonPath("$.preco").value(80.0));
 }

 @Test void deveBuscarProdutoPelaApi() throws Exception {
  // CT05 — Consulta via API
  String respostaPost = mockMvc.perform(post("/produtos")
          .contentType(MediaType.APPLICATION_JSON)
          .content(MOUSE_JSON))
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();

  JsonNode cadastrado = objectMapper.readTree(respostaPost);
  long id = cadastrado.get("id").asLong();
  assertTrue(id > 0, "O ID gerado deve ser positivo");

  mockMvc.perform(get("/produtos/{id}", id))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(cadastrado.get("id").asInt()))
      .andExpect(jsonPath("$.nome").value(cadastrado.get("nome").asText()))
      .andExpect(jsonPath("$.preco").value(cadastrado.get("preco").asDouble()));
 }
}
