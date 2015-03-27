package objetos;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import daos.DAOAutomovel;
import daos.DAOEmprestimo;
import telas.Tabela;

public class Emprestimo {

    private String data_HoraDevPrev,data_HoraEmp;  
    private int codEmp, agencia, idAutomovel, idCliente, idPagamento, localDevPrev, tipoTarifa;
    private double kmEmp, km;
    private boolean gps,bebe,motorista;
    
    public int getLocalDevPrev() {
		return localDevPrev;
	}

	public void setLocalDevPrev(int localDevPrev) {
		this.localDevPrev = localDevPrev;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}

	public String getData_HoraDev() {
		return data_HoraDevPrev;
	}

	public void setData_HoraDevPrev(String data_HoraDev) {
		this.data_HoraDevPrev = data_HoraDev;
	}

	public String getData_HoraEmp() {
		return data_HoraEmp;
	}

	public void setData_HoraEmp(String data_HoraEmp) {
		this.data_HoraEmp = data_HoraEmp;
	}

	public double getKmEmp() {
		return kmEmp;
	}

	public void setKmEmp(double kmEmp) {
		this.kmEmp = kmEmp;
	}

	public int getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(int tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

    
    public int getIdAutomovel() {
		return idAutomovel;
	}

	public void setIdAutomovel(int idAutomovel) {
		this.idAutomovel = idAutomovel;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public boolean isBebe() {
		return bebe;
	}

	public void setBebe(boolean bebe) {
		this.bebe = bebe;
	}

	public boolean isMotorista() {
		return motorista;
	}

	public void setMotorista(boolean motorista) {
		this.motorista = motorista;
	}

	public String getData_HoraDevPrev() {
		return data_HoraDevPrev;
	}
	

	public DefaultTableModel relatorioAutomoveis(int tiporelat,Tabela pane) throws SQLException{        
        DAOEmprestimo emp = new DAOEmprestimo();         
        return emp.relatorioLocacoes(tiporelat, pane); 
    }
	
    
    public Emprestimo consultarEmprestimo(int codEmp) throws SQLException{	
    	DAOEmprestimo daoe = new DAOEmprestimo();
    	return daoe.consultarEmprestimo(codEmp);    	
    }
    
    public void cadastrarEmprestimo(String data_horaEmp, String data_horaDev, int codagenciaEmp,int codagenciaDevPrev, int tipoTarifa,int idCliente,
			int idPagamento, int idAutomovel, double km, double kmEmp, boolean gps, boolean bebe, boolean motorista) throws SQLException{
			
		DAOEmprestimo daoemp = new DAOEmprestimo();
		daoemp.cadastrarEmprestimo(data_horaEmp,data_horaDev,codagenciaEmp,codagenciaDevPrev,tipoTarifa,
				idCliente,idPagamento,idAutomovel, km, kmEmp, gps, bebe, motorista);
	}
	
    
    public double somaEmprestimo(int relat) throws SQLException{	
    	DAOEmprestimo daoe = new DAOEmprestimo();
    	return daoe.somaEmprestimo(relat);
    	
    }	
    
    
    public int consultaIdEmprestimo() throws SQLException{	
    	DAOEmprestimo daoe = new DAOEmprestimo();
    	return daoe.consultaIdEmprestimo();
    	
    }	

}

