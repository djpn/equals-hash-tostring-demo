package edu.cnm.deepdive;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Demo implements Serializable {

  private static final long serialVersionUID = -5390786974047307421L;
  private static final String FORMAT_STRING = "%s(x: %d, y: %d, z: %.3f)";

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
    return other == this // If comparing object to itself, equals must behave as == (reflexivity).
        || (other != null // a.equals(null) must return false. (Note that necessarily, a != null.)
            && other.getClass() == getClass() // Check for same class. (Note: This is not always the
                                              // correct behavior, as it can lead to a violation of
                                              // the Liskov substitution principle, but it preserves
                                              // symmetry, and is appropriate for some applications.)
            && other.hashCode() == hashCode() // If implementing the recommended equals-hashCode
                                              // contract, we can depend on this.
            && Arrays.deepEquals(((Demo) other).state(), state())); // Compare arrays of fields.
  }

  @Override
  public String toString() {
    return String.format(FORMAT_STRING, getClass().getName(), x, y, z);
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
