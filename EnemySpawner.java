import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class EnemySpawner extends Actor
{
    // Spawn timer
    private float enemySpawnTimer = 0;
    private float enemySpawnInterval = 200;
    
    private int enemiesToSpawn = 0;
    private int enemiesToSpawnPerWave = 5;
    private int spawnedEnemies = 0;
    
    private int currentEnemyCount = 0;
    
    private int wave = 0;
    
    private static EnemySpawner instance;
    
    public EnemySpawner()
    {
        instance = this;
        progressWave();
    }
    
    public static EnemySpawner getInstance()
    {
        if(instance != null)
        {
            return instance;  
        }
        
        return null;
    }
    
    public void act()
    {
        handleEnemySpawning();
        
        if(allWaveEnemiesSpawned() && currentEnemyCount <= 0)
        {
            Greenfoot.delay(5);
            progressWave();
        }
    }
    
    private void progressWave()
    {
        wave++;
        enemiesToSpawn = enemiesToSpawnPerWave * wave;
        spawnedEnemies = 0;
        enemySpawnTimer = enemySpawnInterval;
    }
    
    private boolean allWaveEnemiesSpawned()
    {
        return spawnedEnemies >= enemiesToSpawn;
    }
    
    private void handleEnemySpawning()
    {
        if(allWaveEnemiesSpawned()) return;
        
        enemySpawnTimer--;
        if(enemySpawnTimer <= 0)
        {
            enemySpawnTimer = enemySpawnInterval;
            spawnEnemy();
        }
    }

    private void spawnEnemy()
    {
        int width = getWorld().getWidth();
        int height = getWorld().getHeight();
        
        // Random position
        int x = Greenfoot.getRandomNumber(width);
        int y = Greenfoot.getRandomNumber(height);
        
        // Choose side
        switch(Greenfoot.getRandomNumber(4))
        {
            case 0:
                x = 0;
                break;
            case 1:
                x = width;
                break;
            case 2:
                y = 0;
                break;
            case 3:
                y = height;
                break;
        }
        
        getWorld().addObject(new Enemy(), x, y);
        
        currentEnemyCount++;
        spawnedEnemies++;
    }
    
    public void enemyKilled()
    {
        currentEnemyCount--;
    }
}
