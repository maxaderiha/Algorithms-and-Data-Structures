package com.gmail.maxaderiha.ads.individual;

import java.io.*;
import java.util.*;

import static com.gmail.maxaderiha.ads.common.TaskFirst.write;

public class RecursionFirst {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), temp = (int) Math.sqrt(a);

            if (a < b) {
                write("output.txt", "-1");
                System.exit(0);
            }

            if (a >= b && a <= c) {
                write("output.txt", "1");
                System.exit(0);
            }

            int[] Dividers = new int[2 * temp + 1];
            int index = 0;


            for (int i = 1; i <= temp; i++) {
                if ((a % i) == 0) {
                    Dividers[index] = i;
                    index++;
                }
            }

            for (int k = 0; k < index; ++k) {
                Dividers[2 * index - 1 - k] = (a / Dividers[k]);
            }

            index = 2 * index - 1;
            int[] lengths = new int[index + 1];

            for (int i = 0; i < index; ++i) {
                lengths[i] = 2 * index;
            }
            lengths[index] = 1;

            for (int i = index - 1; i >= 1; --i) {
                for (int j = i + 1; j <= index; ++j) {
                    if (Dividers[j] / Dividers[i] >= b && Dividers[j] / Dividers[i] <= c && Dividers[j] % Dividers[i] == 0) {
                        if (lengths[i] > (lengths[j] + 1)) {
                            lengths[i] = lengths[j] + 1;
                        }
                    }
                }
            }

            int min = 2 * index;

            if (a > c) {
                temp = c;
            } else {
                temp = a - 1;
            }
            for (int i = 0; i <= index; ++i) {
                if (Dividers[i] >= b && Dividers[i] <= temp) {
                    if (min > lengths[i]) {
                        min = lengths[i];
                    }
                }
            }

            if (min == 2 * index) {
                write("output.txt", "-1");
            } else {
                write("output.txt", Integer.toString(min));
            }
            sc.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
