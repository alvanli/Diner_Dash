import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class chefLocation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class chefLocation extends chefs
{
    /**
     * Act - do whatever the chefLocation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private String output = "";
    public void act() 
    {
        Actor chef = (Actor)getWorld().getObjects(chefs.class).get(0);
        output = "X = " + chef.getX() + ", Y = " + chef.getY() + "\n" + "plate4: Bo" + plate4.hasFood[1] + " Br" + plate4.hasFood[2] + " Be" + plate4.hasFood[3] + " Ch" + plate4.hasFood[4];
        setImage(new GreenfootImage(output,30,Color.BLACK, Color.WHITE));
    }    
}
