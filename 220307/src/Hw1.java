import java.util.Scanner;

public class Hw1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("input: ");
        int s = scanner.next().charAt(0);

        System.out.printf("\\u%X",s);
    }
}
