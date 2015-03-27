package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetos.Agencia;
import objetos.Automovel;

public class DAOAgencia {
	
	private int idAgencia;
	private String nomeAgencia, cidadeAgencia;
	
	AcessoBD bd = new AcessoBD();
	
	public Agencia consultarAgencia(int codAgencia) throws SQLException{
		 Connection conn = bd.obtemConexao();
	        Agencia ag = new Agencia();
	         
	        String sqlSelect = "select * from agencia where idAgencia = ?";
	        PreparedStatement stm = null;
	        ResultSet rs = null;
	        try{
	            stm = conn.prepareStatement(sqlSelect);
	            stm.setInt(1, codAgencia);
	            rs = stm.executeQuery();
	         
	            if(!rs.next()){	 
	                JOptionPane.showMessageDialog(null,"Agencia não encontrado no banco de dados","ERROR", JOptionPane.ERROR_MESSAGE);                 
	            }else{               
	            	ag.setIdAgencia(rs.getInt(1));
	            	ag.setNomeAgencia(rs.getString(2));
	            	ag.setCidadeAgencia(rs.getString(3));
	            	            	
	            }
	            
			}catch (Exception e){			
				System.out.println("erro");
				try{
					conn.rollback();
				}
				catch (SQLException e1){
					System.out.print(e1.getStackTrace());
				}			
			}
			finally	{
				if (stm != null){
					try{
						stm.close();
					}
					catch (SQLException e1){
						System.out.print(e1.getStackTrace());
					}
				}
			}		
	        return ag;  
		}

	public ArrayList<Agencia> consultarTodasAgencias() throws SQLException {
		Connection conn = bd.obtemConexao();
        
        ArrayList<Agencia> aa = new ArrayList<Agencia>();
         
        String sqlSelect = "select * from agencia";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            stm = conn.prepareStatement(sqlSelect);          
            rs = stm.executeQuery();
         
            while(rs.next()){ 
            	Agencia ag = new Agencia();
            	ag.setIdAgencia(rs.getInt(1));
            	ag.setNomeAgencia(rs.getString(2));
            	ag.setCidadeAgencia(rs.getString(3));   
            	aa.add(ag);
            }
            
		}catch (Exception e){			
			System.out.println("erro");
			try{
				conn.rollback();
			}
			catch (SQLException e1){
				System.out.print(e1.getStackTrace());
			}			
		}
		finally	{
			if (stm != null){
				try{
					stm.close();
				}
				catch (SQLException e1){
					System.out.print(e1.getStackTrace());
				}
			}
		}		
        return aa; 		
	}

	public Agencia consultarAgPorNome(String agencia) throws SQLException {
		Connection conn = bd.obtemConexao();
        Agencia ag = new Agencia();
         
        String sqlSelect = "select * from agencia where nomeAgencia = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            stm = conn.prepareStatement(sqlSelect);
            stm.setString(1, agencia);
            rs = stm.executeQuery();
         
            if(!rs.next()){ 
                JOptionPane.showMessageDialog(null,"Agencia não encontrado no banco de dados","ERROR", JOptionPane.ERROR_MESSAGE);                 
            }else{               
            	ag.setIdAgencia(rs.getInt(1));
            	ag.setNomeAgencia(rs.getString(2));
            	ag.setCidadeAgencia(rs.getString(3));            	            	
            }
            
		}catch (Exception e){			
			System.out.println("erro");
			try{
				conn.rollback();
			}
			catch (SQLException e1){
				System.out.print(e1.getStackTrace());
			}			
		}
		finally	{
			if (stm != null){
				try{
					stm.close();
				}
				catch (SQLException e1){
					System.out.print(e1.getStackTrace());
				}
			}
		}		
        return ag; 
	}

}
