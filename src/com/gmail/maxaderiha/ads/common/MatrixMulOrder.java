package com.gmail.maxaderiha.ads.common;

import java.io.*;
import java.util.Scanner;

import static com.gmail.maxaderiha.ads.common.TaskFirst.write;

public class MatrixMulOrder {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int tmp = sc.nextInt();
            int[] v = new int[tmp + 1];

            for (int i = 0; i < v.length - 1; i++) {
                v[i] = sc.nextInt();
                tmp = sc.nextInt();
            }

            v[v.length - 1] = tmp;

            write("output.txt", Integer.toString(matrixMultiplicationOrder(v)));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static int matrixMultiplicationOrder(int[] size) {
        int n = size.length - 1;
        int[][] m = new int[n + 1][n + 1];

        for (int p = 2; p <= n; p++) {
            for (int i = 1; i <= n - p + 1; i++) {
                int j = i + p - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    m[i][j] = Math.min(m[i][j],
                            m[i][k] + m[k + 1][j] + size[i - 1] * size[k] * size[j]);
                }
            }
        }

        return m[1][n];
    }
}