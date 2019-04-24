/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

import java.awt.Graphics;                                   // For Graphics
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Color;
import java.awt.geom.*;
import javax.swing.JComponent;
import cuadrilib.*;

/**
 *
 * @author Danel
 */
public class Cuadrigrama extends JComponent implements Constants {
  private SagitWizard sagitWizard;
  private Grafe grafe;
  private FastVector graphicsToDraw;
  private FastVector graphicsToFill;

  public Cuadrigrama(SagitWizard sw, Grafe g) {
    sagitWizard = sw;
    grafe = g;
    setSize(defaultGrafeWidth, defaultGrafeHeight);
    buildGraphic();
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    for (int i = 0; i < graphicsToDraw.size(); i++) {
      Feature f = (Feature)graphicsToDraw.elementAt(i);
      Shape s = f.getShape();
      g2D.setPaint(f.color());
      g2D.setStroke(new BasicStroke(f.width(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
      g2D.draw(s);
    }
    for (int i = 0; i < graphicsToFill.size(); i++) {
      Feature f = (Feature)graphicsToFill.elementAt(i);
      Shape s = f.getShape();
      g2D.setPaint(f.color());
      g2D.fill(s);
    }
  }

  private void buildGraphic() {
    graphicsToDraw = new FastVector();
    graphicsToFill = new FastVector();
    buildReferences();
    buildArcs();
    builEndPoint();
  }
  
  /**
   * Construir los enlaces
   */
  private void buildArcs() {
    // Si el grafe sólo tiene una letra no hay arco que hacer
    if (grafe.length() == 1)  return;

    for (int i = 0; i < grafe.length() - 1; i++) {
      Character iniC = grafe.characterAt(i);
      Character endC = grafe.characterAt(i + 1);
      Arc a = sagitWizard.getArc(iniC, endC, getWidth(), getHeight());
      if (a != null)
        graphicsToDraw.addElement(a);
    }
  }

  /**
   * Construir la bolita que indica la fin del grafe
   */
  private void builEndPoint() {
    Character car = grafe.lastCharacter();
    boolean vocal = "AEIOU".indexOf(car.glyph()) >= 0;
    if (car.glyph() == 'U' || grafe.length() == 1 && vocal)
      return; // No poner bolita cuando el grafo sólo contiene una letra y ésta es vocal
    float xAdj;
    float yAdj;
    if (vocal) {
      xAdj = car.xAdjDir() * EndDot.radius;
      yAdj = car.yAdjDir() * EndDot.radius;
    } else {
      xAdj = car.xAdjDir() * (EndDot.radius + referenceWidth);
      yAdj = car.yAdjDir() * (EndDot.radius + referenceWidth);
    }
    float x = (float)car.xPos() * getWidth() + xAdj;
    float y = (float)car.yPos() * getHeight() + yAdj;
    Feature f = new EndDot(x, y);
    graphicsToFill.addElement(f);
  }

  /**
   * Construir las referencias
   */ 
  private void buildReferences() {
    for (int i = 0; i < ReferencesNumber; i++) {
      if (grafe.hasReference(i)) {
        Feature f;
        switch (i) {
          /**
           * NO SE HA TENIDO EN CUENTA EL CASO EN QUE EL CUADRIGRAMA ES COMPACTABLE
           */
          case refLeft:
            f = new Reference(halfRefWidth, (float)mark1 * getHeight(),
                    halfRefWidth, (float)mark3 * getHeight());
            graphicsToDraw.addElement(f);
            break;
          case refRight:
            f = new Reference(getWidth() - halfRefWidth, (float)mark1 * getHeight(),
                    getWidth() - halfRefWidth, (float)mark3 * getHeight());
            graphicsToDraw.addElement(f);
            break;
          case refBottom:
            f = new Reference((float)mark1 * getWidth(), getHeight() - halfRefWidth,
                    (float)mark3 * getWidth(), getHeight() - halfRefWidth);
            graphicsToDraw.addElement(f);
            break;
          case refTop:
            f = new Reference((float)mark1 * getWidth(), halfRefWidth,
                    (float)mark3 * getWidth(), halfRefWidth);
            graphicsToDraw.addElement(f);
            break;
          case refBottomLeft:
            f = new Reference(halfRefWidth, (float)mark3_4 * getHeight(),
                    halfRefWidth, getHeight());
            graphicsToDraw.addElement(f);
            f = new Reference(halfRefWidth, getHeight() - halfRefWidth,
                    (float)mark0_1 * getWidth(), getHeight() - halfRefWidth);
            graphicsToDraw.addElement(f);
            break;
          case refBottomRight:
            f = new Reference((float)mark3_4 * getWidth(), getHeight() - halfRefWidth,
                    getWidth() - halfRefWidth, getHeight() - halfRefWidth);
            graphicsToDraw.addElement(f);
            f = new Reference(getWidth() - halfRefWidth, (float)mark3_4 * getHeight(),
                    getWidth() - halfRefWidth, getHeight());
            graphicsToDraw.addElement(f);
            break;
          case refTopLeft:
            f = new Reference(halfRefWidth, halfRefWidth,
                    (float)mark0_1 * getWidth(), halfRefWidth);
            graphicsToDraw.addElement(f);
            f = new Reference(halfRefWidth, halfRefWidth,
                    halfRefWidth, (float)mark0_1 * getHeight());
            graphicsToDraw.addElement(f);
            break;
          case refTopRight:
            f = new Reference((float)mark3_4 * getWidth(), halfRefWidth,
                    getWidth() - halfRefWidth, halfRefWidth);
            graphicsToDraw.addElement(f);
            f = new Reference(getWidth() - halfRefWidth, halfRefWidth,
                    getWidth() - halfRefWidth, (float)mark0_1 * getHeight());
            graphicsToDraw.addElement(f);
            break;
          case refCentral:
            f = new CentralRef(getWidth() / 2, getHeight() / 2);
            graphicsToDraw.addElement(f);
        }
      }
    }
  }

}
