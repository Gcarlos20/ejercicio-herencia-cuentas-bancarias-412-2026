import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uniajc.CuentaCorriente;
import org.junit.jupiter.api.Test;

public class CuentaCorrienteTest {

    @Test
    void testConsignarActualizaSaldo() {
        CuentaCorriente cuenta = new CuentaCorriente(100.0f, 0.05f);

        cuenta.consignatr(50.0f);

        assertEquals(150.0f, cuenta.getSaldo(), 0.001f);
    }

    @Test
    void testRetirarCantidadNegativaSeRechaza() {
        CuentaCorriente cuenta = new CuentaCorriente(100.0f, 0.05f);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> cuenta.retirar(-10.0f)
        );

        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
        assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
    }
}
