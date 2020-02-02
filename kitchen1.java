import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class kitchen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class kitchen1 extends kitchens
{
    private GreenfootImage bok0 = new GreenfootImage("kitchen1.PNG"); 
    public static Actor food;
    public static Actor timer;

    public void addedToWorld(World world)
    {               
        bok0.scale((int)(bok0.getWidth()/2.2), (int)(bok0.getHeight()/2.2));
        setImage(bok0);
    }

    public void act() 
    {
        food = getOneObjectAtOffset(0,0,Bokchoi.class);
        if (food == null)
        {
            food = getOneObjectAtOffset(0,0,broccoli.class);
            if (food == null)
            {
                food = getOneObjectAtOffset(0,0,beef.class);
                if (food == null)
                {
                    food = getOneObjectAtOffset(0,0,chicken.class);
                }
            }
        }

        timer = getOneObjectAtOffset(-5,20,timerClass.class);
        if (timer == null)
        {
            timer = getOneObjectAtOffset(5,20,timerClass.class);
            if (timer == null)
            {
                timer = getOneObjectAtOffset(5,30,timerClass.class);
            }
        }
        else if (timer != null)
        {
            //System.out.println("yeet");
        }
    }    
}
