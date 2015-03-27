package telas;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import objetos.Agencia;
import objetos.Pagamento;
import credito.SistemaCredito;
import daos.DAOPagamento;


public class GUICartaoCredito extends JDialog implements ActionListener{

	private JLabel lbcartao, ltitular, lcpf, lnumcart, ldatval, lcodseg, lfrase, lpagar,lvisa,lmaster,lamex,ltotalpag;
	private JTextField ttitular;
	private JFormattedTextField tdatval,tnumcart,tcodseg,tcpf;
	private JButton ok, cancelar;
	private JRadioButton visa,mastercard,americanexpress;
	private ResourceBundle bn = null;
	private Icon iconvisa, iconmaster,iconamex;
	private ButtonGroup radioGroup;
	private boolean validado;
	private int codpagamento;
	private boolean fechou = false;
	String valor;
	
	
	

	public GUICartaoCredito(JFrame fr, ResourceBundle bn,String valor){
		super(fr,true);
		this.valor = valor;
		setLayout(null);
		this.bn = bn;
		Container co = getContentPane();
		
		lbcartao     = new JLabel(bn.getString("guicartcred.cartao"));
		ltitular     = new JLabel(bn.getString("guicartcred.titular"));
		lcpf      	 = new JLabel(bn.getString("guicartcred.cpf"));
		lnumcart     = new JLabel(bn.getString("guicartcred.numcartao"));
		ldatval      = new JLabel(bn.getString("guicartcred.dataval"));
		lcodseg  	 = new JLabel(bn.getString("guicartcred.codseg"));
		lfrase 		 = new JLabel(bn.getString("guicartcred.frase"));
		lpagar 		 = new JLabel(bn.getString("guicartcred.pagar"));
		
		NumberFormat numb = NumberFormat.getCurrencyInstance();
		
		ltotalpag 	 = new JLabel(numb.format(Double.parseDouble(valor)));

		ok 	= new JButton(bn.getString("guicartcred.ok"));
		cancelar = new JButton(bn.getString("guicartcred.cancelar"));
		
		 ok.addActionListener(this);
		 cancelar.addActionListener(this);
		
		
		ttitular 	= new JTextField(10);
		tcpf     	= new JFormattedTextField(mascara("###.###.###-##"));
		tnumcart 	= new JFormattedTextField(mascara("####-####-####-####"));
		tdatval  	= new JFormattedTextField(mascara("####-##"));
		tcodseg  	= new JFormattedTextField(mascara("###"));
		
		visa 		 = new JRadioButton();
		mastercard 		 = new JRadioButton();
		americanexpress		 = new JRadioButton();
		
		iconvisa  	= new ImageIcon(getClass().getResource("/telas/visa.png"));
		iconmaster  = new ImageIcon(getClass().getResource("/telas/master.png"));
		iconamex    = new ImageIcon(getClass().getResource("/telas/amex.png"));
		
		lvisa 		 = new JLabel(iconvisa);
		lmaster 	 = new JLabel(iconmaster);
		lamex 		 = new JLabel(iconamex);

		
		
		
		//Lugar das lable, buttons e radiobuttons
		
		lfrase.setBounds(130,10,900,30);                                // lfrase.setFont( new Font( "Serif" , Font.PLAIN , 20 )); 
		lbcartao.setBounds(25,100,900,30); 
	    ltitular.setBounds(25,120,900,30);   
		lcpf.setBounds(25,155,900,30);     	
		lnumcart.setBounds(25,190,900,30);      
		ldatval.setBounds(25,225,900,30);    
		lcodseg.setBounds(25,260,900,30);
		lpagar.setBounds(25,295,900,30); 
		ltotalpag.setBounds(170,295,900,30); 
		cancelar.setBounds(170,360,90,25);
		ok.setBounds(280,360,90,25);
		lvisa.setBounds(50,50,120,35);
		lmaster.setBounds(200,50,120,35);
		lamex.setBounds(340,50,120,35);
		visa.setBounds(100,95,20,20);
		mastercard.setBounds(250,95,20,20);
		americanexpress.setBounds(390,95,20,20);
		
		//lugar dos textfield e combobox
		

		ttitular.setBounds(170,125,300,20);
		tcpf.setBounds(170,160,140,20); 
		tnumcart.setBounds(170,195,140,20); 
		tdatval.setBounds(170,230,100,20); 
		tcodseg.setBounds(170,265,30,20);

		// Adicionando ao Container
		
		co.add(lfrase);
		//co.add(lbcartao);
		//co.add(bandcart);
		co.add(lvisa);
		co.add(lmaster);
		co.add(lamex);
		co.add(ltitular);
		co.add(ttitular);
		co.add(lcpf);
		co.add(tcpf);
		co.add(lnumcart);
		co.add(tnumcart);
		co.add(ldatval);
		co.add(tdatval);
		co.add(lcodseg);
		co.add(tcodseg);
		co.add(ok);
		co.add(cancelar);
		co.add(lpagar);
		co.add(ltotalpag);
		co.add(visa);
		co.add(mastercard);
		co.add(americanexpress);
		
	 radioGroup = new ButtonGroup();
		 
		 radioGroup.add(visa);
		 radioGroup.add(mastercard);
		 radioGroup.add(americanexpress);
		 
		 this.addWindowListener(new WindowAdapter(){
	            public void windowClosing(WindowEvent e){
	            	fechou = true;
	            }
	        });
		
		
		
		
		setTitle(bn.getString("guicartcred.titulo"));
		setSize(510,450);
		setResizable(false);
		this.setLocationRelativeTo(null); 
		setVisible(true);
		
	}

public void actionPerformed(ActionEvent evento) {
		
		if(ok == evento.getSource()){
			if(visa.isSelected() == false && mastercard.isSelected() == false && americanexpress.isSelected() == false ){
				JOptionPane.showMessageDialog(null, "Selecione uma operadora de cartão");
			}else{
			SistemaCredito sist = new SistemaCredito();
			
			visa.setActionCommand("Visa"); 
			mastercard.setActionCommand("MasterCard"); 
			americanexpress.setActionCommand("AmericanExpress"); 
			 
			String cartao = radioGroup.getSelection().getActionCommand();  
					try {
						if(sist.verficarDados(cartao,ttitular.getText(), tcpf.getText().replace(".","").replace("-",""), tnumcart.getText(), tdatval.getText(), tcodseg.getText())){
								Pagamento pag = new Pagamento();
								
								pag.cadastrarPagamentoCredito(cartao,Double.parseDouble(valor),ttitular.getText(), tcpf.getText().replace(".","").replace("-",""), true,tnumcart.getText(), tdatval.getText().replace("-","")+"01", tcodseg.getText());
								codpagamento = pag.consultaIdPagamento();
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
		
		}else if(cancelar == evento.getSource()){
				
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
	
	

		public int getCodpag() {
			return codpagamento;
		}
		
		public void setCodpag(int codpagamento) {
			this.codpagamento = codpagamento;
		}

}
	
	
	

