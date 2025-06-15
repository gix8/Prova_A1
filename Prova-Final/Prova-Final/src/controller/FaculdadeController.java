package controller;
import java.util.List;
import java.util.ArrayList;
import model.*;
import console.Console;

// Classe FaculdadeController que gerencia o cadastro de alunos, professores, cursos e aulas
public class FaculdadeController {

    // Listas para armazenar alunos, professores, cursos e aulas
    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Aula> aulas = new ArrayList<>();
  
    // Getters para acessar as listas de alunos, professores, cursos e aulas
    public List<Aluno> getAlunos() {return alunos;}
    public List<Professor> getProfessores() {return professores;}
    public List<Curso> getCursos() {return cursos;}
    public List<Aula> getAulas() {return aulas;}

    public void cadastrarAluno() {
        System.out.println("\n--- Cadastro de Aluno ---");
        System.out.print("Nome: ");
        String nome = Console.lerString();
        System.out.print("Gmail: ");
        String gmail = Console.lerString();
        System.out.print("Senha: ");
        String senha = Console.lerString();
        System.out.print("RGM: ");
        String rgm = Console.lerString();
        System.out.print("Curso: ");
        String cursoAluno = Console.lerString();

        Aluno aluno = new Aluno(nome, gmail, senha, rgm, cursoAluno);
        // Adiciona o aluno à lista de alunos
        alunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public void cadastrarProfessor() {
        System.out.println("\n--- Cadastro de Professor ---");
        System.out.print("Nome: ");
        String nome = Console.lerString();
        System.out.print("Gmail: ");
        String gmail = Console.lerString();
        System.out.print("Senha: ");
        String senha = Console.lerString();
        System.out.print("Curso ministrado: ");
        String cursoMinistrado = Console.lerString();

        Professor professor = new Professor(nome, gmail, senha, cursoMinistrado);
        professores.add(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    public void cadastrarCurso() {
        System.out.println("\n--- Cadastro de Curso ---");
        System.out.print("Nome do curso: ");
        String nomeCurso = Console.lerString();
        System.out.print("Descrição do curso: ");
        String descricao = Console.lerString();

        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado. Cadastre um professor antes de criar o curso.");
            return;
        }

        System.out.println("Professores disponíveis:");
        // Exibe a lista de professores disponíveis para selecionar o responsável pelo curso
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            System.out.printf("%d - %s (Curso ministrado: %s)%n", i + 1, p.getNome(), p.getCursoM());
        }
        System.out.print("Escolha o número do professor responsável pelo curso: ");
        // Lê a entrada do usuário para selecionar o professor responsável
        // Verifica se a entrada é válida e se o índice está dentro do intervalo da lista de professores
        int profIndex;
        // Tratamento de exceção para garantir que a entrada seja um número válido
        // Se a entrada for inválida, exibe uma mensagem de erro e cancela a operação
        try {
            profIndex = Console.lerInt() - 1;
            if (profIndex < 0 || profIndex >= professores.size()) {
                System.out.println("Professor inválido. Operação cancelada.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Operação cancelada.");
            return;
        }
        Professor professorResponsavel = professores.get(profIndex);
        // Cria um novo curso com o nome, descrição e professor responsável selecionado

        Curso curso = new Curso(nomeCurso, descricao, professorResponsavel);

        boolean adicionandoModulos = true;
        // Loop para adicionar módulos ao curso
        // Enquanto o usuário quiser adicionar módulos, continua solicitando a entrada
        while (adicionandoModulos) {
            System.out.println("\nAdicionar módulo ao curso?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não, terminar cadastro");
            System.out.print("Opção: ");
            int opcao = Console.lerInt();

            if (opcao == 1) {
                // Chama o método para cadastrar um módulo
                // Se o módulo for criado com sucesso, adiciona ao curso
                Modulo modulo = cadastrarModulo();
                if (modulo != null) {
                    curso.adicionarModulo(modulo);
                }
            } else {
                adicionandoModulos = false;
            }
        }

        cursos.add(curso);
        System.out.println("Curso cadastrado com sucesso!");
    }

    private Modulo cadastrarModulo() {
        System.out.println("\n--- Cadastro de Módulo ---");
        System.out.print("Título do módulo: ");
        String tituloModulo = Console.lerString();
        System.out.print("Descrição do módulo: ");
        String descricaoModulo = Console.lerString();

        Modulo modulo = new Modulo(tituloModulo, descricaoModulo);

        boolean adicionandoAulas = true;
        // Loop para adicionar aulas ao módulo
        // Enquanto o usuário quiser adicionar aulas, continua solicitando a entrada
        while (adicionandoAulas) {
            System.out.println("\nAdicionar aula ao módulo?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não, terminar módulo");
            System.out.print("Opção: ");
            int opcao = Console.lerInt();

            if (opcao == 1) {
                Aula aula = cadastrarAula();
                // Chama o método cadastrarAula para criar uma nova aula
                if (aula != null) {
                    modulo.adicionarAula(aula);
                }
            } else {
                adicionandoAulas = false;
            }
        }

        return modulo;
    }

    private Aula cadastrarAula() {
        System.out.println("\n--- Cadastro de Aula ---");
        System.out.print("Título da aula: ");
        String titulo = Console.lerString();
        System.out.print("Conteúdo da aula: ");
        String conteudo = Console.lerString();
        System.out.print("Duração da aula (minutos): ");
        int duracao = 0;
        try {
            duracao = Console.lerInt();
        } catch (Exception e) {
            System.out.println("Duração inválida, configurada para 0 minutos.");
        }

        Aula aula = new Aula(titulo, conteudo, duracao);
        aulas.add(aula); 
        return aula;
    }


    public void adicionarAulaAoCurso() {
        System.out.println("\n--- Adicionar Aula a um Curso ---");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado. Cadastre um curso antes de adicionar aulas.");
            return;
        }

        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < cursos.size(); i++) {// Exibe a lista de cursos disponíveis
            // Itera sobre a lista de cursos e exibe o nome de cada curso com seu índice
            Curso curso = cursos.get(i);
            System.out.printf("%d - %s%n", i + 1, curso.getNome());
        }
        System.out.print("Escolha o número do curso: ");
        int cursoIndex;
        try {
            cursoIndex = Console.lerInt() - 1;// Lê a entrada do usuário para selecionar o curso
            if (cursoIndex < 0 || cursoIndex >= cursos.size()) {
                System.out.println("Curso inválido. Operação cancelada.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Operação cancelada.");
            return;
        }
        Curso cursoSelecionado = cursos.get(cursoIndex);

        Aula aula = cadastrarAula();
        if (aula != null) {
            Modulo modulo = selecionarModulo(cursoSelecionado);
            if (modulo != null) {
                modulo.adicionarAula(aula);
                System.out.println("Aula adicionada com sucesso ao módulo do curso!");
            }
        }
    }

    // Método para selecionar um módulo de um curso
    private Modulo selecionarModulo(Curso cursoSelecionado) {
        List<Modulo> modulos = cursoSelecionado.getModulos();
        if (modulos.isEmpty()) {
            System.out.println("Nenhum módulo cadastrado neste curso.");
            return null;
        }
        System.out.println("Módulos disponíveis:");// Exibe a lista de módulos disponíveis no curso selecionado
        // Itera sobre a lista de módulos e exibe o título de cada módulo com seu índice
        for (int i = 0; i < modulos.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, modulos.get(i).getTitulo());
        }
        System.out.print("Escolha o número do módulo: ");
        int moduloIndex;
        try {
            moduloIndex = Console.lerInt() - 1;// Lê a entrada do usuário para selecionar o módulo
            // Verifica se o índice do módulo está dentro do intervalo da lista de módulos
            if (moduloIndex < 0 || moduloIndex >= modulos.size()) {
                System.out.println("Módulo inválido. Operação cancelada.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Operação cancelada.");
            return null;
        }
        return modulos.get(moduloIndex);// Retorna o módulo selecionado pelo usuário
    }

}

