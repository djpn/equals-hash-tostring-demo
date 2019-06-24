package edu.cnm.deepdive;

import java.io.Serializable;
import java.util.Objects;

public class Demo implements Serializable {

  private final int x;
  private final int y;
  private final double z;
  private transient double temp;

  public Demo(int x, int y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public boolean equals(Object other) {
    boolean result = false;
    if (other != null
        && other.getClass() == getClass()) {
      Demo otherDemo = (Demo) other;
      result = otherDemo.x == x
          && otherDemo.y == y
          && otherDemo.z == z;
    }
    return result;
  }

  @Override
  public String toString() {
    return String.format("%s(x: %d, y: %d, z: %.3f)", getClass().getName(), x, y, z);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

}
