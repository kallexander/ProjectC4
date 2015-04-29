package projectc4.c4.server;

import java.util.Random;
import static projectc4.c4.util.C4Constants.*;

/**
 * Created by Emil on 2015-04-28.
 */
public class Powerups {
    /**
     * Genererar en powerup
     * @return powerup-int
     */
    public static int generatePowerupTier1() {
        Random random = new Random();
        int powerup = random.nextInt(50)+1;
            //Klocka ska åka ner
        if (powerup <= 20) {
            return POWERUP_TIME;
            //Bomb ska åka ner
        } else if (powerup > 20 && powerup <= 40) {
            return POWERUP_BOMB;
            //Colorblind
        } else if (powerup > 40 && powerup <= 50) {
            return POWERUP_COLORBLIND;
        }
        return 0;
    }

    public static int generatePowerupTier3() {
        Random random = new Random();
        int powerup = random.nextInt(3) + 1;
        //extra turn
        if (powerup <= 2) {
            return POWERUP_EXTRATURN;
            //Shuffle board
        } else if (powerup == 3) {
            return POWERUP_SHUFFLE;
        }
        return 0;
    }

    public static int[] SpawnPowerupTier1() {
        Random random = new Random();
        int colRandom = random.nextInt(7);
        int chance = random.nextInt(6)+1;
        if (chance == 1) {
            int[] sendArray = new int[2];
            sendArray[1] = colRandom;
            sendArray[0] = generatePowerupTier1();
            return sendArray;
        }
        return null;
    }

    /**
     * Spawns powerups randomly in the gameboardgrid.
     * @return grid with powerups
     */


    public static int[][] spawnPowerupTier3() {
        int counter = 0;
        Random random = new Random();
        int[][] gameboard = new int[6][7];
        for (int i = 0; i < gameboard.length ; i++) {
            for (int j = 0; j < gameboard[i].length ; j++) {
               if (i <= 2) {
                   int chance = random.nextInt(30)+1;
                   if (chance == 1 && counter <= 3) {
                       gameboard[i][j] = generatePowerupTier3();
                       counter ++;
                   } else {
                       gameboard[i][j] = 0;
                   }
               } else {
                   gameboard[i][j] = 0;
               }
            }
        }
        return gameboard;
    }
}