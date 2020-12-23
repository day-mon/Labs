package Labs.Other;

public class sort {
    public static void main(String[] args) {
        
    }



    public static int[] selection(int[]arr) {

        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min=j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    public static int[] quick(int[] arr, int first, int last) {
        if (last <= first) {
            return arr;
        }

        int pivot = arr[first];
        int pos = last;
    }
}
