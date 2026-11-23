# Semana 14 — Plano de Teste de Usuário Final (Mercado Livre)

Roteiro da disciplina: [TESTE USUARIO FINAL.pdf](https://github.com/JoaoChoma/teste_software_2026/blob/main/aulas/SEMANA14/TESTE%20USUARIO%20FINAL.pdf).

> **Status:** este documento é o **plano de teste de usabilidade**. A sessão com usuário e avaliador **ainda não foi executada**, por isso não há dados coletados. Os instrumentos (planilha de observação e questionário SUS) estão prontos para uso.

## 1. Objetivo

Avaliar a **facilidade de uso, a eficiência e a clareza da interface** do site [Mercado Livre](https://www.mercadolivre.com.br) nas etapas de busca, comparação e simulação de compra de um produto, aplicando conceitos de IHC e de testes de usabilidade.

## 2. Escopo

| Dentro do escopo | Fora do escopo |
|---|---|
| Página inicial, busca, filtros e ordenação | Pagamento e finalização da compra |
| Página de detalhes do produto | Cadastro de nova conta |
| Carrinho e início do checkout | Pós-venda (rastreio, devolução) |
| Navegação de volta à página inicial | Aplicativo mobile |

## 3. Cenário de uso

O usuário procura um **fone de ouvido sem fio** para uso diário. Ele deve encontrar um modelo com boa avaliação, comparar opções e simular a compra até o carrinho, **sem finalizar o pagamento**.

## 4. Participantes e papéis

| Papel | Responsabilidade |
|---|---|
| Usuário final | Executa as tarefas como comprador, sem ajuda, e responde o SUS ao final |
| Avaliador | Cronometra cada tarefa, registra sucesso, erros e comentários; não interfere |
| Instrutor | Orienta e supervisiona o teste |

**Perfil desejado do usuário:** adulto que já fez ao menos uma compra on-line. Para atingir o critério de 90%, recomenda-se executar com **pelo menos 5 usuários**.

## 5. Ambiente e ferramentas

- Computador com navegador atualizado (Google Chrome, Firefox ou Edge), conexão estável.
- Ambiente real de produção do Mercado Livre. Usuário logado, para conseguir chegar ao checkout.
- Cronômetro (celular) para medir o tempo de cada tarefa.
- Planilha de observação (seção 8) e questionário SUS (seção 9), em papel ou digital.

## 6. Tarefas

| # | Tarefa | Critério de conclusão |
|---|---|---|
| T1 | Acessar https://www.mercadolivre.com.br | Página inicial carregada |
| T2 | Pesquisar "fone de ouvido Bluetooth" | Lista de resultados exibida |
| T3 | Filtrar apenas **entrega Full** e ordenar pelos **melhores avaliados** | Filtro Full ativo e ordenação aplicada |
| T4 | Escolher um produto e abrir os detalhes | Página do produto aberta |
| T5 | Adicionar o item ao carrinho | Confirmação de item no carrinho |
| T6 | Simular o início da compra | Tela de entrega/pagamento exibida; **interromper antes de pagar** |
| T7 | Retornar à tela inicial | Página inicial exibida |

## 7. Procedimento

1. O avaliador lê o cenário (seção 3) para o usuário, sem mostrar o caminho das telas.
2. O usuário executa T1 a T7 em sequência, falando em voz alta o que pensa (*think aloud*).
3. O avaliador inicia o cronômetro em cada tarefa e o para quando o critério de conclusão é atingido.
4. Se o usuário pedir ajuda ou passar de **2 minutos** numa tarefa, ela é marcada como **sem sucesso** e o avaliador o conduz à tarefa seguinte.
5. Ao final, o usuário responde o questionário SUS sem interferência.

## 8. Instrumento — Planilha de observação

| Tarefa | Tempo (s) | Sucesso (S/N) | Erros | Observações |
|---|---:|:---:|---:|---|
| T1 — Acessar site | | | | |
| T2 — Buscar produto | | | | |
| T3 — Aplicar filtros | | | | |
| T4 — Abrir detalhes | | | | |
| T5 — Adicionar ao carrinho | | | | |
| T6 — Simular compra | | | | |
| T7 — Voltar à tela inicial | | | | |

**Contam como erro:** clique incorreto, perda do caminho (voltar ou navegar sem querer), aplicação errada de filtro ou ordenação, e pedido de ajuda.

## 9. Instrumento — Questionário SUS (System Usability Scale)

Escala de **1 (discordo totalmente)** a **5 (concordo totalmente)**:

1. Eu acho que gostaria de usar este site com frequência.
2. Eu achei o site desnecessariamente complexo.
3. Eu achei o site fácil de usar.
4. Eu acho que precisaria de ajuda de uma pessoa técnica para usar este site.
5. Eu achei que as funções do site estavam bem integradas.
6. Eu achei que havia muita inconsistência no site.
7. Imagino que a maioria das pessoas aprenderia a usar este site rapidamente.
8. Eu achei o site muito complicado de usar.
9. Eu me senti muito confiante usando o site.
10. Eu precisei aprender muitas coisas antes de conseguir usar o site.

**Cálculo:** itens ímpares → (nota − 1); itens pares → (5 − nota). Soma × **2,5** = pontuação de 0 a 100.
**Interpretação:** < 50 inaceitável · 50–67 marginal · **68 média** · > 80 excelente.

## 10. Métricas e critérios de sucesso

| Métrica | Como é medida | Critério de sucesso |
|---|---|---|
| Taxa de sucesso | % de usuários que concluem T5 (adicionar ao carrinho) sem ajuda | **≥ 90%** |
| Tempo de execução | Soma dos tempos de T1 a T7, média entre usuários | **≤ 4 min (240 s)** |
| Erros cometidos | Total de erros por usuário e por tarefa | Usado para localizar problemas |
| Satisfação (SUS) | Média das pontuações SUS | Referência: ≥ 68 |

## 11. Análise planejada

- Comparar a taxa de sucesso e o tempo médio com os critérios da seção 10.
- Identificar as tarefas com mais erros ou maior tempo. Pela complexidade, a hipótese é que **T3 (filtros Full + ordenação)** concentre a maior parte das dificuldades.
- Classificar cada problema encontrado por gravidade (baixa, média, alta) e propor melhorias na interface.

## 12. Riscos e limitações

- O site é um ambiente de produção: o layout, os anúncios e os resultados podem mudar entre as sessões.
- Usuários que já compram no Mercado Livre tendem a ter tempos menores (viés de familiaridade).
- O checkout exige login, e a sessão deve ser interrompida antes de qualquer pagamento.

## Aluno

| Nome | R.A. | GitHub |
|---|---|---|
| Gabriel Santos Furlanette | 24000873-2 | [@GabSantos-pjt](https://github.com/GabSantos-pjt) |
