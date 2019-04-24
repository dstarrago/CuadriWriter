/*
 * CuadrigrafiaView.java
 */

package cuadrigrafia;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class CuadrigrafiaView extends FrameView {

  private Composer canvas;
  private CuadrigrafiaApp cuadriApp;
  private int m_fontSize;
  private int m_traceWidth;

  private void initCanvas(CuadrigrafiaApp app) {
    canvas = new Composer(app);
    canvas.setMaximumSize(new java.awt.Dimension(1000, 32767));
    canvas.setMinimumSize(new java.awt.Dimension(10, 100));
    canvas.setName("paintBox"); // NOI18N
    canvas.setPreferredSize(new java.awt.Dimension(10, 220));

    javax.swing.GroupLayout paintBoxLayout = new javax.swing.GroupLayout(canvas);
    canvas.setLayout(paintBoxLayout);
    paintBoxLayout.setHorizontalGroup(
      paintBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 237, Short.MAX_VALUE)
    );
    paintBoxLayout.setVerticalGroup(
      paintBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 220, Short.MAX_VALUE)
    );

    jScrollPane2.setViewportView(canvas);
  }

  public Composer getCanvas() {
    return canvas;
  }

  public CuadrigrafiaView(SingleFrameApplication app) {
        super(app);
        cuadriApp = (CuadrigrafiaApp)app;
        initComponents();
        initCanvas(cuadriApp);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CuadrigrafiaApp.getApplication().getMainFrame();
            aboutBox = new CuadrigrafiaAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CuadrigrafiaApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    inputText = new javax.swing.JTextArea();
    jSeparator1 = new javax.swing.JSeparator();
    jScrollPane2 = new javax.swing.JScrollPane();
    jToolBar1 = new javax.swing.JToolBar();
    jButton1 = new javax.swing.JButton();
    jSeparator2 = new javax.swing.JToolBar.Separator();
    jLabel1 = new javax.swing.JLabel();
    fontSize = new javax.swing.JSpinner();
    jLabel2 = new javax.swing.JLabel();
    traceWidth = new javax.swing.JSpinner();
    menuBar = new javax.swing.JMenuBar();
    javax.swing.JMenu fileMenu = new javax.swing.JMenu();
    javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
    javax.swing.JMenu helpMenu = new javax.swing.JMenu();
    javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
    statusPanel = new javax.swing.JPanel();
    javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
    statusMessageLabel = new javax.swing.JLabel();
    statusAnimationLabel = new javax.swing.JLabel();
    progressBar = new javax.swing.JProgressBar();

    mainPanel.setName("mainPanel"); // NOI18N
    mainPanel.setLayout(new java.awt.BorderLayout());

    jPanel1.setName("jPanel1"); // NOI18N
    jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    inputText.setColumns(1);
    inputText.setLineWrap(true);
    inputText.setRows(5);
    inputText.setMinimumSize(new java.awt.Dimension(200, 900));
    inputText.setName("inputText"); // NOI18N
    inputText.setPreferredSize(new java.awt.Dimension(500, 94));
    jScrollPane1.setViewportView(inputText);

    jPanel1.add(jScrollPane1);

    jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jSeparator1.setAutoscrolls(true);
    jSeparator1.setMaximumSize(new java.awt.Dimension(2, 32767));
    jSeparator1.setMinimumSize(new java.awt.Dimension(2, 0));
    jSeparator1.setName("jSeparator1"); // NOI18N
    jPanel1.add(jSeparator1);

    jScrollPane2.setName("jScrollPane2"); // NOI18N
    jPanel1.add(jScrollPane2);

    mainPanel.add(jPanel1, java.awt.BorderLayout.CENTER);

    jToolBar1.setRollover(true);
    jToolBar1.setMaximumSize(new java.awt.Dimension(13, 30));
    jToolBar1.setMinimumSize(new java.awt.Dimension(13, 30));
    jToolBar1.setName("jToolBar1"); // NOI18N
    jToolBar1.setPreferredSize(new java.awt.Dimension(100, 30));

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(cuadrigrafia.CuadrigrafiaApp.class).getContext().getActionMap(CuadrigrafiaView.class, this);
    jButton1.setAction(actionMap.get("Transcribir")); // NOI18N
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(cuadrigrafia.CuadrigrafiaApp.class).getContext().getResourceMap(CuadrigrafiaView.class);
    jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
    jButton1.setFocusable(false);
    jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton1.setName("jButton1"); // NOI18N
    jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(jButton1);

    jSeparator2.setName("jSeparator2"); // NOI18N
    jToolBar1.add(jSeparator2);

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jLabel1.setMaximumSize(new java.awt.Dimension(130, 14));
    jLabel1.setMinimumSize(new java.awt.Dimension(120, 14));
    jLabel1.setName("jLabel1"); // NOI18N
    jLabel1.setPreferredSize(new java.awt.Dimension(125, 14));
    jToolBar1.add(jLabel1);

    fontSize.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));
    fontSize.setMaximumSize(new java.awt.Dimension(62, 32767));
    fontSize.setName("fontSize"); // NOI18N
    fontSize.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        fontSizeStateChanged(evt);
      }
    });
    jToolBar1.add(fontSize);

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setMaximumSize(new java.awt.Dimension(100, 14));
    jLabel2.setMinimumSize(new java.awt.Dimension(77, 14));
    jLabel2.setName("jLabel2"); // NOI18N
    jLabel2.setPreferredSize(new java.awt.Dimension(84, 14));
    jToolBar1.add(jLabel2);

    traceWidth.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
    traceWidth.setMaximumSize(new java.awt.Dimension(62, 32767));
    traceWidth.setName("traceWidth"); // NOI18N
    traceWidth.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        traceWidthStateChanged(evt);
      }
    });
    jToolBar1.add(traceWidth);

    mainPanel.add(jToolBar1, java.awt.BorderLayout.NORTH);

    menuBar.setName("menuBar"); // NOI18N

    fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
    fileMenu.setName("fileMenu"); // NOI18N

    exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
    exitMenuItem.setName("exitMenuItem"); // NOI18N
    fileMenu.add(exitMenuItem);

    menuBar.add(fileMenu);

    helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
    helpMenu.setName("helpMenu"); // NOI18N

    aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
    aboutMenuItem.setName("aboutMenuItem"); // NOI18N
    helpMenu.add(aboutMenuItem);

    menuBar.add(helpMenu);

    statusPanel.setName("statusPanel"); // NOI18N

    statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

    statusMessageLabel.setName("statusMessageLabel"); // NOI18N

    statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

    progressBar.setName("progressBar"); // NOI18N

    javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
    statusPanel.setLayout(statusPanelLayout);
    statusPanelLayout.setHorizontalGroup(
      statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
      .addGroup(statusPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(statusMessageLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(statusAnimationLabel)
        .addContainerGap())
    );
    statusPanelLayout.setVerticalGroup(
      statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(statusPanelLayout.createSequentialGroup()
        .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(statusMessageLabel)
          .addComponent(statusAnimationLabel)
          .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(3, 3, 3))
    );

    setComponent(mainPanel);
    setMenuBar(menuBar);
    setStatusBar(statusPanel);
  }// </editor-fold>//GEN-END:initComponents

    private void fontSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fontSizeStateChanged
      // TODO add your handling code here:
      m_fontSize = (Integer)(fontSize.getModel().getValue());
     canvas.repaint();
}//GEN-LAST:event_fontSizeStateChanged

    private void traceWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_traceWidthStateChanged
      // TODO add your handling code here:
      m_traceWidth = (Integer)(traceWidth.getModel().getValue());
      canvas.repaint();
    }//GEN-LAST:event_traceWidthStateChanged

    public int getFontSize() {
      return m_fontSize;
    }

    public int getTraceWidth() {
      return m_traceWidth;
    }

  @Action
  public void Transcribir() {
    Source s = new Source(inputText.getText());
    cuadriApp.setSource(s);
    canvas.setSource(s);
    m_fontSize = (Integer)(fontSize.getModel().getValue());
    canvas.repaint();
    /*
    Graphics g = paintBox.getGraphics();
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.BLACK);
    g2D.draw(new Line2D.Double(0, 0, paintBox.getWidth(), paintBox.getHeight()));
     */
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JSpinner fontSize;
  private javax.swing.JTextArea inputText;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JToolBar.Separator jSeparator2;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JProgressBar progressBar;
  private javax.swing.JLabel statusAnimationLabel;
  private javax.swing.JLabel statusMessageLabel;
  private javax.swing.JPanel statusPanel;
  private javax.swing.JSpinner traceWidth;
  // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
