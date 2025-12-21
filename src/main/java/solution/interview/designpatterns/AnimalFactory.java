package solution.interview.designpatterns;

/**
 * Factory pattern implementation
 */
public class AnimalFactory {
    public static Animal createAnimal(String type) {
        return switch (type.toLowerCase()) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}