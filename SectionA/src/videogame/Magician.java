package videogame;

public class Magician extends Entity implements SpellCaster {
  public Magician(String name, int lifePoints) {
    super(name, lifePoints);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    int damageInflicted = Math.min(lifePoints, damageAmount);
    lifePoints -= damageInflicted;
    return damageInflicted;
  }

  @Override
  public int minimumStrikeToDestroy() {
    return lifePoints;
  }

  @Override
  public int getStrength() {
    return 2 * lifePoints;
  }

  @Override
  public String toString() {
    return String.format("%s(%d)", name, lifePoints);
  }
}

