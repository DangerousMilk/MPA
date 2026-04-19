import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Message extends Actor
{
    public static Message instance;
    
    private int transparency = 0;
    
    // Transition
    // 0 - fade in
    // 1 - pause
    // 3 - fade out
    private int transitionStage = 0;
    private int transitionSpeed = 0;
    
    // Pause
    private int pauseTime = 0;
    private int pauseTimer = 0;
    
    public Message()
    {
        instance = this;
    }
    
    @Override
    public void addedToWorld(World world)
    {
        setLocation(
            world.getWidth() / 2, 
            world.getHeight() / 2
        );
    }
    
    public static Message getInstance()
    {
        if(instance != null)
        {
            return instance;
        }
        return null;
    }
    
    public void act()
    {
        if(transitionStage >= 3) return;
        
        switch(transitionStage)
        {
            case 0:
                transparency += transitionSpeed;
                if(transparency >= 255)
                {
                    transparency = 255;
                    transitionStage++;
                }
                break;
            case 1:
                pauseTimer += transitionSpeed;
                if(pauseTimer >= pauseTime)
                {
                    transitionStage++;
                }
                break;
            case 2:
                transparency -= transitionSpeed;
                if(transparency <= 0)
                {
                    transparency = 0;
                    transitionStage++;
                }
                break;
        }
        
        getImage().setTransparency(transparency);
    }
    
    public void showMessage(String text, int speed, int pause)
    {
        transitionStage = 0;
        
        transitionSpeed = speed;
        
        // Pause
        pauseTimer = 0;
        pauseTime = pause;
        
        transparency = 0;
        
        setImage(new GreenfootImage("" + text, 50, new Color(255, 255, 255, 255), null));
        getImage().setTransparency(transparency);
    }
}
