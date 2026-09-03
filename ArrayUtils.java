import java.util.Arrays;

public class ArrayUtils {

    // 1. Sum of all elements
    public static int sum(int[] values) {
        int total = 0;

        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }

        return total;
    }

    // 2. Reverse an array
    public static int[] reverse(int[] values) {
        int[] result = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[values.length - 1 - i];
        }

        return result;
    }

    // 3. Find maximum element
    public static int max(int[] values) {
        int maximum = values[0];

        for (int i = 1; i < values.length; i++) {
            if (values[i] > maximum) {
                maximum = values[i];
            }
        }

        return maximum;
    }

    // 4. Check whether target exists
    public static boolean contains(int[] values, int target) {

        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return true;
            }
        }

        return false;
    }

    // 5. Filter strings by prefix
    public static String[] filterByPrefix(String[] words, String prefix) {

        // First count how many words match
        int count = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(prefix)) {
                count++;
            }
        }

        // Create result array with exact size
        String[] result = new String[count];

        int index = 0;

        // Store matching words
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(prefix)) {
                result[index] = words[i];
                index++;
            }
        }

        return result;
    }

    // 6. Merge two sorted arrays
    public static int[] mergeSorted(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        // Compare elements from both arrays
        while (i < a.length && j < b.length) {

            if (a[i] <= b[j]) {
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
            }

            k++;
        }

        // Copy remaining elements from a
        while (i < a.length) {
            result[k] = a[i];
            i++;
            k++;
        }

        // Copy remaining elements from b
        while (j < b.length) {
            result[k] = b[j];
            j++;
            k++;
        }

        return result;
    }

    // 7. Rotate array left by k positions
    public static int[] rotateLeft(int[] values, int k) {

        int n = values.length;

        if (n == 0) {
            return values;
        }

        // Handle k greater than array length
        k = k % n;

        // Temporary array
        int[] result = new int[n];

        // Move elements
        for (int i = 0; i < n; i++) {
            result[i] = values[(i + k) % n];
        }

        // Copy result back into original array
        System.arraycopy(result, 0, values, 0, n);

        return values;
    }

    // 8. Find duplicates
    public static int[] findDuplicates(int[] values) {

        // Temporary array to store duplicates
        int[] temp = new int[values.length];

        int duplicateCount = 0;

        for (int i = 0; i < values.length; i++) {

            // Check if value appeared before
            boolean appearedBefore = false;

            for (int j = 0; j < i; j++) {
                if (values[i] == values[j]) {
                    appearedBefore = true;
                    break;
                }
            }

            // If already processed, skip
            if (appearedBefore) {
                continue;
            }

            // Check if value appears again later
            boolean duplicate = false;

            for (int j = i + 1; j < values.length; j++) {
                if (values[i] == values[j]) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                temp[duplicateCount] = values[i];
                duplicateCount++;
            }
        }

        // Create result array with exact size
        int[] result = new int[duplicateCount];

        for (int i = 0; i < duplicateCount; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    // Test driver
    public static void main(String[] args) {

        int[] numbers = { 1, 2, 3, 4, 5 };

        System.out.println("Original array: "
                + Arrays.toString(numbers));

        // Sum
        System.out.println("Sum: "
                + sum(numbers));

        // Reverse
        System.out.println("Reverse: "
                + Arrays.toString(reverse(numbers)));

        // Maximum
        System.out.println("Maximum: "
                + max(numbers));

        // Contains
        System.out.println("Contains 3: "
                + contains(numbers, 3));

        System.out.println("Contains 10: "
                + contains(numbers, 10));

        // Filter by prefix
        String[] words = {
                "apple",
                "application",
                "banana",
                "app",
                "orange"
        };

        System.out.println(
                "Words starting with 'app': "
                        + Arrays.toString(
                                filterByPrefix(words, "app")));

        // Merge sorted arrays
        int[] a = { 1, 3, 5, 7 };
        int[] b = { 2, 4, 6, 8 };

        System.out.println(
                "Merged array: "
                        + Arrays.toString(
                                mergeSorted(a, b)));

        // Rotate left
        int[] rotateArray = { 1, 2, 3, 4, 5 };

        System.out.println(
                "Before rotation: "
                        + Arrays.toString(rotateArray));

        rotateLeft(rotateArray, 2);

        System.out.println(
                "After rotating left by 2: "
                        + Arrays.toString(rotateArray));

        // Find duplicates
        int[] duplicateArray = {
                1, 2, 3, 2, 4, 5, 1, 3, 6
        };

        System.out.println(
                "Duplicates: "
                        + Arrays.toString(
                                findDuplicates(duplicateArray)));
    }
}