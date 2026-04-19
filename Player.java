import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor implements IDamagable
{
    private int speed = 2;
    private int health = 100;
    
    private int shootCooldownTimer = 0;
    private int shootCooldown = 25;
    
    private boolean isHoldingDownMouse;

    @Override
    public void addedToWorld(World world)
    {
        getWorld().addObject(new HealthBar(this), 0, 0);
    }
    
    public void act()
    {
        handleMouseInput();
        handleShooting();
        
        handleMovement();
        handleRotation();
    }
    
    private void handleMouseInput()
    {
        if(Greenfoot.mousePressed(null))
        {
            isHoldingDownMouse = true;
        }
        
        if(Greenfoot.mouseClicked(null))
        {
            isHoldingDownMouse = false;
        }
    }
    
    private void handleShooting()
    {
        if(shootCooldownTimer <= 0)
        {
            if(isHoldingDownMouse)
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
        if(health <= 0)
        {
            getWorld().removeObject(this);
            Message.getInstance().showMessage("Du hast verloren!", 10, 2000);
            return;
        }
    }
    
    public int getHealth()
    {
        return health;
    }
}
