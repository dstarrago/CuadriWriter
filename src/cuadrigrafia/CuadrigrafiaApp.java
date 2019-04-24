/*
 * CuadrigrafiaApp.java
 */

package cuadrigrafia;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class CuadrigrafiaApp extends SingleFrameApplication {

  private CuadrigrafiaView view;
  private Source source;
  public SagitWizard sagitWizard = new SagitWizard();

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
      view = new CuadrigrafiaView(this);
      show(view);
    }

    public CuadrigrafiaView getView() {
      return view;
    }

    public void setSource(Source s) {
      source = s;
    }

    public Source source() { return source; }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of CuadrigrafiaApp
     */
    public static CuadrigrafiaApp getApplication() {
        return Application.getInstance(CuadrigrafiaApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(CuadrigrafiaApp.class, args);
    }
}
