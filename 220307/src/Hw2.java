import java.util.Scanner;

public class Hw2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        String email = null;
        String mobile = null;
        System.out.print("input name: ");
        if(scanner.hasNext()){
            name = scanner.nextLine();
        }
        System.out.print("input email: ");
        if(scanner.hasNext()){
            email = scanner.nextLine();
        }
        System.out.print("input mobile: ");
        if(scanner.hasNext()){
            mobile = scanner.nextLine();
        }
        System.out.println("OK");
        System.out.printf("%n");
        System.out.printf("name:   %20s%n",name);
        System.out.printf("email:  %20s%n",email);
        System.out.printf("mobile: %20s%n",mobile);
    }
}
