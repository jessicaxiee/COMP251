import java.util.*;
import java.math.BigInteger;

public class A2_Q2 {
    public static BigInteger num_swaps(int[] numbers) {
        int[] temp = new int[numbers.length];
        return BigInteger.valueOf(divide(numbers, temp, 0, numbers.length - 1));
    }

    private static long divide(int[] arr, int[] temp, int leftLim, int rightLim) {
        // Base Case: Subarray is length of 1 element
        if (leftLim >= rightLim) {
            return 0;
        }

        // Find the middle index of the subarray
        int middle = leftLim + (rightLim - leftLim) / 2;

        // Recursively divide the array
        long leftHalf = divide(arr, temp, leftLim, middle);
        long rightHalf = divide(arr, temp, middle + 1, rightLim);

        // Merge and count swaps during the merge
        long mergeSwap = conquer(arr, temp, leftLim, rightLim, middle);

        return leftHalf + rightHalf + mergeSwap;
    }

    private static long conquer(int[] arr, int[] temp, int leftLim, int rightLim, int middle) {
        int i = leftLim;
        int j = middle + 1;
        int k = leftLim;
        long numOfSwaps = 0;

        // Merge two halves while counting swaps (inversions)
        while (i <= middle && j <= rightLim) {
            if (arr[i] <= arr[j]) {  // No swap needed
                temp[k++] = arr[i++];
            } else {  // Swap needed, count the inversions
                temp[k++] = arr[j++];
                numOfSwaps += (middle - i + 1);  // All remaining elements in the left half need to swap with arr[j]
            }
        }

        // Copy remaining elements from the left subarray (if any)
        while (i <= middle) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements from the right subarray (if any)
        while (j <= rightLim) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted elements back into the original array
        System.arraycopy(temp, leftLim, arr, leftLim, rightLim - leftLim + 1);

        return numOfSwaps;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 1, 2};
        System.out.println(num_swaps(arr1) + " swaps"); // Output: 2 swaps

        int[] arr2 = {9, 5, 4, 3, 7, 2, 6, 1, 8, 10};
        System.out.println(num_swaps(arr2)); // Output: 22

        int[] arr3 = {17, 86, 51, 65, 59, 75, 16, 7, 95, 93};
        System.out.println(num_swaps(arr3)); // Output: 33
    }
}
