import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Effect extends Actor
{
    private int lifetimeTimer;
    
    @Override
    public void addedToWorld(World world)
    {
        lifetimeTimer = getLifetime();
    }
    
    public void act()
    {
        lifetimeTimer --;
        if(lifetimeTimer <= 0)
        {
            getWorld().removeObject(this);
            return;
        }
    }
    
    public int getLifetime()
    {
        return 50;
    }
}
