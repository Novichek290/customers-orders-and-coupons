package dev.sorokin.offtopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SequenceType {
    //По последовательности чисел во входных данных определите ее вид:
    //
    //
    //CONSTANT – последовательность состоит из одинаковых значений
    //ASCENDING – последовательность является строго возрастающей
    //WEAKLY ASCENDING – последовательность является нестрого возрастающей
    //DESCENDING – последовательность является строго убывающей
    //WEAKLY DESCENDING – последовательность является нестрого убывающей
    //RANDOM – последовательность не принадлежит ни к одному из вышеупомянутых типов
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        while(sc.hasNextInt()) {
            numbers.add(sc.nextInt());
        }

        boolean isConst = true;
        boolean isRandom = true;

        boolean isAscending = true;
        boolean isWeakAscend = true;

        boolean isDescending = true;
        boolean isWeaklyDescend = true;



        for(int i = 0; i < numbers.size()-2; i++) {
            int a = numbers.get(i);
            int b = numbers.get(i+1);
            int c = numbers.get(i+2);

            if(a!=b) isConst = false;

            if(a > b && b > c) {
                isAscending = false;
                isWeakAscend = false;
            }

            if(a < b && b < c) {
                isDescending = false;
                isWeaklyDescend = false;
            }

            if(isConst || isAscending || isWeakAscend || isDescending || isWeaklyDescend) isRandom = false;

        }
        if(isConst) System.out.println("CONSTANT");
        if(isAscending) System.out.println("ASCENDING");
        if(isWeakAscend) System.out.println("WEAKLY ASCENDING");
        if(isDescending) System.out.println("DESCENDING");
        if(isWeaklyDescend) System.out.println("WEAKLY DESCENDING");
        if(isRandom) System.out.println("RANDOM");

    }
}
