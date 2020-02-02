import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class customer extends Actor
{
    /**
     * Act - do whatever the customer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = 2;
    private GreenfootImage picAnimate[][] = new GreenfootImage[9][9];
    private int pic = 0;
    private int frame = 0;
    public void act() 
    {
        if (newCust() && !isFull())
        {
            getWorld().addObject(new customer(), 959, 204);
            kitchen.queue++;
        }
        // Add your action code here.
    }

    public boolean newCust()
    {
        Random r = new Random();
        int time = r.nextInt(11) + 5;
        long startTime = System.currentTimeMillis()/1000;
        int diff = (int)(System.currentTimeMillis()/1000 - startTime);
        if (diff < time)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isFull()
    {
        if (kitchen.queue >= 4)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void custMove()
    {
        int newX = this.getX();
        int newY = this.getY();
        int direction = 0; // "1" = down, "2" = left, "3" = up, "4" = right
        if (!kitchen.q1) //tests if there is a customer already waiting at this queue position, runs if false
        {
            while (newY <= 370)
            {
                newY -= speed;
                direction = 3; //down
                animate(direction);
                this.setLocation(newX, newY);
            }
            while (newX >= 917)
            {
                newX -= speed;
                direction = 2; //left
                animate(direction);
                this.setLocation(newX, newY);
            }
            kitchen.q1 = true;
        }

        else if (!kitchen.q2)
        {
            while (newY <= 309)
            {
                newY -= speed;
                direction = 3; //down
                animate(direction);
                this.setLocation(newX, newY);
            }
            while (newX >= 899)
            {
                newX -= speed;
                direction = 2; //left
                animate(direction);
                this.setLocation(newX, newY);
            }
            kitchen.q2 = true;
        }

        else if (!kitchen.q3)
        {
            while (newY <= 260)
            {
                newY -= speed;
                direction = 3; //down
                animate(direction);
                this.setLocation(newX, newY);
            }
            while (newX >= 884)
            {
                newX -= speed;
                direction = 2; //left
                animate(direction);
                this.setLocation(newX, newY);
            }
            kitchen.q3 = true;
        }

        else if (!kitchen.q4)
        {
            while (newY <= 212)
            {
                newY -= speed;
                direction = 3; //down
                animate(direction);
                this.setLocation(newX, newY);
            }
            while (newX >= 865)
            {
                newX -= speed;
                direction = 2; //left
                animate(direction);
                this.setLocation(newX, newY);
            }
            kitchen.q4 = true;
        }
    }

    private void animate (int dir)
    {
        //int direction = lastMoveToDir();

        //Every 10 frames, increment animation picture by 1
        if (frame == 0){
            frame = 8;
        }
        if (frame == 1){            
            pic++;
            //System.out.println(direction + " " + pic);
            setImage(picAnimate[dir][pic]);
            if (pic >= 8){
                pic = 0;
            }
        }
        if (frame > 0){
            frame --;
        }
    }

    public void addedToWorld (World world)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int n = 0;  n < 9; n++) 
            {
                picAnimate[i][n] = new GreenfootImage("girl1"+ (i) +"" + n + ".png");
                //System.out.println("loaded");
                picAnimate[i][n].scale(picAnimate[i][n].getWidth()+32, picAnimate[i][n].getHeight()+25);
            }
        }
        /**for (int i = 4; i < 8; i++)
        {
        for (int n = 0;  n < 6; n++)
        {
        picAnimate[i][n] = new GreenfootImage("girl1"+ (i) + n + ".png");
        picAnimate[i][n].scale(picAnimate[i][n].getWidth()+32, picAnimate[i][n].getHeight()+25);
        }
        }**/
    }
}