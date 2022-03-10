import java.util.Scanner;

public class Hw3 {
    public static void main(String[] args) {
        Hw3 h = new Hw3();
        h.inchToCm();
        h.cmToInch();
    }
    public void inchToCm(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("cm으로 변경할 inch를 입력하세요.");
        int inch = scanner.nextInt();
        System.out.printf("%d inch는 %.2f cm입니다.%n",inch,inch*2.54f);
    }
    public void cmToInch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("inch로 변경할 cm을 입력하세요.");
        double cm = scanner.nextDouble();
        System.out.printf("%.2f cm는 %.2f inch입니다.%n",cm,cm/2.54);
    }
}
