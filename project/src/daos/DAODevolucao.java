package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAODevolucao {
	
	
	public DAODevolucao(){
		
	}

	public void devolver(String data_horaDev, int diasAtraso, double acrescimo,int codagenciaDev, int idPagamentoDev, int cod_emp) throws SQLException {
		AcessoBD bd = new AcessoBD();
		Connection conn = bd.obtemConexao();
		try{				
			
			String sqlSelect = "update emprestimo set data_horaDev=?, diasAtraso=?, acrescimo=?,codagenciaDev=?,idPagamentoDev=? " +
					"where cod_emp=?";
			PreparedStatement stm  = conn.prepareStatement(sqlSelect);
			
			stm.setString(1, data_horaDev);
			stm.setInt(2, diasAtraso);
			stm.setDouble(3, acrescimo);
			stm.setInt(4, codagenciaDev);
			stm.setInt(5, idPagamentoDev);
			stm.setInt(6,cod_emp);
			
			stm.executeUpdate();			
			stm.close();
	
		}	
		catch (Exception e){
			e.printStackTrace();
			try{
			conn.rollback();
			}
			catch (SQLException e1){
			System.out.print(e1.getStackTrace());
			}
		
		}
		
	}

}
