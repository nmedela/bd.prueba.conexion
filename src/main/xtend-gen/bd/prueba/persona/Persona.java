package bd.prueba.persona;

import bd.prueba.seguro.Seguro;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Persona {
  private int id_persona;
  
  private String nombre;
  
  private String direccion;
  
  private int telefono;
  
  private int dni;
  
  private String tipo;
  
  private List<Seguro> seguros = new ArrayList<Seguro>();
  
  public Persona(final int _id, final String _nombre, final String _direccion, final int _tel, final int _dni, final String _tipo) {
    this.id_persona = _id;
    this.nombre = _nombre;
    this.direccion = _direccion;
    this.telefono = _tel;
    this.dni = _dni;
    this.tipo = _tipo;
  }
  
  @Pure
  public int getId_persona() {
    return this.id_persona;
  }
  
  public void setId_persona(final int id_persona) {
    this.id_persona = id_persona;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }
  
  @Pure
  public String getDireccion() {
    return this.direccion;
  }
  
  public void setDireccion(final String direccion) {
    this.direccion = direccion;
  }
  
  @Pure
  public int getTelefono() {
    return this.telefono;
  }
  
  public void setTelefono(final int telefono) {
    this.telefono = telefono;
  }
  
  @Pure
  public int getDni() {
    return this.dni;
  }
  
  public void setDni(final int dni) {
    this.dni = dni;
  }
  
  @Pure
  public String getTipo() {
    return this.tipo;
  }
  
  public void setTipo(final String tipo) {
    this.tipo = tipo;
  }
  
  @Pure
  public List<Seguro> getSeguros() {
    return this.seguros;
  }
  
  public void setSeguros(final List<Seguro> seguros) {
    this.seguros = seguros;
  }
}
