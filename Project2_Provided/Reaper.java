import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Reaper object should just continually move closer to 
 * the game's spaceship. 
 * 
 * @author Stephen Blythe
 * @version 3/2019
 */
public class Reaper extends Actor
{
    //instance variabes position of the repear on the X and Y axis
    double posX;
    double posY;
    
    //the constructor
    public Reaper(int x, int y){
        //initialy position of the Repear
        posX = x;  
        posY = y;
    }
    public void act(){
        //gets myWorld and the Spaceship
        World world = this.getWorld();
        MyWorld myWorld = (MyWorld)this.getWorld(); 
        Spaceship spaceship = myWorld.getSpaceship();       
        
        // if The Spaceship is still at the myWorld. Only one the Spaceship should be at myWorld
        if( myWorld.getObjects(Spaceship.class).size() == 1) {
            //distance between the Reaper and the Spaceship on X and Y axis     
                double distanceX = spaceship.getX() - posX;
                double distanceY = spaceship.getY() - posY;    
                
        //distance between the Reaper and the Spaceship
                double distanceToSpaceship = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
        //the ratio of the projection distance on the X and Y axis to the distance(cos angles beetwen projections and the distance)
        
                double unitToSpaceshipX = distanceX/distanceToSpaceship;
                double unitToSpaceshipY = distanceY/distanceToSpaceship;
        // a Reaper moves 0.1 pixels closer to theSpaceship.So velocity values on the X and Y axis  should be calculated             
        
                double velX = unitToSpaceshipX * 0.1;
                double velY = unitToSpaceshipY * 0.1;
        
        //increase posX  by velX     
                posX += velX;
        //increase posY  by velY         
                posY += velY;
         //Set new location of the Reaper.Cast double values posX and posY to int.
                setLocation((int)posX, (int)posY);        
        }
        //if the Spaceship is not at the myWorld return from method act()
        return; 
    }
}
