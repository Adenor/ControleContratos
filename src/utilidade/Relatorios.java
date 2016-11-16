/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidade;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.HashMap;
import java.util.Map;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.PrintHelper;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewControllerImpl;
import ro.nextreports.engine.Report;
import ro.nextreports.engine.ReportRunner;
import ro.nextreports.engine.exporter.exception.NoDataFoundException;
import ro.nextreports.engine.util.LoadReportException;
import ro.nextreports.engine.util.ReportUtil;


/**
 *
 * @author amrsilva
 */
public class Relatorios {

    public Connection createConnection() throws ClassNotFoundException, SQLException { 
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://"+formularios.formPrincipal.SERVIDOR+":3306/contratosbd?user="+
                formularios.formPrincipal.USUARIO+"&password="+formularios.formPrincipal.SENHA;
        System.out.println("Connect to '" + url + "'");
        return DriverManager.getConnection(url);
    }
    
    public static Report loadReport() throws FileNotFoundException, LoadReportException {
        String file = "./rptContratos.report";
        Report report = ReportUtil.loadReport(new FileInputStream(file));
        return report;    	
    }
 
    public void runReport(String Campo, Integer[] Valores) throws Exception, NoDataFoundException {
        Map<String, Object> Parametros = new HashMap<String, Object>();
        Parametros.put(Campo, Valores);
        ReportRunner runner = new ReportRunner();
        runner.setConnection(createConnection());
        runner.setReport(loadReport());
        runner.setQueryTimeout(60); // optional
        if (Valores.length>0)runner.setParameterValues(Parametros);// optional (for no parameters);
        runner.setFormat(ReportRunner.PDF_FORMAT);
        runner.run(new FileOutputStream("./Contratos.pdf"));
    }
    
    public void MostrarRelatorio(String Relatorio) throws URISyntaxException {

        // build a controller
        SwingController controller = new SwingController();
        // Build a SwingViewFactory configured with the controller
        SwingViewBuilder factory = new SwingViewBuilder(controller);
        JPanel viewerComponentPanel = factory.buildViewerPanel();
        // add copy keyboard command
        ComponentKeyBinding.install(controller, viewerComponentPanel);
        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
        new org.icepdf.ri.common.MyAnnotationCallback(
        controller.getDocumentViewController()));
        // Use the factory to build a JPanel that is pre-configured
        //with a complete, active Viewer UI.
        // Create a JFrame to display the panel in
        JFrame window = new JFrame("Controle Contratos");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.getContentPane().add(viewerComponentPanel);
        window.pack();
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/Icone.png")));
        window.setVisible(true);
        // Open a PDF document to view
        controller.openDocument(Relatorio);
        controller.setPageFitMode(DocumentViewControllerImpl.PAGE_FIT_WINDOW_HEIGHT, true);
        controller.getViewModel().setPrintHelper(new PrintHelper(controller.getDocumentViewController().getViewContainer(),
                controller.getPageTree(), 0, MediaSizeName.ISO_A4, javax.print.attribute.standard.PrintQuality.DRAFT));
    }

}
