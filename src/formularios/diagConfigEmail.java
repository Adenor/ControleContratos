/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import utilidade.ConfigUtil;

/**
 *
 * @author amrsilva
 */
public class diagConfigEmail extends javax.swing.JDialog {
    private final ConfigUtil configUtil;
    /**
     * Creates new form diagConfigEmail
     * @param parent
     * @param modal
     * @param configUtil
     */
    public diagConfigEmail(java.awt.Frame parent, boolean modal, ConfigUtil configUtil) {
        super(parent, modal);
        this.configUtil = configUtil;
        initComponents();
        loadSettings();
    }

    private void loadSettings() {
        Properties configProps = null;
        Properties otherProps = null;
	try {
            configProps = configUtil.loadConfigProperties();
            otherProps = configUtil.loadMailProperties();
	} catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro lendo arquivo de configuração: " + ex.getMessage(), "Erro"
                    , JOptionPane.ERROR_MESSAGE);
        }
        txtServidor.setText(configProps.getProperty("mail.smtp.host"));
	txtPorta.setText(configProps.getProperty("mail.smtp.port"));
	txtRemetente.setText(configProps.getProperty("mail.user"));
        
        txtMensagem.setText(otherProps.getProperty("mail.message"));
	txtDestinatario.setText(otherProps.getProperty("mail.addressee"));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblServidor = new javax.swing.JLabel();
        lblPorta = new javax.swing.JLabel();
        lblRemetente = new javax.swing.JLabel();
        lblDestinatario = new javax.swing.JLabel();
        txtDestinatario = new javax.swing.JTextField();
        txtRemetente = new javax.swing.JTextField();
        txtPorta = new javax.swing.JTextField();
        txtServidor = new javax.swing.JTextField();
        lblMensagem = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtMensagem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuração de e-mail");
        setResizable(false);

        lblServidor.setText("Servidor:");

        lblPorta.setText("Porta:");

        lblRemetente.setText("Remetente:");

        lblDestinatario.setText("Destinatário(s):");

        lblMensagem.setText("Mensagem:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDestinatario)
                    .addComponent(lblServidor)
                    .addComponent(lblPorta)
                    .addComponent(lblRemetente)
                    .addComponent(lblMensagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(97, 97, 97))
                    .addComponent(txtDestinatario)
                    .addComponent(txtRemetente)
                    .addComponent(txtServidor)
                    .addComponent(txtPorta)
                    .addComponent(txtMensagem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblServidor)
                    .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPorta)
                    .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRemetente)
                    .addComponent(txtRemetente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDestinatario)
                    .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensagem)
                    .addComponent(txtMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        try {
            configUtil.saveConfigProperties(txtServidor.getText(), txtPorta.getText(), txtRemetente.getText(), "");
            configUtil.saveMailProperties(txtMensagem.getText(), txtDestinatario.getText());
            JOptionPane.showMessageDialog(this, "Configurações salvas com sucesso.");
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro salvando configurações: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }		
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblDestinatario;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblPorta;
    private javax.swing.JLabel lblRemetente;
    private javax.swing.JLabel lblServidor;
    private javax.swing.JTextField txtDestinatario;
    private javax.swing.JTextField txtMensagem;
    private javax.swing.JTextField txtPorta;
    private javax.swing.JTextField txtRemetente;
    private javax.swing.JTextField txtServidor;
    // End of variables declaration//GEN-END:variables
}
