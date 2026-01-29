package strings;

public class StringBuilderDemo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Java");

        sb.append(" Programming");
        sb.reverse();

        System.out.println(sb);
    }
}
