import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class muzzleFlash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class muzzleFlash extends Actor
{
    private int startX;
    private int startY;
    private int dirX;
    private int dirY;
    private int rotation;
    
    public void act()
    {
        calculateRotation();
    }
    
    public muzzleFlash(Player player, MouseInfo mouseInfo){
        startX = player.getX();
        startY = player.getY();
        dirX = mouseInfo.getX();
        dirY = mouseInfo.getY();
        
        setImage(new GreenfootImage("muzzle_flash.png"));
    }
    
    private void calculateRotation(){
        double dx = dirX-startX;
        double dy = dirY-startY;
        double angle = Math.atan2(dy, dx);
        
        rotation = (int) Math.toDegrees(angle);
        setRotation(rotation);
    }
}
