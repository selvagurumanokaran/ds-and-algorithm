package zolando;

class SolutionOld {

    //Task 2
    public int solution1(int[] A, int[] B) {
        // write your code in Java SE 8
        long sumA = 0, sumB = 0;
        for (int i = 0; i < A.length; i++) {
            sumA += A[i];
            sumB += B[i];
        }
        long currSumA = 0;
        long currSumB = 0;
        int fairCnt = 0;
        for (int i = 0; i < A.length - 1; i++) {
            currSumA += A[i];
            currSumB += B[i];
            if (currSumA == (sumA - currSumA) && (sumA - currSumA) == currSumB && currSumB == (sumB - currSumB))
                fairCnt++;
        }
        return fairCnt;
    }

    //Task 3
    public int solution2(int N) {
        // write your code in Java SE 8
        String strN = Integer.toString(N);
        int index = findFirstNonZeroDigit(strN);
        if (strN.substring(0, index).equals("")) {
            int lastDigit = Integer.parseInt(strN.substring(0, 1)) - 1;
            return Integer.parseInt("1" + strN.substring(1) + lastDigit);
        }
        int firstHalfN = Integer.parseInt(strN.substring(0, index));
        int secondHalfN = Integer.parseInt(strN.substring(index));
        String resultStr = (firstHalfN + 1) +
                Integer.toString(secondHalfN - 1);
        return Integer.parseInt(resultStr);
    }


    //Task 1
    public String solution(int U, int L, int[] C) {

        int sum = 0;
        for (int c = 0; c < C.length; c++) {
            if (C[c] < 0 || C[c] > 2) return "IMPOSSIBLE";
            sum += C[c];
        }
        if (L + U != sum) return "IMPOSSIBLE";
        // write your code in Java SE 8
        int[][] M = new int[2][C.length];
        for (int u = 0; u < C.length && U > 0; u++) {
            if (C[u] != 0) {
                M[0][u] = 1;
                U--;
            }
        }
        if (U != 0) return "IMPOSSIBLE";
        for (int l = 0; l < C.length && L > 0; l++) {
            M[1][l] = C[l] - M[0][l];
            if (M[1][l] == 1) L--;
        }
        if (L != 0) return "IMPOSSIBLE";
        StringBuilder result = new StringBuilder();
        for (int u = 0; u < C.length; u++) result.append(M[0][u]);
        result.append(",");
        for (int l = 0; l < C.length; l++) result.append(M[1][l]);
        return result.toString();
    }

    private int findFirstNonZeroDigit(String strN) {
        int i = strN.length() - 1;
        int index = -1;
        while (i >= 0 && index == -1) {
            if (strN.charAt(i) != '0') index = i;
            i--;
        }
        return index;
    }

    public static void main(String[] args) {
        SolutionOld solutionOld = new SolutionOld();
        System.out.println(solutionOld.solution(2, 2, new int[]{2, 0, 2, 0}));
    }
}

