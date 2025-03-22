import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends Actor
{
    /**
     * Act - do whatever the Screen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Screen(World world)
    {
        GreenfootImage screen = new GreenfootImage(world.getWidth(), world.getHeight());
        screen.setColor(Color.BLACK);
        screen.setTransparency(50);
        screen.fillRect(0,0,world.getWidth(), world.getHeight());
        setImage(screen);
    }
}
