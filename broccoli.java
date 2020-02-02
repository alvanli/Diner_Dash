import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the images of the food item, and makes the images follow the chef around
 * 
 * @ Alvin Li 
 * @ Started on May 28 2019 
 * @ Updated until Jun 02 2019
 */
public class broccoli extends food
{
    /**
     * Act - do whatever the brocchoi wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage broc0 = new GreenfootImage("broccoli_uncooked.png");    
    private GreenfootImage broc1 = new GreenfootImage("broccoli_cut.png");
    private GreenfootImage broc2 = new GreenfootImage("broccoli_cooked.png");
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
        broc0.scale(broc0.getWidth()-30, broc0.getHeight()-30);
        broc1.scale(broc0.getWidth()-10, broc0.getHeight()-10);
        broc2.scale(broc0.getWidth()-20, broc0.getHeight()-20);
        setImage(broc0);
        status = 0;
        Actor chef = (Actor)getWorld().getObjects(chefs.class).get(0);
        follow(chef);
        //System.out.println("loaded");
        //broc.scale(broc.getWidth()+30, broc.getHeight()+50);       
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
            setImage(broc0);
        }
        else if (status == 1)
        {
            setImage(broc1);          
        }
        else if (status == 2)
        {
            setImage(broc2);
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
