import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class OptimalMaxHeap implement MaxHeapInterface{

    private int[] heap;
    private int size;
    private int swapCount;

    public OptimalMaxHeap(int[] entries) {
        size = entries.length;
        heap = new int[size + 1];
        swapCount = 0;

        for (int index = 0; index < entries.length; index++) {
            heap[index + 1] = entries[index];
        }

        for (int rootIndex = size / 2; rootIndex > 0; rootIndex--) {
            reheap(rootIndex);
        }
    }


    private void reheap(int index) {
        int largest = index;
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        // Check if left bigger
        if(leftChild <= size && heap[leftChild] > heap[largest]){
            largest = leftChild;
        }

        //check if right bigger
        if(rightChild <= size && heap[rightChild] > heap[largest]){
            largest = rightChild;
        }

        if(largest != index) {
            swap(index, largest);
            reheap(largest);
        }

    }


    private void swap(int one, int two){
        int temp = heap[one];
        heap[one] = heap[two];
        heap[two] = temp;
        swapCount++;
    }


    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap[1]; 
        heap[1] = heap[size]; 
        size--; 
        reheap(1); 
        return max; 
    }


    public int[] getFirstElements(int count) {
        return Arrays.copyOfRange(heap, 1, Math.min(count + 1, size + 1));
    }

    public int getSwapCount() {
        return swapCount;
    }
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
             FileWriter writer = new FileWriter("optimal_heap_output.txt")) {

            int[] data = reader.lines().mapToInt(Integer::parseInt).toArray();

            OptimalMaxHeap optimalHeap = new OptimalMaxHeap(data);

            writer.write("First 10 elements after building the heap: "  + Arrays.toString(optimalHeap.getFirstElements(10)) + "\n");

            writer.write("Number of swaps performed: " + optimalHeap.getSwapCount() + "\n");

            for (int i = 0; i < 10; i++) {
                optimalHeap.remove();
            }

            writer.write("First 10 elements after 10 removals: " + Arrays.toString(optimalHeap.getFirstElements(10)) + "\n");

        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
        }
        
    }
    
} 
