import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.util.Scanner;
import java.io.*;
/**
 * MyWorld is the "containing" class for the second CSC14400 project.
 * It allows a spacehip to fly around:
 *    - without hitting walls
 *    - without hitting reaper (objects)
 *    - gaining fuel when close to 4 or more non-empty fuel sources
 *    - updating the ScoreBoard as needed
 * @author Stephen Blythe
 * @version 3/2019
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    //instance variabes Spaceship,Reaper,ScoreBoard
    public Spaceship spaceship;  
    private Reaper reaper;
    private ScoreBoard scoreBoard;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        // Create a new Spaceship 
        spaceship = new Spaceship();
        // add the Spaceship in the center of the world.
        addObject(spaceship, 400, 300);
        //Create a new Reaper
        reaper = new Reaper(35, 60);
        //add the Reaper object at location x=35, y=60
        addObject(reaper, 35, 60);
        //Create a new ScoreBoard
        scoreBoard = new ScoreBoard();
        //add a new ScoreBoard object at the top middle of the world
        addObject(scoreBoard, 400, 0);

    }

    /**
     * act allows user to press "L" to load a new game. 
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("l")){
            //remove the Spaceship
            removeObject(spaceship);
            // remove any"Game Over"message
            showText(null, 400, 300);
            //a new Spaceship should be added at the center of the world
            spaceship = new Spaceship();
            addObject(spaceship, 400, 300);
            //if the current game score is higher than the high score, the high score should be updated
            scoreBoard = getScoreBoard();
            if(scoreBoard.getScore() > scoreBoard.getHighScore()){
                scoreBoard.setHighScore(scoreBoard.getScore());

            } 
            //set  score_= 0 and FuelLeft=100
            scoreBoard.setScore(0);            
            scoreBoard.setFuelLeft(100);
            //remove all objects of FuelSource and Reaper classes 
            removeObjects(getObjects(FuelSource.class));
            removeObjects(getObjects(Reaper.class));
            //create FileDialog GUI
            FileDialog fd = null;
            fd = new FileDialog(fd, "Select an input file", FileDialog.LOAD);
            fd.setVisible(true);
            //Absolute path selected file(getDirectory() gets the directory of this Filedialog and getFile() gets the selected file) 
            String path = fd.getDirectory() + fd.getFile();
            //Create a new File from absolute path
            File myFile = new File(path);
            Scanner scanner = null;
            try 
            {  
                //Constructs a new Scanner that produces values scanned from the myFile
                scanner = new Scanner(myFile);
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("Could not open file: " + path);
                return;
            } 
            while(scanner.hasNext()) 
            {
                //the scanner finds and returns the next complete token
                String str = scanner.next(); 
                // if the String will begin with a’#’character the scanner should be return the rest of the line.Line is a comment and 
                //should be ignored
                if(str.startsWith("#")){
                    scanner.nextLine(); 

                }
                //if statment in a case insensitive manner
                else if(str.equalsIgnoreCase("fuel")){
                    //scanner returns Integer fuel, coordinats x and y 
                    int fuel = scanner.nextInt();
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    //Create new FuelSource
                    FuelSource source = new FuelSource(fuel);                   
                    //add the FuelSource at the myWorld
                    addObject(source, x, y);
                }
                else if(str.equalsIgnoreCase("reaper")){ 
                    //scanner returns Integer coordinats x and y
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    //Create new Reaper with coordinats x and y
                    reaper = new Reaper(x, y);
                    //add the Reaper at the myWorld
                    addObject(reaper, x, y);
                }
            }

        }
        /**
         * An example of a method - replace this comment with your own
         *
         * @param  y  a sample parameter for a method
         * @return    the sum of x and y
         */

    }
    //returns the Scoreboard that was added at myWorld by the constructor
    public ScoreBoard getScoreBoard()
    {
        return scoreBoard;
    }

    //returns the Spaceship that was added at myWorld by the constructor
    public Spaceship getSpaceship()
    {

        return spaceship;
    }

    

}
