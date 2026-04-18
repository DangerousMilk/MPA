import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    MyWorld world; 
    Player player;
    
    int health = 100;
    int speed = 3;
    
    float vx = 0;
    float vy = 0;
    
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
        double dx = player.getX() - getX();
        double dy = player.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
            double normalizedX = dx / distance;
            double normalizedY = dy / distance;
        
            int newX = (int)(getX() + normalizedX * speed);
            int newY = (int)(getY() + normalizedY * speed);
        
            setLocation(newX, newY);
        }
    }
    
    public void damageEnemy(int damage)
    {
        health -= damage;
        
        if(health <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
