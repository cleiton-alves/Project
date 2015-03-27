package telas;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import objetos.Agencia;
import objetos.Automovel;
import objetos.Cliente;
import objetos.Emprestimo;
import objetos.ExportPdf;
import objetos.Pagamento;

public class GUIGerenciarEmprestimo extends JFrame implements ActionListener {

	private JLabel lnome,lsexo,lcpf,lrg,les,lnumreg,lvalidade,ldatnasc,lemail,ltel, ldadoscliente,lcodcarro,
				   ldadosemprest,ldatahora,ltipotarifa, lpreventrega,lvalortarifa, locemprest, locemprest2,locentrega,
				   lvalorpagar,lcpfcli,lacessorio,lformpag,lkmrodar;
	
	private JTextField tnome,tsexo,tes,tnumreg,temail,tcodcarro,tvalorpag,tvalortarifa,tlocempre,tkmrodar;
	private static JFormattedTextField tcpf,trg,tvalidade,tdatnasc,tcpfcli,ttel,tdatahora,tpreventrega;
	private JButton prosseguir, voltar, consultar;
	private JComboBox<String> csexo = new JComboBox<String>();
	private JComboBox<String> ces = new JComboBox<String>();
	private JComboBox<String> tipotarifa = new JComboBox<String>();
	private JComboBox<String> lugardevolv = new JComboBox<String>();
	private JCheckBox gps,bebe, motorista;
	private ResourceBundle bn = null;
	private JSeparator sep,sep2;
	private JRadioButton radiodeb, radiocred;
	private JScrollPane scroll;
	private ButtonGroup radioGroup;
	private int codagencia;
	private int codcli;
	private double valorpag;
	String auxvalor;

	public GUIGerenciarEmprestimo (ResourceBundle bn, String campo, int agencia) throws SQLException{		
		this.bn = bn;
		this.codagencia = agencia;		
		JPanel pn = new JPanel();
		
		Container cont = getContentPane();
		pn.setLayout(null);
		
		//Labels		
		lnome     = new JLabel(bn.getString("guigerenemp.nome"));
		lsexo     = new JLabel(bn.getString("guigerenemp.sexo"));
		lcpf      =	new JLabel(bn.getString("guigerenemp.cpf"));
		lrg       = new JLabel(bn.getString("guigerenemp.docindentidade"));
		les       = new JLabel(bn.getString("guigerenemp.es"));
		lnumreg   =	new JLabel(bn.getString("guigerenemp.numreg"));
		lvalidade = new JLabel(bn.getString("guigerenemp.validade"));
		ldatnasc  = new JLabel(bn.getString("guigerenemp.datnasc"));
		lemail    = new JLabel(bn.getString("guigerenemp.email"));
		ltel      = new JLabel(bn.getString("guigerenemp.tel"));
		lcpfcli   = new JLabel(bn.getString("guimantcli.msgcpfcli"));
		lacessorio   = new JLabel(bn.getString("guigerenemp.acessorio"));
		lformpag   = new JLabel(bn.getString("guigerenemp.formpag"));
		
		lcodcarro = new JLabel(bn.getString("guigerenemp.codcarro"));
		ldadoscliente = new JLabel(bn.getString("guigerenemp.dadoscliente"));
		ldadosemprest = new JLabel(bn.getString("guigerenemp.dadosemp"));
		ldatahora = new JLabel(bn.getString("guigerenemp.datahora"));
		lpreventrega = new JLabel(bn.getString("guigerenemp.preventrega"));
		lvalortarifa = new JLabel(bn.getString("guigerenemp.valortarifa"));
		locemprest = new JLabel(bn.getString("guigerenemp.locemp"));
		locentrega = new JLabel(bn.getString("guigerenemp.locent"));
		lvalorpagar = new JLabel(bn.getString("guigerenemp.valorpag"));
		ltipotarifa = new JLabel(bn.getString("guigerenemp.tipotarifa"));
		locemprest2 = new JLabel("");
		lkmrodar = new JLabel(bn.getString("guigerenemp.kmarodar"));
		
		// CheckBox		
		gps = new JCheckBox(bn.getString("guigerenemp.gps"));
		bebe = new JCheckBox(bn.getString("guigerenemp.bebe"));
		motorista = new JCheckBox(bn.getString("guigerenemp.motorista"));
		
		//Texfields		
		tnome     = new JTextField(50);
		tsexo     = new JTextField(2);
		tcpf      = new JFormattedTextField(mascara("###.###.###-##"));
		trg       = new JFormattedTextField(mascara("##.###.###-#"));
		tes       = new JTextField(2);
		tnumreg   = new JTextField(10);
		tvalidade = new JFormattedTextField(mascara("####-##-##"));
		tdatnasc  = new JFormattedTextField(mascara("####-##-##"));
		temail 	  = new JTextField(20);
		ttel 	  = new JFormattedTextField(mascara("####-####"));
		tcpfcli = new JFormattedTextField(mascara("###.###.###-##"));
		tcodcarro   = new JTextField(10);	
		tvalorpag   = new JTextField(10);
		tkmrodar   = new JTextField(10);
		tvalortarifa   = new JTextField(10);
		tdatahora   = new JFormattedTextField();
		tpreventrega   =new JFormattedTextField(mascara("####-##-##"));
		tlocempre   = new JTextField(10);
		sep = new JSeparator();
		sep2 = new JSeparator();
	
		// Buttons, RadioButtons
		prosseguir = new JButton(bn.getString("guigerenemp.prosseguir"));
		voltar = new JButton(bn.getString("guigerenemp.voltar"));
		consultar = new JButton(bn.getString("guimantcli.consultar"));
		
		radiocred = new JRadioButton(bn.getString("guigerenemp.cartaocred"));
		radiodeb = new JRadioButton(bn.getString("guigerenemp.cartaodeb"));
		
		prosseguir.addActionListener(this);
		voltar.addActionListener(this);
		
		//lugares das label, buttons e RadioButtons
		
		lnome.setBounds(15,10,100,280);    
		lsexo.setBounds(500,10,100,280);        
		lcpf.setBounds(15,10,100,350);          
		lrg.setBounds(265,10,100,350);          
		les.setBounds(500,10,100,350);          
		lnumreg.setBounds(15,10,100,420);       
		lvalidade.setBounds(265,10,100,420);   
		ldatnasc.setBounds(500,10,130,420);     
		lemail.setBounds(15,10,100,490);       
		ltel.setBounds(500,10,100,490);
		prosseguir.setBounds(500,760,110,25);
		voltar.setBounds(370,760,110,25);
		consultar.setBounds(350,48,90,25);
		
		lcodcarro.setBounds(15,10,100,30);
		lcpfcli.setBounds(15,10,150,100);
		ldadoscliente.setBounds(15,10,280,170);  ldadoscliente.setFont( new Font( "Serif" , Font.ITALIC , 20 )) ; 
		ldadosemprest.setBounds(15,10,280,600);  ldadosemprest.setFont( new Font( "Serif" , Font.ITALIC, 20 )) ;   
		ldatahora.setBounds(15,10,120,720);     
		lpreventrega.setBounds(300,10,150,720); 
		ltipotarifa.setBounds(15,10,100,800);
		lkmrodar.setBounds(550,10,100,800);
		lvalortarifa.setBounds(300,10,100,800);    
		locemprest.setBounds(15,10,150,910);     
		locemprest2.setBounds(160,10,150,910); 
		locentrega.setBounds(300,10,150,910);   
		lvalorpagar.setBounds(15,10,170,1150);
		lacessorio.setBounds(15,10,150,1010);	lacessorio.setFont( new Font( "Serif" , Font.ITALIC , 20 )) ;
		lformpag.setBounds(20,10,250,1300);	lformpag.setFont( new Font( "Serif" , Font.ITALIC , 20 )) ;
		
		radiocred.setBounds(20,700,140,15);
		radiodeb.setBounds(200,700,140,15);
		
		//lugares dos textfield, combobox e checkbox  e separador
		
		tcodcarro.setBounds(170,15,100,20);
		tcpfcli.setBounds(170,48,140,20);
		tnome.setBounds(105,140,370,20);    
		csexo.setBounds(630,140,90,20);        
		tcpf.setBounds(105,175,140,20);        
		trg.setBounds(375,175,100,20);          
		ces.setBounds(630,173,50,20);          
		tnumreg.setBounds(105,208,140,20);      
		tvalidade.setBounds(375,208,100,20);   
		tdatnasc.setBounds(630,208,100,20);     
		temail.setBounds(105,243,370,20);       
		ttel.setBounds(630,243,100,20);
		tipotarifa.setBounds(160,400,120,20);
		lugardevolv.setBounds(440,455,180,20);  
		tpreventrega.setBounds(440,360,75,20); 
		tvalorpag.setBounds(160,575,80,20);
		tvalortarifa.setBounds(440,400,75,20);
		tkmrodar.setBounds(640,400,75,20);
		tdatahora.setBounds(160,360,80,20); 
		gps.setBounds(15,540,120,20);    
		bebe.setBounds(170,540,140,20);        
		motorista.setBounds(330,540,120,20);      
		sep.setBounds(50, 285, 650, 80);
		sep2.setBounds(50,540, 650, 80);
		sep2.setBounds(50,630, 650, 80);
		
		
		//add radio no grupo
		
		radiodeb.addItemListener(null);
		radiocred.addItemListener(null);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radiocred);
		radioGroup.add(radiodeb);
	
		
		//add itens aos Combobox
		csexo.addItem("");
		csexo.addItem(bn.getString("guimantcli.masculino"));
		csexo.addItem(bn.getString("guimantcli.feminino"));
		
		tipotarifa.addItem("");
		tipotarifa.addItem(bn.getString("guigerenemp.kmlivre"));
		tipotarifa.addItem(bn.getString("guigerenemp.kmcontrolado"));
		tcodcarro.setText(campo);
		
		lugardevolv.addItem("");
		Agencia ag = new Agencia();		
		ArrayList<Agencia> nomes = ag.consultarTodasAgencias();
		for (int i=0; i<nomes.size();i++){
			lugardevolv.addItem(nomes.get(i).getNomeAgencia());

		}
		
		
		

		//add items no combo box estado
		String [] estados = {"","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
        for (int i=0; i<estados.length;i++){
        	ces.addItem(estados[i]);        	
        }
		
		// Adicionando ao container
		pn.add(lcodcarro);
		pn.add(tcodcarro);
		pn.add(lcpfcli);
		pn.add(tcpfcli);
		pn.add(ldadoscliente);
		pn.add(lnome);
		pn.add(tnome);
		pn.add(lsexo);
		pn.add(tsexo);
		pn.add(lcpf);
		pn.add(tcpf);
		pn.add(lrg);
		pn.add(trg);
		pn.add(les);
		pn.add(tes);
		pn.add(lnumreg);
		pn.add(tnumreg);
		pn.add(lvalidade);
		pn.add(tvalidade);
		pn.add(ldatnasc);
		pn.add(tdatnasc);
		pn.add(lemail);
		pn.add(temail);
		pn.add(ltel);
		pn.add(ttel);
		pn.add(prosseguir);
		pn.add(voltar);
		pn.add(consultar);
		pn.add(csexo);
		pn.add(ces);
		pn.add(ldadosemprest);
		pn.add(ldatahora);
		pn.add(tdatahora);
		pn.add(lpreventrega);
		pn.add(ltipotarifa);
		pn.add(tipotarifa);
		pn.add(locemprest);
		pn.add(tlocempre);
		pn.add(lvalortarifa);
		pn.add(tvalortarifa);
		pn.add(locentrega);
		pn.add(lugardevolv);
		pn.add(lvalorpagar);
		pn.add(tvalorpag);
		pn.add(tvalortarifa);
		pn.add(tpreventrega);
		pn.add(lacessorio);
		pn.add(gps);
		pn.add(bebe);
		pn.add(motorista);
		pn.add(lformpag);
		pn.add(radiocred);
		pn.add(radiodeb);		
		pn.add(sep);
		pn.add(sep2);
		pn.add(locemprest2);
		pn.add(lkmrodar);
		pn.add(tkmrodar);
		
		
		consultar.addActionListener(this);
		
		voltar.addActionListener(this);
		
		tipotarifa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent){
				int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
			}			
		});
		// copia esse
			tkmrodar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent actionEvent){
					int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
			});
		
		tpreventrega.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar data = Calendar.getInstance();
				long dias =0;
				try {
					dias = calcDias(sdf.format(data.getTime()), tpreventrega.getText());
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Data não permitida!");
					//e1.printStackTrace();
				}				
				if(dias < 0){
					JOptionPane.showMessageDialog(null, "Data não permitida!");
					tpreventrega.setText("");
				}
				
				
				
				int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
			}			
		});
		
		motorista.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent){
				int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
			}			
		});
		
		gps.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent){
				int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
			}			
		});
		
		bebe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent){
				int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
			}			
		});	
		
		lugardevolv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent){
				int index = tipotarifa.getSelectedIndex();				
					try {
						mostrarTarifa(index);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
			}			
		});
         
		
		pn.setPreferredSize(new Dimension(730,820));
		scroll = new JScrollPane(pn);
		
		scroll.getViewport().add(pn);
		cont.add(scroll);
		setTitle(bn.getString("guigerenemp.titulo"));
		setVisible(true);
		setSize(770,700);
		setResizable(true);
		this.setLocationRelativeTo(null); 
		
		tnome.setEnabled(false);    
		tsexo.setEnabled(false);
		tcpf.setEnabled(false);       
		trg.setEnabled(false);       
		tes.setEnabled(false);          
		tnumreg.setEnabled(false);       
		tvalidade.setEnabled(false); 
		tdatnasc.setEnabled(false);  
		temail.setEnabled(false);       
		ttel.setEnabled(false);		
		ces.setEnabled(false);
		csexo.setEnabled(false);		
		tcodcarro.setEnabled(false);
		tkmrodar.setEnabled(false);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar data = Calendar.getInstance();
		
		//inicialização da tela com alguns dados
		tdatahora.setText(sdf.format(data.getTime()));
		tdatahora.setEnabled(false);
		
			
		ag = ag.consultarAgencia(codagencia);
		locemprest2.setText(ag.getNomeAgencia()); 
		
	}
	// copia esse
	private void mostrarTarifa(int index) throws ParseException {
		tvalortarifa.setText("");
		try {
			Automovel auto = new Automovel();
			auto = auto.consultarAutomovel(Integer.parseInt(tcodcarro.getText()));
			valorpag=0;
			double acres = 0;
	         if (motorista.isSelected()){
	         	acres+=300.0;
	         }
	         if (gps.isSelected()){
	         	acres+=50.0;
	         }
	         if (bebe.isSelected()){
	         	acres+=150.0;
	         }	         
	         valorpag+=acres;
	         
	          double maisacres = 0;
	         if(!locemprest2.getText().equals(lugardevolv.getSelectedItem())){	
	        	if(lugardevolv.getSelectedIndex() != 0){
					Agencia agen = new Agencia();
					String cidadeEmp = agen.consultarAgencia(codagencia).getCidadeAgencia();
					String cidadePrev = agen.consultarAgencia(lugardevolv.getSelectedIndex()).getCidadeAgencia();
					maisacres+=30.00; // se agencia devolvida nao for a mesma da emprestada
										
					if(!cidadeEmp.equals(cidadePrev)){
						maisacres+=20.00; // se a cidade for diferente
					}
	        	}
			}else {//se for na mesma nao será cobrado
				maisacres = 0;
			}
	         
	        valorpag+=maisacres;
			
			if(index == 1){				
				tvalortarifa.setText(""+auto.getTKmLivre());
				String aux  = ""+ calcDias(tdatahora.getText(),tpreventrega.getText());
				valorpag +=  Double.parseDouble(aux) * Double.parseDouble(tvalortarifa.getText());
				tvalorpag.setText(""+valorpag);
				tkmrodar.setText("");
				tkmrodar.setEnabled(false);
				
			}else if(index ==2){				
				tvalortarifa.setText(""+auto.getTKmCont());				
				tkmrodar.setEnabled(true);
				if(tkmrodar.getText().equals("")){					
				}else{
					valorpag+=Double.parseDouble(tkmrodar.getText()) *  Double.parseDouble(tvalortarifa.getText());
					tvalorpag.setText(""+valorpag);
		 		}
			}else{
				tvalortarifa.setText("");
				tkmrodar.setText("");
				tkmrodar.setEnabled(false);
			}
			
		} catch (NumberFormatException | SQLException e) {				
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent evento) {	
		Cliente cli = new Cliente();
		Emprestimo emp  = new Emprestimo();
		
		int codpagamento = 0;
		int codagenciaemp = 0;
		int codagenciadev = 0;
		
		if(prosseguir == evento.getSource()){
			Agencia ag = new Agencia();
			Automovel auto = new Automovel();
			try {
				auto = auto.consultarAutomovel(Integer.parseInt(tcodcarro.getText()));
			} catch (NumberFormatException | SQLException e1) {				
				e1.printStackTrace();
			}			
			
			if(tnome.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Selecione o cliente.");
			}else
			
			if(lugardevolv.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Selecione agencia para devolução.");
                
			}else if(radiodeb.isSelected() == false && radiocred.isSelected() == false){
                JOptionPane.showMessageDialog(null, "Selecione a forma de pagamento.");
                
            }else if(radiodeb.isSelected()){
            	GUICartaoDebito cartdeb = new GUICartaoDebito(this,bn,tvalorpag.getText());
            	if(cartdeb.getFechou() == false){
            	
            	try {
					codpagamento = cartdeb.getCodPagamento();
				} catch (SQLException e3) {							
					e3.printStackTrace();
				}
                revalidate();
                
                try {
					ag = ag.consultarAgPorNome(locemprest2.getText());
					codagenciaemp = ag.getIdAgencia();
					ag = ag.consultarAgPorNome((String)lugardevolv.getSelectedItem());
					codagenciadev = ag.getIdAgencia();
					
				} catch (SQLException e2) {
			
					e2.printStackTrace();
				}                     
                if(tkmrodar.getText().equals("")){
                	tkmrodar.setText("0.0");
                }
              
               
                
                try {
					emp.cadastrarEmprestimo(tdatahora.getText(),tpreventrega.getText(),codagenciaemp,codagenciadev,tipotarifa.getSelectedIndex(),codcli, codpagamento,Integer.parseInt(tcodcarro.getText()), Double.parseDouble(tkmrodar.getText()), auto.getKm(), gps.isSelected(), bebe.isSelected(), motorista.isSelected());
					JOptionPane.showMessageDialog(null,"Empréstimo efetuado com sucesso"); 
					Pagamento pag = new Pagamento();
					pag = pag.consultarPagamento(codpagamento);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    Calendar data = Calendar.getInstance();				    
					auto.alterarStatusAutomovel(Integer.parseInt(tcodcarro.getText()),true, auto.getKm());
					 ExportPdf  pdf = new  ExportPdf();
					 
					 
					 
					 
					 
					 pdf.reciboEmprestimo(pdf.getCaminho(),emp.consultaIdEmprestimo(), pag.getTitular() , pag.getCpf(), auto.getModelo(),"Cartão de Débito - " + pag.getBanco(),pag.getValor(), sdf.format(data.getTime()));	
					dispose();
				} catch (NumberFormatException e){
					e.printStackTrace();					
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Não foi possivel efetuar o empréstimo.");
				} catch (Exception e) {		
					e.printStackTrace();
				}                  
            	} 
            }else if(radiocred.isSelected()){
            	
            	
            	GUICartaoCredito cartcred = new GUICartaoCredito(this,bn,tvalorpag.getText());
            	
            	if(cartcred.getFechou() == false){
            	try {
					codpagamento = cartcred.getCodPagamento();
				} catch (SQLException e3) {							
					e3.printStackTrace();
				}
                revalidate();
                try {
					ag = ag.consultarAgPorNome(locemprest2.getText());
					codagenciaemp = ag.getIdAgencia();
					ag = ag.consultarAgPorNome((String)lugardevolv.getSelectedItem());
					codagenciadev = ag.getIdAgencia();
					
				} catch (SQLException e2) {
			
					e2.printStackTrace();
				}            
                    
                    // fiz esse if pq ta dando problema pra cadastrar com klivre, pq o campo do km controlado tava em branco, 
                    //ai eu seitem 0.0 pra conseguir cadastrar   
                if(tkmrodar.getText().equals("")){
                	tkmrodar.setText("0.0");                	
                }
                    
                try {
            		
					emp.cadastrarEmprestimo(tdatahora.getText(),tpreventrega.getText(),codagenciaemp,codagenciadev,tipotarifa.getSelectedIndex(),codcli, codpagamento,Integer.parseInt(tcodcarro.getText()), Double.parseDouble(tkmrodar.getText()), auto.getKm(), gps.isSelected(), bebe.isSelected(), motorista.isSelected());
					JOptionPane.showMessageDialog(null,"Empréstimo efetuado com sucesso");
					
					
					Pagamento pag = new Pagamento();
					
					pag = pag.consultarPagamento(codpagamento);
					auto.alterarStatusAutomovel(Integer.parseInt(tcodcarro.getText()),true, auto.getKm());
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    Calendar data = Calendar.getInstance();
					
					
					
					 ExportPdf  pdf = new  ExportPdf();
					 
				
					 
					 
					 pdf.reciboEmprestimo(pdf.getCaminho(),emp.consultaIdEmprestimo(), pag.getTitular() , pag.getCpf(), auto.getModelo(),"Cartão de Crédito - " + pag.getBandeiraCartao(),pag.getValor(), sdf.format(data.getTime()));
					   
                        dispose();
				} catch (NumberFormatException e){
					e.printStackTrace();
					
				} catch (SQLException e) {
					e.printStackTrace();
					
					JOptionPane.showMessageDialog(null,"Não foi possivel efetuar o empréstimo.");
				} catch (Exception e) {
	
					e.printStackTrace();
				}
				
                try {
					auto.alterarStatusAutomovel(Integer.parseInt(tcodcarro.getText()),true, auto.getKm());
					  
                        dispose();
				} catch (NumberFormatException e){
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}                     
             
            }
		}	

		}else if(voltar == evento.getSource()){			
				dispose();
		}else if(consultar ==evento.getSource()){			
			
			try {
				cli = cli.consultarCliente(tcpfcli.getText().replace(".","").replace("-",""));
				tnome.setText(cli.getNome());
				tsexo.setText(cli.getSexo());
				tcpf.setText(cli.getCpf());
				trg.setText(cli.getDocIdentidade());
				tes.setText(cli.getEstadoEmissor());
				tnumreg.setText(cli.getNregistro());
				tvalidade.setText(cli.getValidadeCnh());
				tdatnasc.setText(cli.getDataNasc());
				temail.setText(cli.getEmail());
				ttel.setText(cli.getTelefone());
				csexo.setSelectedItem(cli.getSexo());
				ces.setSelectedItem(cli.getEstadoEmissor());			
				codcli = cli.getId();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
				e.printStackTrace();
			}
		}
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
	
	 
	 public long calcDias(String atual, String entrega)throws ParseException {  
	        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");  
	        df.setLenient(false);  
	        java.util.Date d1 =   df.parse (atual);   
	        java.util.Date d2 =   df.parse (entrega);    
	        long dt = (d2.getTime() - d1.getTime()) + 3600000; 
	        if (dt / 86400000L == 0){
	        	return (dt / 86400000L) + 1;
	        } else{
	        	return (dt / 86400000L);
	        }
	 }
	 
	 
	 
	 

}	
	


