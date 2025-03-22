import greenfoot.*;

public abstract class Weapons extends Actor{
    protected GreenfootImage sprite;
    protected int damage;

    public Weapons() {
    }

    public abstract void use(Player player, MouseInfo mouseInfo);

    // Get the sprite for the weapon 
    public GreenfootImage getSprite() {
        return sprite;
    }

    // Get the damage of the weapon
    public int getDamage() {
        return damage;
    }
}

