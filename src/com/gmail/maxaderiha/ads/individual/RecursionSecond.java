package com.gmail.maxaderiha.ads.individual;

import java.io.*;
import java.util.*;

import static com.gmail.maxaderiha.ads.common.TaskFirst.write;

public class RecursionSecond {
    private static int b, s;
    private static int[][] matrix;
    private static int[] prices, count;
    private static int[][][][][] result;
    private static Map<String, Integer> map;

    static {
        try {
            Scanner sc = new Scanner(new File("discount.in"));
            map = new HashMap<>();
            count = new int[5];

            b = Integer.valueOf(sc.nextLine());

            int[] tmpPrices = new int[5];

            int fakeKey = Integer.MAX_VALUE;
            for (int i = 0; i < 5; i++) {
                String[] tmp = {Integer.toString(fakeKey--), "0", "0"};
                if (b - i > 0) {
                    tmp = sc.nextLine().split("\\s");
                }
                map.put(tmp[0], i);
                count[i] = Integer.valueOf(tmp[1]) < 5 ? Integer.valueOf(tmp[1]) : 5;
                tmpPrices[i] = Integer.valueOf(tmp[2]);
            }

            s = Integer.valueOf(sc.nextLine());
            matrix = new int[b + s][5];
            prices = new int[b + s];

            for (int i = 0; i < b; i++) {
                prices[i] = tmpPrices[i];
                matrix[i][i] = 1;
            }

            for (int i = b; i < b + s; i++) {
                String tmp = sc.nextLine();
                int size = Integer.valueOf(tmp.split("\\s")[0]);

                int count = 1;
                String[] discount = tmp.split("\\s");

                for (int j = 0; j < size; j++) {
                    matrix[i][map.get(discount[count++])] = Integer.valueOf(discount[count]) < 5 ? Integer.valueOf(discount[count]) : 5;
                    prices[i] = Integer.valueOf(discount[++count]);
                }
            }

            sc.close();

            result = new int[6][6][6][6][6];
            for (int i = 0; i <= count[0]; i++) {
                for (int j = 0; j <= count[1]; j++) {
                    for (int k = 0; k <= count[2]; k++) {
                        for (int l = 0; l <= count[3]; l++) {
                            for (int m = 0; m <= count[4]; m++) {
                                result[i][j][k][l][m] = i * tmpPrices[0] + j * tmpPrices[1] +
                                        k * tmpPrices[2] + l * tmpPrices[3] + m * tmpPrices[4];
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void run() {
        for (int i = 0; i < b + s; i++) {
            for (int n = matrix[i][0]; n <= count[0]; n++) {
                for (int n1 = matrix[i][1]; n1 <= count[1]; n1++) {
                    for (int n2 = matrix[i][2]; n2 <= count[2]; n2++) {
                        for (int n3 = matrix[i][3]; n3 <= count[3]; n3++) {
                            for (int n4 = matrix[i][4]; n4 <= count[4]; n4++) {
                                if (result[n][n1][n2][n3][n4] != Integer.MAX_VALUE) {
                                    int c = n - matrix[i][0], c1 = n1 - matrix[i][1], c2 = n2 - matrix[i][2];
                                    int c3 = n3 - matrix[i][3], c4 = n4 - matrix[i][4];
                                    if (result[n][n1][n2][n3][n4] > result[c][c1][c2][c3][c4] + prices[i]) {
                                        result[n][n1][n2][n3][n4] = result[c][c1][c2][c3][c4] + prices[i];
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        write("discount.out", Integer.toString(result[count[0]][count[1]][count[2]][count[3]][count[4]]));
    }

    public static void main(String[] args) {
        run();
    }
}
