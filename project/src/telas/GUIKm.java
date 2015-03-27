package telas;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

import objetos.Agencia;
import objetos.Pagamento;
import credito.SistemaCredito;
import daos.DAOPagamento;

public class GUIKm extends JDialog implements ActionListener{
	
	
		private JLabel label;
		private JTextField txt;
		private ResourceBundle bn;		
		private JButton ok, cancelar;
		private double km;
		
		
		
		public GUIKm(JFrame fr, ResourceBundle bn,String valor){
			super(fr,true);
			
			setLayout(new FlowLayout());
			this.bn = bn;
			Container co = getContentPane();
			

			
			label     = new JLabel(valor);

			ok 	= new JButton(bn.getString("guicartcred.ok"));
			cancelar = new JButton(bn.getString("guicartcred.cancelar"));	
			 ok.addActionListener(this);
			 cancelar.addActionListener(this);
			
			
			txt	= new JTextField(10);
			
			
			//Lugar das lable, buttons e radiobuttons		

			co.add(label);
			co.add(txt);
			co.add(ok);
			co.add(cancelar);			
			
			
			setTitle("BSR CAR");
			setSize(380,130);
			setResizable(false);
			this.setLocationRelativeTo(null); 
			setVisible(true);
			
		}

	public void actionPerformed(ActionEvent evento) {
			
			if(ok == evento.getSource()){
				boolean opt = false;							
					try{
						if(txt.getText().equals("")){
							JOptionPane.showMessageDialog(null,bn.getString("guidevolucao.denovo") );
						}else{
							km = Double.parseDouble(txt.getText());	
							dispose();
						}
					}catch (Exception e){
						JOptionPane.showMessageDialog(null,bn.getString("guidevolucao.denovo"));
					}
				
				
			}else if(cancelar == evento.getSource()){					
					dispose();
			}
			
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}
}
	
	

