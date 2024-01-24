import java.awt.event.KeyEvent;
/**
 * Arrow class has Arrow related variables, constants, Arrow moment methods and Arrow's data fields get, set and plus methods
 * @author Kerem Bozkurt
 * @since Date: 16.04.2023
 */
public class Arrow {
    // Creates  variables for Arrow Clas
    private  double ArrowX;
    private double ArrowY=0;
    private boolean flag=true;
    Arrow(){ }
    // Creates methods to get, set and increase the data fields
    public double getArrowY() {
        return ArrowY ;
    }
    public double plusArrowY(){
        return ArrowY+0.2;
    }
    public  void setArrowY(double Y) {
        this.ArrowY=Y;
    }
    public void  setFlagFalse(){
        this.flag=false;
    }
    public void  setFlagTrue(){
        this.flag=true;
    }
    public boolean getFlag(){
        return flag;
    }
    public void setArrowX(double X){
        this.ArrowX=X;
    }
    public double getArrowX(){
        return ArrowX;
    }

    public void arrowMovement(Player p){
        // Finds the Arrow moment at that time
        if (!getFlag()) {
            if (getArrowY() >= 4.5) {
                setFlagTrue();
                double arrowX = getArrowX();
                StdDraw.picture(arrowX, 4.5, "arrow.png", 0.2, 4.5 * 2);
                setArrowY(0.0);

            } else if (0.0 < getArrowY() && getArrowY() < 4.5) {
                double arrowY = plusArrowY();
                double arrowX = getArrowX();
                StdDraw.picture(arrowX, arrowY, "arrow.png", 0.2, arrowY * 2);
                setArrowY(arrowY);
            }

        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            if (getFlag()) {
                setFlagFalse();
                setArrowX(p.getPlayerX());
                double arrowX = p.getPlayerX();
                double arrowY = plusArrowY();
                StdDraw.picture(arrowX, arrowY, "arrow.png", 0.2, arrowY * 2);
                setArrowY(arrowY);
            }
        }
    }

}

