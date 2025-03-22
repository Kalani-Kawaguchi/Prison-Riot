import greenfoot.*;  // (Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Label extends Actor {
    private GreenfootImage image;
    private int size;

    public Label(String text, int size) {
        this.size = size;
        image = new GreenfootImage(text, size, Color.WHITE, new Color(0,0,0,0));  // Create a label with white text
        setImage(image);
    }

    public void setText(String text) {
        image = new GreenfootImage(text, size, Color.WHITE, new Color(0,0,0,0));  // Create a label with white text
        setImage(image);
    }
}
