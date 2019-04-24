/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cuadrigrafia;

import java.awt.geom.Point2D;
import cuadrilib.Arc;

/**
 *
 * @author Danel
 */
public class SagitWizard {
  private float Izquierda = -1;
  private float mIzquierda = -0.5f;
  private float Derecha = 1;
  private float mDerecha = 0.5f;
  private float  Arriba = -1;
  private float  mArriba = -0.5f;
  private float  Abajo = 1;
  private float  mAbajo = 0.5f;
  private float  NoAction = 0;
  
  private FastVector knowledge = new FastVector();

  public SagitWizard() {
    buildKnowledgeBase();
  }

  public Arc getArc(Character iniC, Character endC, int scaleX, int scaleY) {
    Point2D.Float iniP = new Point2D.Float((float)iniC.xPos() * scaleX, (float)iniC.yPos() * scaleY);
    Point2D.Float endP = new Point2D.Float((float)endC.xPos() * scaleX, (float)endC.yPos() * scaleY);
    String id = String.valueOf(iniC.glyph()) + String.valueOf(endC.glyph());
    float D = (float)Math.abs(Math.abs(endC.xPos() - iniC.xPos()) - Math.abs(endC.yPos() - iniC.yPos()));
    if (D < 0.01) D = 0.25f;
    float cx = (iniP.x + endP.x) / 2;
    float cy = (iniP.y + endP.y) / 2;
    Fact f = findExFact(id);
    //float lon = (float)Math.sqrt((iniP.x - endP.x) * (iniP.x - endP.x) + (iniP.y - endP.y) * (iniP.y - endP.y));
    if (f == null) return null;
    float dx = scaleX * D * f.hAction() / 2;
    float dy = scaleY * D * f.vAction() / 2;
    Point2D.Float midP = new Point2D.Float(cx + dx, cy + dy);
    return new Arc(iniP, midP, endP);
  }

  private void addFact(String id, float hAction, float vAction) {
    knowledge.addElement(new Fact(id, hAction, vAction));
  }

  private void addFact(Fact f) {
    knowledge.addElement(f);
  }
  
  private Fact fact(int i) {
    return (Fact)knowledge.elementAt(i);
  }

  private Fact findExFact(String id) {
    Fact f = findFact(id);
    if (f == null) {
      String invId = id.substring(1) + id.substring(0, 1);
      f = findFact(invId);
    }
    return f;
  }

  private Fact findFact(String id) {
    Fact f = null;
    for (int i = 0; i < knowledge.size(); i++) {
      f = fact(i);
      if (f.id().equals(id)) {
        return f;
      }
    }
    return null;
  }
  
  public class Fact {
    private String id;
    private float hAction;
    private float vAction;
    private float althAction;
    private float altvAction;
    private boolean multiple;

    public Fact(String id, float hAction, float vAction) {
      this.id = id;
      this.hAction = hAction;
      this.vAction = vAction;
    }

    public void setAlternative(float hAction, float vAction) {
      althAction = hAction;
      altvAction = vAction;
      multiple = true;
    }

    public String id() { return id;}
    public float hAction() { return hAction; }
    public float vAction() { return vAction; }
    public float althAction() { return althAction; }
    public float altvAction() { return altvAction; }
    public boolean hasChoice() { return multiple; }
  }

  private void buildKnowledgeBase() {
    Fact f;
    addFact("AB", Derecha, mArriba);
    addFact("AC", Derecha, mArriba);
    addFact("AD", Derecha, mArriba);
    addFact("AE", mDerecha, NoAction);
    addFact("AF", mDerecha, NoAction);
    addFact("AG", mDerecha, mAbajo);
    addFact("AH", mDerecha, mAbajo);
    f = new Fact("AI", Izquierda, Arriba);
    f.setAlternative(Derecha, Abajo);
    addFact(f);
    addFact("AJ", mIzquierda, Arriba);
    addFact("AS", mIzquierda, mArriba);
    addFact("AL", NoAction, mArriba);
    addFact("AO", NoAction, Arriba);
    addFact("AM", mDerecha, Arriba);
    addFact("AN", mDerecha, Arriba);
    addFact("AP", mDerecha, Arriba);
    f = new Fact("AQ", mDerecha, NoAction);
    f.setAlternative(NoAction, mArriba);
    addFact(f);
    addFact("AR", Derecha, NoAction);
    addFact("AK", mDerecha, NoAction);
    addFact("AT", Derecha, NoAction);
    f = new Fact("AV", Izquierda, Arriba);
    f.setAlternative(Derecha, Abajo);
    addFact(f);
    addFact("AX", NoAction, Arriba);
    addFact("AY", NoAction, mArriba);
    addFact("AZ", NoAction, Arriba);
    f = new Fact("AU", mDerecha, mAbajo);
    f.setAlternative(mIzquierda, mArriba);
    addFact(f);

    addFact("EF", mDerecha, Abajo);
    addFact("EG", mDerecha, Abajo);
    addFact("EH", mDerecha, Abajo);
    addFact("EI", NoAction, mAbajo);
    addFact("EJ", NoAction, mAbajo);
    addFact("ES", mIzquierda, mAbajo);
    addFact("EL", mIzquierda, Abajo);
    f = new Fact("EO", Derecha, Arriba);
    f.setAlternative(Izquierda, Abajo);
    addFact(f);
    addFact("EM", Derecha, mAbajo);
    addFact("EN", mDerecha, mArriba);
    addFact("EP", mDerecha, NoAction);
    addFact("EA", Derecha, NoAction);
    addFact("EB", Derecha, mAbajo);
    addFact("EC", Derecha, mAbajo);
    addFact("ED", Derecha, mAbajo);
    addFact("EQ", mDerecha, NoAction);
    addFact("ER", Derecha, NoAction);
    f = new Fact("EK", mDerecha, NoAction);
    f.setAlternative(NoAction, mAbajo);
    addFact(f);
    addFact("ET", NoAction, Abajo);
    addFact("EV", NoAction, mAbajo);
    addFact("EX", NoAction, Abajo);
    f = new Fact("EY", Derecha, Arriba);
    f.setAlternative(Izquierda, Abajo);
    addFact(f);
    addFact("EZ", Derecha, NoAction);
    f = new Fact("EU", mDerecha, mArriba);
    f.setAlternative(mIzquierda, mAbajo);
    addFact(f);

    addFact("IJ", Izquierda, mAbajo);
    addFact("IS", Izquierda, mAbajo);
    addFact("IL", Izquierda, mAbajo);
    addFact("IO", mIzquierda, NoAction);
    addFact("IM", mIzquierda, NoAction);
    addFact("IN", mIzquierda, mArriba);
    addFact("IP", mIzquierda, mArriba);
    f = new Fact("IA", Derecha, Abajo);
    f.setAlternative(Izquierda, Arriba);
    addFact(f);
    addFact("IB", mDerecha, mAbajo);
    addFact("IC", mDerecha, mAbajo);
    addFact("ID", NoAction, mAbajo);
    addFact("IE", NoAction, Abajo);
    addFact("IF", mIzquierda, Abajo);
    addFact("IG", mIzquierda, Abajo);
    addFact("IH", mIzquierda, Abajo);
    f = new Fact("IQ", Derecha, Abajo);
    f.setAlternative(Izquierda, Arriba);
    addFact(f);
    addFact("IR", NoAction, Abajo);
    addFact("IK", NoAction, mAbajo);
    addFact("IT", NoAction, Abajo);
    f = new Fact("IV", mIzquierda, NoAction);
    f.setAlternative(NoAction, mAbajo);
    addFact(f);
    addFact("IX", Izquierda, NoAction);
    addFact("IY", mIzquierda, NoAction);
    addFact("IZ", Izquierda, NoAction);
    f = new Fact("IU", mDerecha, mAbajo);
    f.setAlternative(mIzquierda, mArriba);
    addFact(f);
    
    addFact("OM", mIzquierda, Arriba);
    addFact("ON", mIzquierda, Arriba);
    addFact("OP", mIzquierda, Arriba);
    addFact("OA", NoAction, mArriba);
    addFact("OB", NoAction, mArriba);
    addFact("OC", mDerecha, mArriba);
    addFact("OD", mDerecha, mArriba);
    f = new Fact("OE", Derecha, Arriba);
    f.setAlternative(Izquierda, Abajo);
    addFact(f);
    addFact("OF", mIzquierda, mAbajo);
    addFact("OG", mIzquierda, mAbajo);
    addFact("OH", mIzquierda, NoAction);
    addFact("OI", Izquierda, NoAction);
    addFact("OJ", Izquierda, mArriba);
    addFact("OS", Izquierda, mArriba);
    addFact("OL", Izquierda, mArriba);
    addFact("OQ", NoAction, mArriba);
    addFact("OR", NoAction, Arriba);
    f = new Fact("OK", Derecha, Arriba);
    f.setAlternative(Izquierda, Abajo);
    addFact(f);
    addFact("OT", Izquierda, NoAction);
    addFact("OV", mIzquierda, NoAction);
    addFact("OX", Izquierda, NoAction);
    f = new Fact("OY", NoAction, mArriba);
    f.setAlternative(mIzquierda, NoAction);
    addFact(f);
    addFact("OZ", NoAction, Arriba);
    f = new Fact("OU", mDerecha, mArriba);
    f.setAlternative(mIzquierda, mAbajo);
    addFact(f);
    
    addFact("CB", Derecha + 0.5F, NoAction);
    addFact("CD", Derecha + 0.5F, NoAction);
    addFact("CF", mDerecha, Abajo);
    addFact("CG", mDerecha, Abajo);
    addFact("CH", Derecha, Abajo);
    addFact("CJ", mIzquierda, NoAction);
    f = new Fact("CS", NoAction, mArriba / 2);
    f.setAlternative(NoAction, mAbajo / 2);
    addFact(f);
    addFact("CL", Izquierda, Arriba);
    addFact("CM", Derecha, Arriba);
    addFact("CN", Derecha, Arriba);
    addFact("CP", Derecha, Arriba);
    addFact("CQ", Derecha, Arriba);
    f = new Fact("CR", mDerecha, mArriba);
    f.setAlternative(mDerecha, mAbajo);
    addFact(f);
    addFact("CK", Derecha, Abajo);
    addFact("CT", Derecha, Abajo);
    addFact("CV", Derecha, Abajo);
    f = new Fact("CX", mDerecha, mArriba);
    f.setAlternative(mDerecha, mAbajo);
    addFact(f);
    addFact("CY", Derecha, Arriba);
    addFact("CZ", Derecha, Arriba);
    f = new Fact("CU", mDerecha, mArriba);
    f.setAlternative(mDerecha, mAbajo);
    addFact(f);
/*
    addFact("SB", , );
    addFact("SD", , );
    addFact("SF", , );
    addFact("SG", , );
    addFact("SH", , );
    addFact("SJ", , );
    addFact("SS", , );
    addFact("SL", , );
    addFact("SM", , );
    addFact("SN", , );
    addFact("SP", , );
    addFact("SQ", , );
    addFact("SR", , );
    addFact("SK", , );
    addFact("ST", , );
    addFact("SV", , );
    addFact("SX", , );
    addFact("SY", , );
    addFact("SZ", , );
    addFact("SU", , );

    addFact("GB", , );
    addFact("GD", , );
    addFact("GF", , );
    addFact("GG", , );
    addFact("GH", , );
    addFact("GJ", , );
    addFact("GS", , );
    addFact("GL", , );
    addFact("GM", , );
    addFact("GN", , );
    addFact("GP", , );
    addFact("GQ", , );
    addFact("GR", , );
    addFact("GK", , );
    addFact("GT", , );
    addFact("GV", , );
    addFact("GX", , );
    addFact("GY", , );
    addFact("GZ", , );
    addFact("GU", , );

    addFact("NB", , );
    addFact("ND", , );
    addFact("NF", , );
    addFact("NG", , );
    addFact("NH", , );
    addFact("NJ", , );
    addFact("NS", , );
    addFact("NL", , );
    addFact("NM", , );
    addFact("NN", , );
    addFact("NP", , );
    addFact("NQ", , );
    addFact("NR", , );
    addFact("NK", , );
    addFact("NT", , );
    addFact("NV", , );
    addFact("NX", , );
    addFact("NY", , );
    addFact("NZ", , );
    addFact("NU", , );
 */
  }
}
