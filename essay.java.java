/**
 * Nama File: essay.java
 * Topik: Jawaban Soal Essay Pemrograman Berorientasi Objek (OOP)
 */

public class Essay {

    /* =========================================================================
     * SOAL 1
     * Jelaskan bagaimana prinsip encapsulation, inheritance, polymorphism, dan 
     * abstraction saling mendukung dalam membangun sistem perangkat lunak yang 
     * mudah dikembangkan dan dipelihara. Sertakan contoh analogi dalam 
     * kehidupan nyata untuk masing-masing konsep.
     * =========================================================================
     * JAWABAN:
     * Keempat pilar OOP saling melengkapi untuk menciptakan sistem yang modular:
     * - Abstraction mendefinisikan *apa* yang harus dilakukan (kerangka awal).
     * - Encapsulation melindungi detail dari abstraksi tersebut agar data 
     *   tidak diubah sembarangan dari luar.
     * - Inheritance mendaur ulang kerangka (abstraksi) dan logika (enkapsulasi) 
     *   ke entitas baru yang lebih spesifik.
     * - Polymorphism memanfaatkan pewarisan dan abstraksi agar satu antarmuka 
     *   bisa digunakan oleh berbagai bentuk objek yang berbeda.
     * 
     * Analogi Kehidupan Nyata:
     * - Abstraction: Mesin kopi. Kita menekan tombol "Latte" tanpa perlu tahu 
     *   cara mesin memanaskan air di dalamnya.
     * - Encapsulation: Kapsul obat. Bubuk obat dibungkus dalam cangkang kapsul. 
     *   Kita tinggal menelannya tanpa bisa mengutak-atik isinya.
     * - Inheritance: Anak mewarisi sifat fisik orang tuanya, tetapi bisa punya 
     *   keahlian uniknya sendiri.
     * - Polymorphism: Seseorang bernama Budi. Di rumah ia "Ayah", di kantor ia 
     *   "Manajer", di lapangan ia "Striker". Entitasnya sama, perilakunya beda 
     *   tergantung konteks.
     */


    /* =========================================================================
     * SOAL 2
     * Apa kelebihan menggunakan Java versi terbaru (Java 21) dibanding 
     * versi-versi sebelumnya dalam konteks pengembangan berbasis OOP? 
     * Berikan minimal dua fitur modern Java 21 dan jelaskan bagaimana fitur 
     * tersebut menyederhanakan pengembangan aplikasi OOP.
     * =========================================================================
     * JAWABAN:
     * Java 21 (LTS) secara drastis mengurangi penulisan kode berulang 
     * (boilerplate) dan memperkuat paradigma OOP yang lebih deklaratif.
     * 
     * 1. Record Classes: Java men-generate constructor, getter, equals(), 
     *    hashCode(), dan toString() secara otomatis untuk class "wadah data". 
     *    Ini memperkuat konsep kekekalan data (immutability) dalam OOP.
     * 2. Pattern Matching for switch: Membantu Polimorfisme. Di versi lama, 
     *    kita harus melakukan pengecekan `if (obj instanceof TipeA)` lalu 
     *    melakukan casting. Kini, switch bisa mendeteksi tipe objek dan 
     *    langsung melakukan casting otomatis di dalam blok kasusnya.
     */


    /* =========================================================================
     * SOAL 3
     * Mahasiswa sering kali salah memahami perbedaan antara class dan object. 
     * Jelaskan secara detail perbedaan keduanya dan berikan contoh penggunaan 
     * class dan object dalam konteks program manajemen data mahasiswa.
     * =========================================================================
     * JAWABAN:
     * - Class: Adalah cetak biru (blueprint) atau template. Tidak wujud nyata di 
     *   memori. Hanya mendefinisikan atribut (data) dan method (perilaku).
     * - Object: Adalah wujud nyata (instance) dari class yang dialokasikan di 
     *   memori dengan nilai data spesifik.
     * 
     * Contoh:
     * Class `Mahasiswa` mendefinisikan atribut `nama` dan `NIM`. Class ini 
     * belum menunjuk siapa-siapa. Ketika kita membuat Object dari class 
     * tersebut, barulah nyata, misal: Object 1 (Andi, NIM 1001) dan Object 2 
     * (Siti, NIM 1002).
     */


    /* =========================================================================
     * SOAL 4
     * Anda diminta membuat class BankAccount. Jelaskan bagaimana Anda akan 
     * menerapkan encapsulation agar data balance tidak bisa diubah sembarangan. 
     * Mengapa encapsulation penting untuk keamanan sistem?
     * =========================================================================
     * JAWABAN:
     * Implementasi:
     * 1. Jadikan variabel `balance` memiliki akses modifier `private`.
     * 2. Jangan buat metode `setBalance()` yang bebas menerima nilai.
     * 3. Buat metode publik terkontrol seperti `deposit(amount)` dan 
     *    `withdraw(amount)` yang di dalamnya terdapat validasi (misal: saldo 
     *    harus cukup sebelum dikurangi).
     * 
     * Pentingnya:
     * Encapsulation melindungi integritas data. Tanpanya, saldo bisa diubah 
     * menjadi minus secara ilegal. Sistem memaksa interaksi data harus melewati 
     * "pintu resmi" yang memiliki validasi keamanan.
     */


    /* =========================================================================
     * SOAL 5
     * Jelaskan bagaimana mekanisme constructor chaining bekerja pada pewarisan 
     * di Java. Apa yang terjadi jika constructor pada superclass tidak dipanggil 
     * secara eksplisit? Sertakan ilustrasi class Karyawan dan subclass Manager.
     * =========================================================================
     * JAWABAN:
     * Constructor Chaining adalah proses pemanggilan constructor superclass 
     * secara berantai sebelum constructor subclass dieksekusi. Ini memastikan 
     * objek induk dibangun dulu sebelum bagian anak ditambahkan.
     * 
     * Jika tidak dipanggil eksplisit:
     * Java otomatis menyisipkan `super()` tanpa parameter di baris pertama 
     * constructor anak. Jika induk tidak punya constructor kosong, akan terjadi 
     * Compile-time Error.
     * 
     * Ilustrasi:
     * - Superclass Karyawan: punya `public Karyawan(String nama) { ... }`
     * - Subclass Manager: punya `public Manager(String nama, String dept)`
     *   Di dalam constructor Manager, baris pertamanya WAJIB memanggil 
     *   `super(nama);` baru kemudian menginisialisasi `this.dept = dept;`.
     */


    /* =========================================================================
     * SOAL 6
     * Polymorphism memungkinkan kita menulis kode yang fleksibel dan mudah 
     * di-maintain. Jelaskan bagaimana penggunaan interface mendukung konsep 
     * ini, dan berikan contoh penggunaannya dalam sistem pemesanan makanan 
     * online.
     * =========================================================================
     * JAWABAN:
     * Interface mendefinisikan sebuah "kontrak". Polimorfisme memungkinkan 
     * sistem utama berkomunikasi lewat kontrak tersebut tanpa peduli 
     * implementasinya, membuat kode fleksibel.
     * 
     * Contoh:
     * Buat interface `MetodePembayaran` dengan method `prosesBayar()`.
     * Class `GoPay`, `KartuKredit`, dan `Transfer` mengimplementasikan 
     * interface tersebut dengan cara kerjanya masing-masing.
     * Di class `PesananMakanan`, kita cukup membuat fungsi:
     * `bayar(MetodePembayaran metode) { metode.prosesBayar(); }`
     * Jika ada metode pembayaran baru, class `PesananMakanan` tidak perlu 
     * diubah sama sekali.
     */


    /* =========================================================================
     * SOAL 7
     * Abstraction membantu menyembunyikan kompleksitas internal. Bandingkan 
     * penggunaan abstract class, interface, dan sealed class di Java. 
     * Dalam kasus apa masing-masing lebih tepat digunakan?
     * =========================================================================
     * JAWABAN:
     * - Abstract Class: 
     *   Bisa punya metode beserta isinya dan variabel state. Digunakan saat 
     *   ingin membuat kerangka dasar untuk class turunannya yang memiliki 
     *   hubungan erat (Is-A / Adalah sebuah).
     * 
     * - Interface: 
     *   Murni kontrak perilaku (kecuali default method di Java modern) tanpa 
     *   state. Digunakan untuk mendefinisikan "kemampuan" (Can-Do / Bisa 
     *   melakukan) lintas class yang tidak se-hierarki.
     * 
     * - Sealed Class: 
     *   Membatasi class mana saja yang boleh meng-extend dirinya. Digunakan 
     *   untuk Domain Modeling di mana opsi hierarkinya terbatas dan sudah 
     *   diketahui (misal: StatusTransaksi hanya boleh Sukses, Gagal, Pending).
     */

    public static void main(String[] args) {
        System.out.println("File essay.java berhasil dicompile. Berisi jawaban essay OOP.");
    }
}