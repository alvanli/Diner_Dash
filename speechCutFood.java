import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class speechCutFood here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class speechCutFood extends dialogue
{
    private GreenfootImage speech = new GreenfootImage("speech2.png");    
    public void addedToWorld(World world)
    {
        setImage(speech);
        speech.scale(speech.getWidth()/4, speech.getHeight()/4);
        setImage(speech);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
