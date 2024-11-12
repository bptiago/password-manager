import java.util.Scanner;

public class ProgramManager {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Bem-vindo ao quebrador de hash\n");
        System.out.println("Insira o algoritmo gerador de hash (SHA-256 ou MD5): ");
        String algorithm = scanner.nextLine().toUpperCase();

        while (!algorithm.equals("SHA-256") && !algorithm.equals("MD5")) {
            System.out.println("Algoritmo inválido, insira SHA-256 ou MD5: ");
            algorithm = scanner.nextLine().toUpperCase();
        }

        System.out.println("Insira o hash que deseja quebrar: ");
        String hash = scanner.nextLine();
        boolean isHash = validateHash(hash, algorithm);

        while (!isHash) {
            System.out.println("Insira um hash válido:");
            hash = scanner.nextLine();
            isHash = validateHash(hash, algorithm);
        }

        BruteForce bf = new BruteForce(algorithm);
        bf.forceHash(hash);
    }

    public boolean validateHash(String hash, String algo) {
        return switch (algo) {
            case "SHA-256" -> hash.getBytes().length == 64;
            case "MD5" -> hash.getBytes().length == 32;
            default -> false;
        };
    }
}
