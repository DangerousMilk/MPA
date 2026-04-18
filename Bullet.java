import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor
{
    protected Class<?> collisionMask = Enemy.class;
    private Actor shooter;
    
    public Bullet(int angle, Actor shooter)
    {
        setRotation(angle);
        getImage().scale(10, 10);
        this.shooter = shooter;
    }
    
    public void act()
    {
        move(getSpeed());
        checkCollision();
    }
    
    private void checkCollision()
    {
        if(isTouching(collisionMask))
        {
            hit(getOneIntersectingObject(collisionMask));
            return;
        }
        
        if(isAtEdge())
        {
            hit(null);
            return;
        }  
    }
    
    private void hit(Actor hitObject)
    {
        if(hitObject != null)
        {
            IDamagable damagable = (IDamagable)hitObject;
            damagable.takeDamage(getDamage(), shooter.getX(), shooter.getY(), getKnockback());
        }
        
        getWorld().removeObject(this);
    }
    
    public int getDamage()
    {
        return 15;
    }
    
    public int getSpeed()
    {
        return 50;
    }
    
    public int getKnockback()
    {
        return 2;
    }
}
