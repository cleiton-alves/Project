package telas;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import objetos.Pagamento;
import credito.SistemaCredito;
import debito.SistemaDebito;


public class GUICartaoDebito extends JDialog implements ActionListener{

	private JLabel ltitular, lcpf, lagencia, ltelefone, lconta, lfrase, lpagar,itau,
					bradesco,bancobrasil,caixa,ltotalpag;
	private JTextField ttitular;
	private JFormattedTextField tagencia, tconta, ttelefone, tcpf; 
	private JButton ok, cancelar;
	private JRadioButton radio1,radio2,radio3,radio4;
	private ResourceBundle bn = null;	
	private Icon iconitau, iconbrasil,iconcaixa,iconbrad;
	private ButtonGroup radioGroup;
	private boolean validado;
	private int codpagamento;	
	private String agencia;
	private boolean fechou = false;
	String valor ;
	
	
	public GUICartaoDebito(JFrame fr, ResourceBundle bn,String valor){
		super(fr,true);
		this.bn = bn;
		setLayout(null);
		this.valor = valor;
		Container co = getContentPane();
		
		
		lagencia     = new JLabel(bn.getString("guicartdeb.agencia"));
		lcpf      	 = new JLabel(bn.getString("guicartdeb.cpf"));
		lconta     = new JLabel(bn.getString("guicartdeb.conc"));
		ltitular     = new JLabel(bn.getString("guicartdeb.titular"));
		ltelefone 	 = new JLabel(bn.getString("guicartdeb.tel"));
		lfrase 		 = new JLabel(bn.getString("guicartdeb.frase"));
		lpagar 		 = new JLabel(bn.getString("guicartdeb.pagar"));
		
		NumberFormat numb = NumberFormat.getCurrencyInstance();
		ltotalpag 		 = new JLabel(numb.format(Double.parseDouble(valor)));
		
		cancelar = new JButton(bn.getString("guicartdeb.cancelar"));
		ok 		 = new JButton(bn.getString("guicartdeb.ok"));
		
		
		ttitular 	= new JTextField(10);
		tcpf    	= new JFormattedTextField(mascara("###.###.###-##"));
		tagencia 	= new JFormattedTextField(mascara("####"));
		ttelefone  	= new JFormattedTextField(mascara("####-####"));
		tconta  	= new JFormattedTextField(mascara("#####-#"));
		radio1 		= new JRadioButton();
		radio2 		= new JRadioButton();
		radio3 		= new JRadioButton();
		radio4 		= new JRadioButton();
		
		iconbrad  	= new ImageIcon(getClass().getResource("/telas/logoBradesco.png"));
		iconbrasil  = new ImageIcon(getClass().getResource("/telas/logoBancoBrasil.png"));
		iconcaixa    = new ImageIcon(getClass().getResource("/telas/logoCaixa.png"));
		iconitau    = new ImageIcon(getClass().getResource("/telas/logoItau.png"));
		
		
		bradesco 	 = new JLabel(iconbrad);
		caixa 	 	 = new JLabel(iconcaixa);
		bancobrasil  = new JLabel(iconbrasil);
		itau		 = new JLabel(iconitau);

		
		
		
		//Lugar das lable, buttons e radiobuttons
		
		lfrase.setBounds(130,5,900,30);                 // lfrase.setFont( new Font( "Serif" , Font.PLAIN , 20 )); 

	    ltitular.setBounds(25,120,900,30); 
	    lconta.setBounds(25,155,900,30);    
	    lagencia.setBounds(25,190,900,30);   
		lcpf.setBounds(25,225,900,30);  
		ltelefone.setBounds(25,260,900,30);
		lpagar.setBounds(25,295,900,30); 
		ltotalpag.setBounds(140,295,900,30); 
		ok.setBounds(280,360,90,25);
		cancelar.setBounds(170,360,90,25);
		
		radio1.setBounds(70,95,20,20);
		radio2.setBounds(170,95,20,20);
		radio3.setBounds(270,95,20,20);
		radio4.setBounds(370,95,20,20);
		
		itau.setBounds(20,40,120,50);
		bradesco.setBounds(120,40,120,50);
		caixa.setBounds(220,40,120,50);
		bancobrasil.setBounds(320,40,120,50);
		
		//lugar dos textfield e combobox
		

		ttitular.setBounds(140,125,300,20);
		tconta.setBounds(140,160,120,20); 
		tagencia.setBounds(140,195,120,20);
		tcpf.setBounds(140,230,120,20); 
		ttelefone.setBounds(140,265,120,20);
		

		// Adicionando ao Container
		
		co.add(lfrase);
		co.add(lagencia);
		co.add(tagencia);
		co.add(lconta);
		co.add(tconta);
		co.add(ltitular);
		co.add(ttitular);
		co.add(lcpf);
		co.add(tcpf);
		co.add(ltelefone);
		co.add(ttelefone);
		co.add(ok);
		co.add(cancelar);
		co.add(lpagar);
		co.add(ltotalpag);
		co.add(radio1);
		co.add(radio2);
		co.add(radio3);
		co.add(radio4);
		co.add(itau);
		co.add(bradesco);
		co.add(caixa);
		co.add(bancobrasil);
		
		// agrupando radiobuttons
	 radioGroup = new ButtonGroup();
		 
		 radioGroup.add(radio1);
		 radioGroup.add(radio2);
		 radioGroup.add(radio3);
		 radioGroup.add(radio4);
		
		
		
		//Adcionando os botoes ao actionlistener
		
		ok.addActionListener(this);
		cancelar.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	fechou = true;
            }
        });
		
		
		
		
		
		setTitle(bn.getString("guicartdeb.titulo"));
		setSize(470,450);
		setResizable(false);
		this.setLocationRelativeTo(null); 
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent evento) {
		
		if(ok == evento.getSource()){
			if(radio1.isSelected() == false && radio2.isSelected() == false && radio3.isSelected() == false && radio4.isSelected() == false){
				JOptionPane.showMessageDialog(null, "Selecione o banco.");
			}else{
			SistemaDebito sist = new SistemaDebito();
			
			radio1.setActionCommand("Itau"); 
			radio2.setActionCommand("Bradesco"); 
			radio3.setActionCommand("CaixaEconomica"); 
			radio4.setActionCommand("BancoDoBrasil"); 
			 
			String banco = radioGroup.getSelection().getActionCommand();  
					try {
						if(sist.verficarDados(banco,ttitular.getText(), tconta.getText().replace("-","") , tagencia.getText(),tcpf.getText().replace(".","").replace("-",""),ttelefone.getText().replace("-",""))){
								Pagamento pag = new Pagamento();
								System.out.println("sai da view debito");								
								pag.cadastrarPagamentoDebito(banco,tconta.getText().replace("-",""),Double.parseDouble(valor),ttitular.getText(), tcpf.getText().replace(".","").replace("-",""), true,ttelefone.getText().replace("-",""),tagencia.getText());
								//codpagamento = pag.consultaIdPagamento();
								validado = true;
								dispose();
								
							}else{
								JOptionPane.showMessageDialog(null, "Dados incorretos");
							}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Não foi possivel realizar o pagamento");
						e.printStackTrace();
					}			
				}	
		
		}else
		
			if(cancelar == evento.getSource()){
				
				dispose();
				fechou = true;
			}
	}
	
	public boolean getFechou() {
		return fechou;
	}

	public void setFechou(boolean fechou) {
		this.fechou = fechou;
	}

	public boolean isValidado() {
		return validado;
	}
	
	public int getCodPagamento() throws SQLException {
		
		Pagamento pag = new Pagamento();
		return pag.consultaIdPagamento();
	}
	
	 public static MaskFormatter mascara(String Mascara){  
	        
	        MaskFormatter F_Mascara = new MaskFormatter();  
	        try{  
	            F_Mascara.setMask(Mascara);  
	            F_Mascara.setPlaceholderCharacter(' ');   
	        }  
	        catch (Exception e) {  
	        	e.printStackTrace();  
	        }   
	        return F_Mascara;  
	    }

	 
	 
	 
}

	
	
	
	

