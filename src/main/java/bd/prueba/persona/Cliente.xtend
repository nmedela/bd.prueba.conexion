package bd.prueba.persona

import java.time.LocalDateTime
import bd.prueba.seguro.SeguroVida
import bd.prueba.seguro.Seguro
import java.util.Set
import java.sql.Date
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List
import java.util.ArrayList

@Accessors
class Cliente extends Persona {
	Date fecha_de_nacimiento
	SeguroVida seguroVida
	List<Beneficiario> beneficiarios = new  ArrayList

	new(int _id, String _nombre, String _direccion, int _tel, int _dni, String _tipo, Date _fecha) {
		super(_id, _nombre, _direccion, _tel, _dni, _tipo)
		this.fecha_de_nacimiento = _fecha
	}
}
