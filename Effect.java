import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Effect extends Actor
{
    private int lifetimeTimer;
    
    // Animaiton
    private int animationTimer;
    private int currentAnimationFrame;
    protected String[] animationFrames;
    
    @Override
    public void addedToWorld(World world)
    {
        lifetimeTimer = getLifetime();
        animationTimer = getAnimationSpeed();
        
        // Animation
        animationFrames = getAnimationFrames();
        setImage(animationFrames[0]);
    }
    
    public void act()
    {
        if(!destroyOnAnimationFinish()) handleLifetime();
        
        handleAnimation();
    }
    
    private void handleLifetime()
    {
        lifetimeTimer --;
        if(lifetimeTimer <= 0)
        {
            destroyEffect();
            return;
        }
    }
    
    private void handleAnimation()
    {   
        animationTimer--;
        if(animationTimer <= 0)
        {
            currentAnimationFrame++;
            
            if(currentAnimationFrame < animationFrames.length)
            {
                setImage(animationFrames[currentAnimationFrame]);
                getImage().scale(getSize(), getSize());
            }
            else
            {
                destroyEffect();
                return;
            }
            
            animationTimer = getAnimationSpeed();
        }
    }
    
    public void destroyEffect()
    {
        getWorld().removeObject(this);
    }
    
    public int getLifetime()
    {
        return 50;
    }
    
    public int getAnimationSpeed()
    {
        return 3;
    }
    
    public String[] getAnimationFrames()
    {
        return null;
    }
    
    public int getSize()
    {
        return 5;
    }
    
    public boolean destroyOnAnimationFinish()
    {
        return false;
    }
}