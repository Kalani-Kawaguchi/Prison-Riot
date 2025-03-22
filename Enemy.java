import greenfoot.*;  
import java.util.List;
import java.util.ArrayList;

public class Enemy extends Character {
    private List<GreenfootImage> runRSprites = new ArrayList<>();
    private List<GreenfootImage> runLSprites = new ArrayList<>();
    private List<GreenfootImage> fallSprites = new ArrayList<>();
    private GreenfootImage idleImg;
    private int startX;
    protected Player player;

    public Enemy() {
        idleImg = new GreenfootImage("EnemyIdle.png");
        setImage(idleImg);
        speed = 2;
        health = 50;
        damage = 10;

        // Load sprites for different actions
        loadSprites(runRSprites, "EnemyRun.png", 10);
        loadSprites(runLSprites, "EnemyRunL.png", 10);
        loadSprites(fallSprites, "EnemyFall.png", 5);
    }

    public void act() {
        if (health > 0) {
            updateState(); 
            moveEnemy();   
            animate();
        }
        if (state.equals("fall")) {
            animateFall();
        }
    }
    
    public void setStartX() {
        startX = getX();
    }

    protected void updateState() {
        player = getWorld().getObjects(Player.class).isEmpty() ? null : getWorld().getObjects(Player.class).get(0);

        if (player != null) {
            if (player.getX() > getX()) state = "runR";
            if (player.getX() < getX()) state = "runL";
            speed = Math.abs(speed);
        }
    }

    protected void moveEnemy() {
        int originalX = getX();
        int originalY = getY();
        
        if (state.equals("runR") || state.equals("runL")) {
            // Check if the player is to the right or left of the enemy
            if (player != null) {
                int newX = getX(), newY = getY();
                
                if (player.getX() > getX()) {
                    newX += speed;  // Move to the right
                } else if (player.getX() < getX()) {
                    newX -= speed;  // Move to the left
                }
    
                if (player.getY() > getY()) {
                    newY += speed;  // Move down if the player is below
                } else if (player.getY() < getY()) {
                    newY -= speed;  // Move up if the player is above
                }
                
                setLocation(newX, newY);
                
                if(isColliding()){
                    if(state.equals("runR")) setLocation(originalX - 40, originalY);
                    if(state.equals("runL")) setLocation(originalX + 40, originalY);
                }
            }
        }
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

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            fall();
        }
    }

    public void attack() {
        player = getWorld().getObjects(Player.class).get(0);
        player.takeDamage(damage);
    }

    public void fall() {
        state = "fall";
    }

    // New Method to Scale Difficulty
    public void scaleDifficulty(int level) {
        health += level * 10;    // Increase health by 10 per level
    }
    
    @Override
    public boolean isColliding(){
        List<Player> collidingObjects = getWorld().getObjectsAt(getX(), getY(), Player.class);
        if(!collidingObjects.isEmpty()){
            attack();
        }
        return !collidingObjects.isEmpty();
    }
}
