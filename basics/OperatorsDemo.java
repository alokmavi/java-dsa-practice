public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 10, b = 3;

        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);
        System.out.println(a % b);

        System.out.println(a > b);
        System.out.println(a == b);

        boolean result = (a > b) && (b > 0);
        System.out.println(result);
    }
}

