package objetos;

import java.sql.SQLException;

import daos.DAODevolucao;

public class Devolucao {

    private double acrescimo;
    private int diasAtraso, localDev;
    private Double kmDev;
    private String dataDev;
    private Emprestimo emp;
    
    public Devolucao(){
    	
    } 

    
     public double getAcrescimo() {
		return acrescimo;
	}


	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}


	public int getDiasAtraso() {
		return diasAtraso;
	}


	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}


	public double getKmDev() {
		return kmDev;
	}


	public void setKmDev(double kmDev) {
		this.kmDev = kmDev;
	}


	public int getLocalDev() {
		return localDev;
	}


	public void setLocalDev(int localDev) {
		this.localDev = localDev;
	}


	public String getDataDev() {
		return dataDev;
	}


	public void setDataDev(String dataDev) {
		this.dataDev = dataDev;
	}
    
     public void devolver(String data_horaDev, int diasAtraso, double acrescimo ,int codagenciaDev, int idPagamentoDev, int cod_emp) throws SQLException {
       DAODevolucao dao = new DAODevolucao();
       dao.devolver(data_horaDev, diasAtraso, acrescimo ,codagenciaDev, idPagamentoDev, cod_emp);
       
    }
}
