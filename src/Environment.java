import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Environment class has game loop method, draw the final picture method, restart or quit the game method, control Arrow hits Ball or Ball hits Player methods and constants
 * @author Kerem Bozkurt, Student ID: 2020400177
 * @since Date: 16.04.2023
 */
public class Environment {
    // Creates static variables for Environment Class
    static int canvasWidth=800;
    static int canvasHeight=500;
    static int PAUSE_DURATION = 30;
    private final double GRAVITY=0.000000000003;

    public Environment() {
        //Sets Canvas, Scales and creates background
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
        StdDraw.picture(8, 4.5, "background.png", 16, 9, 0);
        StdDraw.picture(8,-0.5,"bar.png",16,1,0);
    }
    public void gameLoop() {
        // gameLoop is my main method to run the game, there firstly I call the Player, Arrow and Bar constructors and creates objects
        Player p = new Player();
        Arrow a = new Arrow();
        Bar bar = new Bar();

        // Creates time variables to keep time differences
        double startTime = System.currentTimeMillis() ;
        double fromTime = System.currentTimeMillis() / 1000.0;

        // isGameFinish and winOrLost booleans provides the information whether the game finisher or not
        boolean isGameFinish=false;
        boolean winOrLost=false;

        // I have three ball objects in the beginning of the game, I create them in here
        Ball ball0=new Ball(3.7,5.,0,0.05,0.2);
        Ball ball1=new Ball(5,5,1,-0.05,0.6);
        Ball ball2=new Ball(4,7,2,0.05,0.8);

        // To hold the ball obects I creates Arraylist<Ball>, and add the beginning balls
        ArrayList<Ball> ballList= new ArrayList<>();
        ballList.add(ball0);
        ballList.add(ball1);
        ballList.add(ball2);



        // Creates a while loop to create the picture of every moment
        while (true) {
            // I clear the canvas and calls player movements and arrow moments from Arrow and Player classes
            StdDraw.clear();
            StdDraw.picture(8, 4.5, "background.png", 16, 9, 0);
            p.playerMovement();
            a.arrowMovement(p);

            // I creates variables to hold the coordinates of Arrow, and Player
            double arrowX = a.getArrowX();
            double arrowY = a.getArrowY() * 2;
            boolean arrowValid = a.getFlag(); // It looks whether there is an arrow or not
            double playerX = p.getPlayerX();

            // I creates the Player picture with help of playerX and creates the background of bar
            StdDraw.picture(playerX, 0.50, "player_back.png", 1, 1);
            StdDraw.picture(8, -0.5, "bar.png", 16, 1, 0);

            // I create currentTime to keep the time on each turn of the loop
            double currentTime = System.currentTimeMillis() / 1000.0;
            double dT = currentTime - fromTime;  // I find the time differences between current time and previous turn of loop
            fromTime = currentTime;              // Set the fromTime current time for next turns of loop

            ArrayList<Ball> removeBall = new ArrayList<>(); // Creates Arraylist<Ball> to hold the data which balls were hit
            ArrayList<Ball> addBall = new ArrayList<>();    // Creates Arraylist<Ball> to hold the data I have to add new balls after hit level 2 or leve 1 balls

            //Creates for loop to make the ball movements and understand whether the ball hits player or arrow hits the ball
            for (Ball ball : ballList) {
                // Calls the data fields of ball object
                int level = ball.getLevel();
                double tempradius = ball.getRadius();

                double tempX = ball.getPositionX();
                double tempspeedX = ball.getSpeedX();

                double speedYinitial = ball.getSpeedY();
                double tempY = ball.getPositionY();

                // Do calculations to find the Y axis speed and Y axis movement of the ball with gravitational acceleration
                double dV = -(dT * GRAVITY * 100000000000.0 * 20);
                double speedYfinal = speedYinitial + dV;
                double dX = ((speedYinitial + speedYfinal) / 2) * dT;

                // Calls movement of X axis and Y axis from methods
                tempY= ball.moveY(dX,tempY,tempradius,speedYfinal);
                double finalPositionX = ball.moveX(tempX, tempspeedX, tempradius, ball);

                //Sets the new position of ball in X axis and Y axis
                ball.setPositionY(tempY);
                ball.setPositionX(finalPositionX);

                // Checks whether the ball hits the player and if ball hits changes the booleans to finish the game
                if (!isBallHitPlayer(playerX, finalPositionX, tempY, tempradius/2)) {
                    StdDraw.picture(finalPositionX, tempY, "ball.png", tempradius,tempradius);
                    isGameFinish=true;
                    winOrLost=false;
                    break;
                }

                // Checks whether the arrow hits the ball and if arrow hits the ball add the ball to remove list and makes arrow inactive with method
                if (isArrowHitBall(arrowX, arrowY, tempradius, finalPositionX, tempY, arrowValid)) {
                    removeBall.add(ball);
                    a.setFlagTrue();
                    a.setArrowY(0.0);

                    // Add 2 new balls to the game if the level of the hit ball is 2 or 1
                    if (level == 2) {
                        addBall.add(new Ball(finalPositionX, tempY, 1, -tempspeedX, Math.abs(speedYfinal)));
                        addBall.add(new Ball(finalPositionX, tempY, 1, tempspeedX, Math.abs(speedYfinal)));
                    }
                    if (level == 1) {
                        addBall.add(new Ball(finalPositionX, tempY, 0, -tempspeedX, Math.abs(speedYfinal)));
                        addBall.add(new Ball(finalPositionX, tempY, 0, tempspeedX, Math.abs(speedYfinal)));

                    }
                } else { // Makes the ball image if the ball didn't hit the player or arrow didn't hit the ball
                    StdDraw.picture(finalPositionX, tempY, "ball.png", tempradius, tempradius);

                }
            }

            //Removes Balls from ball list
            for (Ball ball : removeBall) {
                ballList.remove(ball);
            }

            //Adds balls to ball list
            for (Ball ball : addBall) {
                ballList.add(ball);
            }

            //Checks the whether the ball list is empty
            if(ballList.isEmpty()){
                isGameFinish=true;
                winOrLost=true;
            }


            // FindS the time difference for which level the bar should be drawn
            double nowTime = System.currentTimeMillis();
            double timeDif = (nowTime - startTime) / 1000.0;
            int color = bar.returnColorValue(timeDif);
            if (color == 0) {
                //Checks whether the bar time finishes or not
                isGameFinish=true;
                winOrLost=false;
            }
            double barWeight = bar.returnBarWeight(timeDif);  // Finds the bar weight
            bar.drawBar(color, barWeight);

            if(isGameFinish){ //// Checks the game were finished or not
                finishDraw(arrowX,a,playerX,color,bar,barWeight,winOrLost ); // Draws the picture where the game finishes
                restartOrQuit(p);  // Checks whether the player want to continue to play game or not
            }
            StdDraw.show();
            StdDraw.pause(PAUSE_DURATION);
        }

    }

    private boolean isArrowHitBall(double arrowX,double arrowY,double tempradius,double tempx,double tempy,boolean arrowValid){
        //Controls whether the Arrow hits the ball or not with distance calculations
        if(arrowValid){
            return false;
        }
        else {
            double x_distance=Math.pow(Math.abs(tempx - arrowX),2);
            double y_distance=Math.pow(Math.abs(tempy - arrowY),2);
            double distance=Math.sqrt(x_distance+y_distance);
            if (distance <= tempradius) { //Math.abs(tempx - arrowX) <= tempradius
                return true;
            }
            else if ((arrowY>=tempy) &&(x_distance<=tempradius) ){
                return true;
             }
            else {
                return false;
            }
        }
    }
    private boolean isBallHitPlayer(double playerX,double tempx,double tempy,double tempradius){
        //Controls whether the Ball hits the Player or not with distance calculations
        if((Math.abs(tempx-(playerX-0.5))<=tempradius) && (tempy<=1.0)){
            return false;
        }
        else if((Math.abs(tempx-(playerX+0.5))<=tempradius) && tempy<=1.0){
            return false;
        }
        else if((Math.abs(tempy-1.0)<=tempradius) && Math.abs(tempx-(playerX+0.5))<=tempradius) {
            return false;
        }
        else if((Math.abs(tempy-1.0)<=tempradius) && Math.abs(tempx-(playerX-0.5))<=tempradius) {
            return false;
        }
        else if((Math.abs(tempy-1.0)<=tempradius) && Math.abs(tempx-(playerX))<=0.5) {
            return false;
        }
        else {
            return true;
        }
    }
    public void finishDraw(double arrowX, Arrow a, double playerX, int color, Bar bar, double barWeight,boolean winOrLost){
        // Makes the picture when the game were finished
        StdDraw.clear();
        StdDraw.picture(8, 4.5, "background.png", 16, 9, 0);
        if(!a.getFlag()){
            StdDraw.picture(arrowX, 4.5, "arrow.png", 0.2, 4.5 * 2);
        }
        StdDraw.picture(playerX, 0.50, "player_back.png", 1, 1);
        StdDraw.picture(8, -0.5, "bar.png", 16, 1, 0);
        if(color!=0){
            bar.drawBar(color, barWeight);
        }
        StdDraw.picture(8,9/2.18,"bar.png",16/3.8,9/4.0);
        StdDraw.setPenColor(Color.black);
        if(winOrLost){
            StdDraw.setFont( new Font("Helvetica", Font.BOLD, 30) );
            StdDraw.text(16/2.0,9/2.0,"YOU WON!");
            StdDraw.setFont( new Font("Italic", Font.ITALIC, 15) );
            StdDraw.text(16/2.0,9/2.3,"To Replay Click “Y”");
            StdDraw.text(16/2.0,9/2.6,"To Quit Click “N”");

        }
        else {
            StdDraw.setFont( new Font("Helvetica", Font.BOLD, 30) );
            StdDraw.text(16/2.0,9/2.0,"GAME OVER!");
            StdDraw.setFont( new Font("Italic", Font.ITALIC, 15) );
            StdDraw.text(16/2.0,9/2.3,"To Replay Click “Y”");
            StdDraw.text(16/2.0,9/2.6,"To Quit Click “N”");
        }
        StdDraw.show();

    }
    public void restartOrQuit(Player p){
        // Checks whether the Player want to continue to play game or not
        while (true){
            if(StdDraw.isKeyPressed(KeyEvent.VK_N)){
                System.exit(0);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                p.setPlayerX(8.0);
                gameLoop();
            }
        }
    }

}

