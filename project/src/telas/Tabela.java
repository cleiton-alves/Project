package telas;
 
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ResourceBundle;
 
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
 
 
public class Tabela extends JPanel{
     
    public JTable table;    
	public Object[][] lc = null;
    public String[] cab;
    public JScrollPane scroll;
    public JPanel pn;
    private ResourceBundle bn = null;
    public DefaultTableModel modeloTable;    
     
     
    public Tabela(/*Object[][] lc,*/String[] cab,String titulo){         
        this.bn = bn;
        this.cab = cab; 
        JPanel pn = new JPanel();
        pn.setLayout(new FlowLayout());
        pn.setBorder(new TitledBorder(titulo));         
         
        table = new JTable();
        modeloTable = new DefaultTableModel(lc,cab){public boolean isCellEditable(int row,int col){
            return false;}};
        table.setModel(modeloTable);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         
        scroll = new JScrollPane();
         
        table.setPreferredScrollableViewportSize(new Dimension(900,200));
         
        /*table.getColumn(cab[0]).setPreferredWidth(30);
        table.getColumn(cab[1]).setPreferredWidth(75);
        table.getColumn(cab[2]).setPreferredWidth(75);
        table.getColumn(cab[3]).setPreferredWidth(285);
        table.getColumn(cab[4]).setPreferredWidth(270);
        table.getColumn(cab[5]).setPreferredWidth(100);
        table.getColumn(cab[6]).setPreferredWidth(130);
        table.getColumn(cab[7]).setPreferredWidth(50);*/
 
         
        //table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
         
     
         
        pn.add(scroll);
        scroll.getViewport().add(table);
         
        add(pn);
        setVisible(true);
         
    }
     
    public void setSizeTableConsultar(){
         
         
        table.getColumn(cab[0]).setPreferredWidth(30);
        table.getColumn(cab[1]).setPreferredWidth(75);
        table.getColumn(cab[2]).setPreferredWidth(75);
        table.getColumn(cab[3]).setPreferredWidth(285);
        table.getColumn(cab[4]).setPreferredWidth(270);
        table.getColumn(cab[5]).setPreferredWidth(100);
        table.getColumn(cab[6]).setPreferredWidth(130);
        table.getColumn(cab[7]).setPreferredWidth(50);         
    }
     
    public void setSizeTableRelatorio(){
    	
    	table.getColumn(cab[0]).setPreferredWidth(12);
        table.getColumn(cab[1]).setPreferredWidth(30);
        table.getColumn(cab[2]).setPreferredWidth(75);
        table.getColumn(cab[3]).setPreferredWidth(130);
        table.getColumn(cab[4]).setPreferredWidth(35);
        table.getColumn(cab[5]).setPreferredWidth(170);
        table.getColumn(cab[6]).setPreferredWidth(95);
        table.getColumn(cab[7]).setPreferredWidth(70);       
         
    }          
     
     
    public DefaultTableModel getModel(){
        return modeloTable;
    }
    public void setModel( DefaultTableModel model){
        modeloTable = model;
     
    }
    
    public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
    
    
     
 
}