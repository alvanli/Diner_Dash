import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class flame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class flame extends actionImage
{
    private GreenfootImage[] flame = new GreenfootImage[8];
    public static long time = 0;
    private long start = System.currentTimeMillis();
    private int[] timeNeed = {3,3,5,5};
    private int frame = 0;
    private int pic = 0;
    public void addedToWorld(World world)
    {
        for (int i = 0; i < 8; i++)
        {
            flame[i] = new GreenfootImage("smoking"+ (i) + ".png");
            flame[i].scale(flame[i].getWidth(), flame[i].getHeight());
        }
        //getWorld().addObject(new timerClass(timeNeed[chefs.holding],10,30),this.getX()-20, this.getY()-40);
        getImage().setTransparency(0);
    }

    public void act() 
    {
        time = (System.currentTimeMillis() - start)/1000;
        if (time < timeNeed[chefs.holding] * 1.8)
        {
            getImage().setTransparency(0);
        }
        else if (time >= (timeNeed[chefs.holding] *1.8))
        {
            
        }
        else if (time > timeNeed[chefs.holding])
        {
            getImage().setTransparency(255);
            //Bokchoi.status = 2;
        }
    }    
    
    public void idleMove()
    {
        if (frame == 0){
            frame = 8;
            //System.out.println("qrsvdqasrvd");
        }
        if (frame == 1){            
            setImage(flame[pic]);
            //System.out.println(pic);
            pic++; 
            if (pic > 7){
                pic = 0;
                //System.out.println("ekfdjb");
            }
        }
        if (frame > 0){
            frame --;
        }
    }
}
