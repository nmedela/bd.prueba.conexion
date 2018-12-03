package bd.prueba.seguro;

import bd.prueba.seguro.Beneficiarios;
import bd.prueba.seguro.Seguro;
import java.util.Set;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class SeguroVida extends Seguro {
  private String ocupacion;
  
  private String cobertura;
  
  private Set<Beneficiarios> beneficiarios = CollectionLiterals.<Beneficiarios>newHashSet();
  
  @Pure
  public String getOcupacion() {
    return this.ocupacion;
  }
  
  public void setOcupacion(final String ocupacion) {
    this.ocupacion = ocupacion;
  }
  
  @Pure
  public String getCobertura() {
    return this.cobertura;
  }
  
  public void setCobertura(final String cobertura) {
    this.cobertura = cobertura;
  }
  
  @Pure
  public Set<Beneficiarios> getBeneficiarios() {
    return this.beneficiarios;
  }
  
  public void setBeneficiarios(final Set<Beneficiarios> beneficiarios) {
    this.beneficiarios = beneficiarios;
  }
}
