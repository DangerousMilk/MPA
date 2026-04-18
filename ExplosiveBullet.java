import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class ExplosiveBullet extends Bullet
{
    private int areaOfEffect = 150;
    
    public ExplosiveBullet(int angle, Actor shooter)
    {
        super(angle, shooter);
    }

    public void act()
    {
        super.act();
    }
    
    @Override
    public void hit(Actor hitObject)
    {
        getWorld().addObject(new ExplosionEffect(), getX(), getY());
        
        // Damage everything in the explosion area
        List<Actor> hitActors = getNeighbours(areaOfEffect, true, Actor.class);
        for (Actor hitActor : hitActors)
        {
            if(hitActor instanceof IDamagable damagable)
            {
                // Calculate falloff
                double dx = getX() - hitActor.getX();
                double dy = getY() - hitActor.getY();
                double falloff = 1 - (Math.sqrt(dx * dx + dy * dy) / areaOfEffect);
                // Prevent negative values
                falloff = Math.max(0, falloff);
    
                damagable.takeDamage(
                    (int)(falloff * getDamage()), 
                    getX(),
                    getY(), 
                    falloff * getKnockback()
                );   
            }
        }
        
        getWorld().removeObject(this);
    }
    
    @Override
    public int getDamage()
    {
        return 50;
    }
    
    @Override
    public int getSpeed()
    {
        return 10;
    }
    
    @Override
    public int getKnockback()
    {
        return 25;
    }
}
