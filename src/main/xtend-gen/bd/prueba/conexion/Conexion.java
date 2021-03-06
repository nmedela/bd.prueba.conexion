package bd.prueba.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Exceptions;

@Accessors
@SuppressWarnings("all")
public class Conexion {
  public static Connection cnx = null;
  
  public static String contraseniaUno = "creoentuvoz";
  
  public static String contraseniaDos = "root";
  
  public static Connection obtener() throws SQLException, ClassNotFoundException {
    if ((Conexion.cnx == null)) {
      try {
        Conexion.cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", Conexion.contraseniaDos);
      } catch (final Throwable _t) {
        if (_t instanceof SQLException) {
          final SQLException ex = (SQLException)_t;
          throw new SQLException(ex);
        } else if (_t instanceof ClassNotFoundException) {
          final ClassNotFoundException ex_1 = (ClassNotFoundException)_t;
          String _message = ex_1.getMessage();
          throw new ClassCastException(_message);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
    return Conexion.cnx;
  }
  
  public static void cerrar() throws SQLException {
    if ((Conexion.cnx != null)) {
      Conexion.cnx.close();
    }
  }
}
