import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uniajc.CuentaAhorros;
import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {

    @Test
    void testCuentaAhorrosActivaSeActivaConSaldoMinimo() {
        CuentaAhorros cuenta = new CuentaAhorros(10000.0f, 0.05f);

        assertEquals(10000.0f, cuenta.getSaldo(), 0.001f);
    }

    @Test
    void testConsignarPuedeActivarCuentaAhorros() {
        CuentaAhorros cuenta = new CuentaAhorros(5000.0f, 0.05f);

        cuenta.consignar(5000.0f);

        assertEquals(10000.0f, cuenta.getSaldo(), 0.001f);
    }

    @Test
    void testRetirarSiCuentaEsInactivaLanzaExcepcion() {
        CuentaAhorros cuenta = new CuentaAhorros(9000.0f, 0.05f);

        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> cuenta.retirar(1000.0f)
        );

        assertEquals("La cuenta de ahorros está inactiva. No se pueden realizar transacciones.", exception.getMessage());
        assertEquals(9000.0f, cuenta.getSaldo(), 0.001f);
    }

    @Test
    void testConsignarCantidadNegativaSeRechaza() {
        CuentaAhorros cuenta = new CuentaAhorros(10000.0f, 0.05f);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> cuenta.consignar(-1.0f)
        );

        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
        assertEquals(10000.0f, cuenta.getSaldo(), 0.001f);
    }
}
