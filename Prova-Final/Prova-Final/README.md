-- Sistema de Gestão Acadêmica

-- Descrição Geral

Este projeto tem como objetivo implementar um sistema educacional em Java para gerenciamento de cursos, módulos, aulas, professores e alunos. A aplicação foi desenvolvida com foco em aplicar os principais conceitos de **orientação a objetos**, **tratamento de exceções**, **persistência de dados com arquivos**, e **boas práticas de codificação**.

A interface é interativa via console, permitindo ao usuário realizar cadastros, listagens e simular interações típicas de um sistema de uma instituição de ensino.

---

-- Funcionalidades Principais

- Cadastro de usuários (alunos, professores, administradores)
- Gerenciamento de cursos, módulos e aulas
- Associação entre professores e aulas
- Associação entre alunos e cursos
- Persistência de dados em arquivos `.txt`
- Interface em linha de comando para navegação e entrada de dados
- Validações com tratamento de exceções
- Organização do projeto em pacotes MVC (`model`, `view`, `controller`)

---

-- Estrutura de Classes e Relações

-- Herança
- `Usuario` é a superclasse de `Aluno`, `Professor` e `Administrador`, com atributos e comportamentos comuns.

-- Interfaces
- Interface `Console` define métodos interativos para entrada de dados.

-- Composição e Associação
- Um `Curso` contém múltiplos `Modulo`s.
- Cada `Modulo` possui múltiplas `Aula`s.
- `Professor` é associado a `Aula`s.
- `Aluno` é associado a `Curso`.

-- Polimorfismo
- Métodos sobrepostos entre `Usuario`, `Aluno`, `Professor` e `Administrador`, como identificação e cadastro.

---

-- Como Executar o Projeto

1. **Pré-requisitos**: Java JDK 8 ou superior
2. **Compilação**:
   ```bash
   javac -d bin src/**/*.java
--Execução:

bash
Copiar
Editar
java -cp bin Principal

-- Uso de Ferramentas de Apoio
ChatGPT foi utilizado exclusivamente para correção de erros, validação de estrutura de pacotes, e revisão do README.

Blackbox.ai foi utilizado como apoio no desenvolvimento de métodos e lógica de controle nas classes FaculdadeController e FaculdadeView.

-- Persistência de Dados
O sistema lê e grava arquivos .txt como Cursos.txt e Cadastros.txt, garantindo a manutenção dos dados entre execuções.

-- Boas Práticas e Organização
Estrutura baseada em MVC com pacotes separados: model, controller, view, console

Uso de coleções para armazenar objetos (ArrayList)

Tratamento robusto de exceções com try-catch

Métodos coesos e bem nomeados (Clean Code) 

-- Referências
Java SE API Documentation: https://docs.oracle.com/javase/8/docs/api/

Slides e aulas do curso

IDE utilizada: VS Code com extensão Java