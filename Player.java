import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Player extends Character {
    private World world;
    private List<GreenfootImage> idleSprites = new ArrayList<>();
    private List<GreenfootImage> runRightSprites = new ArrayList<>();
    private List<GreenfootImage> runLeftSprites = new ArrayList<>();
    private List<GreenfootImage> runUpSprites = new ArrayList<>();
    private List<GreenfootImage> runDownSprites = new ArrayList<>();
    private List<GreenfootImage> deadSprites = new ArrayList<>();
    private GreenfootImage idleImg;

    private Weapons currentWeapon; 
    private Gun gun;

    private int curHealth = 100;
    private int maxHealth = 100;

    public Player(World world) {
        // Initialize player sprites
        idleImg = new GreenfootImage("Idle.png");
        setImage(idleImg);
        
        loadSprites(runRightSprites, "Run.png", 10); 
        loadSprites(runLeftSprites, "RunL.png", 10);
        loadSprites(runUpSprites, "Run.png", 10); 
        loadSprites(runDownSprites, "RunL.png", 10);
        loadSprites(deadSprites, "Dead.png", 4);
        this.world = world;
        gun = new Gun(world);
        currentWeapon = gun;
    }

    public void act() {
        if (curHealth > 0){
            movePlayer();
            updateState();
            animate();
            handleWeaponInput();
        }
        if (state.equals("fall")){
            animateFall();
        }
    }

    private void movePlayer()
    {
        int[] velocity = new int[]{0,0}; //x, y
        int originalX = getX();
        int originalY = getY();
        
        //Increase or Decrease X or Y depedent on the key pressed
        if (Greenfoot.isKeyDown("w")) 
        {
            velocity[1]--;
            state = "up";
        }
        if (Greenfoot.isKeyDown("s")) 
        {
            velocity[1]++;
            state = "down";
        }
        if (Greenfoot.isKeyDown("a")) 
        {
            velocity[0]--;
            state = "left";
        }
        if (Greenfoot.isKeyDown("d")) 
        {
            velocity[0]++;
            state = "right";
        }
        
        int newX;
        int newY;
        //If the sum of velocity is greater than 1, diagonal movement is normalized
        if(Math.abs(velocity[0]) + Math.abs(velocity[1]) > 1){
            newX = getX() + velocity[0]*4;
            newY = getY() + velocity[1]*3;
        }else{
            newX = getX() + velocity[0]*5;
            newY = getY() + velocity[1]*5;
        }
        
        // Set player location to newX and newY to check for collision
        setLocation(newX, newY);
        
        // If colliding with enemy at newX and newY, go back to original location
        if(isColliding()){
            setLocation(originalX, originalY);
        }
    }

    private void updateState() {
        if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("d") &&
            !Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("s") && !state.equals("attack")) {
            state = "idle";
        }
    }

    private void animate()
    {
        List<GreenfootImage> currentSprites = null;
        
        if (state.equals("up")) {
            currentSprites = runUpSprites;
        }else if (state.equals("down")) {
            currentSprites = runDownSprites;
        }else if (state.equals("left")) {
            currentSprites = runLeftSprites;
        }else if (state.equals("right")) {
            currentSprites = runRightSprites;
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
        List<GreenfootImage> currentSprites = deadSprites;

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

    private void handleWeaponInput() {
        if (Greenfoot.mouseClicked(world)) {
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            attack(mouseInfo);
        }
    }

    private void attack(MouseInfo mouseInfo) {
        if (currentWeapon != null) {
            currentWeapon.use(this, mouseInfo);  
        }
    }

    public void takeDamage(int damage) {
        curHealth -= damage;
        if (curHealth <= 0) {
            curHealth = 0;
            fall();
        }
    }
    
    private void fall(){
        state = "fall";
    }
    
    @Override
    public boolean isColliding(){
        List<Enemy> enemies = world.getObjectsAt(getX(), getY(), Enemy.class);
        return !enemies.isEmpty();
    }
    
    public int getCurHealth(){
        return curHealth;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public void heal(){
        maxHealth += 25;
        curHealth = maxHealth;
    }
}
