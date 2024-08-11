// Singleton Pattern: Game State Management
class Game {
    private static Game instance;
    private String currentLevel;
    private String difficulty;

    private Game() {
        currentLevel = "Level 1";
        difficulty = "Easy";
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void setCurrentLevel(String level) {
        currentLevel = level;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void startGame() {
        System.out.println("Starting game at " + currentLevel + " with " + difficulty + " difficulty.");
    }
}

// Factory Method Pattern: Enemy Creation
interface Enemy {
    void attack();
}

class Goblin implements Enemy {
    public void attack() {
        System.out.println("Goblin attacks!");
    }
}

class Orc implements Enemy {
    public void attack() {
        System.out.println("Orc attacks!");
    }
}

abstract class EnemyFactory {
    public abstract Enemy createEnemy();
}

class GoblinFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new Goblin();
    }
}

class OrcFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new Orc();
    }
}

// Abstract Factory Pattern: Weapon and Power-Up Creation
interface Weapon {
    void use();
}

class Sword implements Weapon {
    public void use() {
        System.out.println("Swinging sword!");
    }
}

class Bow implements Weapon {
    public void use() {
        System.out.println("Shooting arrow!");
    }
}

interface PowerUp {
    void activate();
}

class HealthPotion implements PowerUp {
    public void activate() {
        System.out.println("Health potion activated!");
    }
}

class SpeedBoost implements PowerUp {
    public void activate() {
        System.out.println("Speed boost activated!");
    }
}

interface GameFactory {
    Weapon createWeapon();
    PowerUp createPowerUp();
}

class EasyGameFactory implements GameFactory {
    public Weapon createWeapon() {
        return new Sword();
    }

    public PowerUp createPowerUp() {
        return new HealthPotion();
    }
}

class HardGameFactory implements GameFactory {
    public Weapon createWeapon() {
        return new Bow();
    }

    public PowerUp createPowerUp() {
        return new SpeedBoost();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        // Using Singleton pattern to manage game state
        Game game = Game.getInstance();
        game.setDifficulty("Easy");
        game.startGame();

        // Using Factory Method pattern to create enemies
        EnemyFactory goblinFactory = new GoblinFactory();
        Enemy goblin = goblinFactory.createEnemy();
        goblin.attack();

        EnemyFactory orcFactory = new OrcFactory();
        Enemy orc = orcFactory.createEnemy();
        orc.attack();

        // Using Abstract Factory pattern to create weapons and power-ups
        GameFactory easyGameFactory = new EasyGameFactory();
        Weapon easyWeapon = easyGameFactory.createWeapon();
        PowerUp easyPowerUp = easyGameFactory.createPowerUp();
        easyWeapon.use();
        easyPowerUp.activate();

        GameFactory hardGameFactory = new HardGameFactory();
        Weapon hardWeapon = hardGameFactory.createWeapon();
        PowerUp hardPowerUp = hardGameFactory.createPowerUp();
        hardWeapon.use();
        hardPowerUp.activate();
    }
}
