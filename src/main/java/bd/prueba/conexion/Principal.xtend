package bd.prueba.conexion

import java.util.ArrayList
import java.util.List

class Principal {
	def static void main(String[] args) {
		var List<String> columnas = new ArrayList()
		var List<String> valores = new ArrayList()
		columnas.add("nombre")
		columnas.add("apellido")
		valores.add("Nance")
		valores.add("lalala")
		
		val consultas = new Consulta()
		consultas.insertar("usuarios",columnas,valores)
		consultas.select()
	}
}