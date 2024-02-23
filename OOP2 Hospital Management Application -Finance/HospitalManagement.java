import java.time.LocalDateTime;
import java.util.*;

// Interface for NHIF funds
interface NHIF {
    double calculateNHIF(double totalBill);
}

// Base class for Patient
class Patient {
    private String name;
    private int age;

    // Constructor
    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method to prompt user for patient details
    public static Patient createPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patient's name:");
        String name = scanner.nextLine();
        System.out.println("Enter patient's age:");
        int age = scanner.nextInt();
        return new Patient(name, age);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Subclass for Payment
class Payment {
    private double amount;
    private String paymentDate;
    private LocalDateTime lastUpdatedDateTime; // New field for last updated date and time

    public Payment(double amount, String paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdatedDateTime = LocalDateTime.now(); // Initialize last updated date and time to current date and time
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    // Method to update payment record and set last updated date and time
    public void updatePayment(double newAmount) {
        this.amount = newAmount;
        this.lastUpdatedDateTime = LocalDateTime.now(); // Update last updated date and time to current date and time
    }
}

// Implementation of NHIF interface
class NHIFImpl implements NHIF {
    @Override
    public double calculateNHIF(double totalBill) {
        return totalBill * 0.1; // Assuming 10% of total bill for NHIF
    }
}

// Hospital Management Application
public class HospitalManagement {
    public static void main(String[] args) {
        ArrayList<Patient> patientsList = new ArrayList<>();
        LinkedList<Payment> paymentRecords = new LinkedList<>();
        HashMap<String, Double> nhifFunds = new HashMap<>();

        // Add patients
        System.out.println("Adding Patient Details:");
        patientsList.add(Patient.createPatient());

        // Add payment records
        paymentRecords.add(new Payment(300, "01/01/2022"));
        paymentRecords.add(new Payment(450, "03/01/2022"));

        // Calculate NHIF for each payment record and store in NHIF funds
        NHIF nhifCalculator = new NHIFImpl();
        for (Payment payment : paymentRecords) {
            double nhifAmount = nhifCalculator.calculateNHIF(payment.getAmount());
            nhifFunds.put(payment.getPaymentDate(), nhifAmount);

            double netPayment = payment.getAmount() - nhifAmount;
            System.out.println("Payment Date: " + payment.getPaymentDate() + ", Net Payment: $" + netPayment + ", Last Updated: " + payment.getLastUpdatedDateTime());
        }

        // Display patients details
        System.out.println("\nPatients Details:");
        for (Patient patient : patientsList) {
            System.out.println("Name: " + patient.getName() + ", Age: " + patient.getAge());
        }
    }
}