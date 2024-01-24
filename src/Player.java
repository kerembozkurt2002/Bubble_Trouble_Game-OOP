import java.awt.event.KeyEvent;
/**
 * Player class has Player related constants, Player moment methods and Player's data fields get and set methods
 * @author Kerem Bozkurt
 * @since Date: 16.04.2023
 */
public class Player {
    // Creates variables for Player Clas

    static double PLAYER_WIDTH=0.5;
    private double playerX=8;


    public double getPlayerX(){
        return playerX;
    } // Method to get X axis coordinate of Player
    public void playerMovement(){
        // Makes the Player movement in X axis
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            if (playerX - PLAYER_WIDTH<=0){
                playerX=playerX;
            }
            else {
                playerX = playerX - 0.1;
            }
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            if (playerX+PLAYER_WIDTH>=16.0 ){
                playerX=playerX;
            }
            else {
                playerX = playerX + 0.1;
            }
        }
    }
    public void setPlayerX(double coordinate){
        this.playerX=coordinate;
    } // Set the X axis coordinate of Player
}
