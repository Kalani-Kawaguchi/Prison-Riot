import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StopButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StopButton extends Actor
{
    /**
     * Act - do whatever the StopButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        World world = getWorld();
        createText(world);
        start(world);
    }
    
    public void createText(World w){
        w.showText("Exit", this.getX(), this.getY());
    }
    
    //When button clicked, delete the text and button, load the GameWorld
    public void start(World w){
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.stop();
        }
    }
}
