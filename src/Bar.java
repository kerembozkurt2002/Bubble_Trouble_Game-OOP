/**
 * Bar class has Bar related variables, constants and Bar moment method and Bar draw, sets Bar's color and weight methods
 * @author Kerem Bozkurt, Student ID: 2020400177
 * @since Date: 16.04.2023
 */
public class Bar {
    // Creates variables for Bar Clas
    private double timeDif;
    static int TOTAL_GAME_DURATION = 40;

    public Bar() {
    }

    public int returnColorValue(double timeDif){
        // Finds the colour of bar at that time
        this.timeDif=timeDif;
        double color=255-(255*timeDif/TOTAL_GAME_DURATION);
        return (int)color;
    }
    public double returnBarWeight(double timeDif){
        // Finds the bar weight of bar at that time
        this.timeDif=timeDif;
        return 16-(16*timeDif/TOTAL_GAME_DURATION);
    }

    public void drawBar(int color, double barWeight){
        // Draws the bar
        StdDraw.setPenColor(255,color,0);
        StdDraw.filledRectangle(0,-0.5,barWeight,0.25);
    }

}

