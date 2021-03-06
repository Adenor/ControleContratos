/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import controle.ControleAditivos;
import controle.ControleContas;
import controle.ControleContratos;
import controle.ControleObjetos;
import controle.ControleTarifas;
import entidades.Contratos;
import entidades.Objetos;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import utilidade.FiltroTexto;
import utilidade.Validadores;

/**
 *
 * @author amrsilva
 */
public class informCadContratos extends javax.swing.JInternalFrame {
    ControleContratos vCtrlContratos = new ControleContratos();
    ControleAditivos vCtrlAditivos = new ControleAditivos();
    ControleContas vCtrlContas = new ControleContas();
    ControleTarifas vCtrlTarifas = new ControleTarifas();
    /**
     * Creates new form informCadContratos
     */
    public informCadContratos() {
        initComponents();
        try {
            // TODO add your handling code here:
            final MaskFormatter mfAgencia = new MaskFormatter("####");
            ftfAgencia.setFormatterFactory(new DefaultFormatterFactory(mfAgencia));
            final MaskFormatter mfConta = new MaskFormatter("############");
            ftfAno.setFormatterFactory(new DefaultFormatterFactory(mfConta));
            final MaskFormatter mfData = new MaskFormatter("##/##/####");
            ftfAssinatura.setFormatterFactory(new DefaultFormatterFactory(mfData));
            ftfVencimento.setFormatterFactory(new DefaultFormatterFactory(mfData));
            ftfVigencia.setFormatterFactory(new DefaultFormatterFactory(mfData));
            preencherCombo();
        } catch (ParseException ex) {
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void preencherCombo(){
        try {
            cbxObjeto.removeAll();
            DefaultComboBoxModel vModelo = new DefaultComboBoxModel();
            for (Objetos contObj : new ControleObjetos().ListarTodos()){
                vModelo.addElement((Object)contObj.getObjeto());
            }
            cbxObjeto.setModel(vModelo);
            cbxObjeto.setSelectedIndex(-1);
            cbxObjeto.setSelectedItem("");
        } catch (Exception ex) {
            Logger.getLogger(diagObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpCNPJCPF = new javax.swing.ButtonGroup();
        lblReferencia = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        txtRazao = new javax.swing.JTextField();
        lblRazao = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JLabel();
        lblObjeto = new javax.swing.JLabel();
        lblAgencia = new javax.swing.JLabel();
        lblDataAssinatura = new javax.swing.JLabel();
        lblDataVencimento = new javax.swing.JLabel();
        lblDataVigencia = new javax.swing.JLabel();
        btnAdcionar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        brnFechar = new javax.swing.JButton();
        lblAno = new javax.swing.JLabel();
        rbtCNPJ = new javax.swing.JRadioButton();
        rbtCPF = new javax.swing.JRadioButton();
        ftfCPFCNPJ = new javax.swing.JFormattedTextField();
        ftfAgencia = new javax.swing.JFormattedTextField();
        ftfAno = new javax.swing.JFormattedTextField();
        ftfAssinatura = new javax.swing.JFormattedTextField();
        ftfVencimento = new javax.swing.JFormattedTextField();
        ftfVigencia = new javax.swing.JFormattedTextField();
        btnObjetos = new javax.swing.JButton();
        cbxObjeto = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de contratos");
        setMinimumSize(new java.awt.Dimension(645, 307));

        lblReferencia.setText("Número");

        ((AbstractDocument) txtReferencia.getDocument()).setDocumentFilter(new utilidade.FiltroTexto(10, true));

        ((AbstractDocument) txtRazao.getDocument()).setDocumentFilter(new utilidade.FiltroTexto(100));

        lblRazao.setText("Razão Social / Nome");

        ((AbstractDocument) txtEndereco.getDocument()).setDocumentFilter(new utilidade.FiltroTexto(200));

        lblEndereco.setText("Endereço");

        lblObjeto.setText("Objeto");

        lblAgencia.setText("Agência");

        lblDataAssinatura.setText("Data de Assinatura");

        lblDataVencimento.setText("Data de Vencimento");

        lblDataVigencia.setText("Data de Vigência");

        btnAdcionar.setText("Adcionar Contrato");
        btnAdcionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdcionarActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar Campos");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        brnFechar.setText("Fechar");
        brnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnFecharActionPerformed(evt);
            }
        });

        lblAno.setText("Ano");

        bgpCNPJCPF.add(rbtCNPJ);
        rbtCNPJ.setSelected(true);
        rbtCNPJ.setText("CNPJ");
        rbtCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCNPJActionPerformed(evt);
            }
        });

        bgpCNPJCPF.add(rbtCPF);
        rbtCPF.setText("CPF");
        rbtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCPFActionPerformed(evt);
            }
        });

        try {
            ftfCPFCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfCPFCNPJ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfAgencia.setFocusLostBehavior(3);
        try {
            MaskFormatter mf = new MaskFormatter("####");
            mf.setValueContainsLiteralCharacters(false);
            ftfAgencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftfAno.setFocusLostBehavior(3);
        try {
            MaskFormatter mf = new MaskFormatter("yyyy");
            mf.setValueContainsLiteralCharacters(false);
            ftfAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftfAssinatura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfAssinatura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfVencimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVigencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfVigencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnObjetos.setText("+");
        btnObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObjetosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReferencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRazao)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtRazao)
                                .addGap(10, 10, 10))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEndereco)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDataAssinatura)
                                            .addComponent(ftfAssinatura, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDataVencimento)
                                            .addComponent(ftfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDataVigencia)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ftfVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAdcionar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(brnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbtCNPJ)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rbtCPF))
                                            .addComponent(ftfCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblObjeto)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbxObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnObjetos)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAgencia)
                                            .addComponent(ftfAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {brnFechar, btnAdcionar, btnLimpar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferencia)
                    .addComponent(lblRazao)
                    .addComponent(lblAno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAgencia)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtCNPJ)
                            .addComponent(rbtCPF)
                            .addComponent(lblObjeto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnObjetos)
                            .addComponent(cbxObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDataVencimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDataVigencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdcionar)
                            .addComponent(btnLimpar)
                            .addComponent(brnFechar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDataAssinatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfAssinatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdcionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdcionarActionPerformed
        // TODO add your handling code here:
        int vResposta;
        if (txtReferencia.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Campo \"Número\" é obrigatório.", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtRazao.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Campo \"Razão Social / Nome\" é obrigatório.", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ftfAgencia.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Campo \"Agência\" é obrigatório.", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ftfAno.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Campo \"Ano\" é obrigatório.", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ftfAssinatura.getText().trim().equals("/  /")){
            JOptionPane.showMessageDialog(this, "Campo \"Assinatura\" é obrigatório.", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ftfCPFCNPJ.getText().trim().replace(".", "").replace("/", "").replace("-", "").equals("")){
            JOptionPane.showMessageDialog(this, "Campo \"Número\" é obrigatório.", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cbxObjeto.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(this, "Selecione um Objeto para o contrato", "Informação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String vCPFCNPJ;
        if (rbtCNPJ.isSelected()) {
            if (ftfCPFCNPJ.getText().replace(".", "").replace("-", "").replace("/", "").trim().equals("")){
                vCPFCNPJ = null;
            } else {
                vCPFCNPJ = ftfCPFCNPJ.getText().replace(".", "").replace("-", "").replace("/", "");
                vCPFCNPJ = "00000000000000".substring(vCPFCNPJ.length()) + vCPFCNPJ;
                if (!Validadores.isCNPJ(vCPFCNPJ)) {
                    JOptionPane.showMessageDialog(btnAdcionar, "CNPJ inválido");
                    return;
                }
            }
        } else {
            if (ftfCPFCNPJ.getText().replace(".", "").replace("-", "").replace("/", "").trim().equals("")){
                vCPFCNPJ = null;
            } else {
                vCPFCNPJ = ftfCPFCNPJ.getText().replace(".", "").replace("-", "").replace("/", "");
                vCPFCNPJ = "00000000000".substring(vCPFCNPJ.length()) + vCPFCNPJ;
                if (!Validadores.isCPF(vCPFCNPJ)) {
                    JOptionPane.showMessageDialog(btnAdcionar, "CPF inválido");
                    return;
                }
            }
        }
        Contratos vContrato = new Contratos();
        vContrato.setReferencia(Integer.parseInt(txtReferencia.getText()));
        vContrato.setAgencia(Integer.parseInt(ftfAgencia.getText().trim()));
        vContrato.setIdentificador(vCPFCNPJ);
        vContrato.setObjeto(cbxObjeto.getSelectedItem().toString());
        vContrato.setEndereco(txtEndereco.getText());
        vContrato.setRazao(txtRazao.getText());
        try {
            if (!ftfAno.getText().equals("    ")) vContrato.setAno(Integer.parseInt(ftfAno.getText().trim()));
            if (!ftfAssinatura.getText().equals("  /  /    ")) vContrato.setAssinatura(new SimpleDateFormat("dd/MM/yyyy").parse(
                    ftfAssinatura.getText()));
            if (!ftfVigencia.getText().equals("  /  /    ")) vContrato.setVigencia(new SimpleDateFormat("dd/MM/yyyy").parse(
                    ftfVigencia.getText()));
            if (!ftfVencimento.getText().equals("  /  /    ")) vContrato.setVencimento(new SimpleDateFormat("dd/MM/yyyy").parse(
                    ftfVencimento.getText()));
            vCtrlContratos.Incluir(vContrato);
            vResposta = JOptionPane.showConfirmDialog(this, "Contrato cadastrado com sucesso.\nDeseja incluir as contas?",
                    "Incluir", JOptionPane.YES_NO_OPTION);
            if (vResposta == JOptionPane.YES_OPTION){
                diagContas diagContasInstancia = new diagContas(null, true, vContrato);
                diagContasInstancia.setVisible(true);
            }
            vResposta = JOptionPane.showConfirmDialog(this, "Deseja incluir as tarifas?" ,"Incluir", JOptionPane.YES_NO_OPTION);
            if (vResposta == JOptionPane.YES_OPTION){
                diagTarifas diagTarifasInstancia = new diagTarifas(null, true, vContrato);
                diagTarifasInstancia.setVisible(true);
            }
            this.btnLimparActionPerformed(new ActionEvent(btnAdcionar, 0, ""));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this,"Verifique o campo Data de assinatura\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Verifique o campo Ano\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            if (ex.getCause().getCause().toString().toLowerCase().contains("duplicate entry")) {
                JOptionPane.showMessageDialog(this,"Já existe um contrato com esse número nesse ano\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAdcionarActionPerformed

    private void brnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnFecharActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_brnFecharActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        txtEndereco.setText("");
        cbxObjeto.setSelectedIndex(-1);
        txtRazao.setText("");
        txtReferencia.setText("");
        ftfAgencia.setText("");
        ftfAno.setText("");
        ftfCPFCNPJ.setText("");
        ftfAssinatura.setText("");
        ftfVencimento.setText("");
        ftfVigencia.setText("");
        ftfAssinatura.setValue(null);
        ftfVencimento.setValue(null);
        ftfVigencia.setValue(null);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void rbtCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCNPJActionPerformed
        try {
            // TODO add your handling code here:
            final MaskFormatter mfCNPJ = new MaskFormatter("##.###.###/####-##");
            ftfCPFCNPJ.setText("");
            ftfCPFCNPJ.setValue(null);
            if (evt.getActionCommand().equals("CNPJ")){
                ftfCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory(mfCNPJ));
            }
        } catch (ParseException ex) {
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_rbtCNPJActionPerformed

    private void rbtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCPFActionPerformed
        try {
            // TODO add your handling code here:
            final MaskFormatter mfCPF = new MaskFormatter("###.###.###-##");
            ftfCPFCNPJ.setText("");
            ftfCPFCNPJ.setValue(null);
            if (evt.getActionCommand().equals("CPF")){
                ftfCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory(mfCPF));
            }
        } catch (ParseException ex) {
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rbtCPFActionPerformed

    private void btnObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObjetosActionPerformed
        // TODO add your handling code here:
        new diagObjetos(null, true, this).setVisible(true);
    }//GEN-LAST:event_btnObjetosActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpCNPJCPF;
    private javax.swing.JButton brnFechar;
    private javax.swing.JButton btnAdcionar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnObjetos;
    private javax.swing.JComboBox<String> cbxObjeto;
    private javax.swing.JFormattedTextField ftfAgencia;
    private javax.swing.JFormattedTextField ftfAno;
    private javax.swing.JFormattedTextField ftfAssinatura;
    private javax.swing.JFormattedTextField ftfCPFCNPJ;
    private javax.swing.JFormattedTextField ftfVencimento;
    private javax.swing.JFormattedTextField ftfVigencia;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblDataAssinatura;
    private javax.swing.JLabel lblDataVencimento;
    private javax.swing.JLabel lblDataVigencia;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblObjeto;
    private javax.swing.JLabel lblRazao;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JRadioButton rbtCNPJ;
    private javax.swing.JRadioButton rbtCPF;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtRazao;
    private javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables
}
