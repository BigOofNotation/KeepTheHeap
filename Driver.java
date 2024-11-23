import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
             FileWriter writer = new FileWriter("heap_final_output.txt")) {

            // Read data file
            int[] data = reader.lines().mapToInt(Integer::parseInt).toArray();

            // Sequential method
            MaxHeapOperations sequentialHeap = new MaxHeapOperations(100);
            for (int value : data) {
                sequentialHeap.insert(value);
            }

            writer.write("Heap built using sequential insertions: " +
                    Arrays.toString(sequentialHeap.getFirstElements(10)).replace("[", "").replace("]", "") + "\n");

            writer.write("Number of swaps in the heap creation: " + sequentialHeap.getSwapCount() + "\n");

            for (int i = 0; i < 10; i++) {
                sequentialHeap.remove();
            }

            writer.write("Heap after 10 removals: " + Arrays.toString(sequentialHeap.getFirstElements(10)).replace("[", "").replace("]", "") + "\n\n");

            // Optimal Method
            OptimalMaxHeap optimalHeap = new OptimalMaxHeap(data);

            writer.write("Heap built using optimal method: " +
                    Arrays.toString(optimalHeap.getFirstElements(10)).replace("[", "").replace("]", "") + "\n");

            writer.write("Number of swaps in the heap creation: " + optimalHeap.getSwapCount() + "\n");

            for (int i = 0; i < 10; i++) {
                optimalHeap.remove();
            }

            writer.write("Heap after 10 removals: " +
                    Arrays.toString(optimalHeap.getFirstElements(10)).replace("[", "").replace("]", "") + "\n");

        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
        }
    }
}