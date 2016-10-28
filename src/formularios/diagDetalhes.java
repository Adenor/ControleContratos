/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import controle.ControleAditivos;
import controle.ControleContratos;
import controle.ControleObjetos;
import entidades.Aditivos;
import entidades.Contratos;
import entidades.Objetos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import utilidade.Validadores;

/**
 *
 * @author amrsilva
 */
public class diagDetalhes extends javax.swing.JDialog {
    private List<Aditivos> vListaAdit;
    ControleAditivos vCtrlAdit = new ControleAditivos();
    informConContrato formParent;
    /**
     * Creates new form diagDetalhes
     * @param parent
     * @param modal
     * @param ContratoPai
     */
    Contratos ContratoPai;
    
    public diagDetalhes(java.awt.Frame parent, boolean modal, Contratos ContratoPai) {
        // TODO add your handling code here:
        super(parent, modal);
        this.ContratoPai = ContratoPai;
        initComponents();
        preencherCombo();
        preencherCampos();
        atualizarTabela();
    }
    
    public void preencherCombo(){
        try {
            cbxObjeto.removeAll();
            DefaultComboBoxModel vModelo = new DefaultComboBoxModel();
            for (Objetos contObj : new ControleObjetos().ListarTodos()){
                vModelo.addElement((Object)contObj.getObjeto());
            }
            cbxObjeto.setModel(vModelo);
        } catch (Exception ex) {
            Logger.getLogger(diagObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarTabela(){
        try {
            vListaAdit = vCtrlAdit.ListarTodos(ContratoPai);
        } catch (Exception ex) {
            Logger.getLogger(informConContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel vModelo = (DefaultTableModel) tblAditivos.getModel();
        vModelo.setNumRows(0);
        for(Aditivos contAdit : vListaAdit) {
            vModelo.addRow(new String[3]);
            vModelo.setValueAt(contAdit.getNumero(), vModelo.getRowCount()-1, 0);
            vModelo.setValueAt(contAdit.getDescricao(), vModelo.getRowCount()-1, 1);
            vModelo.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(contAdit.getAssinatura()), vModelo.getRowCount()-1, 2);
        }
    }
    
    private void preencherCampos(){
        try {
            ftfAssinatura.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            ftfVencimento.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            ftfVigencia.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            ftfAssinaturaAditivo.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (ParseException ex) {
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ContratoPai.getAgencia() != 0) ftfAgencia.setText("0000".substring(String.valueOf(ContratoPai.getAgencia()).length())
                + String.valueOf(ContratoPai.getAgencia()));
        txtRazao.setText(ContratoPai.getRazao());
        txtReferencia.setText(String.valueOf(ContratoPai.getReferencia()));
        if (ContratoPai.getConta()!= 0) ftfConta.setText("0000000000".substring(String.valueOf(ContratoPai.getConta()).length())
                + String.valueOf(ContratoPai.getConta()));
        if (ContratoPai.getAno() != 0) ftfAno.setText(String.valueOf(ContratoPai.getAno()));
        if (ContratoPai.getVencimento()!=null) ftfVencimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(
                ContratoPai.getVencimento()));
        ftfAssinatura.setText(new SimpleDateFormat("dd/MM/yyyy").format(ContratoPai.getAssinatura()));
        if (ContratoPai.getVigencia()!=null) ftfVigencia.setText(new SimpleDateFormat("dd/MM/yyyy").format(
                ContratoPai.getVencimento()));
        ftfAssinatura.setText(new SimpleDateFormat("dd/MM/yyyy").format(ContratoPai.getAssinatura()));
        for (int i = 0; i < cbxObjeto.getItemCount(); i++) {
            if (cbxObjeto.getItemAt(i).equals(ContratoPai.getObjeto())) {
                cbxObjeto.setSelectedIndex(i);
                cbxObjeto.setSelectedItem(ContratoPai.getObjeto());
            }
        }
        if  (ContratoPai.getEndereco()!=null) txtEndereco.setText(ContratoPai.getEndereco());
        if (ContratoPai.getIdentificador()!= null){
            boolean vCNPJ = Validadores.isCNPJ("00000000000000".substring(ContratoPai.getIdentificador().length())
                            + ContratoPai.getIdentificador());
            boolean vCPF = false;
            try {
                vCPF = Validadores.isCPF("00000000000".substring(ContratoPai.getIdentificador().length())
                        + ContratoPai.getIdentificador());
            } catch (StringIndexOutOfBoundsException se) {
                System.out.println(se.getMessage());
            }
            try {
                if (vCNPJ) {
                    MaskFormatter mfCNPJ  = new MaskFormatter("##.###.###/####-##");
                    mfCNPJ.setValueContainsLiteralCharacters(false);
                    ftfCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory(mfCNPJ));
                    ftfCPFCNPJ.setText(mfCNPJ.valueToString("00000000000000".substring(ContratoPai.getIdentificador().length())
                            + ContratoPai.getIdentificador()));
                    } else if (vCPF){
                        final MaskFormatter mfCPF  = new MaskFormatter("###.###.###-##");
                        mfCPF.setValueContainsLiteralCharacters(false);
                        ftfCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory(mfCPF));
                        ftfCPFCNPJ.setText(mfCPF.valueToString("00000000000".substring(ContratoPai.getIdentificador().length())
                                + ContratoPai.getIdentificador()));
                    }
                rbtCPF.setSelected(vCPF);
                rbtCNPJ.setSelected(vCNPJ);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao ler CPF/CNPJ no banco de dados.\n" + ex, "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
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

        jButton3 = new javax.swing.JButton();
        gpbDetalhes = new javax.swing.ButtonGroup();
        panAditivos = new javax.swing.JPanel();
        btnAdcionar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        ftfNumero = new javax.swing.JFormattedTextField();
        txtDescricao = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        spnAditivos = new javax.swing.JScrollPane();
        tblAditivos = new javax.swing.JTable();
        ftfAssinaturaAditivo = new javax.swing.JFormattedTextField();
        lblAssinaturaAditivo = new javax.swing.JLabel();
        panDetalhes = new javax.swing.JPanel();
        btnEditarDetalhes = new javax.swing.JButton();
        btnSalvarDetalhes = new javax.swing.JButton();
        btnCancelarDetalhes = new javax.swing.JButton();
        lblObjeto = new javax.swing.JLabel();
        lblAgencia = new javax.swing.JLabel();
        lblConta = new javax.swing.JLabel();
        lblDataAssinatura = new javax.swing.JLabel();
        lblDataVencimento = new javax.swing.JLabel();
        lblDataVigencia = new javax.swing.JLabel();
        lblAno = new javax.swing.JLabel();
        rbtCNPJ = new javax.swing.JRadioButton();
        rbtCPF = new javax.swing.JRadioButton();
        ftfCPFCNPJ = new javax.swing.JFormattedTextField();
        ftfAgencia = new javax.swing.JFormattedTextField();
        ftfConta = new javax.swing.JFormattedTextField();
        ftfAno = new javax.swing.JFormattedTextField();
        lblReferencia = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        ftfAssinatura = new javax.swing.JFormattedTextField();
        ftfVencimento = new javax.swing.JFormattedTextField();
        txtRazao = new javax.swing.JTextField();
        ftfVigencia = new javax.swing.JFormattedTextField();
        lblRazao = new javax.swing.JLabel();
        btnObjetos = new javax.swing.JButton();
        txtEndereco = new javax.swing.JTextField();
        cbxObjeto = new javax.swing.JComboBox();
        lblEndereco = new javax.swing.JLabel();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Teste");
        setModal(true);
        setResizable(false);

        panAditivos.setBorder(javax.swing.BorderFactory.createTitledBorder("Aditivos:"));

        btnAdcionar.setText("Adcionar");
        btnAdcionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdcionarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        ftfNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###"))));
        ftfNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfNumeroFocusLost(evt);
            }
        });

        lblNumero.setText("Nº");

        lblDescricao.setText("Descrição");

        spnAditivos.setBorder(null);

        tblAditivos.setBorder(null);
        tblAditivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Descrição", "Assinatura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAditivos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        spnAditivos.setViewportView(tblAditivos);
        if (tblAditivos.getColumnModel().getColumnCount() > 0) {
            tblAditivos.getColumnModel().getColumn(0).setResizable(false);
            tblAditivos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblAditivos.getColumnModel().getColumn(1).setPreferredWidth(700);
            tblAditivos.getColumnModel().getColumn(2).setResizable(false);
            tblAditivos.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        ftfAssinaturaAditivo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfAssinaturaAditivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblAssinaturaAditivo.setLabelFor(ftfVencimento);
        lblAssinaturaAditivo.setText("Data da Assinatura");

        javax.swing.GroupLayout panAditivosLayout = new javax.swing.GroupLayout(panAditivos);
        panAditivos.setLayout(panAditivosLayout);
        panAditivosLayout.setHorizontalGroup(
            panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAditivosLayout.createSequentialGroup()
                .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panAditivosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panAditivosLayout.createSequentialGroup()
                                .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ftfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNumero))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDescricao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAssinaturaAditivo)
                                    .addComponent(ftfAssinaturaAditivo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panAditivosLayout.createSequentialGroup()
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdcionar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(spnAditivos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        panAditivosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdcionar, btnExcluir, btnSalvar});

        panAditivosLayout.setVerticalGroup(
            panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAditivosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panAditivosLayout.createSequentialGroup()
                        .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumero)
                            .addComponent(lblDescricao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panAditivosLayout.createSequentialGroup()
                        .addComponent(lblAssinaturaAditivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfAssinaturaAditivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panAditivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnSalvar)
                    .addComponent(btnAdcionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnAditivos, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        );

        panDetalhes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Detalhes"));

        btnEditarDetalhes.setText("Editar Detalhes");
        btnEditarDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDetalhesActionPerformed(evt);
            }
        });

        btnSalvarDetalhes.setText("Salvar Detalhes");
        btnSalvarDetalhes.setEnabled(false);
        btnSalvarDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarDetalhesActionPerformed(evt);
            }
        });

        btnCancelarDetalhes.setText("Cancelar Edição");
        btnCancelarDetalhes.setEnabled(false);
        btnCancelarDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarDetalhesActionPerformed(evt);
            }
        });

        lblObjeto.setText("Objeto");

        lblAgencia.setText("Agência");

        lblConta.setText("Conta");

        lblDataAssinatura.setText("Data de Assinatura");

        lblDataVencimento.setText("Data de Vencimento");

        lblDataVigencia.setText("Data de Vigência");

        lblAno.setText("Ano");

        gpbDetalhes.add(rbtCNPJ);
        rbtCNPJ.setSelected(true);
        rbtCNPJ.setText("CNPJ");
        rbtCNPJ.setEnabled(false);
        rbtCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCNPJActionPerformed(evt);
            }
        });

        gpbDetalhes.add(rbtCPF);
        rbtCPF.setText("CPF");
        rbtCPF.setEnabled(false);
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
        ftfCPFCNPJ.setEnabled(false);

        ftfAgencia.setFocusLostBehavior(3);
        try {
            MaskFormatter mf = new MaskFormatter("####");
            mf.setValueContainsLiteralCharacters(false);
            ftfAgencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
            ftfAgencia.setEnabled(false);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftfAgencia.setFocusLostBehavior(3);
        try {
            MaskFormatter mf = new MaskFormatter("##########");
            mf.setValueContainsLiteralCharacters(false);
            ftfConta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
            ftfConta.setEnabled(false);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftfAgencia.setFocusLostBehavior(3);
        try {
            MaskFormatter mf = new MaskFormatter("####");
            mf.setValueContainsLiteralCharacters(false);
            ftfAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfAno.setEnabled(false);

        lblReferencia.setText("Número");

        txtReferencia.setEnabled(false);

        ftfAssinatura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfAssinatura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfAssinatura.setEnabled(false);

        ftfVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfVencimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfVencimento.setEnabled(false);

        txtRazao.setEnabled(false);

        ftfVigencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfVigencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfVigencia.setEnabled(false);

        lblRazao.setText("Razão Social / Nome");

        btnObjetos.setText("+");
        btnObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObjetosActionPerformed(evt);
            }
        });

        txtEndereco.setEnabled(false);

        cbxObjeto.setEnabled(false);

        lblEndereco.setText("Endereço");

        javax.swing.GroupLayout panDetalhesLayout = new javax.swing.GroupLayout(panDetalhes);
        panDetalhes.setLayout(panDetalhesLayout);
        panDetalhesLayout.setHorizontalGroup(
            panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEndereco)
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataAssinatura)
                            .addComponent(ftfAssinatura, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataVencimento)
                            .addComponent(ftfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataVigencia)
                            .addComponent(ftfVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addComponent(btnEditarDetalhes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarDetalhes))
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReferencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRazao)
                            .addComponent(txtRazao)))
                    .addComponent(txtEndereco)
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panDetalhesLayout.createSequentialGroup()
                                .addComponent(rbtCNPJ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbtCPF))
                            .addComponent(ftfCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObjeto)
                            .addGroup(panDetalhesLayout.createSequentialGroup()
                                .addComponent(cbxObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObjetos)))
                        .addGap(18, 18, 18)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAgencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ftfAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConta)
                            .addComponent(ftfConta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panDetalhesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelarDetalhes, btnEditarDetalhes, btnSalvarDetalhes});

        panDetalhesLayout.setVerticalGroup(
            panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferencia)
                    .addComponent(lblRazao)
                    .addComponent(lblAno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblAgencia)
                        .addComponent(lblConta))
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtCNPJ)
                            .addComponent(rbtCPF)
                            .addComponent(lblObjeto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnObjetos)
                            .addComponent(cbxObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addComponent(lblDataVencimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addComponent(lblDataVigencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panDetalhesLayout.createSequentialGroup()
                        .addComponent(lblDataAssinatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfAssinatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarDetalhes)
                    .addComponent(btnCancelarDetalhes)
                    .addComponent(btnSalvarDetalhes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panAditivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panAditivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdcionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdcionarActionPerformed
        // TODO add your handling code here:
        if (!txtDescricao.getText().equals("") && !ftfNumero.getText().equals("")){
            try {
                Aditivos vNovoAditivo = new Aditivos(ContratoPai, Integer.parseInt(ftfNumero.getText()), txtDescricao.getText(), 
                        new SimpleDateFormat("dd/MM/yyyy").parse(ftfAssinaturaAditivo.getText()));
                vCtrlAdit.Incluir(vNovoAditivo);
                ftfNumero.setText("");
                ftfNumero.setValue(null);
                txtDescricao.setText("");
                ftfAssinaturaAditivo.setValue(null);
                ftfAssinaturaAditivo.setText("");
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Aditivo incluído com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao incluir aditivo:\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else JOptionPane.showMessageDialog(this,"O preenchimento de todos os campos é obrigatório.",
                "Mensagem", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnAdcionarActionPerformed

    private void btnEditarDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDetalhesActionPerformed
        // TODO add your handling code here:
        rbtCPF.setEnabled(true);
        ftfAgencia.setEnabled(true);
        ftfCPFCNPJ.setEnabled(true);
        rbtCNPJ.setEnabled(true);
        txtEndereco.setEnabled(true);
        txtRazao.setEnabled(true);
        txtReferencia.setEnabled(true);
        cbxObjeto.setEnabled(true);
        ftfAssinatura.setEnabled(true);
        ftfVencimento.setEnabled(true);
        ftfConta.setEnabled(true);
        ftfAno.setEnabled(true);
        btnSalvarDetalhes.setEnabled(true);
        btnCancelarDetalhes.setEnabled(true);
        btnEditarDetalhes.setEnabled(false);
        ftfVigencia.setEnabled(true);
    }//GEN-LAST:event_btnEditarDetalhesActionPerformed

    private void btnCancelarDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarDetalhesActionPerformed
        // TODO add your handling code here:
        preencherCampos();
        rbtCPF.setEnabled(false);
        ftfAgencia.setEnabled(false);
        ftfCPFCNPJ.setEnabled(false);
        rbtCNPJ.setEnabled(false);
        txtEndereco.setEnabled(false);
        txtRazao.setEnabled(false);
        txtReferencia.setEnabled(false);
        cbxObjeto.setEnabled(false);
        if (ContratoPai.getVencimento()!=null) ftfVencimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(ContratoPai.getVencimento()));
        else {
            ftfVencimento.setValue(null);
            ftfVencimento.setText("");
        }
        if (ContratoPai.getVigencia()!=null) ftfVigencia.setText(new SimpleDateFormat("dd/MM/yyyy").format(ContratoPai.getVigencia()));
        else {
            ftfVigencia.setValue(null);
            ftfVigencia.setText("");
        }
        ftfAssinatura.setText(new SimpleDateFormat("dd/MM/yyyy").format(ContratoPai.getAssinatura()));
        ftfAssinatura.setEnabled(false);
        ftfVencimento.setEnabled(false);
        ftfConta.setEnabled(false);
        ftfAno.setEnabled(false);
        btnSalvarDetalhes.setEnabled(false);
        btnCancelarDetalhes.setEnabled(false);
        btnEditarDetalhes.setEnabled(true);
        ftfVigencia.setEnabled(false);
    }//GEN-LAST:event_btnCancelarDetalhesActionPerformed

    private void btnSalvarDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarDetalhesActionPerformed
        // TODO add your handling code here:
        Contratos ContratoRollback = new Contratos();
        
        ContratoRollback.setAgencia(ContratoPai.getAgencia());
        ContratoRollback.setAno(ContratoPai.getAno());
        ContratoRollback.setAssinatura(ContratoPai.getAssinatura());
        ContratoRollback.setConta(ContratoPai.getConta());
        ContratoRollback.setEndereco(ContratoPai.getEndereco());
        ContratoRollback.setIdentificador(ContratoPai.getIdentificador());
        ContratoRollback.setObjeto(ContratoPai.getObjeto());
        ContratoRollback.setRazao(ContratoPai.getRazao());
        ContratoRollback.setReferencia(ContratoPai.getReferencia());
        ContratoRollback.setVencimento(ContratoPai.getVencimento());
        ContratoRollback.setVigencia(ContratoPai.getVigencia());
        
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
        ContratoPai.setReferencia(Integer.parseInt(txtReferencia.getText()));
        ContratoPai.setAgencia(Integer.parseInt(ftfAgencia.getText().trim()));
        ContratoPai.setConta(Integer.parseInt(ftfConta.getText().trim()));
        ContratoPai.setIdentificador(vCPFCNPJ);
        ContratoPai.setObjeto(cbxObjeto.getSelectedItem().toString());
        ContratoPai.setEndereco(txtEndereco.getText());
        ContratoPai.setRazao(txtRazao.getText());
        try {
            if (!ftfAno.getText().equals("    ")) ContratoPai.setAno(Integer.parseInt(ftfAno.getText()));
            if (!ftfAssinatura.getText().equals("  /  /    ")) ContratoPai.setAssinatura(new SimpleDateFormat("dd/MM/yyyy").parse(
                    ftfAssinatura.getText()));
            if (!ftfVigencia.getText().equals("  /  /    ")) ContratoPai.setVigencia(new SimpleDateFormat("dd/MM/yyyy").parse(
                    ftfVigencia.getText()));
            if (!ftfVencimento.getText().equals("  /  /    ")) ContratoPai.setVencimento(new SimpleDateFormat("dd/MM/yyyy").parse(
                    ftfVencimento.getText()));
            new ControleContratos().Alterar(ContratoPai);
            JOptionPane.showMessageDialog(this, "Contrato cadastrado com sucesso.","Incluir", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this,"Verifique o campo Data de assinatura\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            ContratoPai.setAgencia(ContratoRollback.getAgencia());
            ContratoPai.setAno(ContratoRollback.getAno());
            ContratoPai.setAssinatura(ContratoRollback.getAssinatura());
            ContratoPai.setConta(ContratoRollback.getConta());
            ContratoPai.setEndereco(ContratoRollback.getEndereco());
            ContratoPai.setIdentificador(ContratoRollback.getIdentificador());
            ContratoPai.setObjeto(ContratoRollback.getObjeto());
            ContratoPai.setRazao(ContratoRollback.getRazao());
            ContratoPai.setReferencia(ContratoRollback.getReferencia());
            ContratoPai.setVencimento(ContratoRollback.getVencimento());
            ContratoPai.setVigencia(ContratoRollback.getVigencia());
            return;
        }catch (Exception ex) {
            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(this,"Já existe um contrato com esse número nesse ano\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
            Logger.getLogger(informCadContratos.class.getName()).log(Level.SEVERE, null, ex.getCause());
            ContratoPai.setAgencia(ContratoRollback.getAgencia());
            ContratoPai.setAno(ContratoRollback.getAno());
            ContratoPai.setAssinatura(ContratoRollback.getAssinatura());
            ContratoPai.setConta(ContratoRollback.getConta());
            ContratoPai.setEndereco(ContratoRollback.getEndereco());
            ContratoPai.setIdentificador(ContratoRollback.getIdentificador());
            ContratoPai.setObjeto(ContratoRollback.getObjeto());
            ContratoPai.setRazao(ContratoRollback.getRazao());
            ContratoPai.setReferencia(ContratoRollback.getReferencia());
            ContratoPai.setVencimento(ContratoRollback.getVencimento());
            ContratoPai.setVigencia(ContratoRollback.getVigencia());
            return;
        }
        preencherCampos();
        rbtCPF.setEnabled(false);
        ftfAgencia.setEnabled(false);
        ftfCPFCNPJ.setEnabled(false);
        rbtCNPJ.setEnabled(false);
        txtEndereco.setEnabled(false);
        txtRazao.setEnabled(false);
        txtReferencia.setEnabled(false);
        cbxObjeto.setEnabled(false);
        ftfAssinatura.setEnabled(false);
        ftfVencimento.setEnabled(false);
        ftfConta.setEnabled(false);
        ftfAno.setEnabled(false);
        btnSalvarDetalhes.setEnabled(false);
        btnCancelarDetalhes.setEnabled(false);
        btnEditarDetalhes.setEnabled(true);
        ftfVigencia.setEnabled(false);
    }//GEN-LAST:event_btnSalvarDetalhesActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        try {
            for (int i = 0; i < tblAditivos.getRowCount(); i++) {
                vListaAdit.get(i).setDescricao(tblAditivos.getValueAt(i, 1).toString());
                vListaAdit.get(i).setNumero(Integer.parseInt(tblAditivos.getValueAt(i, 0).toString()));
                vCtrlAdit.Alterar(vListaAdit.get(i));
            }
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Alterações realizadas com sucesso.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar aditivo(s):\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void ftfNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfNumeroFocusLost
        // TODO add your handling code here:
        if (ftfNumero.getText().equals("")) ftfNumero.setValue(null);
    }//GEN-LAST:event_ftfNumeroFocusLost

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Confirma a exclusão deste aditivo?", "Confirmação", JOptionPane.YES_NO_OPTION) == 
                JOptionPane.YES_OPTION){
            if (tblAditivos.getSelectedRow() != -1){
                try {
                    vCtrlAdit.Excluir(vListaAdit.get(tblAditivos.getSelectedRow()));
                    atualizarTabela();
                    JOptionPane.showMessageDialog(this, "Exclusão realizada com sucesso.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir aditivo:\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else JOptionPane.showMessageDialog(this, "Selecione um aditivo.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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
    private javax.swing.JButton btnAdcionar;
    private javax.swing.JButton btnCancelarDetalhes;
    private javax.swing.JButton btnEditarDetalhes;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnObjetos;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarDetalhes;
    private javax.swing.JComboBox<String> cbxObjeto;
    private javax.swing.JFormattedTextField ftfAgencia;
    private javax.swing.JFormattedTextField ftfAno;
    private javax.swing.JFormattedTextField ftfAssinatura;
    private javax.swing.JFormattedTextField ftfAssinaturaAditivo;
    private javax.swing.JFormattedTextField ftfCPFCNPJ;
    private javax.swing.JFormattedTextField ftfConta;
    private javax.swing.JFormattedTextField ftfNumero;
    private javax.swing.JFormattedTextField ftfVencimento;
    private javax.swing.JFormattedTextField ftfVigencia;
    private javax.swing.ButtonGroup gpbDetalhes;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblAssinaturaAditivo;
    private javax.swing.JLabel lblConta;
    private javax.swing.JLabel lblDataAssinatura;
    private javax.swing.JLabel lblDataVencimento;
    private javax.swing.JLabel lblDataVigencia;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblObjeto;
    private javax.swing.JLabel lblRazao;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JPanel panAditivos;
    private javax.swing.JPanel panDetalhes;
    private javax.swing.JRadioButton rbtCNPJ;
    private javax.swing.JRadioButton rbtCPF;
    private javax.swing.JScrollPane spnAditivos;
    private javax.swing.JTable tblAditivos;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtRazao;
    private javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables
}
