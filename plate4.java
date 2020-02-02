import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class plate4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class plate4 extends kitchens
{
    private GreenfootImage bok0 = new GreenfootImage("plate.png"); 
    public static int[] hasFood = new int[5]; 
    /**
     * if hasFood[i] == 1, it has the food
     * hasFood[1] ==> bokchoi
     * hasFood[2] ==> broccoli
     * hasFood[3] ==> beef
     * hasFood[4] ==> chicken
     */
    public void addedToWorld(World world)
    {               
        bok0.scale((int)(bok0.getWidth()/2.7), (int)(bok0.getHeight()/2.7));
        setImage(bok0);
    }
    
    public void act() 
    {
        checkFood();
    }
    
    
    // Checks which foods are present
    public void checkFood()
    {
        Bokchoi f = (Bokchoi)getOneObjectAtOffset(0,0,Bokchoi.class);
        broccoli b = (broccoli)getOneObjectAtOffset(0,0,broccoli.class);
        beef cow = (beef)getOneObjectAtOffset(0,0,beef.class);
        chicken c = (chicken)getOneObjectAtOffset(0,0,chicken.class);        
        if (f!= null)
        {
            hasFood[1] = 1;
        }
        else 
        {
            hasFood[1] = 0;
        }
        if (b != null)
        {
            hasFood[2] = 1;
        }
        else 
        {
            hasFood[2] = 0;
        }
        if (cow != null)
        {
            hasFood[3] = 1;
        }
        else
        {
            hasFood[3] = 0;
        }
        
        if (c != null)
        {
            hasFood[4] = 1;
        }
        else 
        {
            hasFood[4] = 0;
        }
        
    }
}
