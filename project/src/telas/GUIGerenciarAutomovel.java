package telas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objetos.Automovel;

public class GUIGerenciarAutomovel extends JFrame implements ActionListener {

	
	private JLabel lchassi,lplaca,lcidade,lkm,lmodelo,lfabricante,ltfkmlivre,ltfmkcon,lgrupo, lestado,lmsgauto,lano, lstatus;
	private static JTextField tchassi,tplaca,tcidade,tkm,tmodelo,tfabricante,ttfkmlivre,ttfkmcon,tmsgauto,tlano,tstatus;
	private static JButton cadastrar, consultar,alterar,excluir, cancelar, limpar;
	private static JComboBox<String> cgrupo = new JComboBox<String>();
	private static JComboBox<String> cestado = new JComboBox<String>();
	private ResourceBundle bn = null;
	private Icon limp;
	

	public GUIGerenciarAutomovel (ResourceBundle bn){
		Container cont = getContentPane();
		this.bn = bn;
		setLayout(null);

		lchassi     = new JLabel(bn.getString("guimantauto.chassi"));
		lestado     = new JLabel(bn.getString("guimantauto.estado"));
		lplaca      = new JLabel(bn.getString("guimantauto.placa"));
		lcidade     = new JLabel(bn.getString("guimantauto.cidade"));
		lkm         = new JLabel(bn.getString("guimantauto.km"));
		lmodelo     = new JLabel(bn.getString("guimantauto.modelo"));
		lfabricante = new JLabel(bn.getString("guimantauto.fabricante"));
		ltfkmlivre  = new JLabel(bn.getString("guimantauto.tarifakmli"));
		ltfmkcon    = new JLabel(bn.getString("guimantauto.tarifamcon"));
		lgrupo      = new JLabel(bn.getString("guimantauto.grupo"));
		lmsgauto    = new JLabel(bn.getString("guimantauto.msgplacaauto"));
		lano	    = new JLabel(bn.getString("guimantauto.ano"));
		lstatus	    = new JLabel(bn.getString("guimantauto.status"));

		tchassi     = new JTextField(50);
		tplaca      = new JTextField(2);
		tcidade     = new JTextField(10);
		tkm         = new JTextField(10);
		tmodelo     = new JTextField(2);
		tfabricante = new JTextField(10);
		ttfkmlivre  = new JTextField(5);
		ttfkmcon    = new JTextField(10);
		tmodelo 	= new JTextField(20);
		tmsgauto 	= new JTextField(20);
		tlano	 	= new JTextField(20);
		tstatus 	= new JTextField(20);
		tkm         = new JTextField(20);

		cadastrar 	= new JButton(bn.getString("guimantcli.cadastrar"));
		alterar   	= new JButton(bn.getString("guimantcli.alterar"));
		excluir   	= new JButton(bn.getString("guimantcli.excluir"));
		consultar	= new JButton(bn.getString("guimantcli.consultar"));
		cancelar 	= new JButton(bn.getString("guimantcli.cancelar"));		
		
		limp = new ImageIcon(getClass().getResource("/telas/borracha.png"));
		limpar  = new JButton(limp);
		cadastrar.addActionListener(this);
		alterar.addActionListener(this);
		excluir.addActionListener(this);
		consultar.addActionListener(this);
		cancelar.addActionListener(this);

		//lugares das label		
		lmsgauto.setBounds(25,10,160,80); 
		lfabricante.setBounds(25,10,100,200); 
		lmodelo.setBounds(25,10,100,270); 
		lplaca.setBounds(25,10,100,340);
		lano.setBounds(25,10,100,410);  
		lchassi.setBounds(25,10,100,480);        
		lcidade.setBounds(25,10,100,550);        
		lestado.setBounds(25,10,100,620);
		lkm.setBounds(25,10,100,690);                
		ltfmkcon.setBounds(25,10,140,760); 
		ltfkmlivre.setBounds(25,10,100,830);   
		lgrupo.setBounds(25,10,100,900);
		lstatus.setBounds(25,10,100,970); 
		//lkm.setBounds(25,10,100,1200); 

		limpar.addActionListener(this);
        limpar.setBorder(null);
        limpar.setBounds(550,35,40,25); 

		//lugares dos textfield
		tmsgauto.setBounds(200,40,140,20);
		tfabricante.setBounds(160,100,220,20);    
		tmodelo.setBounds(160,135,140,20);       
		tplaca.setBounds(160,170,140,20);
		tlano.setBounds(160,205,100,20);   
		tchassi.setBounds(160,240,100,20);   
		tcidade.setBounds(160,275,140,20);      
		cestado.setBounds(160,310,50,20);  
		tkm.setBounds(160,345,50,20);  
		ttfkmcon.setBounds(160,380,90,20);
		ttfkmlivre.setBounds(160,415,90,20); 
		cgrupo.setBounds(160,450,220,20);
		tstatus.setBounds(25,10,100,970); 
		//tkm.setBounds(25,10,130,1200); 
		
		// lugares dos buttons
		cadastrar.setBounds(50,550,100,25);
		alterar.setBounds(200,550,100,25);
		excluir.setBounds(350,550,100,25);
		consultar.setBounds(380,35,90,25); 
		cancelar.setBounds(500,550,90,25);
		
		
		//add ao container lable			textfield e combobox
		cont.add(lmsgauto);			cont.add(tmsgauto); 
		cont.add(lchassi);          cont.add(tchassi);
		cont.add(lplaca);        	cont.add(tplaca);
		cont.add(lcidade);			cont.add(tcidade);
		cont.add(lkm);				cont.add(tkm);
		cont.add(lmodelo);			cont.add(tmodelo);
		cont.add(lestado);			cont.add(cestado);
		cont.add(lfabricante);		cont.add(tfabricante);
		cont.add(ltfkmlivre);		cont.add(ttfkmlivre);
		cont.add(ltfmkcon);			cont.add(ttfkmcon);
		cont.add(cgrupo);
		cont.add(lgrupo);			cont.add(limpar);
		cont.add(cadastrar);		
		cont.add(consultar);        
		cont.add(alterar);
		cont.add(excluir);
		cont.add(cancelar);
		cont.add(lano);
		cont.add(tlano);


		//add itens no combobox grupo
		cgrupo.addItem("");
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_economico"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_economicoar"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_intermediario"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_intermediariowe"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_excutivo"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_utlitario"));;
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_executivoluxo"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_intermediarioeexc"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_pickup"));	
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_4x4especial"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_minvan"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_furgao"));
		cgrupo.addItem(bn.getString("guigerenmp/guiconsult.comboboxgrupo_blindado"));
		
		//inicializa os botoes desabilitados         
        alterar.setEnabled(false);
        excluir.setEnabled(false); 
		
		//add items no combo box estado
		String [] estados = {"","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
        for (int i=0; i<estados.length;i++){
        	cestado.addItem(estados[i]);        	
        }

		setTitle(bn.getString("guimantauto.titulo"));
		setVisible(true);
		setSize(650,650);
		setResizable(true);
		this.setLocationRelativeTo(null); 
		tstatus.setEnabled(false);
	}

	public void actionPerformed(ActionEvent evento) {
		
		if(cadastrar == evento.getSource()){
			
			if(tkm.getText().equals("")) {
				tkm.setText("0.0");
			}
			if (ttfkmlivre.getText().equals("")){
				ttfkmlivre.setText("0.0");
			}
			
			if(ttfkmlivre.getText().equals("")){			
				ttfkmlivre.setText("0.0");
			}			
			
			if(tplaca.getText().equals("") || tchassi.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Preencha campos em branco!");
			}else{				
				alterar.setEnabled(true); 
				excluir.setEnabled(true);					
				Automovel auto = new Automovel();	
				try { 
					auto.cadastrarAutomovel(cgrupo.getSelectedIndex(), tchassi.getText(), tplaca.getText(), tcidade.getText(),tmodelo.getText(),
							tfabricante.getText(), Double.parseDouble(tkm.getText()), Double.parseDouble(ttfkmlivre.getText()), Double.parseDouble(ttfkmcon.getText()),
							(String)cestado.getSelectedItem(), false, Integer.parseInt(tlano.getText()));
				} catch (NumberFormatException | SQLException e) {					
					e.printStackTrace();
				}						
				limpar();
				alterar.setEnabled(false); 
				excluir.setEnabled(false);
			}
			
		}else if(alterar == evento.getSource()){
			Automovel auto = new Automovel();	
			try {
				auto.alterarAutomovel(cgrupo.getSelectedIndex(), tchassi.getText(), tplaca.getText(), tcidade.getText(),tmodelo.getText(),
						tfabricante.getText(), Double.parseDouble(tkm.getText()), Double.parseDouble(ttfkmlivre.getText()), Double.parseDouble(ttfkmcon.getText()),
						(String)cestado.getSelectedItem(), false, Integer.parseInt(tlano.getText()));
				cadastrar.setEnabled(false);
				excluir.setEnabled(true);
				alterar.setEnabled(true);
				consultar.setEnabled(true);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}else if(excluir == evento.getSource()){
			int aux = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Automóvel?", "Exclusão", JOptionPane.YES_NO_OPTION);
		    if (aux == JOptionPane.YES_OPTION){
		    	Automovel auto = new Automovel();
		    	
				try {
					auto.excluirAutomovel(tplaca.getText());					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				limpar();
			    cadastrar.setEnabled(true);
				excluir.setEnabled(false);
				alterar.setEnabled(false);
				consultar.setEnabled(true);
		    }				
					
		}else if(consultar == evento.getSource()){
			Automovel  auto = new Automovel();
			try {				
				auto = auto.consultarAutomovel(tmsgauto.getText());								
				
			} catch (SQLException e) {
				e.printStackTrace();
			}						
			
			tchassi.setText(auto.getChassi());
			tplaca.setText(auto.getPlaca());
			tcidade.setText(auto.getCidade());
			tkm.setText(""+ auto.getKm());
			tmodelo.setText(auto.getModelo());
			tfabricante.setText(auto.getFabricante());
			ttfkmlivre.setText("" +auto.getTKmLivre());
			ttfkmcon.setText("" +auto.getTKmCont());						
			tlano.setText("" +auto.getAno());
			cestado.setSelectedItem(auto.getEstado());
			cgrupo.setSelectedIndex(auto.getIdCategoria());
			
			
			if (!tplaca.getText().equals("")){				
				cadastrar.setEnabled(false);
				excluir.setEnabled(true);
				alterar.setEnabled(true);
				consultar.setEnabled(true);							
			}else{
				cadastrar.setEnabled(true);
				excluir.setEnabled(false);
				alterar.setEnabled(false);
				consultar.setEnabled(true);
				limpar();
			}
			
		}else if(cancelar == evento.getSource()){							
			dispose();
		}else if(limpar == evento.getSource()){
			limpar();
		}
	}
	
	 public static void limpar(){         
	    	tmsgauto.setText("");
	    	tchassi.setText("");
	    	tplaca.setText("");
	    	cestado.setSelectedIndex(0); 
	    	cgrupo.setSelectedIndex(0); 
	        tcidade.setText("");
	        tkm.setText("");
	        tmodelo.setText("");
	        tfabricante.setText("");
	        ttfkmlivre.setText("");
	        ttfkmcon.setText("");
	        tlano.setText("");
	        tmsgauto.setRequestFocusEnabled(true);
	        cadastrar.setEnabled(true);
	        alterar.setEnabled(false);
	        excluir.setEnabled(false);
	             
	    }
}
