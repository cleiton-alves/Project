package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import objetos.Automovel;
import telas.Tabela;

public class DAOAutomovel {

	AcessoBD bd = new AcessoBD();

	public Automovel consultarAutomovel(String placa) throws SQLException {
		Connection conn = bd.obtemConexao();
		Automovel auto = new Automovel();

		String sqlSelect = "select * from automovel where placa = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, placa);
			rs = stm.executeQuery();

			if (!rs.next()) {

				JOptionPane.showMessageDialog(null,
						"Automóvel não encontrado no banco de dados", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				auto.setChassi(rs.getString(2));
				auto.setPlaca(rs.getString(3));
				auto.setCidade(rs.getString(4));
				auto.setKm(rs.getDouble(5));
				auto.setEstado(rs.getString(6));
				auto.setModelo(rs.getString(7));
				auto.setFabricante(rs.getString(8));
				auto.setTKmLivre(rs.getDouble(9));
				auto.setTKmCont(rs.getDouble(10));
				auto.setIdCategoria(rs.getInt(11));
				auto.setAno(rs.getInt(12));
			}

		} catch (Exception e) {
			System.out.println("erro");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return auto;
	}

	public void cadastrarAutomovel(int idCategoria, String chassi,
			String placa, String cidade, String modelo, String fabricante,
			double km, double tKmLivre, double tKmCont, String estado,
			boolean statusAuto, int ano) throws SQLException {
		Connection conn = bd.obtemConexao();
		try {
			String sqlSelect = "insert into automovel(chassi,placa,cidade,km,estado,modelo,fabricante,"
					+ "tKmLivre,tKmCont, idCategoria, ano, statusAuto) values (?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stm = conn.prepareStatement(sqlSelect);

			stm.setString(1, chassi);
			stm.setString(2, placa);
			stm.setString(3, cidade);
			stm.setDouble(4, km);
			stm.setString(5, estado);
			stm.setString(6, modelo);
			stm.setString(7, fabricante);
			stm.setDouble(8, tKmLivre);
			stm.setDouble(9, tKmCont);
			stm.setInt(10, idCategoria);
			stm.setInt(11, ano);
			stm.setBoolean(12, statusAuto);

			stm.executeUpdate();
			stm.close();

			JOptionPane.showMessageDialog(null,
					"Automóvel cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}

		}
	}

	public void excluirAutomovel(String placa) throws SQLException {
		Connection conn = bd.obtemConexao();
		try {

			String sqlSelect = "delete from automovel where placa = ?";
			PreparedStatement stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, placa);
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}

		}

	}

	public void alterarAutomovel(int idCategoria, String chassi, String placa,
			String cidade, String modelo, String fabricante, double km,
			double tKmLivre, double tKmCont, String estado, boolean statusAuto,
			int ano) throws SQLException {
		Connection conn = bd.obtemConexao();

		try {
			String sqlSelect = "update automovel set chassi=?, placa=?, cidade=?, km=?, estado=?, "
					+ "modelo=?, fabricante=?, tKmLivre=?, tKmCont=?,idCategoria=?, ano=?, statusAuto=?"
					+ " where placa=?";
			PreparedStatement stm = conn.prepareStatement(sqlSelect);

			stm.setString(1, chassi);
			stm.setString(2, placa);
			stm.setString(3, cidade);
			stm.setDouble(4, km);
			stm.setString(5, estado);
			stm.setString(6, modelo);
			stm.setString(7, fabricante);
			stm.setDouble(8, tKmLivre);
			stm.setDouble(9, tKmCont);
			stm.setInt(10, idCategoria);
			stm.setInt(11, ano);
			stm.setBoolean(12, statusAuto);
			stm.setString(13, placa);

			if (stm.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("ERRO --");
			}

		}

	}

	public DefaultTableModel consultarAutomoveisDisponiveis(int categoria,
			Tabela pane) throws SQLException {
		Connection conn = bd.obtemConexao();
		DefaultTableModel modeloTable = (DefaultTableModel) pane.getModel();
		String sqlSelect = "select idAutomovel, placa, c.nome_categoria, modelo, tKmLivre, tKmCont, ano "
				+ "from automovel a inner join categoria c on a.idCategoria = c.idCategoria where a.idCategoria = ? and statusAuto = 0 ";
		String[] dados = new String[8];
		PreparedStatement stm = null;
		ResultSet rs = null;
		int posicao = 0;
		modeloTable.setRowCount(0);
		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, categoria);
			rs = stm.executeQuery();

			if (rs.next() == true) {
				do {
					dados[0] = String.valueOf(posicao + 1);
					dados[1] = rs.getString(1);
					dados[2] = rs.getString(2);
					dados[3] = rs.getString(3);
					dados[4] = rs.getString(4);
					dados[5] = rs.getString(5);
					dados[6] = rs.getString(6);
					dados[7] = rs.getString(7);

					posicao++;
					modeloTable.addRow(dados);

					for (int x = 0; x < dados.length; x++) {
						// System.out.print(" " + dados[x] );
					}
					System.out.print("\n\n ");
				} while (rs.next());

			} else {
				JOptionPane.showMessageDialog(null,
						"Não possui automóveis disponível dessa categoria",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println("erro");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}

		return modeloTable;
	}

	public Automovel consultarAutomovel(int cod) throws SQLException {
		Connection conn = bd.obtemConexao();
		Automovel auto = new Automovel();

		String sqlSelect = "select * from automovel where idAutomovel = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, cod);
			rs = stm.executeQuery();

			if (!rs.next()) {

				JOptionPane.showMessageDialog(null,
						"Automóvel não encontrado no banco de dados", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				auto.setChassi(rs.getString(2));
				auto.setPlaca(rs.getString(3));
				auto.setCidade(rs.getString(4));
				auto.setKm(rs.getDouble(5));
				auto.setEstado(rs.getString(6));
				auto.setModelo(rs.getString(7));
				auto.setFabricante(rs.getString(8));
				auto.setTKmLivre(rs.getDouble(9));
				auto.setTKmCont(rs.getDouble(10));
				auto.setIdCategoria(rs.getInt(11));
				auto.setAno(rs.getInt(12));
				auto.setStatusAuto(rs.getBoolean(13));
			}

		} catch (Exception e) {
			System.out.println("erro");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return auto;
	}

	public DefaultTableModel relatorioLocacoes(int tiporelat, Tabela pane)
			throws SQLException {
		Connection conn = bd.obtemConexao();
		DefaultTableModel modeloTable = (DefaultTableModel) pane.getModel();

		String sqlSelect = "";
		if (tiporelat == 1) {
			sqlSelect = "select a.idAutomovel,a.fabricante,a.modelo,a.ano, c.nome,c.cpf, data_horaDev, valor "
					+ "from emprestimo e inner join automovel a on e.idAutomovel = a.idAutomovel "
					+ "" + "inner join cliente c on e.idCliente = c.idCliente;";
		} else {
			sqlSelect = "select a.idAutomovel,a.fabricante,a.modelo,a.ano, c.nome,c.cpf, data_horaDev, valor from emprestimo "
					+ "e inner join automovel a on e.idAutomovel = a.idAutomovel inner join cliente c on e.idCliente = c.idCliente "
					+ "where e.data_horaEmp =  curdate();";

		}

		String[] dados = new String[9];
		PreparedStatement stm = null;
		ResultSet rs = null;
		int posicao = 0;
		modeloTable.setRowCount(0);
		try {
			stm = conn.prepareStatement(sqlSelect);
			// stm.setInt(1, 0);
			rs = stm.executeQuery();

			if (rs.next() == true) {
				do {
					dados[0] = String.valueOf(posicao + 1);
					dados[1] = rs.getString(1);
					dados[2] = rs.getString(2);
					dados[3] = rs.getString(3);
					dados[4] = rs.getString(4);
					dados[5] = rs.getString(5);
					dados[6] = rs.getString(6);
					dados[7] = rs.getString(7);
					dados[8] = "R$ " + rs.getString(8);

					posicao++;
					modeloTable.addRow(dados);

					for (int x = 0; x < dados.length; x++) {
						// System.out.print(" " + dados[x] );
					}
					System.out.print("\n\n ");
				} while (rs.next());

			} else {
				JOptionPane.showMessageDialog(null,
						"Não possui automóveis locados", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println("erro");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}

		return modeloTable;
	}

	public void alterarStatusAutomovel(int cod, boolean status, double km)
			throws SQLException {
		Connection conn = bd.obtemConexao();

		try {
			String sqlSelect = "update automovel set statusAuto=?, km=?  where idAutomovel=?";
			PreparedStatement stm = conn.prepareStatement(sqlSelect);

			stm.setBoolean(1, status);
			stm.setDouble(2, km);
			stm.setInt(3, cod);

			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("ERRO --");
			}

		}

	}

}
