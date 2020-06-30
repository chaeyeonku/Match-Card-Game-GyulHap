import java.util.ArrayList;

public class QuickSort extends Sort {

    // * Reference: GeeksforGeeks, contributor: Rajat Mishra

    @Override
    public int partition(ArrayList<Integer> arr, int low, int high) {
        int lowest = low - 1;
        int pivot = arr.get(high);
        for (int i= low; i < high; i++){
            if (arr.get(i) < pivot) {
                lowest++;

                int temp = arr.get(lowest);
                arr.set(lowest, arr.get(i));
                arr.set(i, temp);
            }
        }
        int temp = arr.get(lowest+1);
        arr.set(lowest+1, pivot);
        arr.set(high, temp);

        return lowest+1;
    }

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            sort(arr, low, pi-1);
            sort(arr, pi+1, high);

        }
        System.out.println(arr);
        return arr;
    }
}
