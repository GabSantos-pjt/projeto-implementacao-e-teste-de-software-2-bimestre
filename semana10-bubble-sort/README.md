# Semana 10 — Bubble Sort (Prática de TDD)

Projeto Maven com Java 17 e JUnit 5, baseado no material da disciplina
([teste_software_2026/aulas/SEMANA10/bubblesort-tdd](https://github.com/JoaoChoma/teste_software_2026/tree/main/aulas/SEMANA10/bubblesort-tdd)).
Enunciado completo em [`REQUISITOS.md`](REQUISITOS.md).

## Estrutura

```
semana10-bubble-sort/
├── pom.xml
├── REQUISITOS.md
└── src/
    ├── main/java/br/edu/exemplo/BubbleSort.java      # implementação de ordenar()
    └── test/java/br/edu/exemplo/BubbleSortTest.java  # testes fornecidos (sem alteração)
```

## Requisitos x casos de teste

| Requisito | Entrada | Esperado | Teste |
|---|---|---|---|
| 1 — Ordenar vetor desordenado | `[5, 3, 1, 4, 2]` | `[1, 2, 3, 4, 5]` | `deveOrdenarVetorDesordenado` |
| 2 — Manter vetor já ordenado | `[1, 2, 3, 4, 5]` | `[1, 2, 3, 4, 5]` | `deveManterVetorJaOrdenado` |
| 3 — Ordenar valores repetidos | `[3, 1, 3, 2, 1]` | `[1, 1, 2, 3, 3]` | `deveOrdenarVetorComValoresRepetidos` |

## Ciclo TDD

1. **RED** — com `ordenar()` vazio, `mvn test` roda os 3 testes: o Requisito 2 passa (o vetor já está ordenado) e os Requisitos 1 e 3 falham.
2. **GREEN** — implementação do Bubble Sort: vizinhos fora de ordem são trocados, e o maior valor "borbulha" até o fim a cada passada. Os 3 testes passam.
3. **REFACTOR** — extração do método `trocar()`, redução do limite a cada passada (`fim--`) e parada antecipada quando não há troca (melhor caso O(n) para vetor já ordenado).

Os casos de teste fornecidos **não foram alterados**.

## Executar

```bash
mvn test
```

Saída esperada: `Tests run: 3, Failures: 0, Errors: 0, Skipped: 0`.
