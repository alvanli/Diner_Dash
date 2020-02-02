import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class kitchen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class kitchen extends World
{

    /**
     * Constructor for objects of class kitchen.
     * 
     */
    public kitchen()
    {    
        super(960, 640, 1); 
        //Add kitchen wall
        GreenfootImage image = new GreenfootImage("KitchenWall.png");
        image.scale(getWidth(), getHeight());
        setBackground(image);
        setPaintOrder(timerClass.class, cloud.class, chefs.class, Bokchoi.class, chicken.class, broccoli.class, beef.class);
        addObject(new kitchen1(), 335,140);
        addObject(new kitchen2(), 545,140);
        addObject(new cuttingBoard(), 352, 505);
        addObject(new plate1(), 860, 385);
        addObject(new plate2(), 845, 330);
        addObject(new plate3(), 830, 280);
        addObject(new plate4(), 820, 230);
        
        addObject(new chefs(), 588, 400);
        addObject(new chefLocation(), 400,550);
        
        
    }
    
    
}
