import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Boss extends Enemy
{    
    private List<GreenfootImage> runRSprites = new ArrayList<>();
    private List<GreenfootImage> runLSprites = new ArrayList<>();
    private List<GreenfootImage> fallSprites = new ArrayList<>();
    private GreenfootImage idleImg;
    
    public void act()
    {
        if (health > 0) {
            updateState(); 
            moveEnemy();   
            animate();
        }
        if (state.equals("fall")) {
            animateFall();
        }
    }
    
    public Boss(){
        idleImg = new GreenfootImage("IdleBoss.png");
        setImage(idleImg);
        health = 200;
        damage = 50;
        speed = 2;
        
        loadSprites(runRSprites, "RunBoss.png", 10);
        loadSprites(runLSprites, "RunBossL.png", 10);
        loadSprites(fallSprites, "DeadBoss.png", 5);
    }

    private void animate() {
        List<GreenfootImage> currentSprites = null;
        switch (state) {
            case "runR":
                currentSprites = runRSprites;
                break;
            case "runL":
                currentSprites = runLSprites;
                break;
        }

        if (currentSprites != null && !currentSprites.isEmpty()) {
            if (frame % animationSpeed == 0) {
                int index = (frame / animationSpeed) % currentSprites.size();
                setImage(currentSprites.get(index));
            }
            frame++;
        } else {
            setImage(idleImg);
        }
    }
    
    private void animateFall() {
        List<GreenfootImage> currentSprites = fallSprites;

        if (currentSprites != null && !currentSprites.isEmpty()) {
            if (frame % animationSpeed == 0) {
                int index = (frame / animationSpeed) % currentSprites.size();
                setImage(currentSprites.get(index));
                if (index == currentSprites.size() - 1) {
                    World world = getWorld();
                    world.removeObject(this);
                }
            }
            frame++;
        }
    }
}
