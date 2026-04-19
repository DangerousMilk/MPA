import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class EnemySpawner extends Actor
{
    // Spawn timer
    private float enemySpawnTimer = 0;
    private float enemySpawnInterval = 200;
    
    // Enemy spawning
    private int enemiesToSpawn = 0;
    private int enemiesToSpawnPerWave = 5;
    private int spawnedEnemies = 0;
    
    // Tracking
    private int currentEnemyCount = 0;
    private int wave = 0;
    
    // Cooldown
    private int waveCooldownTimer = 0;
    private int waveInterval = 100;
    
    private static EnemySpawner instance;
    
    public EnemySpawner()
    {
        instance = this;
    }
    
    @Override
    public void addedToWorld(World world)
    {
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
        handleWaveCooldown();
        
        if(waveCooldownFinished())
        {
            handleEnemySpawning();   
        }
        
        if(waveCompleted())
        {
            progressWave();
        }
    }
    
    private void progressWave()
    {
        wave++;
        
        // Spawning
        enemiesToSpawn = enemiesToSpawnPerWave * wave;
        spawnedEnemies = 0;
        enemySpawnTimer = enemySpawnInterval;
        
        // Cooldown
        waveCooldownTimer = waveInterval;
        
        Message.getInstance().showMessage("Welle " + wave, 10, 1000);
    }
    
    private boolean waveCooldownFinished()
    {
        return waveCooldownTimer <= 0;
    }
    
    private void handleWaveCooldown()
    {
        if(waveCooldownTimer > 0)
        {
            waveCooldownTimer--;
        }
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
     
    private boolean allWaveEnemiesSpawned()
    {
        return spawnedEnemies >= enemiesToSpawn;
    }
    
    private boolean waveCompleted()
    {
        return allWaveEnemiesSpawned() && currentEnemyCount <= 0;
    }
    
    public void enemyKilled()
    {
        currentEnemyCount--;
    }
}
