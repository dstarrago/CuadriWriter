/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public class Word {
  private FastVector grafes;

  public Word(String text) {
    grafes = new FastVector();
    boolean hope = true;
    int fromIndex = 0;
    while (hope) {
      int pos = text.indexOf('-', fromIndex);
      if (pos > fromIndex) {
        grafes.addElement(new Grafe(text.substring(fromIndex, pos)));
        fromIndex = pos + 1;
      } else {
        if (pos == -1)
          grafes.addElement(new Grafe(text.substring(fromIndex, text.length())));
        hope = false;
      }
    }
  }

  public int length() {
    return grafes.size();
  }

  public Grafe grafeAt(int i) {
    return (Grafe) grafes.elementAt(i);
  }
  
}
