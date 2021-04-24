package videogame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransportUnit extends Entity {
  public static final int CONTAINED_ENTITY_DAMAGE_REDUCTION = 2;
  private Set<Entity> entitiesContained;

  public TransportUnit(String name, int lifePoints) {
    super(name, lifePoints);
    entitiesContained = new HashSet<>();
  }

  public void add(Entity entity) {
    entitiesContained.add(entity);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    int damageSum = 0;
    damageSum += calculateDamageAndApply(damageAmount);
    for (Entity entity : entitiesContained) {
      damageSum += entity
              .propagateDamage(damageAmount / CONTAINED_ENTITY_DAMAGE_REDUCTION);
    }
    return damageSum;
  }

  @Override
  public int minimumStrikeToDestroy() {
    return Math.max(lifePoints,
            entitiesContained.stream().mapToInt(Entity::minimumStrikeToDestroy).max().getAsInt());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append(" transporting: ");
    sb.append("[");
    List<Entity> entityList = new ArrayList<>(entitiesContained);
    for (int i = 0; i < entitiesContained.size(); i++) {
      sb.append(entityList.get(i));
      if (i != entitiesContained.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

