package org.libertas;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private Connection con;
	
	public Conexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://54.91.193.137:3306/libertas5per?verifyServerCertificate=false&useSSL=false",
					"libertas", "123456");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void desconecta() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
