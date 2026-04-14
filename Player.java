import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    int speed = 2;
    
    public void act()
    {
        handleMovement();
        handleRotation();
        handleShooting();
    }
    
    public void handleShooting()
    {
        if(Greenfoot.mouseClicked(null))
        {
            getWorld().addObject(new Bullet(getRotation()), getX(), getY());
        }   
    }
    
    public void handleRotation()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            turnTowards(mouse.getX(), mouse.getY());
        }
    }
    
    public void handleMovement()
    {
        int moveY = Greenfoot.isKeyDown("W") ? 1 : Greenfoot.isKeyDown("S") ? -1 : 0;
        int moveX = Greenfoot.isKeyDown("D") ? 1 : Greenfoot.isKeyDown("A") ? -1 : 0;
        
        setLocation(
            getX() + moveX  * speed, 
            getY() - moveY * speed
        );
    }
}
