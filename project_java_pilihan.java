import java.util.ArrayList;
import java.util.List;

// ============================================================
// INTERFACE: Skill
// Mendefinisikan kontrak untuk berbagai kemampuan unik karakter
// ============================================================
interface Skill {
    String getSkillName();
    int getManaCost();
    int getDamage();
    void useSkill(Character target);
}

// ============================================================
// ABSTRACT CLASS: Character
// Base class untuk semua karakter dalam game RPG
// ============================================================
abstract class Character {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int mana;
    protected int maxMana;
    protected int attackPower;
    protected int defense;
    protected int level;
    protected List<Skill> skills;

    public Character(String name, int health, int mana, int attackPower, int defense) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = mana;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = 1;
        this.skills = new ArrayList<>();
    }

    // Method abstract yang wajib diimplementasikan subclass
    public abstract String getRole();
    public abstract void specialAbility();

    // Method konkret yang bisa digunakan oleh semua subclass
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void useSkill(int skillIndex, Character target) {
        if (skillIndex < 0 || skillIndex >= skills.size()) {
            System.out.println(name + ": Skill tidak ditemukan!");
            return;
        }
        Skill skill = skills.get(skillIndex);
        if (mana >= skill.getManaCost()) {
            mana -= skill.getManaCost();
            skill.useSkill(target);
        } else {
            System.out.println(name + " tidak punya cukup mana untuk menggunakan " + skill.getSkillName() + "!");
        }
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        health = Math.max(0, health - actualDamage);
        System.out.println(name + " menerima " + actualDamage + " damage (setelah defense). HP: " + health + "/" + maxHealth);
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
        System.out.println(name + " dipulihkan " + amount + " HP. HP sekarang: " + health + "/" + maxHealth);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void levelUp() {
        level++;
        maxHealth += 20;
        health = maxHealth;
        maxMana += 10;
        mana = maxMana;
        attackPower += 5;
        defense += 2;
        System.out.println("🎉 " + name + " naik ke Level " + level + "! Stats meningkat!");
    }

    public void showStatus() {
        System.out.println("==============================");
        System.out.println("Nama   : " + name);
        System.out.println("Role   : " + getRole());
        System.out.println("Level  : " + level);
        System.out.println("HP     : " + health + "/" + maxHealth);
        System.out.println("Mana   : " + mana + "/" + maxMana);
        System.out.println("ATK    : " + attackPower);
        System.out.println("DEF    : " + defense);
        System.out.println("Skills : " + skills.size() + " skill tersedia");
        System.out.println("==============================");
    }

    // Getter
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
    public int getLevel() { return level; }
}

// ============================================================
// SUBCLASS: Warrior
// Karakter bertipe fisik, kuat dalam serangan dan pertahanan
// ============================================================
class Warrior extends Character {

    public Warrior(String name) {
        super(name, 200, 50, 35, 20);
    }

    @Override
    public String getRole() {
        return "Warrior";
    }

    @Override
    public void specialAbility() {
        int bonus = 15;
        attackPower += bonus;
        System.out.println("⚔️  " + name + " mengaktifkan Battle Fury! ATK +" + bonus + " untuk ronde ini. ATK sekarang: " + attackPower);
    }

    public void shieldBlock() {
        int defBonus = 25;
        defense += defBonus;
        System.out.println("🛡️  " + name + " bersiap memblokir serangan! DEF +" + defBonus + ". DEF sekarang: " + defense);
    }
}

// ============================================================
// SUBCLASS: Mage
// Karakter bertipe sihir, spesialis serangan elemental
// ============================================================
class Mage extends Character {
    private String element;

    public Mage(String name, String element) {
        super(name, 100, 180, 55, 8);
        this.element = element;
    }

    @Override
    public String getRole() {
        return "Mage (" + element + ")";
    }

    @Override
    public void specialAbility() {
        System.out.println("✨ " + name + " mengaktifkan Mana Surge! Semua skill " + element + " menjadi 2x lebih kuat!");
    }

    public String getElement() { return element; }
}

// ============================================================
// SUBCLASS: Archer
// Karakter bertipe agility, spesialis serangan jarak jauh
// ============================================================
class Archer extends Character {
    private int critChance; // persentase critical hit

    public Archer(String name) {
        super(name, 130, 80, 40, 12);
        this.critChance = 25; // 25% critical hit
    }

    @Override
    public String getRole() {
        return "Archer";
    }

    @Override
    public void specialAbility() {
        System.out.println("🏹 " + name + " mengaktifkan Eagle Eye! Critical hit chance naik ke 50%!");
        critChance = 50;
    }

    public void shoot(Character target) {
        boolean isCrit = Math.random() * 100 < critChance;
        int damage = isCrit ? attackPower * 2 : attackPower;
        String critText = isCrit ? " [CRITICAL HIT!]" : "";
        System.out.println("🏹 " + name + " menembak " + target.getName() + " dengan " + damage + " damage!" + critText);
        target.takeDamage(damage);
    }

    public int getCritChance() { return critChance; }
}

// ============================================================
// SUBCLASS: Healer
// Karakter support, spesialis pemulihan dan perlindungan
// ============================================================
class Healer extends Character {
    private int healingPower;

    public Healer(String name) {
        super(name, 120, 150, 15, 10);
        this.healingPower = 50;
    }

    @Override
    public String getRole() {
        return "Healer";
    }

    @Override
    public void specialAbility() {
        System.out.println("💊 " + name + " mengaktifkan Divine Blessing! Healing Power +" + 30 + "!");
        healingPower += 30;
    }

    public void healAlly(Character ally) {
        System.out.println("💖 " + name + " menyembuhkan " + ally.getName() + " sebesar " + healingPower + " HP!");
        ally.heal(healingPower);
    }
}

// ============================================================
// SEALED CLASS: LegendaryRole
// Mengontrol role eksklusif - hanya ada 1 LegendaryKnight
// ============================================================
abstract sealed class LegendaryRole permits LegendaryKnight, LegendaryWizard {
    protected static int instanceCount = 0;
    protected static final int MAX_INSTANCES = 1;

    public abstract String getLegendaryTitle();
    public abstract void legendarySkill(Character target);
}

// ============================================================
// SUBCLASS dari LegendaryRole: LegendaryKnight
// Role eksklusif - hanya boleh ada 1 instance di seluruh game
// ============================================================
final class LegendaryKnight extends LegendaryRole {
    private static LegendaryKnight instance = null;
    private Warrior warrior;
    private String title;

    // Private constructor - hanya bisa dibuat lewat getInstance()
    private LegendaryKnight(Warrior warrior) {
        this.warrior = warrior;
        this.title = "Knight of the Sacred Blade";
        System.out.println("⚡ " + warrior.getName() + " telah dipilih menjadi LEGENDARY KNIGHT!");
        System.out.println("   Gelar: " + title);
    }

    // Singleton pattern - memastikan hanya ada 1 LegendaryKnight
    public static LegendaryKnight getInstance(Warrior warrior) {
        if (instance == null) {
            instance = new LegendaryKnight(warrior);
            instanceCount++;
            return instance;
        } else {
            System.out.println("❌ GAGAL: Sudah ada Legendary Knight (" + instance.warrior.getName() + ")! Role ini eksklusif, hanya boleh 1.");
            return null;
        }
    }

    public static boolean hasInstance() {
        return instance != null;
    }

    @Override
    public String getLegendaryTitle() {
        return title;
    }

    @Override
    public void legendarySkill(Character target) {
        int legendaryDamage = warrior.getAttackPower() * 3;
        System.out.println("⚔️✨ " + warrior.getName() + " menggunakan SACRED BLADE SLASH!");
        System.out.println("   Damage: " + legendaryDamage + " (3x ATK)");
        target.takeDamage(legendaryDamage);
    }

    public Warrior getWarrior() { return warrior; }
    public String getTitle() { return title; }
}

// ============================================================
// SUBCLASS dari LegendaryRole: LegendaryWizard
// Role eksklusif lainnya - hanya boleh ada 1 instance
// ============================================================
final class LegendaryWizard extends LegendaryRole {
    private static LegendaryWizard instance = null;
    private Mage mage;
    private String title;

    private LegendaryWizard(Mage mage) {
        this.mage = mage;
        this.title = "Archmage of the Ancient Order";
        System.out.println("🌟 " + mage.getName() + " telah dipilih menjadi LEGENDARY WIZARD!");
        System.out.println("   Gelar: " + title);
    }

    public static LegendaryWizard getInstance(Mage mage) {
        if (instance == null) {
            instance = new LegendaryWizard(mage);
            instanceCount++;
            return instance;
        } else {
            System.out.println("❌ GAGAL: Sudah ada Legendary Wizard (" + instance.mage.getName() + ")! Role ini eksklusif, hanya boleh 1.");
            return null;
        }
    }

    @Override
    public String getLegendaryTitle() { return title; }

    @Override
    public void legendarySkill(Character target) {
        int legendaryDamage = mage.getAttackPower() * 4;
        System.out.println("🌟✨ " + mage.getName() + " menggunakan ANCIENT METEOR STORM!");
        System.out.println("   Damage: " + legendaryDamage + " (4x ATK)");
        target.takeDamage(legendaryDamage);
    }

    public Mage getMage() { return mage; }
}

// ============================================================
// IMPLEMENTASI SKILL (Interface Skill)
// ============================================================

// Skill: Slash - Serangan fisik dasar Warrior
class Slash implements Skill {
    private int damage;
    public Slash(int damage) { this.damage = damage; }

    @Override
    public String getSkillName() { return "Slash"; }
    @Override
    public int getManaCost() { return 10; }
    @Override
    public int getDamage() { return damage; }
    @Override
    public void useSkill(Character target) {
        System.out.println("⚔️  Skill [Slash] digunakan! Damage: " + damage);
        target.takeDamage(damage);
    }
}

// Skill: Fireball - Serangan sihir api Mage
class Fireball implements Skill {
    private int damage;
    public Fireball(int damage) { this.damage = damage; }

    @Override
    public String getSkillName() { return "Fireball"; }
    @Override
    public int getManaCost() { return 30; }
    @Override
    public int getDamage() { return damage; }
    @Override
    public void useSkill(Character target) {
        System.out.println("🔥 Skill [Fireball] dilempar! Damage: " + damage);
        target.takeDamage(damage);
    }
}

// Skill: IceArrow - Serangan es jarak jauh Archer
class IceArrow implements Skill {
    private int damage;
    public IceArrow(int damage) { this.damage = damage; }

    @Override
    public String getSkillName() { return "Ice Arrow"; }
    @Override
    public int getManaCost() { return 20; }
    @Override
    public int getDamage() { return damage; }
    @Override
    public void useSkill(Character target) {
        System.out.println("❄️  Skill [Ice Arrow] dilepaskan! Damage: " + damage + " + target dibekukan!");
        target.takeDamage(damage);
    }
}

// Skill: HolyLight - Serangan cahaya suci Healer
class HolyLight implements Skill {
    private int damage;
    public HolyLight(int damage) { this.damage = damage; }

    @Override
    public String getSkillName() { return "Holy Light"; }
    @Override
    public int getManaCost() { return 25; }
    @Override
    public int getDamage() { return damage; }
    @Override
    public void useSkill(Character target) {
        System.out.println("☀️  Skill [Holy Light] menyinari! Damage: " + damage + " (cahaya suci)");
        target.takeDamage(damage);
    }
}

// ============================================================
// MAIN CLASS: Demo Program
// ============================================================
public class project_java_pilihan {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       RPG CHARACTER SYSTEM - DEMO        ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ---- 1. Membuat Karakter ----
        System.out.println(">>> [1] MEMBUAT KARAKTER");
        Warrior warrior = new Warrior("Arthur");
        Mage mage = new Mage("Merlin", "Fire");
        Archer archer = new Archer("Robin");
        Healer healer = new Healer("Elena");

        // ---- 2. Menambahkan Skill ke karakter ----
        warrior.addSkill(new Slash(60));
        mage.addSkill(new Fireball(90));
        archer.addSkill(new IceArrow(55));
        healer.addSkill(new HolyLight(40));

        // ---- 3. Tampilkan Status Karakter ----
        System.out.println("\n>>> [2] STATUS SEMUA KARAKTER");
        warrior.showStatus();
        mage.showStatus();
        archer.showStatus();
        healer.showStatus();

        // ---- 4. Demo Special Ability ----
        System.out.println("\n>>> [3] DEMO SPECIAL ABILITY");
        warrior.specialAbility();
        mage.specialAbility();
        archer.specialAbility();
        healer.specialAbility();

        // ---- 5. Demo Pertarungan ----
        System.out.println("\n>>> [4] DEMO PERTARUNGAN");
        System.out.println("--- Warrior menyerang Mage ---");
        warrior.useSkill(0, mage);

        System.out.println("\n--- Mage membalas dengan Fireball ---");
        mage.useSkill(0, warrior);

        System.out.println("\n--- Archer menembak Warrior ---");
        archer.shoot(warrior);

        System.out.println("\n--- Healer menyembuhkan Warrior ---");
        healer.healAlly(warrior);

        // ---- 6. Demo Sealed Class - LegendaryKnight ----
        System.out.println("\n>>> [5] DEMO SEALED CLASS - LEGENDARY ROLE");
        System.out.println("--- Mencoba membuat Legendary Knight pertama ---");
        LegendaryKnight lk1 = LegendaryKnight.getInstance(warrior);

        System.out.println("\n--- Mencoba membuat Legendary Knight KEDUA (seharusnya GAGAL) ---");
        Warrior warrior2 = new Warrior("Lancelot");
        LegendaryKnight lk2 = LegendaryKnight.getInstance(warrior2); // Harus gagal

        System.out.println("\n--- Mencoba membuat Legendary Wizard ---");
        LegendaryWizard lw1 = LegendaryWizard.getInstance(mage);

        // ---- 7. Demo Legendary Skill ----
        System.out.println("\n>>> [6] DEMO LEGENDARY SKILL");
        if (lk1 != null) {
            System.out.println("HP Monster sebelum: 300");
            Warrior dummyMonster = new Warrior("Dark Dragon");
            lk1.legendarySkill(dummyMonster);
        }
        if (lw1 != null) {
            Warrior dummyMonster2 = new Warrior("Shadow Demon");
            lw1.legendarySkill(dummyMonster2);
        }

        // ---- 8. Level Up ----
        System.out.println("\n>>> [7] LEVEL UP");
        warrior.levelUp();
        warrior.showStatus();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           DEMO SELESAI!                  ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}