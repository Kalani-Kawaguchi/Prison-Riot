import greenfoot.*;  
/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(1000, 800, 1);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        StartButton button = new StartButton("Start");
        addObject(button,getWidth()/2,getHeight()/2);
        StopButton stop = new StopButton();
        addObject(stop,getWidth()/2,getHeight()/2 + 100);
    }
}
