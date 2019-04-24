/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public abstract class Character implements Constants {

  /*
   * Se utiliza para representar caracteres como CH, RR, LL o Ñ.
   */
  private boolean shift;
  /*
   * Se utiliza para representar las letras acentuadas.
   */
  private boolean tilde;

  public boolean isShift() { return shift; }
  public void setShift(boolean b) {
    shift = b;
  }

  public boolean hasTilde() { return tilde; }
  public void setTilde(boolean b) {
    tilde = b;
  }

  /*
   * Símbolo que representa el carácter en el cuadrigrama.
   */
  public abstract char glyph();
  /*
   * Referencia asociada.
   */
  public abstract int getReference();
  /*
   * Indica si la posición del caracter es variable, en cuyo caso se podrá
   * representar varias veces el mismo carácter en el grafe ocupando cada vez
   * un posición ligeramente  distinta.
   */
  public abstract boolean isAlterable();
  /*
   * Posición asociada al carácter en el eje x.
   */
  public abstract double xPos();
  /*
   * Posición asociada al carácter en el eje y.
   */
  public abstract double yPos();

  /*
   * Arreglo de desplazamientos alternativos en el eje x.
   */
  public double xAlt(int i) {
    return 0;
  }

  /*
   * Arreglo de desplazamientos alternativos en el eje y.
   */
  public double yAlt(int i) {
    return 0;
  }

  /**
   * Dirección en que se ajustará la posición x del círculo que representa
   * al caracter sobre la referencia cuando se trata del punto final del grafe.
   *
   * @return El resultado puede ser -1, 0 o 1.
   */
  public int xAdjDir() {
    return 0;
  }
  
  /**
   * Dirección en que se ajustará la posición y del círculo que representa
   * al caracter sobre la referencia cuando se trata del punto final del grafe.
   *
   * @return El resultado puede ser -1, 0 o 1.
   */
  public int yAdjDir() {
    return 0;
  }

}
