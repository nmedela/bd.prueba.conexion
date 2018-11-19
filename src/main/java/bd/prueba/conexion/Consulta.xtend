package bd.prueba.conexion

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.ResultSet
import java.util.List
import java.util.ArrayList

class Consulta {
	var String selectSQL = "SELECT * FROM basedetarea.usuarios ";

	var Connection conn = Conexion.obtener();

	def void insertar(String tabla, String[] columnas, String[] valores) {
		var int i = 0
		var String nombresCol = ""
		var String valoresCol = ""
		for (i = 0; i < columnas.size; i++) {
			nombresCol = nombresCol + "," + columnas.get(i)
			valoresCol = valoresCol + ",'" + valores.get(i) + "'"
		}
		nombresCol = nombresCol.substring(1)
		valoresCol = valoresCol.substring(1)
		System.out.println(valoresCol)
		var String insertarSQL = "INSERT INTO basedetarea." + tabla + "(" + nombresCol + ") VALUES (" + valoresCol +
			")";

		try {
			val PreparedStatement pstmt = this.conn.prepareStatement(insertarSQL);
			pstmt.executeUpdate()
			System.out.println("como si supieramos")

		} catch (SQLException e) {
			System.out.println(e)
		}
	}

	def void select() {
		try {

			val PreparedStatement pstmt = this.conn.prepareStatement(this.selectSQL);
			val ResultSet resultado = pstmt.executeQuery()
			System.out.println("como si supieramos" + resultado.toString)
			val List<Usuario> usuarios = new ArrayList()
			while (resultado.next()) {
				usuarios.add(new Usuario(resultado.getString("nombre"), resultado.getString("apellido")))
			}
			usuarios.forEach[user|System.out.println(user.nombre + " " + user.apellido)]

		} catch (SQLException e) {
			System.out.println(e)
		}

	}
}
