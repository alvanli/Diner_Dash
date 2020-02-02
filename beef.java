import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the images of the food item, and makes the images follow the chef around
 * 
 * @ Alvin Li 
 * @ Started on May 28 2019 
 * @ Updated until Jun 02 2019
 */
public class beef extends food
{
    private GreenfootImage beef0 = new GreenfootImage("beef_uncooked.png");    
    private GreenfootImage beef1 = new GreenfootImage("beef_cut.png");
    private GreenfootImage beef2 = new GreenfootImage("beef_cooked.png");
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
        beef0.scale(beef0.getWidth()-30, beef0.getHeight()-30);
        beef1.scale(beef0.getWidth()-10, beef0.getHeight()-10);
        beef2.scale(beef0.getWidth()-20, beef0.getHeight()-20);
        setImage(beef0);
        status = 0;
        Actor chef = (Actor)getWorld().getObjects(chefs.class).get(0);
        follow(chef);
        //System.out.println("loaded");
        //beef.scale(beef.getWidth()+30, beef.getHeight()+50);       
    }

    public void act() 
    {
        changeImg();
    }    

    /**
     * Allows the food item to follow the chef around the kitchen
     */
    public void follow(Actor a)
    {
        //Actor chef = (Actor)getWorld().getObjects(chefs.class).get(0);
        x = a.getX() - 20;
        y = a.getY();
        this.setLocation(x,y);
    }

    /**
     * Change the image depending on its status
     * 0 --> raw
     * 1 --> chopped
     * 2 --> cooked
     */
    public void changeImg()
    {
        if (status == 0)
        {
            setImage(beef0);
        }
        else if (status == 1)
        {
            setImage(beef1);          
        }
        else if (status == 2)
        {
            setImage(beef2);
        }
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
    
    //Snaps to the kitchen as indicated by int kitchen
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

    /**
     * allow other classes to check whether it is being cooked, and change its values
     * fx = 1 --> check value
     * fx = 2 --> change value
     */
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
