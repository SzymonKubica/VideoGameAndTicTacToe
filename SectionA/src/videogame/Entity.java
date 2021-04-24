package videogame;

public abstract class Entity {
	
	protected String name;
	protected int lifePoints;

	public Entity(String name, int lifePoints) {
		assert(lifePoints >= 0);
		this.name = name;
		this.lifePoints = lifePoints;
	}

	public final boolean isAlive() {
	  return lifePoints != 0;
	}
	
	public final int applySpell(SpellCaster spellCaster) {
	  return propagateDamage(spellCaster.getStrength());
	}
	
	protected abstract int propagateDamage(int damageAmount);

	protected int calculateDamageAndApply(int damageAmount) {
	  int damageInflicted = Math.min(lifePoints, damageAmount);
	  lifePoints -= damageInflicted;
	  return damageInflicted;
	}

	public abstract int minimumStrikeToDestroy();

	@Override
	public String toString() {
	  return String.format("%s(%d)", name, lifePoints);
	}
	
}
