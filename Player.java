import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor implements IDamagable
{
    private int speed = 2;
    private int health = 100;
    
    private int shootCooldownTimer = 0;
    private int shootCooldown = 25;

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
        if(shootCooldownTimer <= 0)
        {
            if(Greenfoot.mouseClicked(null))
            {
                shoot();
                shootCooldownTimer = shootCooldown;
            }   
        }
        else
        {
            shootCooldownTimer--;
        }
    }
    
    private void shoot()
    {
        getWorld().addObject(new ExplosiveBullet(getRotation(), this), getX(), getY());
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
    
    /*
    public Actor getActor()
    {
        return this;
    }
    */
}
