package telas;
 
import javax.swing.*;
import javax.swing.table.DefaultTableModel; 

import objetos.Automovel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ResourceBundle;
 
public class GUIConsultarAutomovel extends JFrame implements ActionListener {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cgrupo;
    private JButton btconsultar,btefetuaremp,btcancelar;
    private Tabela pane;
    private ResourceBundle bn = null;
    private int agencia;
 
     
    public  GUIConsultarAutomovel(ResourceBundle bn, final int agencia){
    	this.agencia = agencia;
		
        this.bn= bn;
         
        Container cont =  getContentPane();
        setLayout(null);
         
         
        // inicializando jbuttons e combobox
         
        cgrupo  = new JComboBox<String>();
        btconsultar = new JButton(bn.getString("guiconsultauto.consultar"));
        btefetuaremp  = new JButton(bn.getString("guiconsultauto.eftemp"));
        btcancelar  = new JButton(bn.getString("guiconsultauto.cancelar"));
         
        String[] cab = {"#",bn.getString("guirelatorio.codigo"),
	     bn.getString("guiconsultauto.placa"),
	     bn.getString("guiconsultauto.categoria"),
	     bn.getString("guiconsultauto.moodelo"),
	     bn.getString("guiconsultauto.tfkmlivre"),
	     bn.getString("guiconsultauto.tfkmcontrolado"),
	     bn.getString("guiconsultauto.ano")};
         
        pane = new Tabela(cab,bn.getString("guiconsultauto.autodisp"));
         
        // adicionando jbuttons no actionlistener
         
        btconsultar.addActionListener(this);
        btefetuaremp.addActionListener(this);
        btcancelar.addActionListener(this);
         
         
        //localizaзгo do combobox, buttons e table         
        pane.setBounds(-10,100,1000,500);
        cgrupo.setBounds(50,50,200,25);
        btcancelar.setBounds(300,410,100,25);
        btefetuaremp.setBounds(450,410,150,25);
        btconsultar.setBounds(300,50,100,25);
         
        // adcionando categotia ao comobobox
         
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
         
                  
        // adicionando ao container
        cont.add(cgrupo);
        cont.add(btconsultar);
        cont.add(btefetuaremp);
        cont.add(btcancelar);
         
        pane.setSizeTableConsultar();
        cont.add(pane);
     
        pack();
        setTitle(bn.getString("guiconsultauto.titulo"));
        setVisible(true);
        setSize(1000,500);
        setResizable(true);
        this.setLocationRelativeTo(null); 
    }
 
     
 
    public void actionPerformed(ActionEvent event) {
         
        if(btcancelar == event.getSource()){
             
            dispose();
        }else
            if(btconsultar == event.getSource()){
                Automovel auto = new Automovel();
                try {
                    pane.setModel(auto.consultarAutomoveisDisponiveis(cgrupo.getSelectedIndex()+1,pane));
                    revalidate();
                     
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                 
                 
            }else if(btefetuaremp == event.getSource()){                 
            	JTable tb = pane.getTable();
            	DefaultTableModel dtm = pane.getModel();
            	int sel[] = tb.getSelectedRows();
            	if(sel.length > 0){
	            	int linha = tb.convertRowIndexToModel(sel[0]);
	            	String campo = (String) dtm.getValueAt(linha,1);  	            	            
	            	try {
						GUIGerenciarEmprestimo g = new GUIGerenciarEmprestimo(bn, campo, agencia);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}else{
            		JOptionPane.showMessageDialog(null, "Selecione um veículo para realizar o empréstimo! ");
            	}
            }
    }
 
 
}