package com.juechen.acm;

import java.util.Scanner;

/**
  * ACM 输入模板
  * @author ${author!'Juechen'}
*/
public class MainTemplate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
<#if loop>
        // 连续输入模式
        while (scanner.hasNext()) {
</#if>
            // 读取输入元素个数
            int n = scanner.nextInt();

            // 读取数组
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

<#if operationType == "SUM">
            int result = 0;
            for (int num : arr) {
                result += num;
            }
<#elseif operationType == "PRODUCT">
            int result = 1;
            for (int num : arr) {
                result *= num;
            }
<#elseif operationType == "MAX">
            int result = arr[0];
            for (int num : arr) {
                if (num > result) {
                    result = num;
                }
            }
<#elseif operationType == "MIN">
            int result = arr[0];
            for (int num : arr) {
                if (num < result) {
                    result = num;
                }
            }
</#if>

            System.out.println("${outputText}" + result);
<#if loop>
        }
</#if>
        scanner.close();
    }
}
