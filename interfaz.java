//este archivo Java es la interfaz la cual se comunicará con el arduino de la manera descrita en el archivo README
//cabe mencionar que es un javaForm y fue creada con el asistente del IDE NetBeans
//NOTA: se necesita descargar la libreria RXTX, descrita en el archivo README para lograr la comunicacion con el arduino

//nombre del paquete
package lcdprinter;

//imports de las clases a utilizar
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import static java.awt.image.ImageObserver.ERROR;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import javax.swing.DefaultListModel;

public class interfaz extends javax.swing.JFrame {
    //ya que se usa un jList utilizaremos un DefaultListModel
    //para poder rellenar ese JList que mostrará el arreglo de la forma descrita en el archivo README
    DefaultListModel modelo = new DefaultListModel();
    //el sigueinte ArrayList almacenará los saludos
    ArrayList<String> saludos = new ArrayList<>();
    
    //el siguiente grupo de variables es importante
    //los valores a considerar con el nombre del puerto (coincidir con el puerto al cual el arduino este conectado)
    //y el DATA_RATE es un parametro que debe coincidir con el que se introduce en el arduino en Serial.begin()
    private OutputStream Output = null;
    SerialPort serialPort;
    private final String PORT_NAME = "COM5";
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    
    //la clase Calendar nos ayudará a tomar la hora del sistema    
    Calendar calendario = Calendar.getInstance();
    int h,s,m;
    //el siguiente constructor nos ayuda a inicializar todos los componentes graficos de la interfaz
    //, la conexion al arduino, el contador de letras y demas
    public interfaz() {
        initComponents();
        letras();
        inicio();
        ArduinoConnection();
    }
    
    //este es el metodo para generar la conexion al arduino
     private void ArduinoConnection() {

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

            if (PORT_NAME.equals(currPortId.getName())) {
                portId = currPortId;
                break;
            }
        }

        if (portId == null) {

            System.exit(ERROR);
            return;
        }

        try {

            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            Output = serialPort.getOutputStream();

        } catch (PortInUseException | UnsupportedCommOperationException | IOException e) {

            System.exit(ERROR);
        }

    }
    //este metodo inicializa los mensajes con los mostrados por default, pero pueden ser cambiados desde la interfaz
    //o desde aqui
    private void inicio(){
        saludos.add("holi");
        saludos.add("que onda");
        saludos.add("saludos");
        
        for (int i = 0; i < saludos.size(); i++) {
            modelo.add(i, saludos.get(i));
        }
        
        lista.setModel(modelo);
    }
    //metodo para actualizar los mensajes que quieran ser cambiados desde la interfaz
    private void actualizar(String aux, int index){
            modelo.remove(index);
            saludos.remove(index);
            saludos.add(aux);
            modelo = new DefaultListModel();
            for (int i = 0; i < saludos.size(); i++) {
            modelo.add(i, saludos.get(i));
            }
            
        lista.setModel(modelo);
    }
    //este metodo cuenta la cantidad de caracteres que se pueden incluir en cada mensaje
    //y bloquea si se escriben de mas
    private  void letras() {
        int caracteres = 16 - txtMensaje.getText().length(); //Indica la catidad de carcteres
        //disponibles. En el LCD solo se permite imprimir 32 caracteres.

        if (caracteres <= 0) { //Si la cantidad de caracteres se ha agotado... 
            label1.setText("Caracteres disponibles: 0"); //Se imprime que la cantidad de 
            //caracteres disponibles es 0
            String cadena; //Se declara la variable que guardará el mensaje a enviar
            cadena = txtMensaje.getText(); //Se asigna el texto del TextField a la variable cadena
            cadena = cadena.substring(0, 16); //Se evita que por alguna razón la variable contenga
            //más de 32 caracteres, utilizando el substring que crea un string a partir de uno mayor.
            txtMensaje.setText(cadena); //se regresa la cadena con 32 caracteres al TextField
        } else {
            //Si la cantidad de caracteres disponibles es ayor a 0 solamente se imprimirá la cantidad
            //de caracteres disponibles
            label1.setText("CARACTERES DISPONIBLES: " + (caracteres));
        }
    }
    //los siguientes metodos son la inicializacion de los elementos graficos de la interfaz
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        txtMensaje = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnMostrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lista);

        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMensajeKeyReleased(evt);
            }
        });

        btnAdd.setText("Modificar Mensaje");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        label1.setText("CARACTERES DISPONIBLES: ");

        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(51, 51, 51))
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMostrar)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMostrar)
                            .addComponent(btnActualizar))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1)
                        .addGap(5, 5, 5)
                        .addComponent(btnAdd)
                        .addGap(65, 65, 65))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //el sigueinte metodo es un evento que se acciona cada que se teclea sobre la textbox
    //y activa el contador de letras
    private void txtMensajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyReleased
        letras();
    }//GEN-LAST:event_txtMensajeKeyReleased
    //el siguiente metodo es un evento y se activa cuando se da click al boton actualizar
    //lo que hace es tomar el saludo del textbox y actualizarlo en la lista
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String aux=(txtMensaje.getText());
        int index = lista.getSelectedIndex();
        actualizar(aux,index);
        txtMensaje.setText("");
        letras();
    }//GEN-LAST:event_btnAddActionPerformed
    //el siguiente metodo es un evento y se activa al dar click en el boton ACTUALIZAR
    //lo que hace es mandar una actualizacion de los saludos al arduino
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            
            String data;
            data = "*";
            for (int i = 0; i < saludos.size(); i++) {
                data = data+saludos.get(i)+",";
            }
            data = data+"*"+0;
            System.out.println(data);
            Output.write(data.getBytes());
        } catch (IOException e) {
            System.exit(ERROR);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed
    //el siguiente metodo es un evento y se activa al hacer click en el boton MOSTRAR
    //lo que hace es mandar una actualizacion al arduino que haga que cambie el mensaje que se quiere mostrar en
    //el display, para seleccionar cual saludo se desea mostrar leer el archivo README
    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        
try {
    String data ="**"+lista.getSelectedIndex();
    System.out.println(data);
Output.write(data.getBytes());
        } catch (IOException e) {
            System.exit(ERROR);
        }
    }//GEN-LAST:event_btnMostrarActionPerformed
    //este metodo de nombre coomunicar
    //sirve para tomar la hora del sistema usando Calendar
    //y mandar toda la primer comunicacion al arduino, que incluye:
    //hora, saludos y saludo a mostrar (por default el primero)
    public void comunicar() {
        try {
            calendario = Calendar.getInstance();
              h = calendario.get(Calendar.HOUR_OF_DAY);
              m = calendario.get(Calendar.MINUTE);
              s = calendario.get(Calendar.SECOND);
            String data;
            data = h+" "+m+" "+s+"*";
            for (int i = 0; i < saludos.size(); i++) {
                data = data+saludos.get(i)+",";
            }
            data = data+"*"+0;
            System.out.println(data);
            Output.write(data.getBytes());
        } catch (IOException e) {
            System.exit(ERROR);
        }
    }
    
    
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        final interfaz in = new interfaz();
        in.setVisible(true);
        in.comunicar();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JList<String> lista;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
