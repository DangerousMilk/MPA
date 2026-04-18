import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor implements IDamagable
{
    int speed = 2;
    int health = 100;

    @Override
    public void addedToWorld(World world)
    {
        getWorld().addObject(new HealthBar(this), 0, 0);
    }
    
    public void act()
    {
        handleMovement();
        handleRotation();
        handleShooting();
    }
    
    private void handleShooting()
    {
        if(Greenfoot.mouseClicked(null))
        {
            getWorld().addObject(new Bullet(getRotation(), this), getX(), getY());
        }   
    }
    
    private void handleRotation()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            turnTowards(mouse.getX(), mouse.getY());
        }
    }
    
    private void handleMovement()
    {
        int moveY = Greenfoot.isKeyDown("W") ? 1 : Greenfoot.isKeyDown("S") ? -1 : 0;
        int moveX = Greenfoot.isKeyDown("D") ? 1 : Greenfoot.isKeyDown("A") ? -1 : 0;
        
        setLocation(
            getX() + moveX  * speed, 
            getY() - moveY * speed
        );
    }
    
    public void takeDamage(int amount, int damagePosX, int damagePosY, double knockback)
    {
        health -= amount;
    }
    
    public int getHealth()
    {
        return health;
    }
}
