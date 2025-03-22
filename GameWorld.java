import greenfoot.*;
import java.util.List;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameWorld extends World {
    private Player player = new Player(this);
    private Timer timer = new Timer();
    private int currentLevel = 1;
    private int enemiesToSpawn = 5;
    private boolean waveInProgress = false;
    private Label levelLabel;  // Declare the label
    private Label healthLabel;
    private List<muzzleFlash> mf;
    private int counter = 0;

    public GameWorld() {    
        super(1000, 800, 1);
        Greenfoot.setSpeed(50);
        prepare(); 
        spawnEnemies(enemiesToSpawn);
    }
    
    public void act() {
        checkPlayer();
        checkWaveStatus();
        checkFlash();
        levelLabel.setText("Level: " + currentLevel);  // Update the level label text
        healthLabel.setText(String.format("%d/%d", player.getCurHealth(), player.getMaxHealth()));
    }

    private void prepare() {
        levelLabel = new Label("Level: " + currentLevel, 24);
        healthLabel = new Label(String.format("%d/%d", player.getCurHealth(), player.getMaxHealth()), 24);
        addObject(levelLabel, 50, getHeight() - 25);
        addObject(healthLabel, getWidth() - 50, getHeight() - 25);
        Heart heart = new Heart();
        addObject(heart, getWidth() - 100, getHeight() - 25);
        Label playerName = new Label("Player", 24);
        addObject(playerName, getWidth() - 50, getHeight() - 50);
        addObject(player, getWidth() / 2, getHeight() / 2);
        addObject(timer, getWidth() / 2, 20);
        timer.startTimer();
    }
    
    private void checkFlash(){
        mf = getObjects(muzzleFlash.class);
        if(mf != null){
            counter++;
            if(counter == 10){
                counter = 0;
                removeObjects(mf);
            }
        }
    }

    private void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            // Randomly scatter enemies across the screen, but ensure they are not overlapping too much
            int x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;  // Spread out horizontally
            int y = Greenfoot.getRandomNumber(getHeight() - 100) + 50; // Spread out vertically
            
            while(getObjectsAt(x,y,Actor.class).contains(Actor.class)){
                x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;
                y = Greenfoot.getRandomNumber(getHeight() - 100) + 50;
            }
            
    
            // Ensure that enemies are not spawned on top of each other
            Enemy enemy = new Enemy();
            enemy.scaleDifficulty(currentLevel);
            addObject(enemy, x, y);
        }
        if(currentLevel == 5){
            int x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;  // Spread out horizontally
            int y = Greenfoot.getRandomNumber(getHeight() - 100) + 50; // Spread out vertically
            
            while(getObjectsAt(x,y,Actor.class).contains(Actor.class)){
                x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;
                y = Greenfoot.getRandomNumber(getHeight() - 100) + 50;
            }
            
            Boss enemy = new Boss();
            addObject(enemy, x, y);
        }
        waveInProgress = true;
    }
    
    private void checkPlayer(){
        if(player.getCurHealth() <= 0){
            gameOver();
        }
    }

    private void checkWaveStatus() {
        List<Enemy> enemies = getObjects(Enemy.class);
        if (enemies.isEmpty() && waveInProgress) {
            waveInProgress = false;
            if(currentLevel == 5){
                gameWin();
                return;
            }
            Greenfoot.delay(100);
            currentLevel++;
            player.heal();
            enemiesToSpawn += 2;
            spawnEnemies(enemiesToSpawn);
        }
    }
    
    private void gameOver(){
        if(getObjects(Screen.class).isEmpty()){
            addObject(new Screen(this),getWidth()/2,getHeight()/2);
            createLabel(0);
            placeButtons();
            removeObject(player);
            timer.stopTimer();
        }
    }
    
    private void gameWin(){
        addObject(new Screen(this),getWidth()/2,getHeight()/2);
        createLabel(1);
        placeButtons();
        removeObject(player);
        timer.stopTimer();
    }
    
    private void createLabel(int num){
        if(num == 0){ //Lose
            Label text = new Label("YOU LOSE", 24);
            addObject(text, getWidth()/2, getHeight()/2-200);
        }else{ //Win
            Label text = new Label("YOU WIN", 24);
            addObject(text, getWidth()/2, getHeight()/2-200);
        }
    }
    
    private void placeButtons(){
        StartButton playAgain = new StartButton("Play Again");
        addObject(playAgain, getWidth()/2, getHeight()/2);
        StopButton quit = new StopButton();
        addObject(quit, getWidth()/2, getHeight()/2+100);
    }
}
