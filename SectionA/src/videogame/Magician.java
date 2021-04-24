package videogame;

public class Magician extends Entity implements SpellCaster {
  public static final int MAGICIAN_DAMAGE_FACTOR = 2;
  public Magician(String name, int lifePoints) {
    super(name, lifePoints);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    return calculateDamageAndApply(damageAmount);
  }

  @Override
  public int minimumStrikeToDestroy() {
    return lifePoints;
  }

  @Override
  public int getStrength() {
    return MAGICIAN_DAMAGE_FACTOR * lifePoints;
  }

}

