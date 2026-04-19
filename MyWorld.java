import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public Player player;
    
    public MyWorld()
    {    
        super(1440, 1080, 1);
        
        setPaintOrder(ExplosionEffect.class, Player.class, HealthBar.class, Enemy.class ,Bullet.class);
        
        player = new Player();
        addObject(player, 300, 300);
        
        // Singletons
        addObject(new Message(), 0, 0);
        addObject(new EnemySpawner(), 0, 0);
    }
    
    public void act()
    {
        
    }
}
