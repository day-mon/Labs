package Labs.Other;

public class BinarySearch {
    public static void main(String[] args) {
        

        int arr[] = new int[101];
            for (int i =0; i < 101; i++) {
                arr[i] = i;
            }
        
            System.out.println(bsR(arr, 57, 0, arr.length-1));
    }

    
    public static int bs(int[] num, int num1) {

        int left = 0; 
        int right = num.length -1;

    


        while (left <= right) {
            int midpoint = left + (right - left) / 2;
            if (num[midpoint] == num1) {
                return midpoint;
            } else {
                if (num[midpoint] < num1) {
                    left = midpoint+1;
                } else {
                    right = midpoint - 1;
                }
            }
        }

        return -1;
    }


    public static int bsR(int num[], int num1, int left, int right) {
        int mid = left + (right - left) / 2;

        if (num1 == num[mid]) return mid;
        if (num[mid] < num1) {return bsR(num, num1, left+1, right);}
        else return bsR(num, num1, left, right-1);

    }
}
