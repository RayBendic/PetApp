package algorithms;


import org.junit.Test;

public class HeapTest {

    @Test
    public void buildMinHeap() {

        int[] array = {3, 4, 2, 3, 1, 8, 9};
        int i = array.length / 2;

    }

    public void min_heapify(int[] array, int i){

        int root = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left < array.length && array[left] < array[root]){
            root = left;
        }

        if (right < array.length && array[right] < array[root]){
            root = right;
        }

        if (root != i){
            swap(array, root, i);
            min_heapify(array, root);
        }

    }

    public static final void swap (int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
