import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Gun extends Weapons {
    private World world;

    public Gun(World world) {
        // Gun's damage level
        this.damage = 20;  
        this.world = world;
    }

    @Override
    public void use(Player player, MouseInfo mouseInfo) {
        shootBullet(player, mouseInfo);
        createFlash(player, mouseInfo);
    }

    private void shootBullet(Player player, MouseInfo mouseInfo) {  
        // Create a new bullet in the direction the player is facing
        Bullet bullet = new Bullet(mouseInfo.getX(), mouseInfo.getY());
        world.addObject(bullet, player.getX(), player.getY()+20);
        bullet.setStart(bullet.getX(), bullet.getY());
    }
    
    private void createFlash(Player player, MouseInfo mouseInfo){
        muzzleFlash mf = new muzzleFlash(player, mouseInfo);
        world.addObject(mf, player.getX(), player.getY()+20);
    }
}