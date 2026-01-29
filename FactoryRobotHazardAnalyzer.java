import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer UC1 - Printing message using print statement
 *
 * This class will take the input from the user. All data required for hazard analysis
 * and display that input data as output.
 *
 * @Developer
 * @version 2.0
* */

public class FactoryRobotHazardAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking Arm Precision value from user
        System.out.println("Enter Arm precision (0.0 - 1.0): ");
        double armPrecision = sc.nextDouble();

        // Taking Worker Density value from user
        System.out.println("Enter Worker Density (1 - 20): ");
        int workerDensity = sc.nextInt();

        sc.nextLine();

        // Taking Machinery State value from user
        System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
        String machineState = sc.nextLine();

        System.out.println("Arm Precision : " + armPrecision);
        System.out.println("Worker Density : " + workerDensity);
        System.out.println("Machinery State : "  + machineState);
    }
}
