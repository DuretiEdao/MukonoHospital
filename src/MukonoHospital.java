import java.util.ArrayList;
import java.util.List;

/**
 * Mukono Hospital Staff Management System
 * Demonstrates abstraction in Java using an abstract class.
 */

// ---------- Abstract parent class ----------
abstract class MedicalStaff {
    // Common attributes shared by all staff
    private final String employeeId;
    private final String name;
    private final String department;
    private final int workingHours;

    // Constructor (used by subclasses via super)
    public MedicalStaff(String employeeId, String name, String department, int workingHours) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.workingHours = workingHours;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getWorkingHours() { return workingHours; }

    // Concrete (non-abstract) method: shared behaviour, written once
    public void displayInfo() {
        System.out.println("Role        : " + getRole());
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Name        : " + name);
        System.out.println("Department  : " + department);
        System.out.println("Hours/Week  : " + workingHours);
    }

    // Concrete method that uses an abstract method (template style)
    public void startShift() {
        System.out.println(name + " (" + getRole() + ") has started the shift.");
        performDuties();
    }

    // Abstract methods: no body; every subclass MUST implement them
    public abstract String getRole();
    public abstract void performDuties();
}

// ---------- Specialized subclasses ----------
class Doctor extends MedicalStaff {
    private final String specialization;

    public Doctor(String id, String name, String department, int hours, String specialization) {
        super(id, name, department, hours);
        this.specialization = specialization;
    }

    @Override
    public String getRole() { return "Doctor (" + specialization + ")"; }

    @Override
    public void performDuties() {
        System.out.println("  -> Examining patients, diagnosing illnesses and prescribing treatment.");
    }
}

class Nurse extends MedicalStaff {
    private final String ward;

    public Nurse(String id, String name, String department, int hours, String ward) {
        super(id, name, department, hours);
        this.ward = ward;
    }

    @Override
    public String getRole() { return "Nurse (Ward: " + ward + ")"; }

    @Override
    public void performDuties() {
        System.out.println("  -> Monitoring vital signs, administering medication and caring for patients.");
    }
}

//Pharmacist class: handles pharmacy duties.
class Pharmacist extends MedicalStaff {
    public Pharmacist(String id, String name, String department, int hours) {
        super(id, name, department, hours);
    }

    // Returns the pharmacist role.
    @Override
    public String getRole() { return "Pharmacist"; }

    @Override
    public void performDuties() {
        System.out.println("  -> Dispensing prescribed drugs and advising patients on correct usage.");
    }
}

class LabTechnician extends MedicalStaff {
    private final String labType;

    public LabTechnician(String id, String name, String department, int hours, String labType) {
        super(id, name, department, hours);
        this.labType = labType;
    }

    @Override
    public String getRole() { return "Laboratory Technician (" + labType + ")"; }

    @Override
    public void performDuties() {
        System.out.println("  -> Collecting samples, running tests and reporting laboratory results.");
    }
}

// ---------- Main class ----------
public class MukonoHospital {
    public static void main(String[] args) {

        // MedicalStaff staff = new MedicalStaff(...);
        // ^ ERROR: MedicalStaff is abstract; cannot be instantiated.

        // Polymorphism: parent-type list holding different staff types
        List<MedicalStaff> staffList = new ArrayList<>();
        staffList.add(new Doctor("D001", "Dr. Okello James", "Surgery", 40, "Surgeon"));
        staffList.add(new Nurse("N001", "Nakato Grace", "Maternity", 45, "Ward B"));
        staffList.add(new Pharmacist("P001", "Mugisha Peter", "Pharmacy", 40));
        staffList.add(new LabTechnician("L001", "Namuli Sarah", "Laboratory", 38, "Hematology"));

        System.out.println("===== MUKONO HOSPITAL STAFF SYSTEM =====\n");

        for (MedicalStaff staff : staffList) {
            staff.displayInfo();   // inherited common behaviour
            staff.startShift();    // each subclass performs its own duties
            System.out.println("----------------------------------------");
        }
    }
}