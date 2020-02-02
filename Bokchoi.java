import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the images of the food item, and makes the images follow the chef around
 * 
 * @ Alvin Li 
 * @ Started on May 28 2019 
 * @ Updated until May 30 2019
 */
public class Bokchoi extends food
{
    /**
     * Act - do whatever the Bokchoi wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage bok0 = new GreenfootImage("bok choy_uncooked.png");    
    private GreenfootImage bok1 = new GreenfootImage("bok choy_cut.png");
    private GreenfootImage bok2 = new GreenfootImage("bok choy_cooked.png");
    private int x = 0;
    private int y = 0;
    private int cookingQ = 0;
    private int status = 0;
    /**
     * 0 --> raw
     * 1 --> chopped
     * 2 --> cooked
     */

    public void addedToWorld (World world)
    {    
        bok0.scale(bok0.getWidth()-30, bok0.getHeight()-30);
        bok1.scale(bok0.getWidth()-10, bok0.getHeight()-10);
        bok2.scale(bok0.getWidth()-20, bok0.getHeight()-20);
        setImage(bok0);
        status = 0;
        Actor chef = (Actor)getWorld().getObjects(chefs.class).get(0);
        follow(chef);
        //System.out.println("loaded");
        //bok.scale(bok.getWidth()+30, bok.getHeight()+50);       
    }

    public void act() 
    {
        checkUnEquip();
        changeImg();
    }    

    /**
     * Allows the food item to follow the chef around the kitchen
     */
    public void follow(Actor a)
    {
        x = a.getX() - 20;
        y = a.getY();
        this.setLocation(x,y);
    }

    /**
     * If not holding the item anymore, delete the item from the world
     */
    public void checkUnEquip()
    {
    }

    public void changeImg()
    {
        if (status == 0)
        {
            setImage(bok0);
        }
        else if (status == 1)
        {
            setImage(bok1);          
        }
        else if (status == 2)
        {
            setImage(bok2);
        }
    }

    public void snapToKitchen (int kitchen)
    {
        if (kitchen == 1)
        {
            this.setLocation(335,140);  
        }
        else if (kitchen == 2)
        {
            this.setLocation(545,140);
        }
        cookingQ = 1;
    }

    
    public void snapToTable(int table)
    {
        if (table == 1)
        {
            this.setLocation(845,380);
        }
        else if (table ==2)
        {
            this.setLocation(830,320);
        }
        else if (table == 3)
        {
            this.setLocation(823,270); 
        }
        else if (table == 4)
        {
            this.setLocation(828,230);
        }      
    }
    
    public int isCooking(int fx, int changed)
    {
        if (fx == 1)
        {
            return cookingQ;   
        } 
        else if (fx == 2)
        {
            cookingQ = changed;
            return -1;
        }
        else
        {
            return -2;
        }
    }

    /**
     * fx = 1 --> get status
     * fx = 2 --> edit status
     * 
     */
    public int status(int fx, int changed)
    {
        if (fx == 1)
        {
            return status;
        }
        else if (fx == 2)
        {
            status = changed;
            return -1;
        }
        else
        {
            return -2;
        }
    }

}
