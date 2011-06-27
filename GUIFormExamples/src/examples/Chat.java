/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Chat.java
 *
 * Created on Jun 15, 2011, 9:54:27 AM
 */
package examples;


/**
 *
 * @author sherry
 */
public class Chat extends javax.swing.JFrame {
    
    private int ns;
    private String[] users= new String[50];
    private String dataFromServer;
    private Integer onlineOtherUsrCnt;
    private String sender, message;
    private String currentUsr;
    public Talk talkView1, talkView2, talkView3, talkView4;
    
    private final char TALK = 'T';  
    private final char TALK_NO = 't';
    private final char BROADCAST_JOIN = 'J';
    private final char BROADCAST_DEP = 'D';
    private final char LIST = 'I';
    
    private native void listUsr(int ns);
    private native String waitForServer(int ns);
   
    public class WaitForServer implements Runnable {
        public void run() {
            receiveDataFromServer();
        } 
    }
    
    private String receiveDataFromServer() {
        while (true) {            
            String tmp = waitForServer(ns);
            dataFromServer += tmp;
           
            System.out.print("from server(in java)");
            System.out.println(dataFromServer);
            if (dataFromServer.charAt(dataFromServer.length() - 1) == '*') {
                System.out.print("complete package from server  ");
                System.out.println(dataFromServer);
                switch (dataFromServer.charAt(0)) {
                    case TALK:
                        sender = dataFromServer.substring(1, dataFromServer.indexOf(" "));
                        message = dataFromServer.substring(dataFromServer.indexOf(" ") + 1, dataFromServer.length() - 1);
                        System.out.print("message from server: sender:");
                        System.out.println(sender);
                        System.out.print("message:");
                        System.out.println(message);
                        if ("Conan".equals(sender)) {
                            if (talkView1 == null) {
                                talkView1 = new Talk(ns, currentUsr, sender);
                                talkView1.setVisible(true);
                            }
                            talkView1.appendChatMsg(sender, message);
                        }
                        else if ("Haibara".equals(sender)) {
                            if (talkView2 == null) {
                                talkView2 = new Talk(ns, currentUsr, sender);
                                talkView2.setVisible(true);
                            }
                            talkView2.appendChatMsg(sender, message);
                        }
                        else if ("Sonoko".equals(sender)) {
                            if (talkView3 == null) {
                                talkView3 = new Talk(ns, currentUsr, sender);
                                talkView3.setVisible(true);
                            }
                            talkView3.appendChatMsg(sender, message);
                        }
                        break;
                    case LIST:
                        revealUsrButtons(dataFromServer.toString());
                        break;
                    case BROADCAST_JOIN:
                        String newUsrname = dataFromServer.substring(1, dataFromServer.length() - 1);
                        System.out.println(newUsrname);
                        appendUsrButton(newUsrname);
                        break;
                    case BROADCAST_DEP:
                        break;
                }
                dataFromServer = "";
            }
        }
    }
   
    static {
        System.load("/home/sherry/Documents/netbeans/chatDynamicLibrary/dist/libchatDynamicLibrary.so");
    }

    /** Creates new form Chat */
    public Chat(int ns, String usrname) {
        initComponents();
        this.ns = ns;
        (new Thread(new WaitForServer())).start();
        listUsr(ns); //send asynchronized message
        dataFromServer = "";
        currentUsr = usrname;
        jLabel2.setText("welcome, " + usrname);
        onlineOtherUsrCnt = 0;
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
    }
    
    private void revealUsrButtons(String nameList) {    
        String st = nameList.substring(1, nameList.length() - 1);
	for (int i = 0; i < st.split(":").length; i++) {           
            users[i] = st.split(":")[i];           	                
	}
        
        for(int i = 0; i < nameList.length(); i++) {
            if(':' == nameList.charAt(i) ){
		onlineOtherUsrCnt++;
	    }
        }
        
        System.out.println("array");
        
        for(int i = 0; i < onlineOtherUsrCnt; i++){
	    System.out.println(users[i]); 
	}
        jLabel1.setText("other users: " + onlineOtherUsrCnt.toString());
        if (onlineOtherUsrCnt >= 1) {
            jButton1.setText(users[0]);
            jButton1.setVisible(true);
        }
        if (onlineOtherUsrCnt >= 2) {
            jButton2.setText(users[1]);
            jButton2.setVisible(true);
        }
        if (onlineOtherUsrCnt >= 3) {
            jButton3.setText(users[2]);
            jButton3.setVisible(true);
        }
        if (onlineOtherUsrCnt >= 4) {
            jButton4.setText(users[3]);
            jButton4.setVisible(true);
        }
    }
    
    public void appendUsrButton(String newUsrname) { 
        if (onlineOtherUsrCnt == 0) {
            jButton1.setText(newUsrname);
            jButton1.setVisible(true);
        }
        else if (onlineOtherUsrCnt == 1) {
            jButton2.setText(newUsrname);
            jButton2.setVisible(true);
        }
        else if (onlineOtherUsrCnt == 2) {
            jButton3.setText(newUsrname);
            jButton3.setVisible(true);
        }
        else if (onlineOtherUsrCnt == 3) {
            jButton4.setText(newUsrname);
            jButton4.setVisible(true);
        }
        onlineOtherUsrCnt++;
        jLabel1.setText("other users: " + onlineOtherUsrCnt.toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("welcome");

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("online users");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String targetUsrname = jButton1.getText();
        showTalkView(targetUsrname);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String targetUsrname = jButton2.getText();
        showTalkView(targetUsrname);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String targetUsrname = jButton3.getText();
        showTalkView(targetUsrname);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String targetUsrname = jButton4.getText();
        showTalkView(targetUsrname);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void showTalkView(String targetUsrname) {
        System.out.println(targetUsrname);
        if ("Conan".equals(targetUsrname)) {
            talkView1 = new Talk(ns, currentUsr, targetUsrname);
            talkView1.setVisible(true);
        }
        else if ("Haibara".equals(targetUsrname)) {
            talkView2 = new Talk(ns, currentUsr, targetUsrname);
            talkView2.setVisible(true);
        }
        else if ("Sonoko".equals(targetUsrname)) {
            talkView3 = new Talk(ns, currentUsr, targetUsrname);
            talkView3.setVisible(true);
        }
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
