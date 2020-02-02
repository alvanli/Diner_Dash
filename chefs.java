import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * This class controls the chef's actions, including walking, picking up food, chopping food and cooking food
 * 
 * @ Alvin Li
 * @ Date: May 31 3PM
 */
public class chefs extends Actor
{
    /**
     * Act - do whatever the chefs wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage picAnimate[][] = new GreenfootImage[9][9];
    private int speed = 4;
    /**
     * each thing in the array represents a direction, if that direction == 1, their lastMove is that direction
     * lastMove[0] --> up
     * lastMove[1] --> down
     * lastMove[2] --> left
     * lastMove[3] --> right
     */
    private int[] lastMove = new int[4]; 
    private int pic = 0;
    private kitchen world;
    private int frame = 0;

    /**
     * 0 --> not holding/ touching anything
     * 1 --> Bokchoy
     * 2 --> Broccoli
     * 3 --> Beef
     * 4 --> Chicken
     */
    public static int holding = 0;
    public static int touching = 0;

    public static int cookieHold1 = 0;
    public static int cookieHold2 = 0;
    public static int keyHold = 0;
    private long start; 

    public void addedToWorld(World world)
    {
        //load images into 2D array 
        for (int i = 0; i < 4; i++)
        {
            for (int n = 0;  n < 9; n++) 
            {
                picAnimate[i][n] = new GreenfootImage("animation"+ (i) +"" + n + ".png");
                //System.out.println("loaded");
                picAnimate[i][n].scale(picAnimate[i][n].getWidth()+32, picAnimate[i][n].getHeight()+25);
            }
        }
        for (int i = 4; i < 8; i++)
        {
            for (int n = 0;  n < 6; n++)
            {
                picAnimate[i][n] = new GreenfootImage("animation"+ (i) + n + ".png");
                picAnimate[i][n].scale(picAnimate[i][n].getWidth()+32, picAnimate[i][n].getHeight()+25);
            }
        }
        setImage(picAnimate[1][0]);
    }

    public void act() 
    {
        ctrlMove();
        touching = checkInterac();
        holdStuff();
        checkCutFoods();
        checkCookFoods();
        checkPlateFoods();
    }   

    private void checkPlateFoods()
    {
        int table = checkTable();
        Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
        broccoli b = (broccoli)getOneObjectAtOffset(-20,0,broccoli.class);
        beef cow = (beef)getOneObjectAtOffset(-20,0,beef.class);
        chicken c = (chicken)getOneObjectAtOffset(-20,0,chicken.class);
        getWorld().removeObjects(getWorld().getObjects(speechPlate.class));
        if (table > 0)
        {           
            getWorld().addObject(new speechPlate(), this.getX()-40, this.getY()-70);  
            if (Greenfoot.isKeyDown("enter"))
            {
                if (f != null && f.status(1,0) == 2)
                {
                    f.snapToTable(table);
                    holding = 0;
                }
                else if (b != null && b.status(1,0) == 2)
                {
                    b.snapToTable(table);
                    holding = 0;
                }
                else if (cow != null && cow.status(1,0) == 2)
                {
                    cow.snapToTable(table);
                    holding = 0;
                }
                else if (c != null && c.status(1,0) == 2)
                {
                    c.snapToTable(table);
                    holding = 0;
                }	            
            }
        }
    }

    private void ctrlMove()
    {
        /**
         * WASD to control chef to go left right up down
         */
        int oldX = this.getX();
        int oldY = this.getY();
        int newX = this.getX();
        int newY = this.getY();
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up"))
        {
            newY -= speed;
            resetMove();
            lastMove[0] = 1;
            animate();
        }
        else if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down"))
        {
            newY += speed;
            resetMove();
            lastMove[1] = 1;
            animate();
        }
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))
        {
            newX -= speed;
            resetMove();
            lastMove[2] = 1;
            animate();
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))
        {
            newX += speed;
            resetMove();
            lastMove[3] = 1;
            animate();
        }

        /**
         * Get food to follow THE CHEF
         */
        Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
        broccoli b = (broccoli)getOneObjectAtOffset(-20,0,broccoli.class);
        beef cow = (beef)getOneObjectAtOffset(-20,0,beef.class);
        chicken c = (chicken)getOneObjectAtOffset(-20,0,chicken.class);
        if (f != null && f.isCooking(1,0) != 1)
        {
            f.follow(this);
        }
        if (b != null && b.isCooking(1,0) != 1)
        {
            b.follow(this);
        }
        if (cow != null && cow.isCooking(1,0) != 1)
        {
            cow.follow(this);
        }
        if (c != null && c.isCooking(1,0) != 1)
        {
            c.follow(this);
        }

        //Prevent chef from going out of the kitchen, this chef is destined to stay in this kitchen FOREVER!!! This prisoner must not escape
        if (newY > 436 || newY < 160)
        {
            newY = oldY;
        }
        if (newY <= 308)
        {
            if (newX < 205)
            {
                newX = 205;
            }
            else if (newX > 780)
            {
                newX = 780;
            }
        }
        else if (newY > 308)
        {
            if (newX < 180)
            {
                newX = 180;
            }
            else if (newX > 794)
            {
                newX = 794;
            }
        }
        this.setLocation(newX, newY);
    }

    //convert last move to direction as Alvin realizes direction may be easier than the lastMove array
    private int lastMoveToDir()
    {
        int direction = 0;
        if (lastMove[0] == 1)
        {
            direction = 0;
        }
        else if (lastMove[1] == 1)
        {
            direction = 1;
        }
        else if (lastMove[2] == 1)
        {
            direction = 2;
        }
        else if (lastMove[3] == 1)
        {
            direction = 3;
        }
        return direction;
    }

    //moving animations
    private void animate ()
    {
        int direction = lastMoveToDir();

        //Every 10 frames, increment animation picture by 1
        if (frame == 0){
            frame = 8;
        }
        if (frame == 1){            
            pic++;
            //System.out.println(direction + " " + pic);
            setImage(picAnimate[direction][pic]);
            if (pic >= 8){
                pic = 0;
            }
        }
        if (frame > 0){
            frame --;
        }
    }

    //To reset last move
    private void resetMove()
    {
        for (int i = 0; i < 4; i++)
        {
            lastMove[i] = 0;
        }
    }

    //Check where the chef is in the kitchen to equip items or perform special moves
    private int checkInterac()
    {
        int x = this.getX();
        int y = this.getY();
        if (x >= 180 && x <= 210 && y <= 344 && y >= 160)
        {
            getWorld().removeObjects(getWorld().getObjects(speechPickFood.class));
            getWorld().addObject(new speechPickFood(), x-40, y-70);       
            if (x >= 180 && x <= 205 && y >= 304 && y < 344)
            {
                //Bok Choi
                return 1; 
            }
            else if (x >= 205 && x <= 210 && y >= 240 && y <= 284)
            {
                //Broccoli
                return 2;
            }
            else if (x >= 205 && x <= 210 && y >= 192 && y <= 239)
            {
                //Beef
                return 3;
            }
            else if (x >= 205 && x <= 210 && y >= 160 && y <= 190)
            {
                //chicken
                return 4;
            }
        }
        else
        {
            getWorld().removeObjects(getWorld().getObjects(speechPickFood.class));
            return 0;          
        }
        return 0;
    }

    //Check which items to cut, when chef is near the cutting boards
    public void checkCutFoods()
    {
        int x = this.getX();
        int y = this.getY();
        //Right cutting board
        Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
        broccoli b = (broccoli)getOneObjectAtOffset(-20,0,broccoli.class);
        beef cow = (beef)getOneObjectAtOffset(-20,0,beef.class);
        chicken c = (chicken)getOneObjectAtOffset(-20,0,chicken.class);
        if ((x<=367 && x>=340) && (y<=437 && y>=424))
        {            
            getWorld().removeObjects(getWorld().getObjects(speechCutFood.class));
            getWorld().addObject(new speechCutFood(), x-40, y-70);            
            if (Greenfoot.isKeyDown("enter"))
            {
                getWorld().removeObjects(getWorld().getObjects(speechCutFood.class));
                if (holding == 1 && f.status(1,0) == 0) //bokchoy
                {

                    if (keyHold != 1)
                    {
                        keyHold = 1;
                        getWorld().addObject(new cloud(), this.getX(), this.getY()+10);
                    }
                    animateCut();
                }
                else if (holding == 2 && b.status(1,0) == 0) //broccoli
                {
                    if (keyHold != 2)
                    {
                        keyHold = 2;
                        getWorld().addObject(new cloud(), this.getX(), this.getY()+10);
                    }
                    animateCut();
                }
                else if (holding == 3 && cow.status(1,0) == 0) //beef
                {
                    if (keyHold != 3)
                    {
                        keyHold = 3;
                        getWorld().addObject(new cloud(), this.getX(), this.getY()+10);
                    }
                    animateCut();
                }
                else if (holding == 4) //chicken
                {
                    if (keyHold != 4)
                    {
                        keyHold = 4;
                        getWorld().addObject(new cloud(), this.getX(), this.getY()+10);
                    }
                    animateCut();
                }
            }
            else
            {
                keyHold = 0;               
                getWorld().removeObjects(getWorld().getObjects(cloud.class));
                timerClass timerT = (timerClass)cuttingBoard.timer;
                getWorld().removeObject(timerT);
            }
        }
        else
        {
            getWorld().removeObjects(getWorld().getObjects(speechCutFood.class));
        }
    }

    //animate the cutting motion of the chef
    private void animateCut()
    {
        if (frame == 0){
            frame = 5;
        }
        if (frame == 1){            
            pic++;
            //System.out.println(direction + " " + pic);
            setImage(picAnimate[6][pic]);
            if (pic >= 5){
                pic = 0;
            }
        }
        if (frame > 0){
            frame --;
        }    
    }

    private int checkTable()
    {
        int x = this.getX();
        int y = this.getY();
        if (x <= 794 && x >= 782 && y >= 352 && y <= 380)
        {
            return 1;
        }
        else if (x <= 794 && x >= 780 && y >= 296 && y <= 328)
        {
            return 2;
        }
        else if (x <= 780 && x >= 768 && y >= 248 && y <= 268)
        {
            return 3;
        }
        else if (x >= 756 && x <= 780 && y >= 196 && y <= 224)
        {
            return 4;
        }
        else
        {
            return 0;
        }
    }

    //check which items to cook, when chef is near stove
    private void checkCookFoods()
    {
        int x = this.getX();
        int y = this.getY();
        int[] timeNeed = {0,2,5,5,5};
        timerClass flaTimer = new timerClass(1,2,2);
        double timePassed = ((double)(System.currentTimeMillis() - start))/1000;
        int kitchen = getKitchen(x,y);       
        if (kitchen == 1)
        {
            Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
            broccoli b = (broccoli)getOneObjectAtOffset(-20,0,broccoli.class);
            beef cow = (beef)getOneObjectAtOffset(-20,0,beef.class);
            chicken c = (chicken)getOneObjectAtOffset(-20,0,chicken.class);

            getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));

            if (cookieHold1 == 0)
            {
                getWorld().addObject(new speechStartCook(), x-40, y-70);       
            }

            /**
             *  BOK CHOY/ CHOI
             */  

            if (cookieHold1 == 1  && Greenfoot.isKeyDown("shift") && holding == 0)
            {
                timerClass tr = (timerClass)kitchen1.timer;
                getWorld().removeObject(tr);
                //Actor k1 = (Actor)getWorld().getObjects(kitchen1.class).get(0);
                f = (Bokchoi)kitchen1.food;
                if (timePassed > timeNeed[cookieHold1] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,Bokchoi.class));
                    holding = 0;
                }
                //System.out.println(flaTimer.whatTimeMrWolf() + " " + timeNeed[holding]);
                if (timePassed > timeNeed[cookieHold1] && f != null)
                {
                    f.status(2,2);
                    holding = 1;
                    f.isCooking(2,0);
                    f.follow(this);
                    //editThis getWorld().addObject(f,this.getX()-20, this.getY());
                    //System.out.println("alkvjdnekajrfbdvc");
                }               
                if (timePassed < timeNeed[cookieHold1] && f != null)
                {
                    holding = 1;
                    f.isCooking(2,0);
                    f.follow(this);
                    //getWorld().addObject(f,this.getX()-20, this.getY());
                }
                cookieHold1 = 0;
            }
            if ((holding == 1 && f != null && f.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                //Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
                if (f != null)
                {
                    f.snapToKitchen(kitchen);
                }
                if (cookieHold1 == 0)
                {
                    cookieHold1 = 1;
                    getWorld().removeObject(flaTimer);
                    getWorld().addObject(new flame(), this.getX(), this.getY()+10);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                    //System.out.println(flaTimer.whatTimeMrWolf());
                }
            }

            /**
             * BROCCOLI
             */

            if (cookieHold1 == 2  && Greenfoot.isKeyDown("shift") && holding == 0)
            {
                timerClass tr = (timerClass)kitchen1.timer;
                getWorld().removeObject(tr);
                b = (broccoli)kitchen1.food;
                if (timePassed > timeNeed[cookieHold1] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,broccoli.class));
                    holding = 0;
                }
                if (timePassed > timeNeed[cookieHold1] && b != null)
                {
                    b.status(2,2);
                    holding = 2;
                    b.isCooking(2,0);
                    b.follow(this);
                }               
                if (timePassed < timeNeed[cookieHold1] && b != null)
                {
                    holding = 2;
                    b.isCooking(2,0);
                    b.follow(this);
                }
                cookieHold1 = 0;
            }
            if ((holding == 2 && b != null && b.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                if (b != null)
                {
                    b.snapToKitchen(kitchen);
                }
                if (cookieHold1 == 0)
                {
                    cookieHold1 = 2;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                    //System.out.println(flaTimer.whatTimeMrWolf());
                }
            }

            /**
             * BEEF
             */

            if (cookieHold1 == 3  && Greenfoot.isKeyDown("shift") && holding == 0)
            {
                timerClass tr = (timerClass)kitchen1.timer;
                getWorld().removeObject(tr);
                cow = (beef)kitchen1.food;

                if (timePassed > timeNeed[cookieHold1] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,beef.class));
                    holding = 0;
                }
                if (timePassed > timeNeed[cookieHold1] && cow != null)
                {
                    cow.status(2,2);
                    holding = 3;
                    cow.isCooking(2,0);
                    cow.follow(this);
                }               
                if (timePassed < timeNeed[cookieHold1] && cow != null)
                {
                    //System.out.println("efjhn");
                    holding = 3;
                    cow.isCooking(2,0);
                    cow.follow(this);
                }
                cookieHold1 = 0;
            }
            if ((holding == 3 && cow != null && cow.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                if (cow != null)
                {
                    cow.snapToKitchen(kitchen);
                }
                if (cookieHold1 == 0)
                {
                    cookieHold1 = 3;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                    //System.out.println(flaTimer.whatTimeMrWolf());
                }
            }

            /**
             * 
             * CHICKEN
             * 
             *
             */

            if (cookieHold1 == 4  && Greenfoot.isKeyDown("shift") && holding ==0)
            {
                timerClass tr = (timerClass)kitchen1.timer;
                getWorld().removeObject(tr);
                c = (chicken)kitchen1.food;
                if (timePassed > timeNeed[cookieHold1] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,chicken.class));
                    holding = 0;
                }
                if (timePassed > timeNeed[cookieHold1] && c != null)
                {
                    c.status(2,2);
                    holding = 4;
                    c.isCooking(2,0);
                    c.follow(this);
                }               
                if (timePassed < timeNeed[cookieHold1] && c != null)
                {
                    holding = 4;
                    c.isCooking(2,0);
                    c.follow(this);
                }
                cookieHold1 = 0;
            }
            if ((holding == 4 && c != null && c.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                if (c != null)
                {
                    c.snapToKitchen(kitchen);
                }
                if (cookieHold1 == 0)
                {
                    cookieHold1 = 4;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                    //System.out.println(flaTimer.whatTimeMrWolf());
                }
            }

        }
        /**
         * 
         * 
         * KITCHEN 2
         * 
         */

        else if (kitchen == 2)
        {
            Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
            getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
            if (cookieHold2 == 0)
            {
                getWorld().addObject(new speechStartCook(), x-40, y-70);       
            }
            if (cookieHold2 == 1  && Greenfoot.isKeyDown("shift") && holding == 0)
            {
                timerClass tr = (timerClass)kitchen2.timer;
                getWorld().removeObject(tr);
                //Actor k1 = (Actor)getWorld().getObjects(kitchen1.class).get(0);
                f = (Bokchoi)kitchen2.food;
                if (timePassed > timeNeed[cookieHold2] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,Bokchoi.class));
                    holding = 0;
                }
                //System.out.println(flaTimer.whatTimeMrWolf() + " " + timeNeed[holding]);
                if (timePassed > timeNeed[cookieHold2] && f != null)
                {
                    f.status(2,2);
                    holding = 1;
                    f.isCooking(2,0);
                    f.follow(this);
                    //editThis getWorld().addObject(f,this.getX()-20, this.getY());
                    //System.out.println("alkvjdnekajrfbdvc");
                }               
                if (timePassed < timeNeed[cookieHold2] && f != null)
                {
                    holding = 1;
                    f.isCooking(2,0);
                    f.follow(this);
                    //getWorld().addObject(f,this.getX()-20, this.getY());
                }
                cookieHold2 = 0;
            }
            if ((holding == 1 && f != null && f.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                //Bokchoi f = (Bokchoi)getOneObjectAtOffset(-20,0,Bokchoi.class);
                if (f != null)
                {
                    f.snapToKitchen(kitchen);
                }
                if (cookieHold2 == 0)
                {
                    cookieHold2 = 1;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                }
            }

            broccoli b = (broccoli)getOneObjectAtOffset(-20,0,broccoli.class);
            beef cow = (beef)getOneObjectAtOffset(-20,0,beef.class);
            chicken c = (chicken)getOneObjectAtOffset(-20,0,chicken.class);

            if (cookieHold2 == 2  && Greenfoot.isKeyDown("shift") && holding ==0)
            {
                timerClass tr = (timerClass)kitchen2.timer;
                getWorld().removeObject(tr);
                //Actor k1 = (Actor)getWorld().getObjects(kitchen1.class).get(0);
                b = (broccoli)kitchen2.food;
                if (timePassed > timeNeed[cookieHold2] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,broccoli.class));
                    holding = 0;
                }
                //System.out.println(flaTimer.whatTimeMrWolf() + " " + timeNeed[holding]);
                if (timePassed > timeNeed[cookieHold2] && b != null)
                {
                    b.status(2,2);
                    holding = 2;
                    b.isCooking(2,0);
                    b.follow(this);
                    //editThis getWorld().addObject(f,this.getX()-20, this.getY());
                    //System.out.println("alkvjdnekajrfbdvc");
                }               
                if (timePassed < timeNeed[cookieHold2] && b != null)
                {
                    holding = 2;
                    b.isCooking(2,0);
                    b.follow(this);
                    //getWorld().addObject(f,this.getX()-20, this.getY());
                }
                cookieHold2 = 0;
            }
            if ((holding == 2 && b != null && b.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                if (b != null)
                {
                    b.snapToKitchen(kitchen);
                }
                if (cookieHold2 == 0)
                {
                    cookieHold2 = 2;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                }
            }

            /**
             * BEEF
             */

            if (cookieHold2 == 3  && Greenfoot.isKeyDown("shift") && holding ==0)
            {
                timerClass tr = (timerClass)kitchen2.timer;
                getWorld().removeObject(tr);
                cow = (beef)kitchen2.food;
                if (timePassed > timeNeed[cookieHold2] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,beef.class));
                    holding = 0;
                }
                if (timePassed > timeNeed[cookieHold2] && cow != null)
                {
                    cow.status(2,2);
                    holding = 3;
                    cow.isCooking(2,0);
                    cow.follow(this);
                }               
                if (timePassed < timeNeed[cookieHold2] && cow != null)
                {
                    holding = 3;
                    cow.isCooking(2,0);
                    cow.follow(this);
                }
                cookieHold2 = 0;
            }
            if ((holding == 3 && cow != null && cow.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                if (cow != null)
                {
                    cow.snapToKitchen(kitchen);
                }
                if (cookieHold2 == 0)
                {
                    cookieHold2 = 3;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                    //System.out.println(flaTimer.whatTimeMrWolf());
                }
            }

            /**
             * 
             * CHICKEN
             * 
             *
             */

            if (cookieHold2 == 4  && Greenfoot.isKeyDown("shift") && holding ==0)
            {
                timerClass tr = (timerClass)kitchen2.timer;
                getWorld().removeObject(tr);
                c = (chicken)kitchen2.food;
                if (timePassed > timeNeed[cookieHold2] * 1.8)
                {
                    getWorld().removeObject(getOneObjectAtOffset(0,0,chicken.class));
                    holding = 0;
                }
                if (timePassed > timeNeed[cookieHold2] && c != null)
                {
                    c.status(2,2);
                    holding = 4;
                    c.isCooking(2,0);
                    c.follow(this);
                }               
                if (timePassed < timeNeed[cookieHold2] && c != null)
                {
                    holding = 4;
                    c.isCooking(2,0);
                    c.follow(this);
                }
                cookieHold2 = 0;
            }
            if ((holding == 4 && c != null && c.status(1,0) == 1) && Greenfoot.isKeyDown("enter"))
            {        
                if (c != null)
                {
                    c.snapToKitchen(kitchen);
                }
                if (cookieHold2 == 0)
                {
                    cookieHold2 = 4;
                    getWorld().removeObject(flaTimer);
                    start = System.currentTimeMillis();
                    flaTimer = null;
                    flaTimer = new timerClass(timeNeed[holding],10,30);
                    holding = 0;
                    getWorld().addObject(flaTimer,this.getX(),this.getY());
                    getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
                    getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
                    getWorld().addObject(new speechStopCook(), x-40, y-70);  
                    //System.out.println(flaTimer.whatTimeMrWolf());
                }
            }

        }
        else
        {
            getWorld().removeObjects(getWorld().getObjects(speechStartCook.class));
            getWorld().removeObjects(getWorld().getObjects(speechStopCook.class));
        }
    }

    //get kitchen number, returns 1 for left kitchen, 2 for right kitchen
    private int getKitchen(int x, int y)
    {
        if  ((x <= 350 && x >= 320) && (y <= 170 && y>=160))
        {
            return 1;
        }
        else if ((x <= 560 && x >= 540) && (y <= 188 && y >= 160))
        {
            return 2;
        }
        else 
        {
            return 0;
        }
    }

    // When the chef is touching a food item, and pressing enter, he holds the item
    private void holdStuff()
    {
        int x = this.getX();
        int y = this.getY();
        if (Greenfoot.isKeyDown("enter"))
        {
            if (touching == 1 && holding != 1)
            {
                holding = 1;
                getWorld().addObject(new Bokchoi(), x,y); 
            }
            else if (touching ==2 && holding != 2)
            {
                holding = 2;
                getWorld().addObject(new broccoli(), x,y); 
            }
            else if (touching == 3 && holding != 3)
            {
                holding = 3;
                getWorld().addObject(new beef(), x,y); 
            }
            else if (touching == 4 && holding != 4)
            {
                holding = 4;
                getWorld().addObject(new chicken(), x,y); 
            }
        }        
        //if user presses backspace, unequip everything
        else if (Greenfoot.isKeyDown("backspace"))
        {
            holding = 0;
            getWorld().removeObject(getOneObjectAtOffset(-20,0,Bokchoi.class));
            getWorld().removeObject(getOneObjectAtOffset(-20,0,chicken.class));
            getWorld().removeObject(getOneObjectAtOffset(-20,0,broccoli.class));
            getWorld().removeObject(getOneObjectAtOffset(-20,0,beef.class));
        }
    }

}
