package bd.prueba.persona
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List
import bd.prueba.seguro.Seguro
import java.util.ArrayList

@Accessors
class Persona {
	int id_persona
	String nombre
	String direccion
	int telefono
	int dni
	String tipo
	List<Seguro> seguros = new ArrayList()

	new(int _id, String _nombre, String _direccion, int _tel, int _dni, String _tipo) {
		this.id_persona = _id
		this.nombre = _nombre
		this.direccion = _direccion
		this.telefono = _tel
		this.dni = _dni
		this.tipo = _tipo
	}


}
