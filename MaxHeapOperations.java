import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MaxHeapOperations implements MaxHeapInterface
{
    private int[] heap;
    private int size;
    private int swapCount;

    public MaxHeapOperations(int capacity)
    {
        heap = new int[capacity + 1];
        size = 0;
        swapCount = 0;
    }

    public void insert(int value)
    {
        heap[++size] = value; 
        heapifyUp(size); 
    }

    private void heapifyUp(int index)
    {
        while (index > 1 && heap[index] > heap[index / 2]){
            swap(index, index / 2);
            index /= 2;
        }
    }

    public int remove() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int max = heap[1]; 
        heap[1] = heap[size--]; 
        heapifyDown(1); 
        return max;
    }

    // Heapify down (for removal)
    private void heapifyDown(int index)
    {
        while (2 * index <= size) { // While at least one child
            int largest = 2 * index; // Left child
            if (largest + 1 <= size && heap[largest + 1] > heap[largest]) {
                largest++; // Use the right child if larger
            }
            if (heap[index] >= heap[largest]) break; 
            swap(index, largest);
            index = largest;
        }
    }

    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        swapCount++;
    }

    public int[] getFirstElements(int count) {
        return Arrays.copyOfRange(heap, 1, Math.min(count + 1, size + 1));
    }

    public int getSwapCount() {
        return swapCount;
    }

    
    public static void main(String[] args)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
             FileWriter writer = new FileWriter("heap_output.txt")){

            MaxHeapOperations maxHeap = new MaxHeapOperations(100);

            // Read data.txt
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] numbers = line.split("\\s+");
                for (String num : numbers) {
                    maxHeap.insert(Integer.parseInt(num.trim()));
                }
            }

            writer.write("First 10 elements after insertion: " + Arrays.toString(maxHeap.getFirstElements(10)) + "\n");

            writer.write("Number of swaps performed: " + maxHeap.getSwapCount() + "\n");

            for (int i = 0; i < 10; i++) {
                maxHeap.remove();
            }

            writer.write("First 10 elements after 10 removals: " + Arrays.toString(maxHeap.getFirstElements(10)) + "\n");

        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
        }
    }
}
