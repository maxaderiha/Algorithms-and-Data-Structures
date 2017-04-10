package com.gmail.maxaderiha.ads.common;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static com.gmail.maxaderiha.ads.common.TaskFirst.write;


public class BinomialHeap {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            BigInteger i = new BigInteger(sc.nextLine());
            getDegreesOfTwo(i.toString(2));
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getDegreesOfTwo(String str) {
        StringBuilder sb = new StringBuilder("");
        int degree = 0;
        for (int i = str.length() - 1; i >= 0; i--, degree++) {
            if (str.charAt(i) == '1') {
                sb.append(Integer.toString(degree) + "\n");
            }
        }
        write("output.txt", sb.substring(0, sb.length() - 1));
    }
}
