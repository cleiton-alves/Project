package objetos;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;



public class ExportPdf {
	private String caminho;
	private Icon figura;
	
	public ExportPdf() throws Exception {
		
		JFileChooser chooser;
		 chooser = new JFileChooser();
		 chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 

		caminho = "";
		File file = null;
		
		int retorno = chooser.showSaveDialog(null);
		
		if(retorno == JFileChooser.APPROVE_OPTION){
			
			caminho = chooser.getSelectedFile().getAbsolutePath();
		}
		if(!caminho.equals("")){
			
			file = new File(caminho+".text");
		}else{
			
		}


	}
	
	

		public void reciboEmprestimo(String caminho,int idemprestimo,String cliente, String cpf,String modelo,
				String formapag,double valor,String data) throws IOException, DocumentException{	

			
			
			//System.out.println
			
			Document document = new Document(PageSize.A4.rotate());

		
			//figura = new ImageIcon(getClass().getResource("/telas/visa.png"));
			
			
			PdfWriter.getInstance(document, new FileOutputStream(caminho+"\\"+cliente + "-" + idemprestimo  + ".pdf")); 
			
			document.open();
			
			//document.add((Element) figura);
			document.add(new Paragraph("Recibo de empréstimo  BSR CAR")); 
			
			document.add(new Paragraph("\nNumero do empréstimo :"+ idemprestimo));
			document.add(new Paragraph("\nCliente: "+cliente));
			document.add(new Paragraph("\nCPF: "+cpf)); 
			document.add(new Paragraph("\nModelo do automóvel: "+modelo)); 
			document.add(new Paragraph("\nForma de pagamento: "+formapag)); 
			document.add(new Paragraph("\nValor: "+valor)); 
			document.add(new Paragraph("\nData: "+data)); 
			document.close();
		
		}
		
		
		
public void inserirDados(String cab, JTable table,String caminho) throws IOException, DocumentException{	
			
		    Document document = new Document(PageSize.A4.rotate());
		    
		 
		    try {
		      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho+"\\relatório.pdf"));

		      document.open();
		      PdfContentByte cb = writer.getDirectContent();

		      cb.saveState();
		      Graphics2D g2 = cb.createGraphicsShapes(1000, 400);

		      Shape oldClip = g2.getClip();
		      g2.clipRect(0, 0, 1500, 100);

		      table.print(g2);
		      g2.setClip(oldClip);

		      g2.dispose();
		      cb.restoreState();
		    } catch (Exception e) {
		      System.err.println(e.getMessage());
		    }
		    document.close();
		  }


		
		
		

		
		public String getCaminho() {
			return caminho;
		}

		public void setCaminho(String caminho) {
			this.caminho = caminho;
		}
		
		
}






