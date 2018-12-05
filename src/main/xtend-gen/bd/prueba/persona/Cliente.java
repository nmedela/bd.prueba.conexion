package bd.prueba.persona;

import bd.prueba.persona.Beneficiario;
import bd.prueba.persona.Persona;
import bd.prueba.seguro.SeguroVida;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Cliente extends Persona {
  private Date fecha_de_nacimiento;
  
  private SeguroVida seguroVida;
  
  private List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
  
  public Cliente(final int _id, final String _nombre, final String _direccion, final int _tel, final int _dni, final String _tipo, final Date _fecha) {
    super(_id, _nombre, _direccion, _tel, _dni, _tipo);
    this.fecha_de_nacimiento = _fecha;
  }
  
  @Pure
  public Date getFecha_de_nacimiento() {
    return this.fecha_de_nacimiento;
  }
  
  public void setFecha_de_nacimiento(final Date fecha_de_nacimiento) {
    this.fecha_de_nacimiento = fecha_de_nacimiento;
  }
  
  @Pure
  public SeguroVida getSeguroVida() {
    return this.seguroVida;
  }
  
  public void setSeguroVida(final SeguroVida seguroVida) {
    this.seguroVida = seguroVida;
  }
  
  @Pure
  public List<Beneficiario> getBeneficiarios() {
    return this.beneficiarios;
  }
  
  public void setBeneficiarios(final List<Beneficiario> beneficiarios) {
    this.beneficiarios = beneficiarios;
  }
}
