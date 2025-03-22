import greenfoot.*;

public class StartButton extends Actor
{
    private String s;
    
    public StartButton(String s){
        this.s = s;
    }
    
    public void act()
    {
        World world = getWorld();
        createText(world);
        start(world);
    }
    
    public void createText(World w) {
        w.showText(s, this.getX(), this.getY());
    }
    
    public void start(World w) {
        if (Greenfoot.mouseClicked(this)) {
            w.showText("", this.getX(), this.getY());
            w.removeObject(this);

            Greenfoot.setWorld(new GameWorld());
        }
    }
}
