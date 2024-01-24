/**
 * Ball class has Ball related variables, constants and Ball movement in X and Y axis movement methods, Ball's data fields get, set and making radius methods
 * @author Kerem Bozkurt
 * @since Date: 16.04.2023
 */
public class Ball {
    // Creates variables for Ball Clas
    private final static double scaleY=10.0;
    private final static  double RADIUS_MULTIPLIER=2.0;
    private final  double minPossibleRadius;
    private double positionX;
    private final int level;
    public double radius;
    private double speedX;
    private double speedY;
    private  double positionY;

    public Ball(double positionX,double positionY,int level,double speedX,double speedY) {
        // Put the values from constructor to data fields of object
        this.speedX=speedX;
        this.speedY=speedY;
        this.positionY=positionY;
        this.level=level;
        this.minPossibleRadius = scaleY * 0.0175 * 2;
        this.positionX = positionX;
        this.radius=this.addRadius(this.level);
    }
    // Makes methods to get and set the data fields

    public double getPositionX() {
        return this.positionX;
    }
    public double getPositionY() {
        return this.positionY;
    }

    public double getSpeedX() {
        return this.speedX;
    }
    public double getSpeedY() {
        return this.speedY;
    }
    public double getRadius() {
        return this.radius;
    }
    public int getLevel(){
        return this.level;
    }

    public void setPositionX(double positionX) {

        this.positionX=positionX;
    }
    public void setPositionY(double positionY) {

        this.positionY=positionY;
    }

    public void setVY(double speedY) {
        this.speedY= speedY;
    }

    public double addRadius(int temp) {
        //Makes the radius of the ball object
        if (temp == 0) {
            return  (minPossibleRadius * (Math.pow(RADIUS_MULTIPLIER, 0)));
        } else if (temp == 1) {
            return (minPossibleRadius * (Math.pow(RADIUS_MULTIPLIER, 1)));
        } else if (temp == 2) {
            return (minPossibleRadius * (Math.pow(RADIUS_MULTIPLIER, 2)));
        }
        return 0;
    }

    public  double moveX(double tempX, double tempspeedX, double tempradius, Ball ball) {
        // Find the X axis movement of the ball at that time with X axis speed
        if (Math.abs(tempX + tempspeedX) > 16.0 - tempradius / 2 || Math.abs(tempX + tempspeedX) < tempradius / 2) {
            ball.speedX=-tempspeedX;
        }
        return tempX + tempspeedX;
    }
    public  double moveY(double dX,double tempY,double tempradius, double speedYfinal) {
        // Find the Y axis movement of the ball at that time with Y axis speed and gravitational acceleration
        if ((dX + tempY) <= (tempradius / 2)) {
            if (level == 0) {
                speedYfinal = 4.0;
            } else if (level == 1) {
                speedYfinal = 6.0;
            } else if (level == 2) {
                speedYfinal = 7.5;
            }
            tempY = tempradius / 2;
            setVY(speedYfinal);

        } else {
            tempY = tempY + dX;
            setVY(speedYfinal);

        }
        return tempY;
    }
}
