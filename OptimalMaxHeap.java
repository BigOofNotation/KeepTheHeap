import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class OptimalMaxHeap {

    private int[] heap;
    private int size;
    private int swapCount;

    // Initializes heap array from input array 'entries'
    // Builds heap using reheap method
    public OptimalMaxHeap(int[] entries) {
        size = entries.length;
        heap = new int[size + 1];
        swapCount = 0;

        // copies input array elements into heap array
        for (int index = 0; index < entries.length; index++) {
            heap[index + 1] = entries[index];
        }

        /* calls reheap on all non-leaf nodes from bottom to top
        to ensure heap property */
        for (int rootIndex = size / 2; rootIndex > 0; rootIndex--) {
            reheap(rootIndex);
        }
    }

    // Ensures max-heap property by moving value at specified index
    private void reheap(int index) {
        int largest = index;
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        // Check if left bigger
        // Compare curr node with left children
        if(leftChild <= size && heap[leftChild] > heap[largest]){
            largest = leftChild;
        }

        //check if right bigger
        // Compare curr node with right children
        if(rightChild <= size && heap[rightChild] > heap[largest]){
            largest = rightChild;
        }

        // if largest is not curr node, swap with largest child
        // and recursively call reheap on affected child
        if(largest != index) {
            swap(index, largest);
            reheap(largest);
        }

    }

    // Swaps two elements in heap
    // Increment swapCount
    private void swap(int one, int two){
        int temp = heap[one];
        heap[one] = heap[two];
        heap[two] = temp;
        swapCount++;
    }

    // Removes & returns max element (root of heap)
    // Replaces root with last element, reduces size
    // Calls reheap to maintain heap property
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

    // Returns first 'count' elements of heap
    public int[] getFirstElements(int count) {
        return Arrays.copyOfRange(heap, 1, Math.min(count + 1, size + 1));
    }

    // Returns total num of swaps performed
    public int getSwapCount() {
        return swapCount;
    }
    
    // Main method for testing
    // Read data from data.txt
    // Initializes heap using OptimalMaxHeap
    // Write heap state & swap count to optimal_heap_output
    // demonstrates heap removals, outputs the heap's state after removals
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
             FileWriter writer = new FileWriter("optimal_heap_output.txt")) {
            
            // reads all lines of input file
            int[] data = reader.lines().mapToInt(Integer::parseInt).toArray();
            
            //initialize heap & build it
            OptimalMaxHeap optimalHeap = new OptimalMaxHeap(data);

            writer.write("First 10 elements after building the heap: "  + Arrays.toString(optimalHeap.getFirstElements(10)) + "\n");
            // writes into optimal_heap_output.txt
            writer.write("Number of swaps performed: " + optimalHeap.getSwapCount() + "\n");

            for (int i = 0; i < 10; i++) {
                optimalHeap.remove();
            }

            //write elements onto optimal_heap_output.txt
            writer.write("First 10 elements after 10 removals: " + Arrays.toString(optimalHeap.getFirstElements(10)) + "\n");

        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
        }
        
    }
    
} 