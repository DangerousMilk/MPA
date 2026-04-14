import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    public Enemy targetEnemy;
    private int yOffset = -25;
    
    public HealthBar(Enemy enemy)
    {
        targetEnemy = enemy;
    }
    
    public void act()
    {
        if(targetEnemy.getWorld() == null)
        {
            getWorld().removeObject(this);
            return;
        }

        setLocation(targetEnemy.getX(), targetEnemy.getY() + yOffset);
        setImage(new GreenfootImage("" + targetEnemy.health, 20, Color.WHITE, null));
    }
}
