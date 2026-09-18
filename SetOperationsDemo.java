import java.util.LinkedHashSet;
import java.util.Set;

public class SetOperationsDemo {

    // Generic Set class
    static class MySet<T> {

        // LinkedHashSet provides:
        // 1. Unique elements
        // 2. O(1) average-case lookup
        // 3. Preserves insertion order
        private final LinkedHashSet<T> elements;

        // Constructor
        public MySet() {
            elements = new LinkedHashSet<>();
        }

        // Private constructor used internally
        private MySet(LinkedHashSet<T> elements) {
            this.elements = elements;
        }

        // Static factory method
        @SafeVarargs
        public static <T> MySet<T> of(T... elements) {
            MySet<T> set = new MySet<>();

            for (T element : elements) {
                set.elements.add(element);
            }

            return set;
        }

        // Union
        // Returns all elements present in either set
        public MySet<T> union(MySet<T> other) {
            LinkedHashSet<T> result = new LinkedHashSet<>(this.elements);
            result.addAll(other.elements);

            return new MySet<>(result);
        }

        // Intersection
        // Returns elements common to both sets
        public MySet<T> intersection(MySet<T> other) {
            LinkedHashSet<T> result = new LinkedHashSet<>(this.elements);
            result.retainAll(other.elements);

            return new MySet<>(result);
        }

        // Difference
        // Returns elements present in current set but not in other
        public MySet<T> difference(MySet<T> other) {
            LinkedHashSet<T> result = new LinkedHashSet<>(this.elements);
            result.removeAll(other.elements);

            return new MySet<>(result);
        }

        // Symmetric Difference
        // Returns elements present in exactly one of the sets
        public MySet<T> symmetricDifference(MySet<T> other) {
            LinkedHashSet<T> result = new LinkedHashSet<>(this.elements);

            // Elements in this set but not in other
            result.removeAll(other.elements);

            // Elements in other set but not in this set
            for (T element : other.elements) {
                if (!this.elements.contains(element)) {
                    result.add(element);
                }
            }

            return new MySet<>(result);
        }

        // Check whether current set is a subset of another set
        public boolean isSubsetOf(MySet<T> superset) {
            return superset.elements.containsAll(this.elements);
        }

        // Display set
        @Override
        public String toString() {
            return elements.toString();
        }
    }

    // Demonstration
    public static void main(String[] args) {

        // Create three distinct Set<Integer> instances
        MySet<Integer> setA = MySet.of(1, 2, 3, 4);
        MySet<Integer> setB = MySet.of(3, 4, 5, 6);
        MySet<Integer> setC = MySet.of(1, 2, 3);

        System.out.println("Set A: " + setA);
        System.out.println("Set B: " + setB);
        System.out.println("Set C: " + setC);

        // Union
        System.out.println("\nUnion (A ∪ B): "
                + setA.union(setB));

        // Intersection
        System.out.println("Intersection (A ∩ B): "
                + setA.intersection(setB));

        // Difference
        System.out.println("Difference (A - B): "
                + setA.difference(setB));

        // Symmetric Difference
        System.out.println("Symmetric Difference (A △ B): "
                + setA.symmetricDifference(setB));

        // Subset
        System.out.println("\nIs C a subset of A? "
                + setC.isSubsetOf(setA));

        System.out.println("Is B a subset of A? "
                + setB.isSubsetOf(setA));
    }
}