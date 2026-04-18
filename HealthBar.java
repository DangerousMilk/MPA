import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    public IDamagable target;
    private int yOffset = -25;
    
    public HealthBar(IDamagable target)
    {
        this.target = target;
    }
    
    public void act()
    {
        Actor targetActor = (Actor)target;
        if(targetActor.getWorld() == null)
        {
            getWorld().removeObject(this);
            return;
        }

        setLocation(targetActor.getX(), targetActor.getY() + yOffset);
        setImage(new GreenfootImage("" + target.getHealth(), 20, Color.WHITE, null));
    }
}
