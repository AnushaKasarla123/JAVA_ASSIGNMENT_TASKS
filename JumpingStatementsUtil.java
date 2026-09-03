import java.util.ArrayList;
import java.util.List;

public class JumpingStatementsUtil {

    // 1. Find the first prime number
    public static int firstPrime(int[] numbers) {

        int firstPrimeNumber = -1;

        for (int i = 0; i < numbers.length; i++) {

            int number = numbers[i];

            if (number < 2) {
                continue;
            }

            boolean isPrime = true;

            for (int j = 2; j * j <= number; j++) {

                if (number % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                firstPrimeNumber = number;
                break;
            }
        }

        return firstPrimeNumber;
    }


    // 2. Sum elements until the sum exceeds the limit
    public static int sumUntilLimit(int[] numbers, int limit) {

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {

            sum += numbers[i];

            if (sum > limit) {
                break;
            }
        }

        return sum;
    }


    // 3. Return even numbers, skipping multiples of 8
    public static List<Integer> filteredEvenNumbers(int[] numbers) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {

            // Skip multiples of 8
            if (numbers[i] % 8 == 0) {
                continue;
            }

            // Add only even numbers
            if (numbers[i] % 2 == 0) {
                result.add(numbers[i]);
            }
        }

        return result;
    }


    // 4. Check whether pattern appears consecutively
    public static boolean containsSequence(
            int[] numbers,
            int[] pattern) {

        if (pattern.length == 0) {
            return true;
        }

        if (pattern.length > numbers.length) {
            return false;
        }

        boolean found = false;

        search:
        for (int i = 0;
             i <= numbers.length - pattern.length;
             i++) {

            for (int j = 0; j < pattern.length; j++) {

                if (numbers[i + j] != pattern[j]) {
                    continue search;
                }
            }

            found = true;
            break;
        }

        return found;
    }


    // 5. Recursive search
    public static int recursiveSearch(
            int[] numbers,
            int target) {

        return recursiveSearch(numbers, target, 0);
    }


    // Helper method for recursion
    private static int recursiveSearch(
            int[] numbers,
            int target,
            int index) {

        // Base case: target not found
        if (index >= numbers.length) {
            return -1;
        }

        // Target found
        if (numbers[index] == target) {
            return index;
        }

        // Search the next index
        return recursiveSearch(
                numbers,
                target,
                index + 1
        );
    }


    // Test driver
    public static void main(String[] args) {

        // Test firstPrime()
        int[] primeNumbers = {
            10, 15, 21, 17, 25, 29
        };

        System.out.println(
            "First prime: "
            + firstPrime(primeNumbers)
        );


        // Test sumUntilLimit()
        int[] values = {
            10, 20, 30, 40, 50
        };

        System.out.println(
            "Sum until limit 55: "
            + sumUntilLimit(values, 55)
        );


        // Test filteredEvenNumbers()
        int[] evenNumbers = {
            2, 4, 8, 10, 16, 18, 20, 24, 30
        };

        System.out.println(
            "Filtered even numbers: "
            + filteredEvenNumbers(evenNumbers)
        );


        // Test containsSequence()
        int[] numbers = {
            1, 2, 3, 4, 5, 6, 7
        };

        int[] pattern1 = {
            3, 4, 5
        };

        int[] pattern2 = {
            4, 6, 7
        };

        System.out.println(
            "Contains [3, 4, 5]: "
            + containsSequence(numbers, pattern1)
        );

        System.out.println(
            "Contains [4, 6, 7]: "
            + containsSequence(numbers, pattern2)
        );


        // Test recursiveSearch()
        int[] searchArray = {
            10, 20, 30, 40, 50
        };

        System.out.println(
            "Index of 30: "
            + recursiveSearch(searchArray, 30)
        );

        System.out.println(
            "Index of 100: "
            + recursiveSearch(searchArray, 100)
        );
    }
}