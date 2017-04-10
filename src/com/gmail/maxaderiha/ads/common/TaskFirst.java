package com.gmail.maxaderiha.ads.common;

import java.io.*;
import java.util.*;

public class TaskFirst {

    public static void main(String[] args) {
        try {
            write("output.txt",Long.toString(task("input.txt")));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    static long task(String path) throws IOException{
        long result = 0;
        Scanner sc = new Scanner(new File(path));
        Set<Integer> numbers = new HashSet<>();
        while (sc.hasNext()) {
            numbers.add(sc.nextInt());
        }
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            result+=it.next();
        }
        return result;
    }

    public static void write(String fileName, String text) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
