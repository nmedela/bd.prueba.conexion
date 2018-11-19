package bd.prueba.conexion

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Usuario {
	String nombre
	String apellido
	new(String _nombre, String _apellido){
		this.nombre = _nombre
		this.apellido=_apellido
	}
	
}