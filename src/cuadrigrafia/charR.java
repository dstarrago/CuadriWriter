/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public class charR extends Character implements Constants {

  /*
   * Símbolo que representa el carácter en el cuadrigrama.
   */
  static public char glyph = 'R';
  /*
   * Referencia asociada.
   */
  static public int reference = refLeft;
  /*
   * Indica si la posición del caracter es variable, en cuyo caso se podrá
   * representar varias veces el mismo carácter en el grafe ocupando cada vez
   * un posición ligeramente  distinta.
   */
  static public boolean alterable = true;
  /*
   * Posición asociada al carácter en el eje x.
   */
  static public double xPos = mark0_1;
  /*
   * Posición asociada al carácter en el eje y.
   */
  static public double yPos = mark2;
  /*
   * Arreglo de desplazamientos alternativos en el eje x.
   */
  static public double[] xAlt = new double[] {0, 0, 0};
  /*
   * Arreglo de desplazamientos alternativos en el eje y.
   */
  static public double[] yAlt = new double[] {shortDesp, -shortDesp, 0};

  public char glyph() { return glyph; }
  public int getReference() { return reference; }
  public boolean isAlterable() { return alterable; }
  public double xPos() { return xPos; }
  public double yPos() { return yPos; }

  @Override
  public double xAlt(int i) {
    return xAlt[i];
  }

  @Override
  public double yAlt(int i) {
    return yAlt[i];
  }

}
