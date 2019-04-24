/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public class charM extends Character implements Constants {

  /*
   * Símbolo que representa el carácter en el cuadrigrama.
   */
  static public char glyph = 'M';
  /*
   * Referencia asociada.
   */
  static public int reference = refBottom;
  /*
   * Indica si la posición del caracter es variable, en cuyo caso se podrá
   * representar varias veces el mismo carácter en el grafe ocupando cada vez
   * un posición ligeramente  distinta.
   */
  static public boolean alterable = false;
  /*
   * Posición asociada al carácter en el eje x.
   */
  static public double xPos = mark3;
  /*
   * Posición asociada al carácter en el eje y.
   */
  static public double yPos = mark4;
  /**
   * Dirección en que se ajustará la posición x del círculo que representa
   * al caracter sobre la referencia cuando se trata del punto final del grafe.
   */
  static public int xAdjDir = 0;
  /**
   * Dirección en que se ajustará la posición y del círculo que representa
   * al caracter sobre la referencia cuando se trata del punto final del grafe.
   */
  static public int yAdjDir = -1;

  public char glyph() { return glyph; }
  public int getReference() { return reference; }
  public boolean isAlterable() { return alterable; }
  public double xPos() { return xPos; }
  public double yPos() { return yPos; }

  @Override
  public int xAdjDir() {
    return xAdjDir;
  }

  @Override
  public int yAdjDir() {
    return yAdjDir;
  }

}
