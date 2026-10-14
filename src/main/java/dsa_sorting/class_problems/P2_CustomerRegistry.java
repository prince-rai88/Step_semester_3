package dsa_sorting.class_problems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class P2_CustomerRegistry {

    public static class Customer {
        private final int id;
        private final String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return id == customer.id && Objects.equals(name, customer.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "(" + id + ", " + name + ")";
        }
    }

    public static class Registry {
        private final Set<Customer> customers = new HashSet<>();

        public boolean addCustomer(Customer customer) {
            return customers.add(customer);
        }

        public boolean containsCustomer(Customer customer) {
            return customers.contains(customer);
        }

        public int getUniqueCount() {
            return customers.size();
        }
    }

    public static void main(String[] args) {
        Registry registry = new Registry();

        Customer c1BranchA = new Customer(101, "Asha");
        Customer c1BranchB = new Customer(101, "Asha");
        Customer c2 = new Customer(102, "Ravi");

        boolean added1 = registry.addCustomer(c1BranchA);
        System.out.println(added1); // true

        boolean added2 = registry.addCustomer(c1BranchB);
        if (!added2) {
            System.out.println("false (duplicate rejected)");
        } else {
            System.out.println("true");
        }

        boolean added3 = registry.addCustomer(c2);
        System.out.println(added3); // true

        System.out.println("unique count " + registry.getUniqueCount()); // 2

        boolean contains = registry.containsCustomer(new Customer(101, "Asha"));
        System.out.println("contains: " + contains); // true
    }
}
