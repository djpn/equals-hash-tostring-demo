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
   * - Are ignored by standard Java serialization/deserialization process.
   * - Are ignored by Gson & Jackson when serializing/deserializing to/from JSON.
   * - Are generally ignored by ORMs (including Room & JPA/Hibernate) when generating DDL and DML.
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
    // Lazily evaluate the hash code.
    if (!hashComputed) {
      hash = Objects.hash(state());
      hashComputed = true;
    }
    return hash;
  }

  @Override
  public boolean equals(Object other) {
    return other == this // reflexive: if comparing an object to itself, equals should work like ==.
        || (other != null
            && other.getClass() == getClass()
            && other.hashCode() == hashCode() // If implementing the recommended equals-hashCode
                                              // contract, we can depend on this.
            && Arrays.equals(((Demo) other).state(), state()));
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

  protected Object[] state() { // protected in case we want to add more state fields in subclasses.
    return new Object[]{x, y, z}; // An array containing all non-transient, non-static fields.
  }

}
