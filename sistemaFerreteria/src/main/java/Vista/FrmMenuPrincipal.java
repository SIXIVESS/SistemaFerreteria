package Vista;


public class FrmMenuPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form FrmMenuPrincipal
     */
    public FrmMenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblIniciar = new javax.swing.JButton();
        lblSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(243, 233, 207));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 70)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(40, 114, 113));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ferretería");

        lblIniciar.setBackground(new java.awt.Color(42, 157, 143));
        lblIniciar.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        lblIniciar.setForeground(new java.awt.Color(255, 255, 255));
        lblIniciar.setText("INICIAR SESIÓN");

        lblSalir.setBackground(new java.awt.Color(231, 111, 81));
        lblSalir.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        lblSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblSalir.setText("SALIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIniciar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblSalir)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(53, 53, 53)
                .addComponent(lblIniciar)
                .addGap(31, 31, 31)
                .addComponent(lblSalir)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 60, 660, 450));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo1.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 570));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lblIniciar;
    private javax.swing.JButton lblSalir;
    // End of variables declaration//GEN-END:variables
}
