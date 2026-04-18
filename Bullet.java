import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor
{
    protected int speed = 50;
    protected int damage = 15;
    protected int knockback = 2;
    
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
        move(speed);
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
            damagable.takeDamage(damage, shooter.getX(), shooter.getY(), 2);
        }
        
        getWorld().removeObject(this);
    }
}
