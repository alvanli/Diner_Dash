import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cuttingBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cuttingBoard extends kitchens
{
    private GreenfootImage bok0 = new GreenfootImage("cuttingBoard.PNG"); 
    public static Actor timer;
    public void addedToWorld(World world)
    {               
        bok0.scale((int)(bok0.getWidth()/2.2), (int)(bok0.getHeight()/2.2));
        setImage(bok0);
    }

    public void act() 
    {
        timer = getOneObjectAtOffset(-10,-100,timerClass.class);
        if (timer == null)
        {
            timer = getOneObjectAtOffset(-20,-100,timerClass.class);
            if (timer == null)
            {
                timer = getOneObjectAtOffset(-10,-110,timerClass.class);
            }
        }
    }    
}
