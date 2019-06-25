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
    // If comparing object to itself, equals must behave like == (reflexivity).
    return other == this
        // See comment below on comparing state. (Also ensures a.equals(null) returns false.)
        || (other instanceof Demo
            // If implementing the recommended equals-hashCode contract, we can depend on this.
            && other.hashCode() == hashCode()
            // Compare arrays of fields. As long as we override state method appropriately for
            // subclasses that add state fields, this approach preserves symmetry and respects the
            // Liskov substitution principle, under the condition that the instances being compared
            // have the same set of state fields (even if not of the same class).
            && Arrays.deepEquals(((Demo) other).state(), state()));
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
