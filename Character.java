import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public abstract class Character extends Actor
{
    protected int frame = 0; 
    protected int animationSpeed = 5; 
    protected String state = "idle";
    protected int speed; 
    protected int health;
    protected int damage;
    
    public Character()
    {
        
    }
    
    public abstract boolean isColliding();
    
    public void loadSprites(List<GreenfootImage> spriteList, String sheetFileName, int frameCount) {
        GreenfootImage spriteSheet = new GreenfootImage(sheetFileName);
        int frameWidth = spriteSheet.getWidth() / frameCount;
        int frameHeight = spriteSheet.getHeight();

        for (int i = 0; i < frameCount; i++) {
            GreenfootImage frame = new GreenfootImage(frameWidth, frameHeight);
            frame.drawImage(spriteSheet, -i * frameWidth, 0);
            spriteList.add(frame);
        }
    }
    
}
