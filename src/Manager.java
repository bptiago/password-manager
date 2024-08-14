import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {

    private final Scanner scanner = new Scanner(System.in);

    private int getInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                int choice = scanner.nextInt();
                // Se não usar nextLine, o nextInt causa um bug no próximo nextLine essencial.
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("\nIncorreto. Insira somente números.");
                // Isso é para resetar a leitura de input e evitar bug
                scanner.next();
            }
        }
    }

    public void run() {
        System.out.println("Bem-vindo ao gerenciador de senhas\n");

        int choice = getInput("Selecione uma das opções disponíveis:\n1) Gerar senha aleatória\n2) Quebrar hash");
        if (choice == 1) {
            int passwordSize = getInput("Digite o tamanho da senha desejada (mínimo 4): ");
            Generator generator = new Generator();
            String password = generator.generatePassword(passwordSize);
            System.out.println("\nSenha gerada aleatoriamente: " + password);

        } else if (choice == 2) {
            System.out.println("Insira o hash que deseja quebrar: ");
            String hash = scanner.nextLine();

            // Validação de hash
            boolean isHash = hash.getBytes().length == 64;
            while (!isHash) {
                System.out.println("Insira um hash SHA-256 válido:");
                hash = scanner.nextLine();
                isHash = hash.getBytes().length == 64;
            }

            BruteForce bf = new BruteForce();
            bf.forceHash(hash);
        } else {
            System.out.println("Opção inválida. Finalizando o programa.");
        }
    }
}
