import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor implements IDamagable
{
    private MyWorld world; 
    private Player player;
    
    // Health
    protected int health = 100;

    // Movement
    protected double maxSpeed = 4;
    protected double acceleration = 0.1;
    private double vx = 0;
    private double vy = 0;
    
    // Stunning
    private int stunTimer = 0;
    private int stunTime = 50;
    
    private EnemyState state = EnemyState.MOVING;

    
    @Override
    public void addedToWorld(World world)
    {
        this.world = getWorldOfType(MyWorld.class);
        player = this.world.player;
        
        // Add health bar
        HealthBar healthBar = new HealthBar(this);
        this.world.addObject(healthBar, 0, 0);
    }

    public void act()
    {
        handleMovement();
        handleRotation();
                
        switch(state)
        {
            case MOVING:
                moveTowardsPlayer();
                handleAttacking();
                break;
            case STUNNED:
                applyDrag();
                handleStunCooldown();
                break;
        }
    }
    
    private void handleAttacking()
    {
        if(isTouching(Player.class))
        {
            IDamagable player = (IDamagable)getOneIntersectingObject(Player.class);
            player.takeDamage(5, getX(), getY(), 0);
            stun();
        }
    }
    
    private void handleStunCooldown()
    {
        stunTimer--;
        if(stunTimer <= 0)
        {
            state = EnemyState.MOVING;
        }
    }
    
    private void stun()
    {
        stunTimer = stunTime;
        state = EnemyState.STUNNED;
    }
    
    private void moveTowardsPlayer()
    {
        double dx = player.getX() - getX();
        double dy = player.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
            // Accelerate towards player
            vx += (dx / distance) * acceleration;
            vy += (dy / distance) * acceleration;
            
            // Clamp velocity at max speed
            double velLength = Math.sqrt(vx * vx + vy * vy);
            if(velLength > maxSpeed)
            {
                vx = (vx / velLength) * maxSpeed;
                vy = (vy / velLength) * maxSpeed;
            }
        }
    }
    private void handleMovement()
    {
        // Push away from other enemies
        double sepX = 0;
        double sepY = 0;
        
        for (Enemy otherEnemy : getNeighbours(40, true, Enemy.class)) {
            double dx = getX() - otherEnemy.getX();
            double dy = getY() - otherEnemy.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);
        
            if (dist > 0) {
                sepX += dx / dist;
                sepY += dy / dist;
            }
        }
        
        // Move enemy
        int newX = (int)(getX() + vx + sepX);
        int newY = (int)(getY() + vy + sepY);
        setLocation(newX, newY);
    }
    
    private void applyDrag()
    {
        vx = vx * 0.95;
        vy = vy * 0.95;
    }
    
    private void handleRotation()
    {
        // Calculate facing direction based on velocity
        double angleRad = Math.atan2(vy, vx);
        // Convert to degrees
        int angleDeg = (int)Math.toDegrees(angleRad);
        setRotation(angleDeg);
    }
    
    public void takeDamage(int amount, int damagePosX, int damagePosY, double knockback)
    {
        health -= amount;
        if(health <= 0)
        {
            getWorld().removeObject(this);
            return;
        }
        
        // Knockback
        double dx = damagePosX - getX();
        double dy = damagePosY - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        vx -= (dx / distance) * knockback;
        vy -= (dy / distance) * knockback;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    /*
    public Actor getActor()
    {
        return this;
    }
    */
}
