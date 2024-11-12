import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MaxHeapOperations
{
    private int[] heap;
    private int size;
    private int swapCount;

    // Constructor to initialize heap array
    public MaxHeapOperations(int capacity)
    {
        heap = new int[capacity + 1]; // +1 to make index 1-based
        size = 0;
        swapCount = 0;
    }

    // Insert element into the heap
    public void insert(int value)
    {
        heap[++size] = value; // Insert at the end
        heapifyUp(size); // Maintain max-heap property
    }

    // Heapify up (for insertion)
    private void heapifyUp(int index)
    {
        while (index > 1 && heap[index] > heap[index / 2])
        { // Parent is at index/2
            swap(index, index / 2);
            index /= 2;
        }
    }

    // Remove the max element (root)
    public int remove()
    {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int max = heap[1]; // Root of the heap
        heap[1] = heap[size--]; // Move the last element to the root
        heapifyDown(1); // Restore max-heap property
        return max;
    }

    // Heapify down (for removal)
    private void heapifyDown(int index)
    {
        while (2 * index <= size)
        { // While there is at least one child
            int largest = 2 * index; // Left child
            if (largest + 1 <= size && heap[largest + 1] > heap[largest])
            {
                largest++; // Use the right child if it is larger
            }
            if (heap[index] >= heap[largest]) break; // Heap property satisfied
            swap(index, largest);
            index = largest;
        }
    }

    // Swap two elements in the heap
    private void swap(int i, int j)
    {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        swapCount++;
    }

    // Get the first `count` elements of the heap
    public int[] getFirstElements(int count)
    {
        return Arrays.copyOfRange(heap, 1, Math.min(count + 1, size + 1));
    }

    // Get the number of swaps performed
    public int getSwapCount()
    {
        return swapCount;
    }

    // Main method
    public static void main(String[] args)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\matth\\Downloads\\data.txt"));
             FileWriter writer = new FileWriter("heap_output.txt")){

            MaxHeapOperations maxHeap = new MaxHeapOperations(100);

            // Read integers from data.txt
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] numbers = line.split("\\s+");
                for (String num : numbers) {
                    maxHeap.insert(Integer.parseInt(num.trim()));
                }
            }

            // Output the first 10 elements of the heap
            writer.write("First 10 elements after insertion: " + Arrays.toString(maxHeap.getFirstElements(10)) + "\n");

            // Output the number of swaps performed
            writer.write("Number of swaps performed: " + maxHeap.getSwapCount() + "\n");

            // Perform 10 removals
            for (int i = 0; i < 10; i++)
            {
                maxHeap.remove();
            }

            // Output the first 10 elements after 10 removals
            writer.write("First 10 elements after 10 removals: " + Arrays.toString(maxHeap.getFirstElements(10)) + "\n");

        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
        }
    }
}
