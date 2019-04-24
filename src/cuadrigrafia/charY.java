/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public class charY extends Character implements Constants {

  /*
   * Símbolo que representa el carácter en el cuadrigrama.
   */
  static public char glyph = 'Y';
  /*
   * Referencia asociada.
   */
  static public int reference = refBottomRight;
  /*
   * Indica si la posición del caracter es variable, en cuyo caso se podrá
   * representar varias veces el mismo carácter en el grafe ocupando cada vez
   * un posición ligeramente  distinta.
   */
  static public boolean alterable = false;
  /*
   * Posición asociada al carácter en el eje x.
   */
  static public double xPos = mark3_4;
  /*
   * Posición asociada al carácter en el eje y.
   */
  static public double yPos = mark3_4;

  public char glyph() { return glyph; }
  public int getReference() { return reference; }
  public boolean isAlterable() { return alterable; }
  public double xPos() { return xPos; }
  public double yPos() { return yPos; }

}
