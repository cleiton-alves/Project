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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import objetos.Cliente;

public class GUIGerenciarCliente extends JFrame implements ActionListener {

	private JLabel lnome,lsexo,lcpf,lrg,les,lnumreg,lvalidade,ldatnasc,lemail,ltel,lmsgcpfcli, lpricnh;
	private static JTextField tnome,tnumreg,temail;
	private static JFormattedTextField tcpf,trg,tvalidade,tdatnasc,ttel,tmsgcpfcli,tpricnh;
	private JButton cadastrar, alterar, excluir, consultar, cancelar, limpar;
	private Icon limp;
	private static JComboBox<String> csexo = new JComboBox<String>();
	private static JComboBox<String> ces = new JComboBox<String>();
	private ResourceBundle bn = null;

	
	public GUIGerenciarCliente (ResourceBundle bn){		
		
	this.bn = bn;
        Container cont = getContentPane();
        cont.setLayout(null);
 
        // labels         
        lnome     = new JLabel(bn.getString("guimantcli.nome"));
        lsexo     = new JLabel(bn.getString("guimantcli.sexo"));
        lcpf      = new JLabel(bn.getString("guimantcli.cpf"));
        lrg       = new JLabel(bn.getString("guimantcli.docindentidade"));
        les       = new JLabel(bn.getString("guimantcli.es"));
        lnumreg   = new JLabel(bn.getString("guimantcli.numreg"));
        lvalidade = new JLabel(bn.getString("guimantcli.validade"));
        ldatnasc  = new JLabel(bn.getString("guimantcli.datnasc"));
        lemail    = new JLabel(bn.getString("guimantcli.email"));
        ltel      = new JLabel(bn.getString("guimantcli.tel"));
        lmsgcpfcli = new JLabel(bn.getString("guimantcli.msgcpfcli"));
        lpricnh     = new JLabel(bn.getString("guimantcli.primeiracnh"));
 
        // TexFields
         
        tnome      = new JTextField(50);
        tcpf       = new JFormattedTextField(mascara("###.###.###-##"));
        trg        = new JFormattedTextField(mascara("##.###.###-#"));
        tnumreg    = new JTextField(10);
        tvalidade  = new JFormattedTextField(mascara("####-##-##"));
        tdatnasc   = new JFormattedTextField(mascara("####-##-##"));
        temail     = new JTextField(20);
        ttel       = new JFormattedTextField(mascara("####-####"));
        tmsgcpfcli = new JFormattedTextField(mascara("###.###.###-##"));
        tpricnh = new JFormattedTextField(mascara("####-##-##"));
         
        //Buttons e icons
         
        limp = new ImageIcon(getClass().getResource("/telas/borracha.png"));
         
        cadastrar = new JButton(bn.getString("guimantcli.cadastrar"));
        consultar = new JButton(bn.getString("guimantcli.consultar"));
        alterar   = new JButton(bn.getString("guimantcli.alterar"));
        excluir   = new JButton(bn.getString("guimantcli.excluir"));
        cancelar  = new JButton(bn.getString("guimantcli.cancelar"));
        limpar  = new JButton(limp);
         
         
         
        cadastrar.addActionListener(this);
        alterar.addActionListener(this);
        consultar.addActionListener(this);
        excluir.addActionListener(this);
        cancelar.addActionListener(this);
        limpar.addActionListener(this);
        limpar.setBorder(null);  
         
        //inicializa os botoes desabilitados         
        alterar.setEnabled(false);
        excluir.setEnabled(false);        
         
         
        //lugares das label e buttons         
        lmsgcpfcli.setBounds(25,10,140,80);  
        lnome.setBounds(25,10,100,200);    
        lsexo.setBounds(580,10,100,200);        
        lcpf.setBounds(25,10,100,270);          
        lrg.setBounds(25,10,120,340);          
        les.setBounds(330,10,100,340);           
        lnumreg.setBounds(25,10,100,410);        
        lvalidade.setBounds(580,10,100,410);   
        ldatnasc.setBounds(25,10,100,480);    
        ltel.setBounds(330,10,100,480); 
        lemail.setBounds(25,10,100,550); 
        lpricnh.setBounds(330,10,100,410);   
          
        cadastrar.setBounds(50,370,100,25);
        alterar.setBounds(230,370,100,25);
        excluir.setBounds(410,370,100,25);
        consultar.setBounds(380,35,90,25); 
        cancelar.setBounds(590,370,90,25);
        limpar.setBounds(580,35,40,25); 
         
         
        //lugares dos textfield e combobox
         
        tmsgcpfcli.setBounds(200,40,140,20); 
        tnome.setBounds(150,100,350,20);    
        csexo.setBounds(670,100,50,20);        
        tcpf.setBounds(150,135,140,20);        
        trg.setBounds(150,170,140,20);          
        ces.setBounds(440,170,50,20);          
        tnumreg.setBounds(150,205,140,20);      
        tvalidade.setBounds(670,205,100,20);   
        tdatnasc.setBounds(150,240,140,20);     
        temail.setBounds(150,275,350,20);      
        ttel.setBounds(440,240,100,20);
        tpricnh.setBounds(440,205,100,20);
         
        // Adicionando ao container
        cont.add(lmsgcpfcli);
        cont.add(tmsgcpfcli);
     
        cont.add(lnome);
        cont.add(tnome);
        cont.add(lsexo);
        cont.add(lcpf);
        cont.add(tcpf);
        cont.add(lrg);
        cont.add(trg);
        cont.add(les);
        cont.add(lnumreg);
        cont.add(tnumreg);
        cont.add(lvalidade);
        cont.add(tvalidade);
        cont.add(ldatnasc);
        cont.add(tdatnasc);
        cont.add(lemail);
        cont.add(temail);
        cont.add(ltel);
        cont.add(ttel);
        cont.add(cadastrar);
        cont.add(consultar);
        cont.add(alterar);
        cont.add(excluir);
        cont.add(cancelar);
        cont.add(csexo);
        cont.add(ces);
        cont.add(lpricnh);
        cont.add(tpricnh);
        cont.add(limpar);
        
        csexo.addItem("");
        csexo.addItem(bn.getString("guimantcli.masculino"));
        csexo.addItem(bn.getString("guimantcli.feminino"));
         
       
        String [] estados = {"","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
        for (int i=0; i<estados.length;i++){
        	ces.addItem(estados[i]);        	
        }
        setTitle(bn.getString("guimantcli.titulo" ));
        setVisible(true);
        setSize(800,450);
        setResizable(false);
        this.setLocationRelativeTo(null); 
 
	}
	
	public void actionPerformed(ActionEvent evento) {

		if(evento.getSource() == consultar){
			Cliente  objcli = new Cliente();
			try {				
				objcli = objcli.consultarCliente(tmsgcpfcli.getText().replace(".","").replace("-",""));	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			tnome.setText(objcli.getNome());
			tcpf.setText(objcli.getCpf());
			ces.setSelectedItem(objcli.getEstadoEmissor());
			tnumreg.setText(""+ objcli.getNregistro());
			tvalidade.setText(objcli.getValidadeCnh());
			trg.setText(objcli.getDocIdentidade());
			ttel.setText("" +objcli.getTelefone());
			tdatnasc.setText(objcli.getDataNasc());
			tpricnh.setText(objcli.getPriCnh());
			csexo.setSelectedItem(objcli.getSexo());
			temail.setText(objcli.getEmail());			
			tcpf.setRequestFocusEnabled(false);
			
			if (!tcpf.getText().equals("")){	
				
				cadastrar.setEnabled(false);
				excluir.setEnabled(true);
				alterar.setEnabled(true);
				consultar.setEnabled(true);
				//tmsgcpfcli.setRequestFocusEnabled(false);
			}else{
				cadastrar.setEnabled(true);
				excluir.setEnabled(false);
				alterar.setEnabled(false);
				consultar.setEnabled(true);
				limpar();
			}
			
		}else if(evento.getSource() == cancelar){
				dispose();
		}else if(evento.getSource() == limpar){
			limpar();
			cadastrar.setEnabled(true);
			excluir.setEnabled(false);
			alterar.setEnabled(false);
			consultar.setEnabled(true);
			
		}else if(evento.getSource() == cadastrar){										
			if(tcpf.getText().replace(".","").replace("-","").trim().equals("") || tnome.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Preencha campos em branco!");
			}else{
				Cliente objcli = new Cliente();	
				try {										
					objcli.cadastrarCliente(tnome.getText(), tcpf.getText().replace(".","").replace("-",""), (String)ces.getSelectedItem(),(String) csexo.getSelectedItem(),
							temail.getText(), trg.getText().replace(".","").replace("-",""), tvalidade.getText().replace("-",""),
							ttel.getText().replace("-",""), tnumreg.getText(), tdatnasc.getText().replace("-",""), tpricnh.getText().replace("-",""));						
					limpar();
					alterar.setEnabled(false); 
					excluir.setEnabled(false);
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			}
		
		}else if(evento.getSource() == alterar){
			Cliente objcli = new Cliente();	
			try {
				objcli.alterarCliente(tnome.getText(), tcpf.getText().replace(".","").replace("-",""), (String)ces.getSelectedItem(),(String) csexo.getSelectedItem(),
						temail.getText(), trg.getText().replace(".","").replace("-",""), tvalidade.getText().replace("-",""),
						ttel.getText().replace("-",""), tnumreg.getText(), tdatnasc.getText().replace("-",""), tpricnh.getText().replace("-",""));
				cadastrar.setEnabled(false);
				excluir.setEnabled(true);
				alterar.setEnabled(true);
				consultar.setEnabled(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(evento.getSource() == excluir){
							
			int aux = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cliente?", "Exclusão", JOptionPane.YES_NO_OPTION);
		    if (aux == JOptionPane.YES_OPTION){
		    	Cliente cli = new Cliente();
		    	
				try {
					cli.excluirCliente(tcpf.getText());					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				limpar();
			    cadastrar.setEnabled(true);
				excluir.setEnabled(false);
				alterar.setEnabled(false);
				consultar.setEnabled(true);
		    }						    
		}
	}	
	
    public static void limpar(){         
    	tmsgcpfcli.setText("");
    	tnome.setText("");
        tcpf.setText("");
        ces.setSelectedIndex(0); 
        tnumreg.setText("");
        tvalidade.setText("");
        tpricnh.setText("");
        trg.setText("");
        ttel.setText("");
        tdatnasc.setText("");
        csexo.setSelectedIndex(0); 
        temail.setText("");
        tcpf.setRequestFocusEnabled(true);
        tmsgcpfcli.setRequestFocusEnabled(true);         
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
			
	
	




