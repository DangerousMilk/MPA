import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor implements IDamagable
{
    private MyWorld world; 
    private Player player;
    
    protected int health = 100;

    protected double maxSpeed = 3;
    protected double acceleration = 0.1;
    
    private double vx = 0;
    private double vy = 0;
    
    @Override
    public void addedToWorld(World world)
    {
        this.world = getWorldOfType(MyWorld.class);
        player = this.world.player;
        
        // Health bar
        HealthBar healthBar = new HealthBar(this);
        this.world.addObject(healthBar, 0, 0);
    }

    public void act()
    {
        handleMovement();
        handleRotation();
        isTouching(Player.class)
        {
            
        }
    }
    
    private void handleMovement()
    {
        double dx = player.getX() - getX();
        double dy = player.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
            // Accelerate towards player
            vx += (dx / distance) * acceleration;
            vy += (dy / distance) * acceleration;
            
            // Clamp velocity at max speed
            double velLength = Math.sqrt(vx * vx + vy * vy);
            if(velLength > maxSpeed)
            {
                vx = (vx / velLength) * maxSpeed;
                vy = (vy / velLength) * maxSpeed;
            }
            
            // Move enemy
            int newX = (int)(getX() + vx);
            int newY = (int)(getY() + vy);
            setLocation(newX, newY);
        }
    }
    
    private void handleRotation()
    {
        // Calculate facing direction based on velocity
        double angleRad = Math.atan2(vy, vx);
        // Convert to degrees
        int angleDeg = (int)Math.toDegrees(angleRad);
        setRotation(angleDeg);
    }
    
    public void takeDamage(int amount, int damagePosX, int damagePosY, double knockback)
    {
        health -= amount;
        if(health <= 0)
        {
            getWorld().removeObject(this);
            return;
        }
        
        // Knockback
        double dx = damagePosX - getX();
        double dy = damagePosY - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        vx -= (dx / distance) * knockback;
        vy -= (dy / distance) * knockback;
    }
    
    public int getHealth()
    {
        return health;
    }
}
