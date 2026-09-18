import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedArrayUtils {

    // =====================================================
    // 1. DEPTH
    // =====================================================

    public static int depth(Object array) {

        if (array == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (!array.getClass().isArray()) {
            return 0;
        }

        int length = Array.getLength(array);

        // Empty array has depth 1
        if (length == 0) {
            return 1;
        }

        int maxDepth = 0;

        for (int i = 0; i < length; i++) {

            Object element = Array.get(array, i);

            int currentDepth = depth(element);

            if (currentDepth > maxDepth) {
                maxDepth = currentDepth;
            }
        }

        return 1 + maxDepth;
    }


    // =====================================================
    // 2. SUM
    // =====================================================

    public static int sum(Object array) {

        if (array == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (!array.getClass().isArray()) {

            if (array instanceof Integer) {
                return (Integer) array;
            }

            throw new IllegalArgumentException(
                    "Array can contain only integers"
            );
        }

        int total = 0;

        int length = Array.getLength(array);

        for (int i = 0; i < length; i++) {

            Object element = Array.get(array, i);

            total += sum(element);
        }

        return total;
    }


    // =====================================================
    // 3. FLATTEN
    // =====================================================

    public static int[] flatten(Object array) {

        if (array == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        List<Integer> values = new ArrayList<>();

        flattenRecursive(array, values);

        int[] result = new int[values.size()];

        for (int i = 0; i < values.size(); i++) {
            result[i] = values.get(i);
        }

        return result;
    }


    private static void flattenRecursive(
            Object array,
            List<Integer> values) {

        if (!array.getClass().isArray()) {

            if (array instanceof Integer) {
                values.add((Integer) array);
                return;
            }

            throw new IllegalArgumentException(
                    "Array can contain only integers"
            );
        }

        int length = Array.getLength(array);

        for (int i = 0; i < length; i++) {

            Object element = Array.get(array, i);

            flattenRecursive(element, values);
        }
    }


    // =====================================================
    // 4. CONTAINS
    // =====================================================

    public static boolean contains(
            Object array,
            int target) {

        if (array == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (!array.getClass().isArray()) {

            if (array instanceof Integer) {
                return (Integer) array == target;
            }

            throw new IllegalArgumentException(
                    "Array can contain only integers"
            );
        }

        int length = Array.getLength(array);

        for (int i = 0; i < length; i++) {

            Object element = Array.get(array, i);

            if (contains(element, target)) {
                return true;
            }
        }

        return false;
    }


    // =====================================================
    // 5. TRANSPOSE
    // =====================================================

    public static Object transpose(Object matrix) {

        if (matrix == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (!matrix.getClass().isArray()) {
            throw new IllegalArgumentException(
                    "Input must be a 2-D matrix"
            );
        }

        int rows = Array.getLength(matrix);

        if (rows == 0) {
            return new int[0][0];
        }

        Object firstRow = Array.get(matrix, 0);

        if (firstRow == null ||
                !firstRow.getClass().isArray()) {

            throw new IllegalArgumentException(
                    "Input must be a 2-D matrix"
            );
        }

        int columns = Array.getLength(firstRow);

        // Check that every row is an integer array
        // and has the same number of columns.
        for (int i = 0; i < rows; i++) {

            Object row = Array.get(matrix, i);

            if (row == null ||
                    !row.getClass().isArray()) {

                throw new IllegalArgumentException(
                        "Input must be a 2-D matrix"
                );
            }

            if (row.getClass().getComponentType() != int.class) {

                throw new IllegalArgumentException(
                        "Matrix must contain integers"
                );
            }

            if (Array.getLength(row) != columns) {

                throw new IllegalArgumentException(
                        "Matrix must be rectangular"
                );
            }
        }

        int[][] result = new int[columns][rows];

        for (int i = 0; i < rows; i++) {

            int[] row = (int[]) Array.get(matrix, i);

            for (int j = 0; j < columns; j++) {

                result[j][i] = row[j];
            }
        }

        return result;
    }


    // =====================================================
    // MAIN / DRIVER PROGRAM
    // =====================================================

    public static void main(String[] args) {

        // -------------------------------------------------
        // DEPTH - Test Case 1
        // -------------------------------------------------

        Object array1 = new int[][]{
                {1, 2},
                {3, 4}
        };

        System.out.println(
                "Depth Test 1: " +
                NestedArrayUtils.depth(array1)
        );


        // -------------------------------------------------
        // DEPTH - Test Case 2
        // -------------------------------------------------

        Object array2 = new Object[]{
                new int[]{1, 2},
                new Object[]{
                        new int[]{3, 4}
                }
        };

        System.out.println(
                "Depth Test 2: " +
                NestedArrayUtils.depth(array2)
        );


        // -------------------------------------------------
        // SUM - Test Case 1
        // -------------------------------------------------

        Object sumArray1 = new Object[]{
                new int[]{1, 2, 3},
                new int[]{4, 5}
        };

        System.out.println(
                "Sum Test 1: " +
                NestedArrayUtils.sum(sumArray1)
        );


        // -------------------------------------------------
        // SUM - Test Case 2
        // -------------------------------------------------

        Object sumArray2 = new Object[]{
                new int[]{10, 20},
                new Object[]{
                        new int[]{30, 40}
                }
        };

        System.out.println(
                "Sum Test 2: " +
                NestedArrayUtils.sum(sumArray2)
        );


        // -------------------------------------------------
        // FLATTEN - Test Case 1
        // -------------------------------------------------

        Object flattenArray1 = new Object[]{
                new int[]{1, 2},
                new int[]{3, 4}
        };

        System.out.println(
                "Flatten Test 1: " +
                Arrays.toString(
                        NestedArrayUtils.flatten(flattenArray1)
                )
        );


        // -------------------------------------------------
        // FLATTEN - Test Case 2
        // -------------------------------------------------

        Object flattenArray2 = new Object[]{
                new int[]{1},
                new Object[]{
                        new int[]{2, 3},
                        new int[]{4}
                },
                new int[]{5}
        };

        System.out.println(
                "Flatten Test 2: " +
                Arrays.toString(
                        NestedArrayUtils.flatten(flattenArray2)
                )
        );


        // -------------------------------------------------
        // CONTAINS - Test Case 1
        // -------------------------------------------------

        Object containsArray1 = new Object[]{
                new int[]{1, 2},
                new int[]{3, 4}
        };

        System.out.println(
                "Contains Test 1: " +
                NestedArrayUtils.contains(
                        containsArray1,
                        3
                )
        );


        // -------------------------------------------------
        // CONTAINS - Test Case 2
        // -------------------------------------------------

        Object containsArray2 = new Object[]{
                new int[]{5, 6},
                new Object[]{
                        new int[]{7, 8}
                }
        };

        System.out.println(
                "Contains Test 2: " +
                NestedArrayUtils.contains(
                        containsArray2,
                        10
                )
        );


        // -------------------------------------------------
        // TRANSPOSE - Test Case 1
        // -------------------------------------------------

        Object matrix1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println(
                "Transpose Test 1: " +
                Arrays.deepToString(
                        (int[][])
                                NestedArrayUtils.transpose(matrix1)
                )
        );


        // -------------------------------------------------
        // TRANSPOSE - Test Case 2
        // -------------------------------------------------

        Object matrix2 = new int[][]{
                {10, 20},
                {30, 40},
                {50, 60}
        };

        System.out.println(
                "Transpose Test 2: " +
                Arrays.deepToString(
                        (int[][])
                                NestedArrayUtils.transpose(matrix2)
                )
        );
    }
}