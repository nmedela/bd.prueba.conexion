package bd.prueba.conexion;

import bd.prueba.conexion.Conexion;
import bd.prueba.conexion.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class Consulta {
  private String selectSQL = "SELECT * FROM basedetarea.usuarios ";
  
  private Connection conn = new Function0<Connection>() {
    public Connection apply() {
      try {
        Connection _obtener = Conexion.obtener();
        return _obtener;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
  
  public void insertar(final String tabla, final String[] columnas, final String[] valores) {
    int i = 0;
    String nombresCol = "";
    String valoresCol = "";
    for (i = 0; (i < ((List<String>)Conversions.doWrapArray(columnas)).size()); i++) {
      {
        String _get = columnas[i];
        String _plus = ((nombresCol + ",") + _get);
        nombresCol = _plus;
        String _get_1 = valores[i];
        String _plus_1 = ((valoresCol + ",\'") + _get_1);
        String _plus_2 = (_plus_1 + "\'");
        valoresCol = _plus_2;
      }
    }
    nombresCol = nombresCol.substring(1);
    valoresCol = valoresCol.substring(1);
    System.out.println(valoresCol);
    String insertarSQL = (((((("INSERT INTO basedetarea." + tabla) + "(") + nombresCol) + ") VALUES (") + valoresCol) + 
      ")");
    try {
      final PreparedStatement pstmt = this.conn.prepareStatement(insertarSQL);
      pstmt.executeUpdate();
      System.out.println("como si supieramos");
    } catch (final Throwable _t) {
      if (_t instanceof SQLException) {
        final SQLException e = (SQLException)_t;
        System.out.println(e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public void select() {
    try {
      final PreparedStatement pstmt = this.conn.prepareStatement(this.selectSQL);
      final ResultSet resultado = pstmt.executeQuery();
      String _string = resultado.toString();
      String _plus = ("como si supieramos" + _string);
      System.out.println(_plus);
      final List<Usuario> usuarios = new ArrayList<Usuario>();
      while (resultado.next()) {
        String _string_1 = resultado.getString("nombre");
        String _string_2 = resultado.getString("apellido");
        Usuario _usuario = new Usuario(_string_1, _string_2);
        usuarios.add(_usuario);
      }
      final Consumer<Usuario> _function = new Consumer<Usuario>() {
        public void accept(final Usuario user) {
          String _nombre = user.getNombre();
          String _plus = (_nombre + " ");
          String _apellido = user.getApellido();
          String _plus_1 = (_plus + _apellido);
          System.out.println(_plus_1);
        }
      };
      usuarios.forEach(_function);
    } catch (final Throwable _t) {
      if (_t instanceof SQLException) {
        final SQLException e = (SQLException)_t;
        System.out.println(e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
