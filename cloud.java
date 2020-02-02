import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cloud here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cloud extends actionImage
{
    private GreenfootImage[] cloud = new GreenfootImage[2];

    private int frame = 0;
    public static int pic = 0;
    public static long time = 0;
    private long start = System.currentTimeMillis();
    private int[] timeNeed = {0,1,2,3,5};
    private boolean cut = false; 
    public void addedToWorld(World world)
    {
        cloud[0] = new GreenfootImage("Cloud0.png");
        cloud[1] = new GreenfootImage("Cloud1.png");
        cloud[0].scale(cloud[0].getWidth()-210, cloud[0].getHeight()-210);
        cloud[1].scale(cloud[1].getWidth()-210, cloud[1].getHeight()-210);  
        getWorld().addObject(new timerClass(timeNeed[chefs.holding],10,30),this.getX()-20, this.getY()-40);
    }

    public void act() 
    {
        idleMove();
        time = (System.currentTimeMillis() - start)/1000;
        Bokchoi f = (Bokchoi)getOneObjectAtOffset(0,0,Bokchoi.class);
        broccoli b = (broccoli)getOneObjectAtOffset(0,0,broccoli.class);
        beef cow = (beef)getOneObjectAtOffset(0,0,beef.class);
        chicken c = (chicken)getOneObjectAtOffset(0,0,chicken.class);
        if (time >= timeNeed[chefs.holding])
        {
            if (time <= timeNeed[chefs.holding] *1.8)
            {
                if (chefs.holding == 1 && f != null)
                {
                    f.status(2,1);
                }
                else if (chefs.holding == 2 && b != null)
                {
                    b.status(2,1);
                }
                else if (chefs.holding == 3 && cow != null)
                {
                    cow.status(2,1);
                }
                else if (chefs.holding == 4 && c != null)
                {
                    c.status(2,1);
                }
            }
            else 
            {
                if (chefs.holding == 1 && f != null)
                {
                    getWorld().removeObject(f);
                    chefs.holding = 0;
                    getWorld().removeObject(this);
                }
                else if (chefs.holding == 2 && b != null)
                {
                    getWorld().removeObject(b);
                    chefs.holding = 0;
                    getWorld().removeObject(this);
                }
                else if (chefs.holding == 3 && cow != null)
                {
                    getWorld().removeObject(cow);
                    chefs.holding = 0;
                    getWorld().removeObject(this);
                }
                else if (chefs.holding == 4 && c != null)
                {
                    getWorld().removeObject(c);
                    chefs.holding = 0;
                    getWorld().removeObject(this);
                }
            }
        }
        else 
        {
            cut = false;
        }
    }    

    public void idleMove()
    {
        if (frame == 0){
            frame = 8;
            //System.out.println("qrsvdqasrvd");
        }
        if (frame == 1){            
            setImage(cloud[pic]);
            //System.out.println(pic);
            pic++; 
            if (pic > 1){
                pic = 0;
                //System.out.println("ekfdjb");
            }
        }
        if (frame > 0){
            frame --;
        }
    }

}
