package console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private static Scanner scanner = new Scanner(System.in);

    public static int lerInt() {

        int valor = 0;

        while (true) {
            
            try {

                valor = scanner.nextInt();
                break;
            
            } catch (InputMismatchException e) {
            
                System.out.println("erro ao tentar ler valor: o valor precisa ser inteiro.");
                System.out.print("digite novamente: ");
            
            }finally{
                scanner.nextLine();
            }

        }

        return valor;
    }

    public static float lerFloat() {

        float valor = 0;

        while (true) {

            try{

                valor = scanner.nextFloat();
                break;

            }catch (InputMismatchException e) {

                System.out.println("Erro ao tentar ler valor: o valor precisa ser um real");
                System.out.print("Digite novamente: ");

            }finally{
                scanner.nextLine();
            }
            
        }

        return valor;

    }

    public static String lerString() {

        String valor = scanner.nextLine();
        return valor;

    }



}
