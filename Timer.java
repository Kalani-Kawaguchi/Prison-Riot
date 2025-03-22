import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    //Global Class variables
    private int mins = 0;
    private int secs = 0;
    private int regulator = 0;
    private boolean start = false;
    
    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        World world = getWorld();
        if(start){
            setTime();
            writeTime(world);
        }
    }
    
    public void startTimer(){
        start = true;
    }
    
    public void stopTimer(){
        start = false;
    }
    
    public void writeTime(World w){
        //Format the timer to display minutes and seconds with leading zeros, width 2
        String time = String.format("%02d:%02d", mins, secs);
        setImage(new GreenfootImage(time, 24, Color.WHITE, new Color(0,0,0,0)));
    }
    
    //increase seconds and minutes based on # of cycles passed
    public void setTime(){
        //increase regulator by 1 every cycle, resetting every 60 cycles
        regulator = (regulator+1)%60;
        
        //At speed 50, 60 cycles is about 1 second
        //When regulator resets to 0, 1 second has passed
        if (regulator == 0){
            secs++;
            if(secs == 60){
                secs = 0;
                mins++;
            }
        }
    }
}
