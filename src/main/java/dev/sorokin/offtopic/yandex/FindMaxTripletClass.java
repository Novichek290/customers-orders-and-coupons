package dev.sorokin.offtopic.yandex;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FindMaxTripletClass {
    public static void main(String[] args) {

        //Дан список. Определите, является ли он монотонно возрастающим
        // (то есть верно ли, что каждый элемент этого списка больше предыдущего).
        //
        //Выведите YES, если массив монотонно возрастает и NO в противном случае.

        try (Scanner sc = new Scanner(System.in)) {
            List<Integer> numbers = new ArrayList<>();

            while (sc.hasNextInt()) {
                int num = sc.nextInt();
                numbers.add(num);
            }

            if (numbers.size() < 3) {
                System.err.println("Нужно ввести минимум 3 числа!");
                return;
            }

            for(int i : findMaxTriplet(numbers)) {
                System.out.print(i + " ");
            }
        }
    }

    public static int[] findMaxTriplet(List<Integer> arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        long a = (long) max1 * min1 * min2;
        long b = (long) max1 * max2 * max3;

        return a > b ? new int[]{max1, min1, min2} : new int[]{max1, max2, max3};
    }
}