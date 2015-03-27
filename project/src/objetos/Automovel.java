package objetos;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import daos.DAOAutomovel;
import telas.Tabela;

public class Automovel {	
	private int idAutomovel, idCategoria;
	private String chassi, placa, cidade, modelo, fabricante, estado;
	private int ano;
	private double km, tKmLivre, tKmCont;
	private boolean statusAuto;	
	
	public Automovel(){
		setIdAutomovel(0);
		setIdCategoria(0);
		setChassi("");
		setPlaca("");
		setCidade("");
		setModelo("");
		setFabricante("");
		setKm(0.0);
		setTKmLivre(0.0);
		setTKmCont(0.0);
		setEstado("");
		setStatusAuto(false);
		setAno(0);	
		
	}
	
	public Automovel(int idCategoria, String chassi, String placa, String cidade, String modelo, String fabricante, double km, double tKmLivre, double tKmCont, String estado, boolean statusAuto, int ano){
		setIdCategoria(idCategoria);
		setChassi(chassi);
		setPlaca(placa);
		setCidade(cidade);
		setModelo(modelo);
		setFabricante(fabricante);
		setKm(km);
		setTKmLivre(tKmLivre);
		setTKmCont(tKmCont);
		setEstado(estado);
		setStatusAuto(statusAuto);		
		setAno(ano);
		
	}
	
	//sets
	public void setIdAutomovel(int idAutomovel){
		this.idAutomovel = idAutomovel;		
	}
	
	public void setIdCategoria(int idCategoria){
		this.idCategoria = idCategoria;		
	}
	
	public void setChassi(String chassi){
		this.chassi = chassi;		
	}
	
	public void setPlaca(String placa){
		this.placa = placa;		
	}
	
	public void setCidade(String cidade){
		this.cidade = cidade;		
	}
	
	public void setModelo(String modelo){
		this.modelo = modelo;		
	}
	
	public void setFabricante(String fabricante){
		this.fabricante = fabricante;		
	}	
	
	public void setKm(double km){
		this.km = km;		
	}
	
	public void setTKmLivre(double tKmLivre){
		this.tKmLivre = tKmLivre;		
	}
	
	public void setTKmCont(double tKmCont){
		this.tKmCont = tKmCont;		
	}
	
	public void setEstado(String estado){
		this.estado = estado;		
	}
	
	public void setStatusAuto(boolean statusAuto){
		this.statusAuto = statusAuto;		
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	//gets
	public int getIdAutomovel(){
		return idAutomovel;		
	}	
	
	public int getIdCategoria(){
		return idCategoria;		
	}
	
	public String getChassi(){
		return chassi;	
	}
	
	public String getPlaca(){
		return placa;		
	}
	
	public String getCidade(){
		return cidade;	
	}
	
	public String getModelo(){
		return modelo;	
	}
	
	public String getFabricante(){
		return fabricante;	
	}	
	
	public double getKm(){
		return km;	
	}
	
	public double getTKmLivre(){
		return tKmLivre;	
	}
	
	public double getTKmCont(){
		return tKmCont;		
	}
	
	public String getEstado(){
		return estado;	
	}	
	
	public boolean getStatusAuto(){
		return statusAuto;		
	}

	public int getAno() {
		return ano;
	}

	public Automovel consultarAutomovel(String placa) throws SQLException{
		
		DAOAutomovel auto = new DAOAutomovel();
	
		return auto.consultarAutomovel(placa);
	}
	
	public Automovel consultarAutomovel(int cod) throws SQLException{
		
		DAOAutomovel auto = new DAOAutomovel();
	
		return auto.consultarAutomovel(cod);
	}
	
	public void cadastrarAutomovel(int idCategoria, String chassi, String placa, String cidade, String modelo, String fabricante, double km, double tKmLivre, double tKmCont, String estado, boolean statusAuto, int ano ) throws SQLException{
		
		DAOAutomovel auto = new DAOAutomovel();
		auto.cadastrarAutomovel(idCategoria, chassi, placa, cidade, modelo, fabricante, km, tKmLivre, tKmCont, estado, statusAuto, ano);
	}

	public void excluirAutomovel(String placa) throws SQLException {
		DAOAutomovel auto = new DAOAutomovel();
		auto.excluirAutomovel(placa);		
	}

	public void alterarAutomovel(int idCategoria, String chassi, String placa, String cidade, String modelo, String fabricante, double km, double tKmLivre, double tKmCont, String estado, boolean statusAuto, int ano) throws SQLException{
		DAOAutomovel auto = new DAOAutomovel();	
		auto.alterarAutomovel(idCategoria, chassi, placa, cidade, modelo, fabricante, km, tKmLivre, tKmCont, estado, statusAuto, ano);
	}
	
	public DefaultTableModel consultarAutomoveisDisponiveis(int categoria,Tabela pane) throws SQLException{        
        DAOAutomovel auto = new DAOAutomovel();         
        return auto.consultarAutomoveisDisponiveis(categoria, pane);
 
    }
	
	
	public void alterarStatusAutomovel(int cod,boolean status,double km) throws SQLException{        
        DAOAutomovel auto = new DAOAutomovel();         
        auto.alterarStatusAutomovel(cod,status,km);
 
    }
	
	
	
}

