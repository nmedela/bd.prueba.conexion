package bd.prueba.api;

import bd.prueba.api.SeguroController;
import org.uqbar.xtrest.api.XTRest;

@SuppressWarnings("all")
public class App {
  public static void main(final String[] args) {
    XTRest.start(9000, SeguroController.class);
  }
}
