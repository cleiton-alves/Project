package telas;

import javax.swing.*;

import objetos.Automovel;
import objetos.Emprestimo;
import objetos.ExportPdf;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUIRelatorio extends JFrame implements ActionListener {

	private ResourceBundle bn = null;;
	private JComboBox comborelat;
	private JButton btgerarrelat,btimprimir,btcancelar;
	private JLabel lval,ltotal;
	
	private Tabela pane;

	
	public  GUIRelatorio(ResourceBundle bn){
	
		this.bn = bn;
		Container cont =  getContentPane();
		setLayout(null);
		
		
		// inicializando jbuttons e combobox
		
		comborelat   = new JComboBox();
		btgerarrelat = new JButton(bn.getString("guirelatorio.gerar"));
		btimprimir   = new JButton(bn.getString("guirelatorio.imprimir"));
		btcancelar   = new JButton(bn.getString("guirelatorio.cancelar"));
		lval 		 = 	new JLabel(bn.getString("guirelatorio.valtotemp"));
		ltotal 		 = 	new JLabel("0.00");

	
		 String[] cab = {"#",bn.getString("guirelatorio.codigo"),
				 			 bn.getString("guirelatorio.fabricante"),
				 			 bn.getString("guirelatorio.modelo"),
				 			 bn.getString("guirelatorio.ano"),
				 			 bn.getString("guirelatorio.cliente"),
				 			 bn.getString("guirelatorio.cpfcliente"),
				 			 bn.getString("guirelatorio.datadev"),
				 			 bn.getString("guirelatorio.valorloc")};

		
		 pane = new Tabela(cab,bn.getString("guirelatorio.relatorio"));

		
		
		// adicionando jbuttons no actionlistener
		
		btgerarrelat.addActionListener(this);
		btimprimir.addActionListener(this);
		btcancelar.addActionListener(this);
		
		
		//localização do combobox, buttons, label e table 
		
		
		
		pane.setBounds(-10,100,1000,300);
		comborelat.setBounds(50,50,200,25);
		btcancelar.setBounds(380,410,100,25);
		btimprimir.setBounds(530,410,150,25);
		btgerarrelat.setBounds(300,50,120,25);
		lval.setBounds(25,100,160,645);
		ltotal.setBounds(190,100,160,645); 
		// adcionando categotia ao comobobox
		
		
		comborelat.addItem(bn.getString("guirelatorio.relatlocdiaria"));
		comborelat.addItem(bn.getString("guirelatorio.relattodasloc"));
	
		
		
		
		
		// adicionando ao container
		
		cont.add(comborelat);
		cont.add(btgerarrelat);
		cont.add(btimprimir);
		cont.add(btcancelar);
		pane.setSizeTableRelatorio();
		cont.add(pane);
		cont.add(lval);
		cont.add(ltotal);
		
	
		
		
		//pack();
	
	
	
		setTitle(bn.getString("guirelatorio.titulo"));
		setVisible(true);
		setSize(1000,500);
		setResizable(true);
		this.setLocationRelativeTo(null); 
	
	
	}

	
	

	public void actionPerformed(ActionEvent event) {
		
		if(btcancelar == event.getSource()){
			
			dispose();
		}else
			if(btimprimir == event.getSource()){
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			
			}else
				if( btgerarrelat == event.getSource()){
					
					Emprestimo emp = new Emprestimo();
					
					int aux = comborelat.getSelectedIndex();
					
					try {
	                    pane.setModel(emp.relatorioAutomoveis(comborelat.getSelectedIndex(),pane));
	                    revalidate();
	                    
	                    if(aux == 0){
	                    	ltotal.setText(""+emp.somaEmprestimo(0));
	                    }else{
	                    	ltotal.setText(""+emp.somaEmprestimo(1));
	                    	
	                    	
	                    }
	
	                     
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
					
				}
	}


}
