import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Creates a "progress bar" with parameters timeNeed (the time for a food item to
 * process), as well as the length and width of the bar
 * 
 * @ Alvin Li
 * @ May 30 2019
 */
public class timerClass extends Actor
{
    /**
     * Act - do whatever the timerClass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private GreenfootImage image;
    private int timeNeed = 5;
    private int timePassed;
    private int spawnX;
    private int spawnY;
    private int w = 80;
    private int l = 30;
    private int red;
    private int blue;
    private int green;
    private long start;
    public timerClass(int timeNeed, int l, int w)
    {
        this.timeNeed = timeNeed;        
        this.l = l;
        this.w = w;
        start = System.currentTimeMillis();
    }
    
    public void addedToWorld(World world)
    {
        //System.out.println(this.getX() + " " + this.getY());
    }

    public void act() 
    {        
        double timePassed = ((double)(System.currentTimeMillis() - start))/1000;
        //System.out.println(timePassed + " " + spawnX + " " + spawnY + " " + w + " " + l);
        image = new GreenfootImage(w, l);
        //System.out.println(w + " " + l);
        newRGB();
        setImage(image);
        image.setColor(Color.BLACK);
        image.fill();
        //image.fillRect(spawnX,spawnY,w,l);
        image.setColor(new Color(red,green,blue));
        if (timePassed < timeNeed)
        {
            //image.fillRect(0, 10,20,20); 
            image.fillRect(1,1,(int)((w-2)*(timePassed/(double)timeNeed)),l-2);   
        }
        else
        {
            image.fillRect(1,1,w-2,l-2);            
        }
        //System.out.println(this.getX() + " " + this.getY());
        setImage(image);
        
    }   

    //change color of the progress bar
    public void newRGB()
    {
        double maxOverTime = timeNeed * 0.8;
        double timePassed = ((double)(System.currentTimeMillis() - start))/1000;
        //from 216,255,181 (light green) to 123,255,0 (bright green)
        if (timePassed < timeNeed)
        {
            red = 216 - (int)(90*(timePassed/(double)timeNeed));
            green = 255;
            blue = 181 - (int)(181*(timePassed/(double)timeNeed));
            //System.out.println("red" + red + "green" + green + "blue" + blue);
        }
        else if (timePassed < (timeNeed + maxOverTime))
        {           
            //from 123,255,0 (bright green) to 255,16,0 (bright red)
            red = 123 + (int)(90*((timePassed-timeNeed)/(double)maxOverTime));
            green = 255-(int)(239*((timePassed-timeNeed)/(double)maxOverTime));
            blue = 0;
        }
    }

    /**
    public double whatTimeMrWolf()
    {
        int time1 =  (int)(System.currentTimeMillis() - start)/1000;
        System.out.println(time1 + " " + System.currentTimeMillis() + ", start from" + start);
        return time1;
    }
    **/
}
