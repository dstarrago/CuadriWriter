/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

/**
 *
 * @author Danel
 */
public class Grafe implements Constants {
  private FastVector codes;
  private boolean[] multiple;
  private int[] multIndex;
  private boolean xCompactable = true;
  private boolean yCompactable = true;
  private boolean[] reference;

  public Grafe(String text) {
    codes = new FastVector();
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      Character car = null;
      switch (c) {
        case 'Ñ':
          car = new charN();
          car.setShift(true);
          break;
        case 'C':
          if (i + 1 < text.length() && text.charAt(i + 1) == 'H') {
            car = new charH();
            car.setShift(true);
            i++;
          } else {
            car = new charC();
          }
          break;
        case 'R':
          car = new charR();
          if (i + 1 < text.length() && text.charAt(i + 1) == 'R') {
            car.setShift(true);
            i++;
          }
          break;
        case 'L':
          car = new charL();
          if (i + 1 < text.length() && text.charAt(i + 1) == 'L') {
            car.setShift(true);
            i++;
          }
          break;
        default:
          try {
            String className = "cuadrigrafia.char" + c;
            car = (Character)Class.forName(className).newInstance();
          } catch(Exception e) {
            e.printStackTrace(System.err);
          }
      }
      if (car != null) {
        codes.addElement(car);
        if (horizPack. indexOf(c) < 0) {
          xCompactable = false;
        }
        if (vertPack. indexOf(c) < 0) {
          yCompactable = false;
        }
      }
    }
    checkMultiplicity();
    checkReferences();
  }

  public int length() {
    return codes.size();
  }

  public Character characterAt(int i) {
    return (Character) codes.elementAt(i);
  }

  public Character lastCharacter() {
    return (Character) codes.lastElement();
  }

  public boolean multiple(int i) {
    return multiple[i];
  }

  public int multIndex(int i) {
    return multIndex[i];
  }

  private void checkMultiplicity() {
    multiple = new boolean[length()];
    multIndex = new int[length()];
    for (int i = 0; i < length(); i++) {
      char c = characterAt(i).glyph();
      for (int j = i + 1; j < length(); j++) {
        if (c == characterAt(j).glyph()) {
          multiple[i] = true;
          multiple[j] = true;
          multIndex[j]++;
        }
      }
    }
  }

  private void checkReferences() {
    reference = new boolean[ReferencesNumber];
    for (int i = 0; i < length(); i++) {
      if (length() == 1) {
        reference[characterAt(i).getReference()] = true;
      } else {
        char c = characterAt(i).glyph();
        if ("AEIO".indexOf(c) < 0) {
          reference[characterAt(i).getReference()] = true;
        }
      }
    }
  }

  public boolean isXcompactable() {
    return xCompactable;
  }

  public boolean isYcompactable() {
    return yCompactable;
  }

  public boolean hasReference(int i) {
    return reference[i];
  }

}

