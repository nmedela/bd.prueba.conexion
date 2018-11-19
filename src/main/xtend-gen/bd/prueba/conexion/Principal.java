package bd.prueba.conexion;

import bd.prueba.conexion.Consulta;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class Principal {
  public static void main(final String[] args) {
    List<String> columnas = new ArrayList<String>();
    List<String> valores = new ArrayList<String>();
    columnas.add("nombre");
    columnas.add("apellido");
    valores.add("Nance");
    valores.add("lalala");
    final Consulta consultas = new Consulta();
    final List<String> _converted_columnas = (List<String>)columnas;
    final List<String> _converted_valores = (List<String>)valores;
    consultas.insertar("usuarios", ((String[])Conversions.unwrapArray(_converted_columnas, String.class)), ((String[])Conversions.unwrapArray(_converted_valores, String.class)));
    consultas.select();
  }
}
