import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    MyWorld world; 
    Player player;
    
    int health = 100;
    int speed = 3;
    
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
        int x = getX();
        int y = getY();
        int dx = Utils.clamp(player.getX() - getX(), -1, 1);
        int dy = Utils.clamp(player.getY() - getY(), -1, 1);
        
        setLocation(x + dx * speed, y + dy * speed);
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
