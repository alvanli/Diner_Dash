import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class speechPickFood here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class speechPickFood extends dialogue
{
    private GreenfootImage speech = new GreenfootImage("speech1.PNG");    
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
