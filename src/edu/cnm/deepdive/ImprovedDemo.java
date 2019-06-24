package edu.cnm.deepdive;

public class ImprovedDemo extends Demo {

  // Even if a sublass doesn't add state fields, it should define its own serialVersionUID.
  private static final long serialVersionUID = -4569463404773389307L;

  public ImprovedDemo(int x, int y, double z) {
    super(x, y, z);
  }

}
