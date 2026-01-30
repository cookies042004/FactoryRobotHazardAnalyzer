import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer
 * UC7 - Machinery State Risk Mapping using Enum
 *
 * @Developer
 * @version7.0
 */

public class FactoryRobotHazardAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Arm Precision (0.0 - 1.0): ");
            double armPrecision = sc.nextDouble();

            System.out.print("Enter Worker Density (1 - 20): ");
            int workerDensity = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.print("Enter Machinery State (Worn/Faulty/Critical): ");
            String machineStateInput = sc.nextLine();

            double risk = calculateHazardRisk(
                    armPrecision,
                    workerDensity,
                    machineStateInput
            );

            System.out.println("Hazard Risk Score: " + risk);

        } catch (RobotSafetyException e) {
            System.out.println("Safety Error: " + e.getMessage());
        }

        sc.close();
    }

    // calculate risk
    public static double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineStateInput)
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

        // Convert string to enum safely
        MachineryState state =
                MachineryState.fromString(machineStateInput);

        double machineRiskFactor = state.getRiskFactor();

        // Hazard formula
        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}

// enum for machineryState
enum MachineryState {

    WORN(1.3),
    FAULTY(2.0),
    CRITICAL(3.0);

    private final double riskFactor;

    MachineryState(double riskFactor) {
        this.riskFactor = riskFactor;
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    // Convert user input string to enum safely
    public static MachineryState fromString(String state)
            throws RobotSafetyException {

        try {
            return MachineryState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RobotSafetyException(
                    "Unsupported machinery state: " + state
            );
        }
    }
}

// custom exception
class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}
