package bd.prueba.conexion;

import bd.prueba.conexion.Conexion;
import bd.prueba.persona.Beneficiario;
import bd.prueba.persona.Cliente;
import bd.prueba.persona.Persona;
import bd.prueba.seguro.Beneficiarios;
import bd.prueba.seguro.SeguroVida;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.omg.CORBA.UserException;

@SuppressWarnings("all")
public class Consultas {
  private String selectSQL = "SELECT * FROM mydb.persona ";
  
  private static PreparedStatement ps;
  
  private static Connection conn = new Function0<Connection>() {
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
    String insertarSQL = (((((("INSERT INTO mydb." + tabla) + "(") + nombresCol) + ") VALUES (") + valoresCol) + ")");
    try {
      final PreparedStatement pstmt = Consultas.conn.prepareStatement(insertarSQL);
      pstmt.executeUpdate();
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
      final PreparedStatement pstmt = Consultas.conn.prepareStatement(this.selectSQL);
      final ResultSet resultado = pstmt.executeQuery();
      final List<Persona> usuarios = new ArrayList<Persona>();
      while (resultado.next()) {
        int _int = resultado.getInt("ID_Persona");
        String _string = resultado.getString("nombre");
        String _string_1 = resultado.getString("direccion");
        int _int_1 = resultado.getInt("telefono");
        int _int_2 = resultado.getInt("dni");
        String _string_2 = resultado.getString("tipo");
        Persona _persona = new Persona(_int, _string, _string_1, _int_1, _int_2, _string_2);
        usuarios.add(_persona);
      }
      final Consumer<Persona> _function = (Persona user) -> {
        String _nombre = user.getNombre();
        String _plus = (_nombre + " ");
        int _dni = user.getDni();
        String _plus_1 = (_plus + Integer.valueOf(_dni));
        System.out.println(_plus_1);
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
  
  public static Cliente buscarClienteParaCrearSeguroDeVida(final int dni) {
    try {
      Consultas.ps = Consultas.conn.prepareStatement("call mydb.buscarClientePorDniParaCrearSeguroDeVida(?)");
      Consultas.ps.setInt(1, dni);
      final ResultSet resultado = Consultas.ps.executeQuery();
      resultado.next();
      final Integer idPersona = Integer.valueOf(resultado.getInt("id_persona"));
      if ((idPersona == null)) {
        throw new Exception("No Existe el cliente buscado");
      }
      int _int = resultado.getInt("id_persona");
      String _string = resultado.getString("nombre");
      String _string_1 = resultado.getString("direccion");
      int _int_1 = resultado.getInt("telefono");
      int _int_2 = resultado.getInt("dni");
      String _string_2 = resultado.getString("tipo");
      Date _date = resultado.getDate("fecha_nacimiento");
      final Cliente cliente = new Cliente(_int, _string, _string_1, _int_1, _int_2, _string_2, _date);
      final Integer idSeguro = Integer.valueOf(resultado.getInt("id_seguro"));
      if ((idSeguro != null)) {
        SeguroVida _seguroVida = new SeguroVida();
        final Procedure1<SeguroVida> _function = (SeguroVida it) -> {
          try {
            it.setId_seguro(Integer.valueOf(resultado.getInt("id_seguro")));
            it.setFecha_inicio(resultado.getDate("fecha_inicio"));
            it.setFecha_vencimiento(resultado.getDate("fecha_vencimiento"));
            it.setPrima(resultado.getDouble("prima"));
            it.setTipo('v');
            it.setEstado(resultado.getString("estado"));
            it.setCobertura(resultado.getString("cobertura"));
            it.setOcupacion(resultado.getString("ocupacion"));
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        };
        SeguroVida seguroVida = ObjectExtensions.<SeguroVida>operator_doubleArrow(_seguroVida, _function);
        System.out.println(cliente.getNombre());
        seguroVida.setBeneficiarios(IterableExtensions.<Beneficiarios>toSet(Consultas.beneficiariosDeSeguro((idSeguro).intValue())));
        cliente.setSeguroVida(seguroVida);
      }
      return cliente;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static List<Beneficiarios> beneficiariosDeSeguro(final int seguro) {
    try {
      List<Beneficiarios> _xblockexpression = null;
      {
        System.out.println("entro aca");
        Consultas.ps = Consultas.conn.prepareStatement("call mydb.beneficiariosDeSeguro(?)");
        Consultas.ps.setInt(1, seguro);
        final List<Beneficiarios> beneficiarios = new ArrayList<Beneficiarios>();
        final ResultSet resultado = Consultas.ps.executeQuery();
        while (resultado.next()) {
          try {
            int _int = resultado.getInt("id_beneficiario");
            String _string = resultado.getString("nombre");
            String _string_1 = resultado.getString("lazo_o_vinculo");
            int _int_1 = resultado.getInt("id_vinculo");
            double _double = resultado.getDouble("porcentaje_asignado");
            Beneficiarios _beneficiarios = new Beneficiarios(_int, _string, "", 0, 0, "b", _string_1, _int_1, seguro, _double);
            beneficiarios.add(_beneficiarios);
          } catch (final Throwable _t) {
            if (_t instanceof UserException) {
              final UserException e = (UserException)_t;
              System.out.println(e.getMessage());
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
        }
        _xblockexpression = beneficiarios;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static List<Beneficiario> beneficiariosDeCliente(final int idCliente) {
    try {
      List<Beneficiario> _xblockexpression = null;
      {
        System.out.println("entro aca");
        Consultas.ps = Consultas.conn.prepareStatement("call mydb.buscarBeneficiariosDeCliente(?)");
        Consultas.ps.setInt(1, idCliente);
        final List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
        final ResultSet resultado = Consultas.ps.executeQuery();
        while (resultado.next()) {
          try {
            int _int = resultado.getInt("id_beneficiario");
            String _string = resultado.getString("nombre");
            String _string_1 = resultado.getString("lazo_o_vinculo");
            Beneficiario _beneficiario = new Beneficiario(_int, _string, "", 0, 0, "b", _string_1, idCliente);
            beneficiarios.add(_beneficiario);
          } catch (final Throwable _t) {
            if (_t instanceof UserException) {
              final UserException e = (UserException)_t;
              System.out.println(e.getMessage());
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
        }
        _xblockexpression = beneficiarios;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
