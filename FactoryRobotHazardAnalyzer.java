import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer UC6 - Custom Exception Handling.
 *
 * Uses RobotSafetyException to handle invalid inputs
 * in a clean and standardized way.
 *
 * @Developer
 * @version 6.0
 */
class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}

public class FactoryRobotHazardAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input collection
            System.out.println("Enter Arm precision (0.0 - 1.0): ");
            double armPrecision = sc.nextDouble();

            System.out.println("Enter Worker Density (1 - 20): ");
            int workerDensity = sc.nextInt();

            sc.nextLine(); // clear buffer

            System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
            String machineState = sc.nextLine();

            // Call method with exception handling
            double hazardRisk = calculateHazardRisk(
                    armPrecision,
                    workerDensity,
                    machineState
            );

            System.out.println("\n--- Hazard Risk Result ---");
            System.out.println("Hazard Risk Score: " + hazardRisk);

        } catch (RobotSafetyException e) {
            System.out.println("\nSafety Error: " + e.getMessage());
        }

        sc.close();
    }

    public static double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineState)
            throws RobotSafetyException {

        // Validation
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException(
                    "Arm precision must be between 0.0 and 1.0."
            );
        }

        if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException(
                    "Worker density must be between 1 and 20."
            );
        }

        if (!machineState.equalsIgnoreCase("Worn") &&
                !machineState.equalsIgnoreCase("Faulty") &&
                !machineState.equalsIgnoreCase("Critical")) {

            throw new RobotSafetyException(
                    "Machinery state must be Worn, Faulty, or Critical."
            );
        }

        // Machine risk factor
        double machineRiskFactor;
        if (machineState.equalsIgnoreCase("Worn")) {
            machineRiskFactor = 1.3;
        } else if (machineState.equalsIgnoreCase("Faulty")) {
            machineRiskFactor = 2.0;
        } else {
            machineRiskFactor = 3.0;
        }

        // Hazard risk calculation
        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}
