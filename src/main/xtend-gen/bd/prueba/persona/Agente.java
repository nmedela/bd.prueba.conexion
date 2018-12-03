package bd.prueba.persona;

import bd.prueba.persona.Persona;

@SuppressWarnings("all")
public class Agente extends Persona {
  private Double comision_anual;
  
  public Agente(final int _id, final String _nombre, final String _direccion, final int _tel, final int _dni, final String _tipo, final Double _comision) {
    super(_id, _nombre, _direccion, _tel, _dni, _tipo);
    this.comision_anual = _comision;
  }
}
