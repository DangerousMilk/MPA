import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public Player player;
    private float enemySpawnTimer = 0;
    private float enemySpawnInterval = 200;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1440, 1080, 1);
        
        setPaintOrder(ExplosionEffect.class, Player.class, HealthBar.class, Enemy.class ,Bullet.class);
        
        player = new Player();
        addObject(player, 300, 300);
    }
    
    public void act()
    {
        handleEnemySpawning();
    }
    
    public void handleEnemySpawning()
    {
        enemySpawnTimer++;
        if(enemySpawnTimer >= enemySpawnInterval)
        {
            enemySpawnTimer = 0;
            spawnEnemy();
        }
    }
    
    public void spawnEnemy()
    {
        addObject(new Enemy(), 0, 0);
    }
}
