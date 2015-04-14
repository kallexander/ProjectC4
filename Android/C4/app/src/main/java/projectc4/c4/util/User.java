package projectc4.c4.util;

import java.io.Serializable;

/**
 * @author Kalle Bornemark
 */
public class User implements Serializable {
    private final String username;
    private int[] gameResults;
    private String firstName;
    private String lastName;

    public User(String username){
        this.username = username;
        gameResults = new int[4];
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 0 = Total games played
     * 1 = Games won
     * 2 = Games lost
     * 3 = Games drawn
     * @param result The result which counter should be increased.
     */
    public void newGameResult(int result) {
        gameResults[result]++;
        gameResults[0]++;
    }

    public int[] getGameResults() {
        return gameResults;
    }
}
