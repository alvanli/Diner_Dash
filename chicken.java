import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the images of the food item, and makes the images follow the chef around
 * 
 * @ Alvin Li 
 * @ Started on May 28 2019 
 * @ Updated until Jun 02 2019
 */
public class chicken extends food
{
    private GreenfootImage chick0 = new GreenfootImage("chicken_uncooked.png");    
    private GreenfootImage chick1 = new GreenfootImage("chicken_cut.png");
    private GreenfootImage chick2 = new GreenfootImage("chicken_cooked.png");
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
        chick0.scale(chick0.getWidth()-30, chick0.getHeight()-30);
        chick1.scale(chick0.getWidth()-10, chick0.getHeight()-10);
        chick2.scale(chick0.getWidth()-20, chick0.getHeight()-20);
        setImage(chick0);
        status = 0;
        Actor chef = (Actor)getWorld().getObjects(chefs.class).get(0);
        follow(chef);
        //System.out.println("loaded");
        //chick.scale(chick.getWidth()+30, chick.getHeight()+50);       
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
            setImage(chick0);
        }
        else if (status == 1)
        {
            setImage(chick1);          
        }
        else if (status == 2)
        {
            setImage(chick2);
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
