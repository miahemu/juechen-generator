package com.juechen.acm;

import java.util.Scanner;

/**
  * ACM 输入模板
  * @author juechen1111
*/
public class MainTemplate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 连续输入模式
        while (scanner.hasNext()) {
            // 读取输入元素个数
            int n = scanner.nextInt();

            // 读取数组
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            int result = 1;
            for (int num : arr) {
                result *= num;
            }

            System.out.println("PRODUCT:" + result);
        }
        scanner.close();
    }
}