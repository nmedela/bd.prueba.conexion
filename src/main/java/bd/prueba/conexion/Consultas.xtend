package bd.prueba.conexion

import bd.prueba.persona.Beneficiario
import bd.prueba.persona.Cliente
import bd.prueba.persona.Persona
import bd.prueba.seguro.Beneficiarios
import bd.prueba.seguro.SeguroVida
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.List
import org.omg.CORBA.UserException

class Consultas {
	var String selectSQL = "SELECT * FROM mydb.persona ";
	static var PreparedStatement ps
	static var Connection conn = Conexion.obtener();

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
		var String insertarSQL = "INSERT INTO mydb." + tabla + "(" + nombresCol + ") VALUES (" + valoresCol + ")";

		try {
			val PreparedStatement pstmt = conn.prepareStatement(insertarSQL);
			pstmt.executeUpdate()

		} catch (SQLException e) {
			System.out.println(e)
		}
	}

	def void select() {
		try {

			val PreparedStatement pstmt = conn.prepareStatement(this.selectSQL);
			val ResultSet resultado = pstmt.executeQuery()
			val List<Persona> usuarios = new ArrayList()
			while (resultado.next()) {
				usuarios.add(
					new Persona(resultado.getInt("ID_Persona"), resultado.getString("nombre"),
						resultado.getString("direccion"), resultado.getInt("telefono"), resultado.getInt("dni"),
						resultado.getString("tipo")))
			}
			usuarios.forEach[user|System.out.println(user.nombre + " " + user.dni)]

		} catch (SQLException e) {
			System.out.println(e)
		}

	}

	static def Cliente buscarClienteParaCrearSeguroDeVida(int dni) {

		ps = conn.prepareStatement("call mydb.buscarClientePorDniParaCrearSeguroDeVida(?)");
		ps.setInt(1, dni)
		val ResultSet resultado = ps.executeQuery()
		resultado.next()
		val Integer idPersona = resultado.getInt("id_persona")
		if (idPersona === null) {
			throw new Exception("No Existe el cliente buscado")
		}
		val cliente = new Cliente(resultado.getInt("id_persona"), resultado.getString("nombre"),
			resultado.getString("direccion"), resultado.getInt("telefono"), resultado.getInt("dni"),
			resultado.getString("tipo"), resultado.getDate("fecha_nacimiento"))
		val Integer idSeguro = resultado.getInt("id_seguro")
		if (idSeguro !== null) {

			var SeguroVida seguroVida = new SeguroVida => [
				id_seguro = resultado.getInt("id_seguro")
				fecha_inicio = resultado.getDate("fecha_inicio")
				fecha_vencimiento = resultado.getDate("fecha_vencimiento")
				prima = resultado.getDouble("prima")
				tipo = 'v'
				estado = resultado.getString("estado")
				cobertura = resultado.getString("cobertura")
				ocupacion = resultado.getString("ocupacion")
			]
			System.out.println(cliente.nombre)

			seguroVida.beneficiarios = beneficiariosDeSeguro(idSeguro).toSet
//				System.out.println(beneficiariosDeSeguro(idSeguro))
			cliente.seguroVida = seguroVida
		}
		return cliente
	}

	static def List<Beneficiarios> beneficiariosDeSeguro(int seguro) {
		System.out.println("entro aca")

		ps = conn.prepareStatement("call mydb.beneficiariosDeSeguro(?)");
		ps.setInt(1, seguro)
		val List<Beneficiarios> beneficiarios = new ArrayList
		val ResultSet resultado = ps.executeQuery()
		while (resultado.next()) {
			try {
				beneficiarios.add(
					new Beneficiarios(resultado.getInt("id_beneficiario"), resultado.getString("nombre"), "", 0, 0, 'b',
						resultado.getString("lazo_o_vinculo"), resultado.getInt("id_vinculo"), seguro,
						resultado.getDouble("porcentaje_asignado"))
				)

			} catch (UserException e) {
				System.out.println(e.message)
			}
		}

		beneficiarios
	}

	static def List<Beneficiario> beneficiariosDeCliente(int idCliente) {
		System.out.println("entro aca")

		ps = conn.prepareStatement("call mydb.buscarBeneficiariosDeCliente(?)");
		ps.setInt(1, idCliente)
		val List<Beneficiario> beneficiarios = new ArrayList
		val ResultSet resultado = ps.executeQuery()
		while (resultado.next()) {
			try {
				beneficiarios.add(
					new Beneficiario(resultado.getInt("id_beneficiario"), resultado.getString("nombre"), "", 0, 0, 'b',
						resultado.getString("lazo_o_vinculo"), idCliente)
				)

			} catch (UserException e) {
				System.out.println(e.message)
			}
		}

		beneficiarios
	}

}
