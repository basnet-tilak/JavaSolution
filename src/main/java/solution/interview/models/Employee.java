package solution.interview.models;

/**
 * Employee model class
 */
public record Employee(String name, int age, String department, double salary) {

    @Override
    public String toString() {
        return name + "(" + age + ", " + department + ", $" + salary + ")";
    }
}