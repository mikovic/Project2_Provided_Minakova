import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color; // uncomment this line if using old version of Greenfoot
/**
 * FuelSource describes a location where fuel can be found
 * 
 * @author Stephen Blythe
 * @version 3/2019
 */

public class FuelSource extends Actor

{
    private int fuel;      // amount fuel of the fuelSource
    private int diametr;   // diametr of the fuelSource
    private boolean unit = false;   // where the spaceship take 1 unit of fuel from  the fuelSource set-> true
    public FuelSource(int fuel){  //constructor init the fuel, the diametr. Redraw image 
        this.fuel = fuel;         //  
        this.diametr = fuel + 1;
        redraw();
    }

    public void act(){
        //if unit = true spaceshipshould take 1 unit of fuel from fuelsource
        if(unit){
            this.diametr = getFuel() + 1; // new diametr 
            if(diametr > 1) { // if new diametr > 1 set unit-> false redraw fuelsource
                setUnit(false);
                redraw();
            }else{
                // if new diametr < 0 remove this fuelsource from the world
                World world = this.getWorld();
                MyWorld myWorld = (MyWorld)this.getWorld();
                myWorld.removeObject(this);
            }

        }
        return;
    }

    private void redraw()
    {
        GreenfootImage img = new GreenfootImage( getDiametr(),getDiametr()); // new empty img
        img.setColor(Color.RED);                                             // set red color                           
        img.fillOval(0, 0, getDiametr(), getDiametr());                      // fill the sircle with red color   
        setImage(img);                                                       // set img
    }

    public int getDiametr(){
        return diametr;
    }

    public int getFuel(){
        return fuel;
    }

    public void setFuel(int fuel){
        this.fuel = fuel;
    }

    public boolean getUnit(){
        return unit;
    }

    public void setUnit(boolean unit){
        this.unit = unit;
    }
}
