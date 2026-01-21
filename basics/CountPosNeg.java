public class CountPosNeg {
    public static void main(String[] args) {
        int[] arr = {-2, 5, -1, 3, -4};
        int pos = 0, neg = 0;

        for (int num : arr) {
            if (num >= 0) {
                pos++;
            } else {
                neg++;
            }
        }

        System.out.println("Positive = " + pos);
        System.out.println("Negative = " + neg);
    }
}