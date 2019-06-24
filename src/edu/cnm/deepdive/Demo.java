package edu.cnm.deepdive;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Demo implements Serializable {

  private static final long serialVersionUID = -5390786974047307421L;

  private final int x;
  private final int y;
  private final double z;

  /*
   * Transient fields ...
   * - Aren't included in standard Java serialization/deserialization.
   * - Are ignored by Gson & Jackson when serializing/deserializing to/from JSON.
   * - Are ignored by Room & JPA (including Hibernate) in generating DDL and DML for data model.
   */
  private transient int hash;
  private transient boolean hashComputed;

  public Demo(int x, int y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public int hashCode() {
    if (!hashComputed) {
      hash = Objects.hash(state());
      hashComputed = true;
    }
    return hash;
  }

  @Override
  public boolean equals(Object other) {
    return other != null
        && other.getClass() == getClass()
        && other.hashCode() == hashCode() // If we're following the recommended equals-hashCode contract, we can depend on this.
        && Arrays.equals(((Demo) other).state(), state());
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

  protected Object[] state() {
    return new Object[]{x, y, z};
  }

}
