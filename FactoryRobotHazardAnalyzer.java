import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer UC4 - Validation using conditional logic.
 *
 * This class validates user inputs and calculates hazard risk
 * only when inputs are valid.
 *
 * @Developer
 * @version 4.0
 */
public class FactoryRobotHazardAnalyzer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input: Arm Precision
        System.out.println("Enter Arm precision (0.0 - 1.0): ");
        double armPrecision = sc.nextDouble();

        // Input: Worker Density
        System.out.println("Enter Worker Density (1 - 20): ");
        int workerDensity = sc.nextInt();

        sc.nextLine();

        // Input: Machinery State
        System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
        String machineState = sc.nextLine();

        // validation
        boolean isValid = true;

        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println("Error: Arm precision must be between 0.0 and 1.0.");
            isValid = false;
        }

        if (workerDensity < 1 || workerDensity > 20) {
            System.out.println("Error: Worker density must be between 1 and 20.");
            isValid = false;
        }

        if (!machineState.equalsIgnoreCase("Worn") &&
                !machineState.equalsIgnoreCase("Faulty") &&
                !machineState.equalsIgnoreCase("Critical")) {

            System.out.println("Error: Machinery state must be Worn, Faulty, or Critical.");
            isValid = false;
        }

        if (isValid) {

            double machineRiskFactor = getMachineRiskFactor(machineState);

            double hazardRisk = calculateHazardRisk(
                    armPrecision,
                    workerDensity,
                    machineRiskFactor
            );

            System.out.println("Hazard Risk Score: " + hazardRisk);
        } else {
            System.out.println("\nHazard risk calculation aborted due to invalid inputs.");
        }

        sc.close();
    }

    // Returns machine risk factor
    public static double getMachineRiskFactor(String machineState) {
        if (machineState.equalsIgnoreCase("Worn")) {
            return 1.3;
        } else if (machineState.equalsIgnoreCase("Faulty")) {
            return 2.0;
        } else { // Critical
            return 3.0;
        }
    }

    // Calculates hazard risk
    public static double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            double machineRiskFactor) {

        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}
