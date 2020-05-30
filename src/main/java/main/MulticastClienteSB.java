package main;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MulticastClienteSB extends javax.swing.JFrame {
    public static final String MCAST_ADDR  = "230.0.0.1"; //dir clase D valida, grupo al que nos vamos a unir
    public static final int MCAST_PORT = 4000;//puerto multicast
    public static final int DGRAM_BUF_LEN=40000; //tamaño del buffer
    public static String nickname = "";
    public static MulticastSocket s_1;
    public static ArrayList <String> usuariosConectados = new ArrayList<>();
    public static InetAddress group;
    
    public MulticastClienteSB() {
        try {
            //Conectandose al canal
            nickname = JOptionPane.showInputDialog("Ingrese su nickname");
            s_1 = new MulticastSocket(MCAST_PORT);
            s_1.setReuseAddress(true);
            s_1.setTimeToLive(255);
            String conexion = "<inicio>"+nickname;
            byte[] b_1 = conexion.getBytes();
            DatagramPacket p = new DatagramPacket(b_1,b_1.length,group,MCAST_PORT);
            s_1.send(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        txtEstado.setText(nickname);
        mostrarUsuarios();
        String []tipo = {"Publico", "Privado"};
        comboTipo.setModel (new DefaultComboBoxModel <>(tipo));
        TextPane.setContentType("text/html");
    }

    public static void mostrarUsuarios (){
        int tamanio = usuariosConectados.size();
        String [][]users = new String [tamanio][1];
        for (int i = 0; i < tamanio; i++){
            users [i][0] = usuariosConectados.get(i);
        }
        String []tipo = new String [tamanio];
        for (int i = 0; i < tamanio;i++){
            tipo [i] = usuariosConectados.get(i);
        }
        if (usuariosConectados.size() > 0){
            tableUsuarios.setModel(new DefaultTableModel(users, new String []{"Nombre usuario"}));
            comboPrivado.setModel (new DefaultComboBoxModel <>(tipo));
        }
    }
    
    public static void eliminaUsuario (String deleteUser){
        for (int i = 0; i < usuariosConectados.size(); i++){
            if (deleteUser.equals(usuariosConectados.get(i))){
                usuariosConectados.remove(i);
                break;
            }
        }
        mostrarUsuarios();
    }
    
    public static void finChat (){
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextPane = new javax.swing.JTextPane();
        btonEnviar = new javax.swing.JButton();
        textEnviar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        txtEstado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        comboPrivado = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(TextPane);

        btonEnviar.setText("Enviar");
        btonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEnviarActionPerformed(evt);
            }
        });

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableUsuarios);

        txtEstado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtEstado.setEnabled(false);

        jLabel1.setText("Nickname:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboPrivado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin usuarios" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btonEnviar)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(comboPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btonEnviar)
                            .addComponent(textEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEnviarActionPerformed
        // TODO add your handling code here:
        String datosNuevos = textEnviar.getText();
        String type = (String)comboTipo.getSelectedItem();
        try {
            if (datosNuevos.equals("SALIR")){
                String dato = "<fin>"+nickname;
                    byte []b_2 = dato.getBytes();
                    DatagramPacket p = new DatagramPacket(b_2,b_2.length,group,MCAST_PORT);
                    s_1.send(p);
            }else{
                if (type.equals("Privado")){
                    String persona = (String)comboPrivado.getSelectedItem();
                    String dato = "<privado><"+nickname+"><"+persona+">"+datosNuevos;
                    System.out.println(dato);
                    byte []b_2 = dato.getBytes();
                    DatagramPacket p = new DatagramPacket(b_2,b_2.length,group,MCAST_PORT);
                    s_1.send(p);
                }else{
                    String dato = "<msj><"+nickname+">"+datosNuevos;
                    byte []b_2 = dato.getBytes();
                    DatagramPacket p = new DatagramPacket(b_2,b_2.length,group,MCAST_PORT);
                    s_1.send(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textEnviar.setText("");
    }//GEN-LAST:event_btonEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MulticastClienteSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MulticastClienteSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MulticastClienteSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MulticastClienteSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MulticastClienteSB().setVisible(true);
            }
        });
                            
    try {          
        
	try {
            group = InetAddress.getByName(MCAST_ADDR);
        } catch (Exception e) {
            e.printStackTrace();
	}
            String cadenaPane = "";
            while (true){
                System.out.println("Esperando a usuario");
                byte[] buf = new byte[DGRAM_BUF_LEN];//crea arreglo de bytes
                MulticastSocket socket = new MulticastSocket(MCAST_PORT);
                socket.joinGroup(group); // se configura para escuchar el paquete
                DatagramPacket recv = new DatagramPacket(buf,buf.length);//Crea el datagrama del paquete a recibir
                socket.receive(recv);//Ya se tiene el datagram packet
                
                String eco = "";
                eco = new String (recv.getData());
                eco = eco.replace(":o", "<img src=\"https://images.emojiterra.com/google/android-10/share/1f618.jpg\" width=40 height=40/>");
                eco = eco.replace(";)", "<img src=\"https://images.emojiterra.com/google/android-10/share/1f60a.jpg\" width=30 height=30/>");
                eco = eco.replace(":S", "<img src=\"https://images.emojiterra.com/google/android-10/share/1f974.jpg\" width=30 height=30/>");
                eco = eco.replace("_Perro_", "<img src=\"https://ih1.redbubble.net/image.465654661.2703/flat,750x,075,f-pad,750x1000,f8f8f8.u5.jpg\" width=100 height=100/>");
                eco = eco.replace("_Homero_", "<img src=\"http://tusimagenesde.com/wp-content/uploads/2015/01/gifs-animados-5.gif\"/>");
                if (eco.contains("<inicio>")&&!eco.contains("<inicio>"+nickname)){
                    String usuario = eco.replace("<inicio>", "");
                    boolean verifica = false;
                    int tamanio = usuariosConectados.size();
                    for (int j = 0; j < tamanio; j++){
                        if (usuariosConectados.get(j).equals(usuario)){
                            verifica = true;
                        }
                    }
                    if (!verifica){
                        System.out.println("User: "+usuario);
                        usuariosConectados.add(usuario);
                        mostrarUsuarios();
                        cadenaPane += usuario+" se ha conectado.<br/>";
                        TextPane.setText (cadenaPane);
                    }
                }else if (eco.contains("<msj>")){
                            eco = eco.replace("<msj>", "").replaceFirst("<", "").replaceFirst(">", ": ")+"<br />";
                            cadenaPane += eco;
                            String name = eco.substring(0,eco.indexOf(":"));
                            boolean verifica = false;
                            int tamanio = usuariosConectados.size();
                            System.out.println("Nombre de user: "+name);
                            if (!name.contains(nickname)){
                                for (int j = 0; j < tamanio; j++){
                                    if (usuariosConectados.get(j).equals(name)){
                                        verifica = true;
                                    }
                                }
                            }else{
                                verifica = true;
                            }
                            if (!verifica){
                                usuariosConectados.add(name);
                                mostrarUsuarios();
                            }
                            eco = "";
                            TextPane.setText(cadenaPane);
                        }else if (eco.contains("<privado>")&&eco.contains("<"+nickname+">")){
                            System.out.println(eco);
                            eco = eco.replace("<privado>", "");
                            eco = eco.replaceFirst("<", "");
                            eco = eco.replaceFirst(">", ": ");
                            System.out.println(eco);
                            int first = eco.indexOf("<");
                            int second = eco.indexOf(">")+1;
                            if (second  == 0){
                                second = eco.indexOf(">");
                            } 
                            String name = eco.substring(first, second);
                            System.out.println(eco);
                            eco = eco.replace(name, "");
                            eco = eco+"<br />";
                            cadenaPane += eco;
                            eco = "";
                            TextPane.setText (cadenaPane);
                        }else if(eco.contains ("<fin>")){
                            String user = eco.replace("<fin>", "");
                            if (user.contains(nickname)){
                                System.exit(0);
                            }else{
                                eliminaUsuario(user);
                                cadenaPane += user+" se ha desconectado.<br/>";
                                TextPane.setText (cadenaPane);
                            }
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }              
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextPane TextPane;
    private javax.swing.JButton btonEnviar;
    private static javax.swing.JComboBox<String> comboPrivado;
    private static javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private static javax.swing.JTable tableUsuarios;
    private static javax.swing.JTextField textEnviar;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables
}
