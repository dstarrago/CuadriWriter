/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public interface Constants {
  static public int refCentral      = 0;
  static public int refLeft         = 1;
  static public int refRight        = 2;
  static public int refBottom       = 3;
  static public int refTop          = 4;
  static public int refBottomLeft   = 5;
  static public int refBottomRight  = 6;
  static public int refTopLeft      = 7;
  static public int refTopRight     = 8;

  static public int ReferencesNumber = 9;

  static public double mark0_1   = .22;      // Acá termina la referencia de la E (en ambas coordenadas)
  static public double mark1     = .36;
  static public double mark2     = .50;
  static public double mark3     = .64;
  static public double mark3_4   = .78;      // Acá empieza la referencia de la O (en ambas coordenadas)
  static public double mark4     = 1.0;

  static public double largeDesp = 0.5;
  static public double shortDesp = 0.25;

  /*
   *  Si el grafe sólo contiene letras en conjunto horizPack entonces el grafe
   *  es compactable en el eje x.
   */
  static public String horizPack = "ABCDEQRKIJSLOVXY";
  /*
   *  Si el grafe sólo contiene letras en conjunto vertPack entonces el grafe
   *  es compactable en el eje y.
   */
  static public String vertPack  = "EFGHIKTVAPNMOQZY";

  static public int referenceWidth = 2;
  static public int halfRefWidth   = referenceWidth / 2;

  static public int defaultGrafeWidth  = 100;
  static public int defaultGrafeHeight = 100;

}
