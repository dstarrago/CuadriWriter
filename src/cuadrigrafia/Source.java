/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public class Source {

  private FastVector words = new FastVector();

  public Source(String s) {
    String text = s.toUpperCase();
    boolean hope = true;
    int fromIndex = 0;
    while (hope) {
      int pos = text.indexOf(' ', fromIndex);
      if (pos > fromIndex) {
        words.addElement(new Word(text.substring(fromIndex, pos)));
        fromIndex = pos + 1;
      } else {
        if (pos == -1)
          words.addElement(new Word(text.substring(fromIndex, text.length())));
        hope = false;
      }
    }
  }

  public boolean empty() { return length() == 0; }

  public int length() {
    return words.size();
  }

  public Word wordAt(int i) {
    return (Word) words.elementAt(i);
  }

}
