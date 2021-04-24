package videogame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransportUnit extends Entity {
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
    int sumOfDamage = 0;
    int damageInflicted = Math.min(lifePoints, damageAmount);
    lifePoints -= damageInflicted;
    sumOfDamage += damageInflicted;
    for (Entity entity : entitiesContained) {
      sumOfDamage += entity.propagateDamage(damageAmount / 2);
    }
    return sumOfDamage;
  }

  @Override
  public int minimumStrikeToDestroy() {
    return Math.max(lifePoints,
     entitiesContained.stream().mapToInt(Entity::minimumStrikeToDestroy).max().getAsInt());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(String.format("(%d)", lifePoints)).append(" transporting: ");
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

