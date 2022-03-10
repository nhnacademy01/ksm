import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.cal();
    }

    public void cal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 연산을 하실건가요? (+,-,*,/)");
        String op = scanner.next();
        calculation(op.toCharArray());
    }

    public void calculation(char[] op) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;

        System.out.println("피연산자 2수를 입력하세요.");
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        switch (op[0]) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                System.out.println("연산자 입력이 잘못됨.");
        }

        System.out.printf("%d %c %d = %d 입니다.%n", a, op[0], b, result);
    }
}
