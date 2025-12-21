package solution.interview.java8;

import solution.interview.models.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Java 8 Features - Streams, Lambda, Optional
 */
public class Java8Features {
    
    public static void demonstrateStreams() {
        List<Employee> employees = Arrays.asList(
            new Employee("John", 25, "IT", 50000),
            new Employee("Jane", 30, "HR", 55000),
            new Employee("Bob", 35, "IT", 60000),
            new Employee("Alice", 28, "Finance", 52000),
            new Employee("Charlie", 32, "IT", 58000)
        );
        
        System.out.println("=== Stream Operations ===");
        
        List<Employee> itEmployees = employees.stream()
            .filter(emp -> "IT".equals(emp.department()))
            .collect(Collectors.toList());
        System.out.println("IT Employees: " + itEmployees);
        
        List<String> namesByAge = employees.stream()
            .sorted(Comparator.comparing(Employee::age))
            .map(Employee::name)
            .collect(Collectors.toList());
        System.out.println("Names by age: " + namesByAge);
        
        Optional<Employee> highestPaid = employees.stream()
            .max(Comparator.comparing(Employee::salary));
        highestPaid.ifPresent(emp -> 
            System.out.println("Highest paid: " + emp));
    }
    
    public static void demonstrateLambdas() {
        System.out.println("\n=== Lambda Expressions ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);
        
        Function<String, String> upperCase = String::toUpperCase;
        System.out.println("Uppercase: " + upperCase.apply("hello world"));
    }
    
    public static void demonstrateOptional() {
        System.out.println("\n=== Optional Examples ===");
        
        Optional<String> optional1 = Optional.of("Hello");
        Optional<String> optional2 = Optional.empty();
        
        optional1.ifPresent(value -> System.out.println("Found: " + value));
        
        String result = optional2.orElse("Default Value");
        System.out.println("Result with orElse: " + result);
        
        Optional<String> result3 = optional1
            .map(String::toUpperCase)
            .filter(s -> s.length() > 3);
        result3.ifPresent(s -> System.out.println("Filtered result: " + s));
    }
    
    static void main() {
        demonstrateStreams();
        demonstrateLambdas();
        demonstrateOptional();
    }
}