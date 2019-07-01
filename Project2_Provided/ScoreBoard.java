import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color; // uncomment this line if using old version of Greenfoot
/**
 * ScoreBoard object for the "banner" for CSC14400 Project #2
 * 
 * @author Stephen Blythe 
 * @version 3/2019
 */
public class ScoreBoard extends Actor
{
    private int fuelLeft;  // how much fuel left to display
    private int currScore; // current score to display
    private int highScore; // high score to display
    
    /**
     * Constructor that takes the initial amount of fuel,
     * and:
     *    - sets the fuel left to the requested amount
     *    - sets the high score and the current score to 0. 
     *    
     *    
     * @param initFuel - the initial amount of fuel to display
     */
    public ScoreBoard(int initFuel)
    {
        fuelLeft=initFuel; // start with appropriate fuel amount
        
        // score and high score are both 0.
        currScore=0;        
        highScore=0;
    }
    
    /**
     * Construct a default ScoreBoard. All values will be 0. 
     */
    public ScoreBoard()
    {
        fuelLeft=0;
        currScore=0;
        highScore=0;
    }
    
    // called after added to world. Automatically moves
    //   scoreboard banner to top center of world 
    protected void addedToWorld(World w)
    {
        //
        GreenfootImage sbimg = new GreenfootImage(w.getWidth(), 30);
        setImage(sbimg);
        redraw();
        setLocation(w.getWidth()/2, sbimg.getHeight()/2);
    }
    
    // redraws the scoreboard with updated values. 
    private void redraw()
    {
        // blank out the current scoreboard image (to all gray)
        GreenfootImage img = getImage();
        img.setColor(Color.GRAY);
        img.fill();
        
        // set drawing color to black and set font size
        img.setColor(Color.BLACK);
        img.setFont(img.getFont().deriveFont(30));

        // add the stats to the image
        img.drawString("Fuel Left: " + fuelLeft, 20, 25);
        img.drawString("Score: " + currScore, 275, 25);
        img.drawString("High Score: " + highScore, 500, 25);        
    }

    /**
     * gets amount of fuel left as indicated in ScoreBoard
     * @return amount of fuel left
     */
    public int getFuelLeft()
    {
        return fuelLeft;
    }
    
    /**
     * sets the amount of fuel displayed in the ScoreBoard
     * @param fuel - the new fuel amount
     */
    public void setFuelLeft(int fuel)
    {
        fuelLeft = fuel; // update amount
        redraw();        // redraw accordingly 
    }
    
    /**
     * gets current score as indicated in ScoreBoard
     * @return current score
     */
    public int getScore()
    {
        return currScore;
    }
    
    /**
     * sets the current score displayed in the ScoreBoard
     * @param score - the new current score to use
     */
    public void setScore(int score)
    {
        currScore=score; // update score
        redraw();        // redraw accordingly
    }
    
    /**
     * gets high score  as indicated in ScoreBoard
     * @return high score
     */
    public int getHighScore()
    {
        return highScore;
    }
    
    /**
     * sets the high score displayed in the ScoreBoard
     * @param high - the new high score
     */
    public void setHighScore(int high)
    {
        highScore=high;  // update the high score value
        redraw();        // redraw accordingly
    }
}
