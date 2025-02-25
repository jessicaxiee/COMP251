import java.util.*;
import java.math.BigInteger;

public class A2_Q2 {
    public static BigInteger num_swaps(int[] numbers){
        return BigInteger.valueOf(divide(numbers, 0, numbers.length - 1));
    }

    public static int divide(int arr[], int leftLim, int rightLim){     //divide, sorting array
        //Base Case: Subarray is length of 1 elmnt
        if (leftLim >= rightLim){
            return 0;
        }
        //Middle of subarray
        int middle = leftLim + (rightLim - leftLim) / 2;

        int leftHalf = divide(arr, leftLim, middle);            //Recursive call for left subarray
        int rightHalf = divide(arr, middle+1, rightLim);            //Recursive call for right subarray

        int mergeSwap = conquer(arr, leftLim, rightLim, middle);
        return mergeSwap + leftHalf + rightHalf;
    }

    public static int conquer(int[] arr, int leftLim, int rightLim, int middle){
        //Variables inits
        int i = leftLim;
        int j = middle + 1;
        int k = 0;
        int numOfSwaps = 0;

        int[] temp = new int[rightLim - leftLim +1]; //stores new elmnts

        while (i <= middle && j <= rightLim){
            if (arr[i] < arr[j]){   //no swap: correct order already
                temp[k++] = arr[i++];
            }
            else {  //arr[j] < arr[i]
                temp[k++] = arr[j++];
                numOfSwaps += (middle - i + 1);
            }
        }
        while (i <= middle){
            temp[k++] = arr[i++];   //appending remaining LEFT subarray elmnts
        }
        while (j <= rightLim){
            temp[k++] = arr[j++];    //appending remaining RIGHT subarray elmnts
        }
        for (int l = 0; l < temp.length; l++) { //copying elemnts in sorted order in og arr
            arr[leftLim + l] = temp[l];
        }
        return numOfSwaps;
    }
}
