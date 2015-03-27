package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import objetos.Emprestimo;
import telas.Tabela;

public class DAOEmprestimo {
	
	AcessoBD bd = new AcessoBD();

	public DefaultTableModel relatorioLocacoes(int tiporelat, Tabela pane) throws SQLException{
     	Connection conn = bd.obtemConexao();
        DefaultTableModel modeloTable = (DefaultTableModel) pane.getModel();   
        
        String sqlSelect = "";
        if(tiporelat == 0){
        	sqlSelect = "select a.idAutomovel,a.fabricante,a.modelo,a.ano, c.nome,c.cpf, data_horaDevPrev, p.valor "+
              		"from emprestimo e inner join automovel a on e.idAutomovel = a.idAutomovel "+
              		"inner join cliente c on e.idCliente = c.idCliente "+ 
              		"inner join pagamento p on e.idPagamentoEmp = p.idPagamento "+
              		"where e.data_horaEmp =  curdate();";

        }else{
        	
        	 sqlSelect = "select a.idAutomovel,a.fabricante,a.modelo,a.ano, c.nome,c.cpf,data_horaDevPrev, p.valor "+
               		"from emprestimo e inner join automovel a on e.idAutomovel = a.idAutomovel "+
               		"inner join cliente c on e.idCliente = c.idCliente "+ 
               		"inner join pagamento p on e.idPagamentoEmp = p.idPagamento; ";
               	
 	
        }
        
        String[] dados = new String[9];
        PreparedStatement stm = null;
        ResultSet rs = null;	
        int posicao = 0;   
        modeloTable.setRowCount(0);
        try{
            stm = conn.prepareStatement(sqlSelect);
            //stm.setInt(1, 0);
            rs = stm.executeQuery();
                            
            if(rs.next() == true){                    
                do{                        
                    dados [0] = String.valueOf(posicao+1);
                    dados [1]= rs.getString(1); 
                    dados [2]=rs.getString(2);  
                    dados [3]=rs.getString(3);
                    dados [4]=rs.getString(4);
                    dados [5]=rs.getString(5);
                    dados [6]=rs.getString(6);
                    dados [7]=rs.getString(7);
                    dados [8]="R$ " + rs.getString(8);

                    posicao++;      
                    modeloTable.addRow(dados);
                     
                    for(int x = 0; x< dados.length;x++){
                       // System.out.print(" " + dados[x] );
                    }
                    System.out.print("\n\n ");
                }while(rs.next());

            }else{
                JOptionPane.showMessageDialog(null,"Não possui automóveis locados","ERROR", JOptionPane.ERROR_MESSAGE);                           
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
        finally {
            if (stm != null){
                try{
                    stm.close();
                }
                catch (SQLException e1){
                    System.out.print(e1.getStackTrace());
                }
            }
        }
                     
        return modeloTable;  
    }	

	
	
	
 
	public Emprestimo consultarEmprestimo(int codEmp) throws SQLException {	
		Connection conn = bd.obtemConexao();	
		Emprestimo emp = new Emprestimo();
		String sqlSelect = "select * from emprestimo where cod_emp = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;		
		try{
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, codEmp);
			rs = stm.executeQuery();				
			if(rs.next()){					
				emp.setCodEmp(rs.getInt(1));
				emp.setData_HoraEmp(rs.getString(2));
				emp.setData_HoraDevPrev(rs.getString(3));				
				emp.setAgencia(rs.getInt(4));
				emp.setLocalDevPrev(rs.getInt(5));
				emp.setTipoTarifa(rs.getInt(6));					
				emp.setKmEmp(rs.getDouble(7));
				emp.setKm(rs.getDouble(8));
				emp.setIdAutomovel(rs.getInt(9));
				emp.setIdCliente(rs.getInt(10));
				emp.setGps(rs.getBoolean(11));
				emp.setBebe(rs.getBoolean(12));
				emp.setMotorista(rs.getBoolean(13));
				emp.setIdPagamento(rs.getInt(14));			
			}
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return emp;
	}
	
									
	public void cadastrarEmprestimo(String data_horaEmp, String data_horaDev, int codagenciaEmp, int codagenciadev,int tipoTarifa,
			int idCliente,int idPagamento, int idAutomovel, double km, double kmEmp, boolean gps, boolean bebe, boolean motorista) throws SQLException {
		
		AcessoBD bd = new AcessoBD();
		Connection conn = bd.obtemConexao();
		try{				
			
			String sqlSelect = "insert into emprestimo (data_horaEmp, data_horaDevPrev, codagenciaEmp,codagenciaDevPrev,tipoTarifa,idCliente,idPagamentoEmp,idAutomovel,contKm, kmEmp, gps, bebe, motorista) " +
					"values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stm  = conn.prepareStatement(sqlSelect);
			
			stm.setString(1, data_horaEmp);
			stm.setString(2, data_horaDev);
			stm.setInt(3, codagenciaEmp);
			stm.setInt(4, codagenciadev);
			stm.setInt(5, tipoTarifa);
			stm.setInt(6, idCliente);
			stm.setInt(7, idPagamento);
			stm.setInt(8, idAutomovel);	
			stm.setDouble(9, km);
			stm.setDouble(10, kmEmp);
			stm.setBoolean(11,gps);
			stm.setBoolean(12,bebe);
			stm.setBoolean(13,motorista);
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
	
	
	public double somaEmprestimo(int relat) throws SQLException{
		Connection conn = bd.obtemConexao();
		String sqlSelect;
		if(relat  == 0){
			 sqlSelect = "SELECT SUM(valor) FROM pagamento p inner join emprestimo e on p.idPagamento = e.idPagamentoEmp where e.data_horaEmp =  curdate();";
		}else{
			sqlSelect = "SELECT SUM(valor) FROM pagamento p inner join emprestimo e on p.idPagamento = e.idPagamentoEmp";
		
		}
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		double soma= 0;
		try{
			stm = conn.prepareStatement(sqlSelect);
			 
			 rs = stm.executeQuery();
			
			 if(rs.next() == true){
				 do{
					 
					 soma += rs.getDouble(1);
				 
				 }while(rs.next());
			 }

		}catch (Exception e){
			e.printStackTrace();
		}
		return soma;
		
	
	}

	public int consultaIdEmprestimo() throws SQLException {	
		Connection conn = bd.obtemConexao();	
		String sqlSelect = "select cod_emp  from emprestimo order by cod_emp  desc limit 1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		int codpag = 0;
		try{
			stm = conn.prepareStatement(sqlSelect);
			 
			 rs = stm.executeQuery();
			
			 if(rs.next()){

				 codpag = rs.getInt(1);
	
			 }
			 

		}catch (Exception e){
			e.printStackTrace();
		}
		return codpag;
		
	
	}

		
	

	
	
	
}
