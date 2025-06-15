package view;
import controller.ArquivoController;
import controller.FaculdadeController;
import console.Console;

public class FaculdadeView {

// Classe FaculdadeView que representa a interface do usuário para o sistema de cadastro da faculdade
    private FaculdadeController controller;
    private ArquivoController controllerA;

    public FaculdadeView() {
        controller = new FaculdadeController();// Inicializa o controller de Faculdade
        controllerA = new ArquivoController();// Inicializa o controller de Arquivo
    }

    public void mostrarMenu() {
        boolean sair = false;// Variável para controlar o loop do menu
        
        while (!sair) {// Loop para exibir o menu até que o usuário escolha sair
            System.out.println("\n=== Sistema de Cadastro da Faculdade ===");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Cadastrar Curso");
            System.out.println("4 - Adicionar Aula ao Curso");
            System.out.println("5 - Salvar Cadastros e cursos em Arquivo");
            System.out.println("6 - Listar Cadastros de Alunos e Professores");
            System.out.println("7 - Listar Cursos Cadastrados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = Console.lerInt();

            switch (opcao) {// Estrutura de controle para lidar com as opções do menu
                case 1:
                    controller.cadastrarAluno();
                    break;
                case 2:
                    controller.cadastrarProfessor();
                    break;
                case 3:
                    controller.cadastrarCurso();
                    break;
                case 4:
                    controller.adicionarAulaAoCurso();
                    break;
                case 5:
                    controllerA.salvarCadastros(controller.getAlunos(), controller.getProfessores());// Salva os cadastros de alunos e professores
                    controllerA.salvarCursos(controller.getCursos());// Salva os cursos cadastrados
                    System.out.println("Cadastros e cursos salvos com sucesso!");
                    break;
                case 6:
                    controllerA.listarCadastros();
                    break;
                case 7:
                    controllerA.listarCursos();
                    break;
                
                case 0:
                    sair = true;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");// Mensagem de erro para opção inválida
            }
        }
    }
}

