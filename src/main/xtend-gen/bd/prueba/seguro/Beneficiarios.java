package bd.prueba.seguro;

import bd.prueba.persona.Beneficiario;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Beneficiarios extends Beneficiario {
  public Beneficiarios(final int _id, final String _nombre, final String _direccion, final int _tel, final int _dni, final String _tipo, final String _lazo, final int _vinculo, final int _seguro, final double porcentaje) {
    super(_id, _nombre, _direccion, _tel, _dni, _tipo, _lazo, _vinculo);
    this.id_seguro = _seguro;
    this.porcentaje_asignado = porcentaje;
  }
  
  private int id_seguro;
  
  private double porcentaje_asignado;
  
  @Pure
  public int getId_seguro() {
    return this.id_seguro;
  }
  
  public void setId_seguro(final int id_seguro) {
    this.id_seguro = id_seguro;
  }
  
  @Pure
  public double getPorcentaje_asignado() {
    return this.porcentaje_asignado;
  }
  
  public void setPorcentaje_asignado(final double porcentaje_asignado) {
    this.porcentaje_asignado = porcentaje_asignado;
  }
}
