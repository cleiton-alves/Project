package telas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import objetos.Agencia;
import objetos.Automovel;
import objetos.Devolucao;
import objetos.Emprestimo;

import java.util.Calendar;

public class GUIDevolverAutomovel extends JFrame implements ActionListener {

	private JLabel lnumeroloc,lsituacao,ldatahrprev,ldatahremp,locprev,locentr,lmulta,lacres,lvalorpag,lformpag, lkm;
	private static JLabel tmulta;
	private static JLabel tsituacaolb;
	private JButton buscar,cancelar,eftpag,ok;
	private static JTextField tnumeroloc,tdatahrprev,tdatahremp,tlocprev,tlocentr,tacres,tvalorpag, tkm;
	private JRadioButton radiodeb, radiocred;
	private int agencia;
	private Emprestimo emp;
	private static double acrescimo, multa=0, valortot;
	private ButtonGroup radioGroup;
	private Automovel auto;
	private Agencia ag;
	private boolean fechou;
	//private String auxmulta = "";
	private String auxvalor;
	
	
	private ResourceBundle bn = null;
	
	public GUIDevolverAutomovel( ResourceBundle bn, final int agencia){
		this.agencia = agencia;				
		this.bn= bn;
	
		Container c = getContentPane();
		setLayout(null);

		//Nome das label		
		lnumeroloc = new JLabel(bn.getString("guidevolucao.numeroloc"));
		lsituacao = new JLabel(bn.getString("guidevolucao.situacao"));
		ldatahrprev = new JLabel(bn.getString("guidevolucao.datahrprev"));
		ldatahremp = new JLabel(bn.getString("guidevolucao.datahremp"));
		locprev = new JLabel(bn.getString("guidevolucao.locentprev"));
		locentr = new JLabel(bn.getString("guidevolucao.locent"));
		lmulta = new JLabel(bn.getString("guidevolucao.muta"));
		lacres = new JLabel(bn.getString("guidevolucao.acres"));
		lvalorpag = new JLabel(bn.getString("guidevolucao.valorpag"));
		lformpag   = new JLabel(bn.getString("guigerenemp.formpag"));
		tsituacaolb	= new JLabel();
		lkm = new JLabel(bn.getString("guidevolucao.kmDev"));
		
		//Nome dos buttons		
		cancelar = new JButton(bn.getString("guidevolucao.cancelar"));
		buscar = new JButton(bn.getString("guidevolucao.buscar"));
		eftpag = new JButton(bn.getString("guidevolucao.efetuarpag"));
		ok = new JButton(bn.getString("guidevolucao.ok"));
		
		//nome dos radiobuttons		
		radiocred = new JRadioButton(bn.getString("guigerenemp.cartaocred"));
		radiodeb = new JRadioButton(bn.getString("guigerenemp.cartaodeb"));
		
		//Add no actionlistener		
		buscar.addActionListener(this);
		cancelar.addActionListener(this);
		eftpag.addActionListener(this);
		ok.addActionListener(this);
		
		// Textfields		
		tnumeroloc 	= new JTextField(10);		
		tdatahrprev	= new JTextField(10);
		tdatahremp	= new JTextField(10);
		tlocprev	= new JTextField(10);
		tlocentr	= new JTextField(10);
		tmulta		= new JLabel();
		tacres		= new JTextField(10);
		tvalorpag	= new JTextField(10);
		tkm			= new JTextField(10);
				
		//local das label		
		lnumeroloc.setBounds(25,10,900,30);                       
		lsituacao.setBounds(25,80,900,30);   
		ldatahrprev.setBounds(25,115,900,30);     	
		ldatahremp.setBounds(25,150,900,30);      
		locprev.setBounds(25,185,900,30);    
		locentr.setBounds(25,220,900,30); 	 
		lmulta.setBounds(25,255,90,30);
		lacres.setBounds(25,290,90,30);
		lkm.setBounds(25,325,120,30);
		lvalorpag.setBounds(25,360,120,30);
		lformpag.setBounds(20,395,250,40);	lformpag.setFont( new Font( "Serif" , Font.ITALIC , 20 )) ;
		
		// local dos buttons		
		buscar.setBounds(350,15,100,25);
		ok.setBounds(145,477,90,25);
		eftpag.setBounds(270,477,145,25);
		cancelar.setBounds(40,477,85,25);		
		
		//local dos Textfield		
		tnumeroloc.setBounds(230,15,95,20);  
		tsituacaolb.setBounds(200,85,120,20); 
		tdatahrprev.setBounds(200,120,120,20); 
		tdatahremp.setBounds(200,155,120,20); 
		tlocprev.setBounds(200,190,120,20); 
		tlocentr.setBounds(200,225,120,20); 
		tmulta.setBounds(200,255,90,30); 
		tacres.setBounds(200,295,80,20); 
		tkm.setBounds(200,330,80,20);
		tvalorpag.setBounds(200,360,80,20); 
		
		
		// local dos radiobutton		
		radiocred.setBounds(20,440,130,15);
		radiodeb.setBounds(200,440,130,15);		
		
		//add ao container		
		c.add(lnumeroloc);
		c.add(lsituacao);
		c.add(ldatahrprev);
		c.add(ldatahremp);
		c.add(locprev);
		c.add(locentr);
		c.add(lmulta);
		c.add(lacres);
		c.add(lvalorpag);
		c.add(buscar);
		c.add(eftpag);
		c.add(lformpag);
		c.add(cancelar);
		c.add(ok);
		c.add(tnumeroloc);
		c.add(radiodeb);
		c.add(radiocred);
		c.add(tsituacaolb);
		c.add(tdatahrprev);
		c.add(tdatahremp);
		c.add(tlocprev);
		c.add(tlocentr);
		c.add(tmulta);
		c.add(tacres);
		c.add(tvalorpag);
		c.add(tkm);
		c.add(lkm);
		
		radiodeb.addItemListener(null);
		radiocred.addItemListener(null);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radiocred);
		radioGroup.add(radiodeb);	
		
		tdatahrprev.setEnabled(false);
		tdatahremp.setEnabled(false);
		tlocprev.setEnabled(false);
		tlocentr.setEnabled(false);
		tkm.setEnabled(false);
		tmulta.setEnabled(false);
		tacres.setEnabled(false);
		tvalorpag.setEnabled(false); 	
		
		
		setTitle(bn.getString("guidevolucao.titulo"));
		setVisible(true);
		setSize(480,550);
		setResizable(false);
		this.setLocationRelativeTo(null);
		
	}	
	

	public void actionPerformed(ActionEvent evento) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar data = Calendar.getInstance();
		long dias=0;
		if( cancelar == evento.getSource()){
			dispose();
		}else if(buscar == evento.getSource()){
			limpar();
			
			try {				
				emp = new Emprestimo();
				auto = new Automovel();
				ag = new Agencia();
				
				emp = emp.consultarEmprestimo(Integer.parseInt(tnumeroloc.getText()));
				
				if (emp.getCodEmp() == Integer.parseInt(tnumeroloc.getText())){
					tdatahrprev.setText(emp.getData_HoraDevPrev());
					tdatahremp.setText(emp.getData_HoraEmp());
					tlocprev.setText(ag.consultarAgencia(emp.getLocalDevPrev()).getNomeAgencia());
					tlocentr.setText(ag.consultarAgencia(agencia).getNomeAgencia());				
					auto = auto.consultarAutomovel(emp.getIdAutomovel());				
	
					
					if((!tlocentr.getText().equals(tlocprev.getText()) || (calcDias(sdf.format(data.getTime()),tdatahrprev.getText()) > 0))){
						tsituacaolb.setText("Pagamento Pendente");
						tsituacaolb.setForeground(Color.RED);
						ok.setEnabled(false);
						eftpag.setEnabled(true);
					}else{
						tsituacaolb.setText("Pagamento ok");
						tsituacaolb.setForeground(Color.GREEN);
						eftpag.setEnabled(false);
						ok.setEnabled(true);
					}			
					 
					//MULTA
					if(emp.getTipoTarifa() == 1){	//livre					
						GUIKm gkm = new GUIKm(this,bn,bn.getString("guidevolucao.msgL"));								
						tkm.setText("" +gkm.getKm());
						dias = calcDias(sdf.format(data.getTime()),tdatahrprev.getText());
						if (dias > 0){
							multa = auto.getTKmLivre() * dias;
						}else if(dias == 0){
							multa = 0;
						}else{
							JOptionPane.showMessageDialog(null, "Entrega antecipada!");
						}
					}else if(emp.getTipoTarifa() == 2){ //controlado copiaaaaaaaaaaaaaaaaaaa
						GUIKm gkm = new GUIKm(this,bn,bn.getString("guidevolucao.msgC"));
						double kmdevolvidos = gkm.getKm() - emp.getKmEmp();
						tkm.setText( ""+gkm.getKm());
						if(kmdevolvidos == 0){
							multa = 0;
						}else if(kmdevolvidos > 0){
							multa = kmdevolvidos * auto.getTKmCont();	
						} 
						//kmEmp = estado que o carro estava quando alugou
						//km é o quanto ele vai rodar
					}			
					
					// ACRESCIMO
					if(!tlocentr.getText().equals(tlocprev.getText())){					
						Agencia agen = new Agencia();
						String cidadeEntrega = agen.consultarAgencia(agencia).getCidadeAgencia();
						String cidadePrev = agen.consultarAgencia(emp.getLocalDevPrev()).getCidadeAgencia(); // testar
						
						if(!tlocentr.equals(agen.consultarAgencia(agencia).getNomeAgencia())){
							acrescimo+=30.00; // se agencia devolvida nao for a mesma da emprestada
						}					
						if(!cidadeEntrega.equals(cidadePrev)){
							acrescimo+=20.00; // se a cidade for diferente
						}
					}else {//se for na mesma nao será cobrado
						acrescimo = 0;
					}
					//copia
					NumberFormat numb = NumberFormat.getCurrencyInstance();
					valortot+=acrescimo + multa;					
					tacres.setText(""+acrescimo);
					tmulta.setText(numb.format(multa));
					tvalorpag.setText(numb.format(valortot));				
				} else {
					JOptionPane.showMessageDialog(null,"Locação não encontrada");			
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Locação não encontrada");			
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
			if(eftpag == evento.getSource()){
				if(radiodeb.isSelected() == false && radiocred.isSelected() == false){
	                JOptionPane.showMessageDialog(null, "Selecione a forma de pagamento.");
	        }else if(radiodeb.isSelected()){            	
            	Devolucao dev = new Devolucao();
            	GUICartaoDebito carddeb = new GUICartaoDebito(this,bn,""+valortot);
            	if(carddeb.getFechou() == false){
            	int codpagamento =0;
				try {
					codpagamento = carddeb.getCodPagamento();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Baixa realizada com sucesso!");
					e.printStackTrace();
				}
            	try {
            		
            		System.out.println("Oque será? :O "+auto.getIdAutomovel());
					dev.devolver(sdf.format(data.getTime()),(int)dias, acrescimo,agencia,codpagamento,Integer.parseInt(tnumeroloc.getText()));
					auto.alterarStatusAutomovel(auto.getIdAutomovel(),false,Double.parseDouble(tkm.getText()));// cuidar do status do carro e ver baixa que nao esta dando update
					JOptionPane.showMessageDialog(null,"Baixa realizada com sucesso!");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"ERRO ao dar baixa");
					e.printStackTrace();
				}
            	}
	         }else if(radiocred.isSelected()){
            	Devolucao dev = new Devolucao();
            	GUICartaoCredito cred = new GUICartaoCredito(this,bn,""+valortot);
            	if(cred.getFechou() == false){
            	int codpagamento=0;            	
				try {
					codpagamento = cred.getCodPagamento();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
            	try {
					dev.devolver(sdf.format(data.getTime()),(int)dias, acrescimo,agencia,codpagamento,Integer.parseInt(tnumeroloc.getText()));
					auto.alterarStatusAutomovel(auto.getIdAutomovel(),false,Double.parseDouble(tkm.getText()));// cuidar do status do carro e ver baixa que nao esta dando update
					JOptionPane.showMessageDialog(null,"Baixa realizada com sucesso!");
					dispose();
            	} catch (SQLException e) {
            		JOptionPane.showMessageDialog(null,"ERRO ao dar baixa");
					e.printStackTrace();
				}
	         	}
	         }
					
			}else if(evento.getSource() == ok){
				try {
					Devolucao dev = new Devolucao();
					int codpagamento=0;
					dev.devolver(sdf.format(data.getTime()),(int)dias, acrescimo,emp.getAgencia(),agencia,codpagamento);
					auto.alterarStatusAutomovel(auto.getIdAutomovel(),false,Double.parseDouble(tkm.getText()));// cuidar do status do carro e ver baixa que nao esta dando update
					JOptionPane.showMessageDialog(null,"Baixa realizada com sucesso!");
					JOptionPane.showMessageDialog(null,"Baixa realizada com sucesso!");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"ERRO ao dar baixa");
					e.printStackTrace();
				}
			}
	}
	
	public long calcDias(String atual, String entrega)throws ParseException {  
        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");  
        df.setLenient(false);  
        java.util.Date d1 =   df.parse (atual);   
        java.util.Date d2 =   df.parse (entrega);   
        long dt = (d1.getTime() - d2.getTime()) + 3600000; 
   
        return (dt / 86400000L);
 }
	

	
	public static void limpar(){
		tsituacaolb.setText(""); 
		tdatahrprev.setText(""); 
		tdatahremp.setText(""); 
		tlocprev.setText(""); 
		tlocentr.setText(""); 
		tmulta.setText(""); 
		tacres.setText(""); 
		tkm.setText(""); 
		tvalorpag.setText(""); 	
		multa = 0;
		valortot = 0;
		acrescimo = 0;
		
	}
	
	
		
}
