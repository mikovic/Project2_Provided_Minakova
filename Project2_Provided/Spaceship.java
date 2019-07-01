import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Spaceship that moves around the world. 
 * 
 * @author Stephen Blythe 
 * @version 3/2019
 */
public class Spaceship extends Actor
{   
    //instance variabes fuel,values of velocity on the X and Y axis. Initially in the Constructor below
    private int fuel;
    private double velX;
    private double velY;    
    private double xlocation;
    private double ylocation;  
    //constructor 
    public Spaceship(){
        //initially fuel
        fuel = 100;
        //velocity values should be 0. So coordinats velocity X and Y axis should be 0
        velX = 0;
        velY = 0;
        //Coordinats x and y of the Spaceship(the center of the world)
        xlocation = 400;
        ylocation = 300;

    }

    public void act(){
        //get the myWorld
        World world = this.getWorld();
        MyWorld myWorld = (MyWorld)this.getWorld();
        //get the ScoreBoard from the myWorld
        ScoreBoard scoreBoard = myWorld.getScoreBoard(); 
        //should a SpaceShip ever touch a reaper, one or more of the edges of the World, or the ScoreBoard, the game should  end
        if(this.isTouching(ScoreBoard.class) ||this.isTouching(Reaper.class)|| this.isAtEdge()){
            //This means that the Spaceship should be removed
            myWorld.removeObject(this); 
            //the high score should be updated if necessary
            if(scoreBoard.getScore() > scoreBoard.getHighScore()){
                scoreBoard.setHighScore(scoreBoard.getScore());

            }
            //message "Game Over"should set in the middle of the world
            myWorld.showText("game over", 400, 300);
            //return from method act()
            return;
        } 
        //Return all objects FuelSource.class within range radius = 40 cells around the Spaceship
        List<FuelSource> list = this.getObjectsInRange(40, FuelSource.class);
        //For each unit of fuel taken from a FuelSource a unit should be added to the Spaceship’s fuel supply
        for(FuelSource source : list) {
            // get fuel from the FuelSource
            int fuel = source.getFuel();
            //decrease the amount of fuel in a FuelSource object by 1 unit of fuel 
            source.setFuel(fuel - 1);
            //set unit = true in a FuelSource object. This means the spaceships take 1 unit of fuel from the FuelSource
            source.setUnit(true);
            //add 1 unit of fuel in the Spaceship
            this.setFuel(this.getFuel() + 1);
            //set new amount of a fuel left in the ScoreBoard
            scoreBoard.setFuelLeft(this.getFuel() + 1);
        }
        if(Greenfoot.isKeyDown("left")) {
            //3 degrees in the counter-clockwisedirection 
            this.turn(-3);
        }
        if(Greenfoot.isKeyDown("right")) {
            //3 degrees in the clockwisedirection 
            this.turn(3);
        }
        if(Greenfoot.isKeyDown("space") && this.getFuel() != 0) {
            //the image for the spaceship should be changed to the rocketWithThrustimage
            GreenfootImage img = new GreenfootImage("rocketWithThrust.png"); 
            setImage(img);
            //the amount of fuel left in the spaceship should be decreased by 1
            int fuel = getFuel();
            setFuel(fuel - 1);
            //set fuelLeft on the scoreboard
            scoreBoard.setFuelLeft(fuel - 1);
            
            //return the current rotation of this spaceship
            double degrees = getRotation();
            //Math.toRadians method for a way to convert degrees to radian
            double degreesRadian = Math.toRadians(degrees);
            //the following two acceleration values should be calculated
            double accelX = 0.1 * Math.cos(degreesRadian);
            double accelY = 0.1 * Math.sin(degreesRadian);
            //add the acceleration values into the spaceship’s velocity values
            velX = velX + accelX;
            velY = velY + accelY;

        }
        //if the user is not pressing the spacebar and the Spaceship still has fuel left,
        else if(!(Greenfoot.isKeyDown("space"))&& this.getFuel() != 0){
            //the image for the spaceship should be changed to the old rocket image
            GreenfootImage img = new GreenfootImage("rocket.png"); 
            setImage(img);
            //keep the spaceship velocity without acceleration
            velX = velX;
            velY = velY;
            
        }
        //spaceship velocity  value should be calculated
        double vel = Math.sqrt(velX * velX + velY * velY);
        //if velocity !=0 set a new score on the scoreBoard
        if(vel != 0){
            scoreBoard.setScore(scoreBoard.getScore() + 1);
        }
        //the spaceship’s locations should be updated by adding velX to its xlocation and velY to its ylocation
        xlocation = xlocation + velX;
        ylocation = ylocation + velY;
        //new Spaceship's location
        setLocation((int)xlocation, (int)ylocation);
    }
    //returns the amount spaceship's fuel
    public int getFuel(){
        return fuel;
    }
    //set the amount spaceship's fuel
    public void setFuel(int fuel){
        this.fuel = fuel;
    }

}

