// 1. Abstraction: Membuat abstract class Vehicle
abstract class Vehicle {
    // Abstract method yang harus diimplementasikan oleh class turunannya
    public abstract void startEngine();
}

// 2. Interface: Membuat interface Electric
interface Electric {
    // Method untuk interface, secara default bersifat public dan abstract
    void chargeBattery();
}

// 3. Inheritance & Interface Implementation: Class Car mewarisi Vehicle dan mengimplementasi Electric
class Car extends Vehicle implements Electric {
    
    // Meng-override method startEngine dari class Vehicle (Polymorphism - Overriding)
    @Override
    public void startEngine() {
        System.out.println("Mobil dihidupkan: Menekan tombol start engine... Vroom!");
    }

    // Mengimplementasikan method chargeBattery dari interface Electric
    @Override
    public void chargeBattery() {
        System.out.println("Baterai mobil sedang diisi daya: Terhubung ke stasiun pengisian EV.");
    }
}

// 4. Inheritance: Class Motorcycle mewarisi class Vehicle
class Motorcycle extends Vehicle {
    
    // Meng-override method startEngine dengan logika yang berbeda (Polymorphism - Overriding)
    @Override
    public void startEngine() {
        System.out.println("Motor dihidupkan: Menggunakan kick starter... Brem brem!");
    }
}

// 5. Class Utama untuk mendemonstrasikan program
public class project_java {
    public static void main(String[] args) {
        System.out.println("=== Demonstrasi Polymorphism ===");
        
        // Polymorphism: Tipe data/referensi adalah superclass (Vehicle), 
        // namun object yang dibuat adalah subclass (Car dan Motorcycle)
        Vehicle myCar = new Car();
        Vehicle myMotorcycle = new Motorcycle();

        // Pemanggilan method yang sama menghasilkan output berbeda tergantung object aslinya
        myCar.startEngine();        // Akan memanggil startEngine() milik Car
        myMotorcycle.startEngine(); // Akan memanggil startEngine() milik Motorcycle

        System.out.println("\n=== Demonstrasi Interface ===");
        
        // Pemanggilan method khusus dari interface. 
        // Karena myCar bertipe Vehicle, kita perlu melakukan casting ke tipe Electric (atau Car) terlebih dahulu
        if (myCar instanceof Electric) {
            Electric electricCar = (Electric) myCar;
            electricCar.chargeBattery();
        }
        
        // Atau bisa juga dipanggil langsung menggunakan instansiasi objek Car biasa
        Car mySpecificCar = new Car();
        mySpecificCar.chargeBattery();
    }
}