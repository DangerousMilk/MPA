import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{

    public Player player;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        setPaintOrder(Player.class, HealthBar.class, Enemy.class ,Bullet.class);
        
        player = new Player();
        addObject(player, 300, 300);
        addObject(new Enemy(), 0, 0);
    }
}
