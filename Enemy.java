import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    MyWorld world; 
    Player player;
    
    int health = 100;
    
    @Override
    public void addedToWorld(World world)
    {
        this.world = getWorldOfType(MyWorld.class);
        player = this.world.player;
        
        HealthBar healthBar = new HealthBar(this);
        this.world.addObject(healthBar, 0, 0);
    }

    public void act()
    {
        turnTowards(player.getX(), player.getY());
        move(1);
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
