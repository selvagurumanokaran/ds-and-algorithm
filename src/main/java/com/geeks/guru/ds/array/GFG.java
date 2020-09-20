package com.geeks.guru.ds.array;

// Java program to find maximum
// subarray size, such that all
// subarrays of that size have
// sum less than K.

import java.util.Arrays;

class GFG {

    // Search for the maximum length
    // of required subarray.
    static int bsearch(int prefixsum[],
                       int n, int k) {
        // Initialize result
        int ans = -1;

        // Do Binary Search for largest
        // subarray size
        int left = 1, right = n;

        while (left <= right) {
            int mid = (left + right) / 2;

            // Check for all subarrays after mid
            int i;
            for (i = mid; i <= n; i++) {

                // Checking if all the subarrays
                // of a size is less than k.
                if (prefixsum[i] - prefixsum[i - mid] > k)
                    break;
            }

            // All subarrays of size mid have
            // sum less than or equal to k
            if (i == n + 1) {
                left = mid + 1;
                ans = mid;
            }

            // We found a subrray of size mid
            // with sum greater than k
            else
                right = mid - 1;
        }

        return ans;
    }

    // Return the maximum subarray size, such
    // that all subarray of that size have
    // sum less than K.
    static int maxSize(int arr[], int n, int k) {

        // Initialize prefix sum array as 0.
        int prefixsum[] = new int[n + 1];
        Arrays.fill(prefixsum, 0);

        // Finding prefix sum of the array.
        for (int i = 0; i < n; i++)
            prefixsum[i + 1] = prefixsum[i] + arr[i];

        return bsearch(prefixsum, n, k);
    }

    // Driver code
    public static void main(String arg[]) {
        int arr[] = {1, 2, 10, 4};
        int n = arr.length;
        int k = 14;
        System.out.println(maxSize(arr, n, k));
    }
}

// This code is contributed by Anant Agarwal.

