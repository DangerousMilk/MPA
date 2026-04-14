import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor
{
    private int speed = 50;
    private int damage = 15;
    
    public Bullet(int angle)
    {
        setRotation(angle);
        getImage().scale(10, 10); 
    }
    
    public void act()
    {
        move(speed);
        
        if(isTouching(Enemy.class))
        {
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
            enemy.damageEnemy(damage);

            getWorld().removeObject(this);
            return;
        }
        
        if(isAtEdge())
        {
            getWorld().removeObject(this);
            return;
        }
        
    }
}
