package ed.lab;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SortingAlgorithms {

    private static final Random RANDOM = new Random();

    public static <T extends Comparable<T>> void highPivotQuickSort(T[] array) {
        quickSort(array, 0, array.length - 1, a -> (l, h) -> highPartition(a, l, h));
    }

        public static <T extends Comparable<T>> void lowPivotQuickSort(T[] array) {
        quickSort(array, 0, array.length - 1, a -> (l, h) -> lowPartition(a, l, h));
    }

    public static <T extends Comparable<T>> void randomPivotQuickSort(T[] array) {
        quickSort(array, 0, array.length - 1, a -> (l, h) -> randomPartition(a, l, h));
    }

    private static <T extends Comparable<T>> int highPartition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                T aux = array[j];
                array[j] = array[i];
                array[i] = aux;
            }
        }

        T aux = array[i + 1];
        array[i + 1] = array[high];
        array[high] = aux;

        return i + 1;
    }

    private static <T extends Comparable<T>> int lowPartition(T[] array, int low, int high) {
        T pivot = array[low];
        int i = high + 1;

        for (int j = high; j > low; j--) {
            if (array[j].compareTo(pivot) >= 0) {
                i--;
                T aux = array[j];
                array[j] = array[i];
                array[i] = aux;
            }
        }

        T aux = array[i - 1];
        array[i - 1] = array[low];
        array[low] = aux;

        return i - 1;
    }

    private static <T extends Comparable<T>> int randomPartition(T[] array, int low, int high) {
        int pivot = RANDOM.nextInt(high - low + 1) + low;

        T aux = array[pivot];
        array[pivot] = array[high];
        array[high] = aux;

        return highPartition(array, low, high);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array,
                                                            int low,
                                                            int high,
                                                            Function<T[], BiFunction<Integer, Integer, Integer>> partitioner) {
        if (high <= low) {
            return;
        }

        int pivot = partitioner.apply(array).apply(low, high);

        quickSort(array, low, pivot - 1, partitioner);
        quickSort(array, pivot + 1, high, partitioner);
    }


}
