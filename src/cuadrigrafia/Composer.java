/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

import java.util.Observer;
import java.util.Observable;
//import javax.swing.event.MouseInputAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 *
 * @author Danel
 */
public class Composer  extends JComponent implements Observer, Constants {
  private CuadrigrafiaApp app;
  private Source source;
  private int cursorX;
  private int cursorY;

  public Composer(CuadrigrafiaApp theApp) {
    app = theApp;
  }

  // Method called by Observable object when it changes
  public void update(Observable o, Object rectangle) {
    // Code to respond to changes in the model...
    repaint();
  }

  public void setSource(Source s) {
    source = s;
    cursorX = 0;
    cursorY = 0;
    removeAll();
    buildComponents();
  }

  private void buildComponents() {
    for (int i = 0; i < source.length(); i++) {
      Word w = source.wordAt(i);
      for (int j = 0; j < w.length(); j++) {
        Grafe g = w.grafeAt(j);
        Cuadrigrama cuad = new Cuadrigrama(app.sagitWizard, g);
        add(cuad);
        cuad.setLocation(cursorX, cursorY);
        cursorX += cuad.getWidth();
        if (cursorX > getWidth()) {
          cursorX = 0;
          cursorY += cuad.getHeight();
        }
      }
    }
  }

/*
  @Override
  public void paint(Graphics g) {
    source = app.source();
    if (source == null || source.empty()) return;
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.BLACK);
    g2D.setStroke(new BasicStroke(app.getView().getTraceWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    g2D.drawString(recompone(), 20, 20);
  }
*/
}
