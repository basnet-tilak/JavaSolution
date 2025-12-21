package solution.interview;

import solution.interview.algorithms.*;
import solution.interview.collections.CollectionsFramework;
import solution.interview.jdbc.JDBCExamplesClean;

/**
 * Interview Questions Runner
 */
public class InterviewRunner {
    
    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("           JAVA INTERVIEW QUESTIONS DEMO");
        System.out.println("=========================================================\n");
        
        runAlgorithmQuestions();
        runCollectionsQuestions();
        runJDBCQuestions();
        
        System.out.println("=========================================================");
        System.out.println("              ALL QUESTIONS COMPLETED");
        System.out.println("=========================================================");
    }
    
    private static void runAlgorithmQuestions() {
        System.out.println("=================== ALGORITHM QUESTIONS ===================");
        
        try {
            System.out.println("\n1. Array Algorithms:");
            ArrayAlgorithms.main(new String[0]);
            
            System.out.println("\n2. String Algorithms:");
            StringAlgorithms.main(new String[0]);
            
            System.out.println("\n3. LinkedList Algorithms:");
            LinkedListAlgorithms.main(new String[0]);
            
            System.out.println("\n4. Dynamic Programming:");
            DynamicProgramming.main(new String[0]);
            
            System.out.println("\n5. Stack & Queue Algorithms:");
            StackQueueAlgorithms.main(new String[0]);
            
            System.out.println("\n6. Tree Algorithms:");
            TreeAlgorithms.main(new String[0]);
            
            System.out.println("\n7. Sorting Algorithms:");
            SortingAlgorithms.main(new String[0]);
            
            System.out.println("\n8. Mathematical Algorithms:");
            MathematicalAlgorithms.main(new String[0]);
            
        } catch (Exception e) {
            System.err.println("Error in algorithm questions: " + e.getMessage());
        }
        
        System.out.println("\n============== ALGORITHM QUESTIONS COMPLETED ==============\n");
    }
    
    private static void runCollectionsQuestions() {
        System.out.println("================== COLLECTIONS QUESTIONS ==================");
        
        try {
            CollectionsFramework.main(new String[0]);
        } catch (Exception e) {
            System.err.println("Error in collections questions: " + e.getMessage());
        }
        
        System.out.println("\n============== COLLECTIONS QUESTIONS COMPLETED ==============\n");
    }
    
    private static void runJDBCQuestions() {
        System.out.println("==================== JDBC QUESTIONS ====================");
        
        try {
            JDBCExamplesClean.main(new String[0]);
        } catch (Exception e) {
            System.err.println("Error in JDBC questions: " + e.getMessage());
        }
        
        System.out.println("\n================ JDBC QUESTIONS COMPLETED ================\n");
    }
}