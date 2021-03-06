import java.util.Random;
import java.util.Scanner;

public class ConsoleAdventureGame {
    public static void main(String[] args) {
        // System objects //
        Scanner in = new Scanner(System.in);
        // Random number object generator
        Random rand = new Random();

        // Player Variables //
        Hero player = new Hero();
        System.out.println("What is your name?");
        player.name = in.nextLine();

        // Running Variable, game keeps running unless there is a condition for it to stop (while loop)
        boolean running = true;

        System.out.println("♞ Welcome to the Swamp " + player.name + "! ♞");

//        GAME:
        while (running) {
            System.out.println("----------------------");
            // Start Combat with Enemy, uses random generator, picks number from 1 to maxEnemyHealth variable, in

//            MAKE RANDOM ENEMY
            int enemySelector = rand.nextInt(5);
            Enemy enemy = null;
            int enemyHealth = 0;


            if (enemySelector == 1) {
                enemy = new Donkey();
                enemyHealth = rand.nextInt(enemy.maxEnemyHealth) + 10;
            } else if (enemySelector == 2) {
                enemy = new Cat();
                enemyHealth = rand.nextInt(enemy.maxEnemyHealth) + 20;
            } else if (enemySelector == 3) {
                enemy = new Farquad();
                enemyHealth = rand.nextInt(enemy.maxEnemyHealth) + 15;
            } else if (enemySelector == 4) {
                enemy = new Godmother();
                enemyHealth = rand.nextInt(enemy.maxEnemyHealth) + 10;
            } else {
                enemy = new Dragon();
                enemyHealth = rand.nextInt(enemy.maxEnemyHealth) + 40;
            }


            // Random enemy generated
            System.out.println("\t# " + enemy.name + " appeared! #\n");

            // Give player options while enemy is alive
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + player.health);
                System.out.println("\t" + enemy.name + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1 Attack");
                System.out.println("\t2 Drink Health Potion");
                System.out.println("\t3 Run!");

                // Get users input
                String input = in.nextLine();
                if (input.equals("1")) {
                    // generate random damage between 1 & 50
                    int damageDealt = rand.nextInt(player.attackDamage) + 10;
                    //  generate random damage taken
                    int damageTaken = rand.nextInt(enemy.enemyAttackDamage);
                    // Take the damage off
                    enemyHealth -= damageDealt;

                    // Attack print
                    System.out.println("\t>1 You attack " + enemy.name + " for " + damageDealt + " damage.");

                    if (enemyHealth <= 0) {
                        enemyDefeated(enemy, player);
                        break;
                    }
//                    ENEMY ATTACKS
                    player.health -= damageTaken;
                    System.out.println("\t>" + enemy.name + " " + enemy.specialAttack + " you! You take " + damageTaken + " damage" +
                            "!\n");

                    // Health Low Print
                    if (player.health < 1) {
                        System.out.println("\t> You have taken too much damage to continue!");
                        break;
                    }
                    // Drink Health Potion //
                } else if (input.equals("2")) {
                    // check for health potion
                    if (player.health == player.baseHealth) {
                        System.out.println("Your health is Full!");

                        int damageTaken = rand.nextInt(enemy.enemyAttackDamage);

                        player.health -= damageTaken;
                        System.out.println("\t>" + enemy.name + " " + enemy.specialAttack + " you! You take " + damageTaken + " damage" +
                                "!");
                        if (player.health < 1) {

                            System.out.println("\t> You have taken too much damage to continue!");
                            break;
                        }
                    } else if (player.numHealthPotions > 0) {
                        // Add to your health
                        int actualHeal = player.healthPotionHealAmount;
                        if (player.health + player.healthPotionHealAmount > player.baseHealth) {
                            actualHeal = player.baseHealth - player.health;
                            player.health = player.baseHealth;

                        } else {
                            player.health += player.healthPotionHealAmount;
                        }
                        // Take one health potion away
                        player.numHealthPotions--;
                        System.out.println("\t> You drink a health potion, +" + actualHeal + "." + "\n\t>" +
                                " You now have " + player.health + " HP" + "\n\t> You have " + player.numHealthPotions + " Health " +
                                "potions left.\n");


//                        ENEMY ATTACK
                        int damageTaken = rand.nextInt(enemy.enemyAttackDamage);

                        player.health -= damageTaken;
                        System.out.println("\t>" + enemy.name + " " + enemy.specialAttack + " you! You take " + damageTaken + " damage" +
                                "!");
                        if (player.health < 1) {

                            System.out.println("\t> You have taken too much damage to continue!");
                            break;
                        }
                    } else {
                        // no health potions
                        System.out.println("\t> You have no health potions left, defeat enemies to try and recover " +
                                "one!");
                    }
                    // For running away //
                } else if (input.equals("3")) {
                    System.out.println("\t> You ran away from the " + enemy.name + "!");
                    break;
                } else {
                    // If user enters something else
                    System.out.println("\tInvalid command.");
                }
            }


            // Health gets too low //
            if (player.health < 1) {
                System.out.println();
                System.out.println("             ,-'     `._ \n" +
                        "                 ,'           `.        ,-. \n" +
                        "               ,'               \\       ),.\\ \n" +
                        "     ,.       /                  \\     /(  \\; \n" +
                        "    /'\\\\     ,o.        ,ooooo.   \\  ,'  `-') \n" +
                        "    )) )`. d8P\"Y8.    ,8P\"\"\"\"\"Y8.  `'  .--\"' \n" +
                        "   (`-'   `Y'  `Y8    dP       `'     / \n" +
                        "    `----.(   __ `    ,' ,---.       ( \n" +
                        "           ),--.`.   (  ;,---.        ) \n" +
                        "          / \\O_,' )   \\  \\O_,'        | \n" +
                        "         ;  `-- ,'       `---'        | \n" +
                        "         |    -'         `.           | \n" +
                        "        _;    ,            )          : \n" +
                        "     _.'|     `.:._   ,.::\" `..       | \n" +
                        "  --'   |   .'     \"\"\"         `      |`. \n" +
                        "        |  :;      :   :     _.       |`.`.-'--. \n" +
                        "        |  ' .     :   :__.,'|/       |  \\ \n" +
                        "        `     \\--.__.-'|_|_|-/        /   ) \n" +
                        "         \\     \\_   `--^\"__,'        ,    | \n" +
                        "   -hrr- ;  `    `--^---'          ,'     | \n" +
                        "          \\  `                    /      / \n" +
                        "           \\   `    _ _          / \n" +
                        "            \\           `       / \n" +
                        "             \\           '    ,' \n" +
                        "              `.       ,   _,' \n" +
                        "                `-.___.---' ");
                System.out.println("#########################");
                System.out.println(" # You have been slain # ");
                System.out.println("#########################");
                System.out.println(" # This is my swamp # ");
                System.out.println("#########################");
                break;
            }

            System.out.println("----------------------");

            System.out.println(" # You have " + player.health + " HP left. #");


            System.out.println("----------------------");
            // Next Adventure Options
            System.out.println("What would you like to do?");
            System.out.println("1. Continue fighting.");
            System.out.println("2. Leave the Swamp.");
            System.out.println("3. Check yourself");
            if (player.numHealthPotions > 0) {
                System.out.println("4. Heal");
            }


            // Run loop based on user's input
            String input = in.nextLine();
            // If player input is not 1 or 2
            while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                System.out.println("Invalid command!");
                input = in.nextLine();
            }
            // If player chooses 1 or 2
            if (input.equals("1")) {
                System.out.println("You continue further into the Swamp.....");
            } else if (input.equals("2")) {
                System.out.println("You left the Swamp....");
                break;
            } else if (input.equals("3")) {
                System.out.println("\t\n Your stats:");
                System.out.println("\t Your health: " + player.health + " HP");
                System.out.println("\t Maximum health: " + player.baseHealth);
                System.out.println("\t To next level: " + player.toNextLevel + " exp.");
                System.out.println("\t Strength: " + player.attackDamage);
                System.out.println("\t Health potions: " + player.numHealthPotions);
                System.out.println("\t And you have " + player.totalExperience + " total exp points");
            } else if (input.equals("4") && player.numHealthPotions > 0) {
                if (player.health == player.baseHealth) {
                    System.out.println("Your health is full!");
                } else {
                    int actualHeal = player.healthPotionHealAmount;
                    if (player.health + player.healthPotionHealAmount > player.baseHealth) {
                        actualHeal = player.baseHealth - player.health;
                        player.health = player.baseHealth;

                    } else {
                        player.health += player.healthPotionHealAmount;
                    }
                    // Take one health potion away
                    player.numHealthPotions--;
                    System.out.println("\t> You drink a health potion, +" + actualHeal + "." + "\n\t>" +
                            " You now have " + player.health + " HP" + "\n\t> You have " + player.numHealthPotions + " Health " +
                            "potions left.\n");
                }

            }
        }
        // Leaving Game
        System.out.println("#########################");
        System.out.println(" # THANKS FOR PLAYING! # ");
        System.out.println("#########################");
    }

    public static void enemyDefeated(Enemy enemy, Hero player) {
        Random rand = new Random();
        System.out.println("\t> " + enemy.name + " was defeated!\n");
        System.out.println("# You gained " + enemy.experienceYield + " exp! #\n");

        levelUp(player, enemy.experienceYield);

        if (rand.nextInt(100) < player.healthPotionDropChance) {
            if (player.numHealthPotions < 4) {
                player.numHealthPotions++;
                System.out.println(" # The " + enemy.name + " dropped a health potion. # ");
                System.out.println(" # You have " + player.numHealthPotions + " health potion(s).");
            } else {
                System.out.println(" # The " + enemy.name + " dropped a health potion. # ");
                System.out.println(" # Your pack is full! # ");
            }
            if (rand.nextInt(100) < player.levelUpChance) {
                player.attackDamage = player.attackDamage + 5;
                int attackDisplay = player.attackDamage + 10;
                System.out.println(" # You got stronger. # ");
                System.out.println(" # Your base attack is  " + attackDisplay);
            }
        }
    }

    public static void levelUp(Hero player, int exp) {
        int spillover = exp - player.toNextLevel;

        if (player.toNextLevel - exp <= 0) {
//            LEVEL UP LOGIC
            int expHolder = player.toNextLevel;

            player.baseLevel += 1000;
            player.toNextLevel = player.baseLevel;
//            player.toNextLevel = player.toNextLevel - spillover;
            System.out.println("# You leveled up! #");
            player.baseHealth += 5;
            player.health = player.baseHealth;
            System.out.println("# Your health is now " + player.health + "HP! #\n");
            if (player.toNextLevel - spillover <= 0) {
                player.totalExperience += expHolder;
                levelUp(player, spillover);
            } else {
                player.totalExperience += exp;
                player.toNextLevel -= spillover;
//                System.out.println("# " + player.toNextLevel + "exp to level up.\n");
            }
        } else {
            player.totalExperience += exp;
            player.toNextLevel -= exp;
//            System.out.println("# " + player.toNextLevel + " exp to level up.\n");
        }
    }

}