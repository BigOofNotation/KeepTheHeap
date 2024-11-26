public interface MaxHeapInterface
{

    /**
     * Removes and returns the maximum value from the heap.
     * 
     * @return the maximum value in the heap
     * @throws IllegalStateException if the heap is empty
     */
    int remove();

    /**
     * Returns the first n elements of the heap without modifying it.
     * 
     * @param count the number of elements to retrieve
     * @return an array containing the first n elements of the heap
     */
    int[] getFirstElements(int count);

    /**
     * Returns the total number of swaps performed during heap operations.
     * 
     * @return the number of swaps
     */
    int getSwapCount();

    /**
     * Swaps two elements in the heap at the given indices.
     * 
     * @param index1 the index of the first element to swap
     * @param index2 the index of the second element to swap
     */
    void swap(int index1, int index2);
}