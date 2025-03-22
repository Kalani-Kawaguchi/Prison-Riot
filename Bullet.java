import greenfoot.*;

public class Bullet extends Actor {
    private int speed = 10;  
    private int damage = 20;
    private int startX;
    private int startY;
    private int dirX;
    private int dirY;
    private int rotation;
    
    public Bullet(int directionX, int directionY) {
        dirX = directionX;
        dirY = directionY;
        setImage(new GreenfootImage("bullet_sprite.png"));
    }
    
    public void act() {
        calculateRotation();
        moveBullet();
        checkForCollisions();
    }
    
    private void calculateRotation(){
        double dx = dirX-startX;
        double dy = dirY-startY;
        double angle = Math.atan2(dy, dx);
        
        rotation = (int) Math.toDegrees(angle);
    }
    
    public void setStart(int x, int y){
        startX = x;
        startY = y;
    }

    private void moveBullet() {
        setRotation(rotation);
        // distance between click location and bullet origin
        double dx = dirX - startX;
        double dy = dirY - startY;
        
        // length of the vector
        double magnitude = Math.sqrt(dx*dx + dy*dy);
        
        // normalize the vector and scale it by speed
        double velocityX = (dx / magnitude) * speed;
        double velocityY = (dy / magnitude) * speed;
        
        setLocation(getX() + (int) velocityX, getY() + (int) velocityY);
    }

    private void checkForCollisions() {
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);  
        if (enemy != null) {
            getWorld().removeObject(this);
            enemy.takeDamage(damage);
            return;
        }

        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
