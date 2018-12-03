package bd.prueba.seguro;

import java.sql.Date;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Seguro {
  private Integer id_seguro;
  
  private int id_cliente;
  
  private int id_agente;
  
  private String estado;
  
  private Date fecha_inicio;
  
  private Date fecha_vencimiento;
  
  private double prima;
  
  private char tipo;
  
  @Pure
  public Integer getId_seguro() {
    return this.id_seguro;
  }
  
  public void setId_seguro(final Integer id_seguro) {
    this.id_seguro = id_seguro;
  }
  
  @Pure
  public int getId_cliente() {
    return this.id_cliente;
  }
  
  public void setId_cliente(final int id_cliente) {
    this.id_cliente = id_cliente;
  }
  
  @Pure
  public int getId_agente() {
    return this.id_agente;
  }
  
  public void setId_agente(final int id_agente) {
    this.id_agente = id_agente;
  }
  
  @Pure
  public String getEstado() {
    return this.estado;
  }
  
  public void setEstado(final String estado) {
    this.estado = estado;
  }
  
  @Pure
  public Date getFecha_inicio() {
    return this.fecha_inicio;
  }
  
  public void setFecha_inicio(final Date fecha_inicio) {
    this.fecha_inicio = fecha_inicio;
  }
  
  @Pure
  public Date getFecha_vencimiento() {
    return this.fecha_vencimiento;
  }
  
  public void setFecha_vencimiento(final Date fecha_vencimiento) {
    this.fecha_vencimiento = fecha_vencimiento;
  }
  
  @Pure
  public double getPrima() {
    return this.prima;
  }
  
  public void setPrima(final double prima) {
    this.prima = prima;
  }
  
  @Pure
  public char getTipo() {
    return this.tipo;
  }
  
  public void setTipo(final char tipo) {
    this.tipo = tipo;
  }
}
