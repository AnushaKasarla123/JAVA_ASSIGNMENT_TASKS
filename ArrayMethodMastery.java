import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayMethodMastery {

    // ==========================================
    // Product Class
    // ==========================================

    static class Product {
        int id;
        String name;
        double price;
        int stock;
        List<String> tags;

        Product(int id, String name, double price, int stock, List<String> tags) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", stock=" + stock +
                    ", tags=" + tags +
                    '}';
        }
    }


    // ==========================================
    // Customer Class
    // ==========================================

    static class Customer {
        int id;
        String name;
        LocalDate lastLogin;

        Customer(int id, String name, LocalDate lastLogin) {
            this.id = id;
            this.name = name;
            this.lastLogin = lastLogin;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", lastLogin=" + lastLogin +
                    '}';
        }
    }


    // ==========================================
    // Order Item Class
    // ==========================================

    static class OrderItem {
        int productId;
        int quantity;

        OrderItem(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "OrderItem{" +
                    "productId=" + productId +
                    ", quantity=" + quantity +
                    '}';
        }
    }


    // ==========================================
    // Order Class
    // ==========================================

    static class Order {
        int id;
        int customerId;
        String status;
        double totalAmount;
        boolean verified;
        List<OrderItem> items;

        Order(int id, int customerId, String status,
              double totalAmount, boolean verified,
              List<OrderItem> items) {

            this.id = id;
            this.customerId = customerId;
            this.status = status;
            this.totalAmount = totalAmount;
            this.verified = verified;
            this.items = items;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", customerId=" + customerId +
                    ", status='" + status + '\'' +
                    ", totalAmount=" + totalAmount +
                    ", verified=" + verified +
                    ", items=" + items +
                    '}';
        }
    }


    // ==========================================
    // 1. Apply Discount
    // ==========================================

    public static List<Product> applyDiscount(
            List<Product> products,
            double percent) {

        return products.stream()
                .map(product -> new Product(
                        product.id,
                        product.name,
                        product.price * (1 - percent / 100),
                        product.stock,
                        product.tags
                ))
                .collect(Collectors.toList());
    }


    // ==========================================
    // 2. Get Active Customers
    // ==========================================

    public static List<Customer> getActiveCustomers(
            List<Customer> customers) {

        LocalDate thirtyDaysAgo =
                LocalDate.now().minusDays(30);

        return customers.stream()
                .filter(customer ->
                        !customer.lastLogin.isBefore(thirtyDaysAgo))
                .collect(Collectors.toList());
    }


    // ==========================================
    // 3. Calculate Revenue
    // ==========================================

    public static double calculateRevenue(
            List<Order> orders) {

        return orders.stream()
                .filter(order ->
                        order.status.equals("completed"))
                .mapToDouble(order -> order.totalAmount)
                .sum();
    }


    // ==========================================
    // 4. Find First Out-of-Stock Product
    // ==========================================

    public static Optional<Product> findOutOfStock(
            List<Product> products) {

        return products.stream()
                .filter(product -> product.stock == 0)
                .findFirst();
    }


    // ==========================================
    // 5. Check High-Value Order
    // ==========================================

    public static boolean hasHighValueOrder(
            List<Order> orders,
            double threshold) {

        return orders.stream()
                .anyMatch(order ->
                        order.status.equals("completed")
                                && order.totalAmount > threshold);
    }


    // ==========================================
    // 6. Check All Orders Verified
    // ==========================================

    public static boolean areAllOrdersVerified(
            List<Order> orders) {

        return orders.stream()
                .allMatch(order -> order.verified);
    }


    // ==========================================
    // 7. Flatten All Order Items
    // ==========================================

    public static List<OrderItem> flattenAllItems(
            List<Order> orders) {

        return orders.stream()
                .flatMap(order -> order.items.stream())
                .collect(Collectors.toList());
    }


    // ==========================================
    // 8. Group Orders by Customer
    // ==========================================

    public static Map<Integer, List<Order>> groupOrdersByCustomer(
            List<Order> orders) {

        return orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.customerId
                ));
    }


    // ==========================================
    // 9. Top-Selling Products
    // ==========================================

    public static List<Integer> topSellingProducts(
            List<Order> orders,
            int n) {

        Map<Integer, Integer> productQuantities =
                orders.stream()
                        .flatMap(order -> order.items.stream())
                        .collect(Collectors.groupingBy(
                                item -> item.productId,
                                Collectors.summingInt(
                                        item -> item.quantity)
                        ));

        return productQuantities.entrySet()
                .stream()
                .sorted(Map.Entry
                        .<Integer, Integer>comparingByValue()
                        .reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    // ==========================================
    // 10. Extract Unique Tags
    // ==========================================

    public static List<String> extractUniqueTags(
            List<Product> products) {

        return products.stream()
                .flatMap(product -> product.tags.stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }


    // ==========================================
    // MAIN METHOD
    // ==========================================

    public static void main(String[] args) {

        // --------------------------------------
        // Products
        // --------------------------------------

        List<Product> products = Arrays.asList(

                new Product(
                        1,
                        "Laptop",
                        60000,
                        5,
                        Arrays.asList("electronics", "computer")
                ),

                new Product(
                        2,
                        "Mouse",
                        1000,
                        0,
                        Arrays.asList("electronics", "accessories")
                ),

                new Product(
                        3,
                        "Keyboard",
                        2000,
                        10,
                        Arrays.asList("electronics", "accessories")
                ),

                new Product(
                        4,
                        "Headphones",
                        3000,
                        0,
                        Arrays.asList("electronics", "audio")
                )
        );


        // --------------------------------------
        // Customers
        // --------------------------------------

        List<Customer> customers = Arrays.asList(

                new Customer(
                        101,
                        "Anusha",
                        LocalDate.now()
                ),

                new Customer(
                        102,
                        "Rahul",
                        LocalDate.now().minusDays(10)
                ),

                new Customer(
                        103,
                        "Priya",
                        LocalDate.now().minusDays(60)
                )
        );


        // --------------------------------------
        // Orders
        // --------------------------------------

        List<Order> orders = Arrays.asList(

                new Order(
                        1,
                        101,
                        "completed",
                        61000,
                        true,
                        Arrays.asList(
                                new OrderItem(1, 1),
                                new OrderItem(2, 2)
                        )
                ),

                new Order(
                        2,
                        102,
                        "completed",
                        5000,
                        true,
                        Arrays.asList(
                                new OrderItem(3, 2),
                                new OrderItem(4, 1)
                        )
                ),

                new Order(
                        3,
                        101,
                        "pending",
                        80000,
                        false,
                        Arrays.asList(
                                new OrderItem(1, 1),
                                new OrderItem(3, 3)
                        )
                )
        );


        // ======================================
        // TEST ALL OPERATIONS
        // ======================================


        // 1. Discount
        System.out.println("1. Products after 10% discount:");

        applyDiscount(products, 10)
                .forEach(System.out::println);


        // 2. Active Customers
        System.out.println("\n2. Active Customers:");

        getActiveCustomers(customers)
                .forEach(System.out::println);


        // 3. Revenue
        System.out.println("\n3. Total Completed Revenue:");

        System.out.println(calculateRevenue(orders));


        // 4. Out of Stock
        System.out.println("\n4. First Out-of-Stock Product:");

        System.out.println(
                findOutOfStock(products)
                        .orElse(null)
        );


        // 5. High Value Order
        System.out.println("\n5. Has High-Value Order above 50000:");

        System.out.println(
                hasHighValueOrder(orders, 50000)
        );


        // 6. All Orders Verified
        System.out.println("\n6. Are All Orders Verified:");

        System.out.println(
                areAllOrdersVerified(orders)
        );


        // 7. Flatten Items
        System.out.println("\n7. Flattened Order Items:");

        flattenAllItems(orders)
                .forEach(System.out::println);


        // 8. Group Orders
        System.out.println("\n8. Orders Grouped by Customer:");

        groupOrdersByCustomer(orders)
                .forEach((customerId, customerOrders) ->
                        System.out.println(
                                "Customer " + customerId
                                        + " -> " + customerOrders
                        )
                );


        // 9. Top Selling Products
        System.out.println("\n9. Top 2 Selling Products:");

        System.out.println(
                topSellingProducts(orders, 2)
        );


        // 10. Unique Tags
        System.out.println("\n10. Unique Product Tags:");

        System.out.println(
                extractUniqueTags(products)
        );
    }
}