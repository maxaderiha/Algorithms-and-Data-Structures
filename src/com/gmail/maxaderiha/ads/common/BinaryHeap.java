package com.gmail.maxaderiha.ads.common;

import java.io.*;
import java.util.Scanner;

import static com.gmail.maxaderiha.ads.common.TaskFirst.write;

public class BinaryHeap {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int tmp = sc.nextInt();

            if (tmp == 1) {
                write("output.txt", "Yes");
                System.exit(0);
            }

            int[] heap = new int[tmp + 1];

            int i = 1;
            while (sc.hasNextInt()) {
                heap[i++] = sc.nextInt();
            }

            sc.close();

            for (i = 1; i < heap.length; i++) {
                if (2 * i < heap.length) {
                    if (heap[2 * i] < heap[i]) {
                        write("output.txt", "No");
                        System.exit(0);
                    }
                }
                if (2 * i + 1 < heap.length) {
                    if (heap[2 * i + 1] < heap[i]) {
                        write("output.txt", "No");
                        System.exit(0);
                    }
                }
            }
            write("output.txt", "Yes");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
