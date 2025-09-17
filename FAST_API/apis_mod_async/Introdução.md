# FastAPI

FastAPI é um framework de alta performance para criação de APIs e websites em **Python**. Ele foi desenvolvido por Sebastián Ramírez, que utilizou extensivamente os "type hints" do Python, juntamente com as bibliotecas **Pydantic** e **Starlette**, além de fazer uso nativo de programação assíncrona (`async`).

**Características principais:**
- Baseado em padrões de documentação para APIs: **OpenAPI** e **JSON Schema**.

---

## Programação Assíncrona

### Execução de um programa Python

1. O interpretador Python cria um processo no sistema operacional.
2. O processo Python cria uma thread (linha de execução) para executar o código.

```
(Source code) -> (Compiler -> Bytecode -> Python Virtual Machine)
         ^                                         |
         |----------------- Library Modules --------|
```

---

## Concorrência

Concorrência é a execução de múltiplas instruções sequenciais ao mesmo tempo.

**Tipos de concorrência:**
- Programação paralela
- Programação assíncrona

**Pontos fundamentais:**
- Ordem de execução
- Recursos compartilhados

---

### Programação Paralela

Melhor utilizada em tarefas que fazem uso intensivo de **CPU** e **GPU**.

**Exemplos:**
- Operações em strings
- Algoritmos de busca
- Processamento gráfico
- Algoritmos de processamento numérico
- Etc.

---

### Programação Assíncrona

Melhor utilizada em tarefas que exigem uso intensivo de **I/O**, como:

- Leitura ou escrita em banco de dados
- Chamadas a Web Services (APIs)
- Cópia, upload ou download de dados
- Etc.