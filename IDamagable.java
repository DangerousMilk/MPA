public interface IDamagable  
{
    void takeDamage(int amount, int damagePosX, int damagePosY, double knockback);
    int getHealth();
}