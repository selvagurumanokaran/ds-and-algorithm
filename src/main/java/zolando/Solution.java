package zolando;

import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;

public class Solution {

    public int solution(int N) {
        // write your code in Java SE 8
        StringBuilder num = new StringBuilder("" + N);
        int length = num.length();
        if (length == 1) {
            return 10 + (N - 1);
        }
        if (length > 1 && num.charAt(length - 1) != '0') {
            int secondPart = Integer.parseInt(num.substring(length - 1));
            secondPart--;
            int firstPart = Integer.parseInt(num.substring(0, length - 1));
            firstPart++;
            return Integer.parseInt(firstPart + "" + secondPart);
        }

        int nonZeroIndex = length - 1;
        while (num.charAt(nonZeroIndex) == '0') nonZeroIndex--;
        if (nonZeroIndex != 0) {
            int secondPart = Integer.parseInt(num.substring(nonZeroIndex));
            secondPart--;
            int firstPart = Integer.parseInt(num.substring(0, nonZeroIndex));
            firstPart++;
            return Integer.parseInt(firstPart + "" + secondPart);
        }

        return N * 10;
    }

    public int nextGreatestElementWithSameDigitSum(int N) {
        StringBuilder digits = new StringBuilder(Integer.toString(N));
        int i = digits.length() - 1;
        while (digits.charAt(i) == '0') --i;
        int k = i;
        --i;
        while (i >= 0 && digits.charAt(i) == '9') --i;
        int j = i;
        digits.setCharAt(k, (char) (digits.charAt(k) - 1));
        char[] secondPart = digits.substring(j + 1).toCharArray();
        Arrays.sort(secondPart);
        StringBuilder result = new StringBuilder();

        if (j == -1) result.append('1');
        else {
            digits.setCharAt(j, (char) (digits.charAt(j) + 1));
            for (int l = 0; l <= j; l++) {
                result.append(digits.charAt(l));
            }
        }

        for (char c : secondPart) {
            result.append(c);
        }

        return Integer.parseInt(result.toString());
    }

    public int nextGreatestElementWithSameDigitSumWithChar(int N) {
        String stringN = Integer.toString(N);
        int length = stringN.length();
        Character[] charArr = new Character[length];
        for (int i = 0; i < length; i++) {
            charArr[i] = stringN.charAt(i);
        }
        int z = length - 1;
        while (charArr[z] == '0') --z;
        int b = z;
        z--;
        while (z >= 0 && charArr[z] == '9') --z;
        int a = z;
        StringBuilder builder = new StringBuilder();
        if (a == -1) {
            builder.append('1');
        } else {
            charArr[a] = (char) (charArr[a] + 1);
            for (int i = 0; i <= a; i++) {
                builder.append(charArr[i]);
            }
        }
        charArr[b] = (char) (charArr[b] - 1);
        Arrays.sort(charArr, a + 1, length);
        for (int i = a + 1; i < length; i++) {
            builder.append(charArr[i]);
        }
        return Integer.parseInt(builder.toString());
    }

    private char[] convertIntToString(int N) {
        return Integer.toString(N).toCharArray();
    }

    int nextGreatestElementDumb(int N) {
        int originalSum = getDigitSum(N);
        int otherSum;
        do {
            N++;
            otherSum = getDigitSum(N);
        } while (originalSum != otherSum);
        return N;
    }

    int getDigitSum(int n) {
        String s = "" + n;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += Integer.parseInt("" + s.charAt(i));
        }
        return sum;
    }


    public int findEqualSum(int[] A, int[] B) {
        // write your code in Java SE 8
        int totalA = 0, totalB = 0, result = 0;
        for (int i = 0; i < A.length; i++) {
            totalA += A[i];
            totalB += B[i];
        }
        int sumA = 0, sumB = 0;
        for (int i = 0; i < A.length - 1; i++) {
            sumA += A[i];
            sumB += B[i];
            if (sumA == sumB && sumB == (totalA - sumA) && (totalA - sumA) == (totalB - sumB))
                result++;
        }
        return result;
    }

    public String constructMatrix(int U, int L, int[] C) {
        String impossible = "IMPOSSIBLE";
        StringBuilder upperResult = new StringBuilder();
        StringBuilder lowerResult = new StringBuilder();
        for (int c : C) {
            char upperEle = '0', lowerEle = '0';
            if (c == 1) {
                if (U == 0 && L == 0) return impossible;
                if (U > 0) {
                    upperEle = '1';
                    U--;
                } else {
                    lowerEle = '1';
                    L--;
                }
            } else if (c == 2) {
                if (U == 0 || L == 0) return impossible;
                upperEle = '1';
                lowerEle = '1';
                U--;
                L--;
            }
            upperResult.append(upperEle);
            lowerResult.append(lowerEle);
        }
        if (L > 0 || U > 0) return impossible;
        return upperResult.append(",").append(lowerResult).toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        /*int n = 999;
        for (int i = 1; i <= n; i++) {
            int actual = solution.nextGreatestElementWithSameDigitSumWithChar(i);
            System.out.println("For " + i + " -> " + actual);
            Assert.assertEquals(solution.nextGreatestElementDumb(i), actual);
        }
        System.out.println(solution.nextGreatestElementWithSameDigitSum(29990));
        System.out.println(solution.nextGreatestElementWithSameDigitSumWithChar(29990));*/
        int[] C = {0, 1, 1};
//        System.out.println(solution.constructMatrix(0, 2, C));
        LocalDate now = LocalDate.now();
        System.out.println(now.plusMonths(4));
        System.out.println(now.plusMonths(4).format(DateTimeFormatter.ofPattern("dd/MMM/yy")));
    }
}
