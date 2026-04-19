import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class ExplosionEffect extends Effect
{
    public void act()
    {
        super.act();
    }
    
    @Override
    public String[] getAnimationFrames()
    {
        String[] sprites = {
            "explosion_00.png",
            "explosion_01.png",
            "explosion_02.png",
            "explosion_03.png",
            "explosion_04.png",
            "explosion_05.png",
            "explosion_06.png",
            "explosion_07.png",
            "explosion_08.png",
            "explosion_09.png",
            "explosion_10.png",
            "explosion_11.png",
            "explosion_12.png",
            "explosion_13.png",
            "explosion_14.png",
            "explosion_15.png"
        };
        return sprites;
    }
    
    @Override
    public int getSize()
    {
        return 220;
    }
    
    @Override
    public boolean destroyOnAnimationFinish()
    {
        return true;
    }
}
