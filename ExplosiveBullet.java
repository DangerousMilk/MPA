import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ExplosiveBullet extends Bullet
{
    public ExplosiveBullet(int angle, Actor shooter)
    {
        super(angle, shooter);
    }

    public void act()
    {
        super.act();
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
        return 10;
    }
}
