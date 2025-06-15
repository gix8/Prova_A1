import view.FaculdadeView;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Cadastro da Faculdade!");
        FaculdadeView view = new FaculdadeView();// Cria uma instância da FaculdadeView
        view.mostrarMenu();// Exibe o menu da FaculdadeView e inicia a aplicação
    }
}
