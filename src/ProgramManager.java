import java.util.Scanner;

public class ProgramManager {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Bem-vindo ao quebrador de hash\n");
        System.out.println("Insira o hash que deseja quebrar: ");
        String hash = scanner.nextLine();

        boolean isHash = hash.getBytes().length == 64;
        while (!isHash) {
            System.out.println("Insira um hash SHA-256 válido:");
            hash = scanner.nextLine();
            isHash = hash.getBytes().length == 64;
        }

        BruteForce bf = new BruteForce();
        bf.forceHash(hash);
    }
}
