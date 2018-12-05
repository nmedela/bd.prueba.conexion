package bd.prueba.persona;

import bd.prueba.persona.Persona;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Beneficiario extends Persona {
  private int id_vinculo;
  
  private String lazo_o_vinculo;
  
  public Beneficiario(final int _id, final String _nombre, final String _direccion, final int _tel, final int _dni, final String _tipo, final String _lazo, final int _vinculo) {
    super(_id, _nombre, _direccion, _tel, _dni, _tipo);
    this.id_vinculo = _vinculo;
    this.lazo_o_vinculo = _lazo;
  }
  
  @Pure
  public int getId_vinculo() {
    return this.id_vinculo;
  }
  
  public void setId_vinculo(final int id_vinculo) {
    this.id_vinculo = id_vinculo;
  }
  
  @Pure
  public String getLazo_o_vinculo() {
    return this.lazo_o_vinculo;
  }
  
  public void setLazo_o_vinculo(final String lazo_o_vinculo) {
    this.lazo_o_vinculo = lazo_o_vinculo;
  }
}
