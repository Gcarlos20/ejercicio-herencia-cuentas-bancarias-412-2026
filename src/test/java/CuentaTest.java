import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uniajc.Cuenta;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    @Test
    void testConsignarActualizaSaldoYContador() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);

        cuenta.consignar(50.0f);

        assertEquals(150.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(1, cuenta.getNumeroConsignaciones());
        assertEquals(0, cuenta.getNumeroRetiros());
    }

    @Test
    void testConsignarCantidadNegativaEsRechazada() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> cuenta.consignar(-10.0f)
        );

        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
        assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(0, cuenta.getNumeroConsignaciones());
    }

    @Test
    void testRetirarCuandoHayFondosActualizaSaldoYContador() {
        Cuenta cuenta = new Cuenta(200.0f, 0.05f);

        cuenta.retirar(50.0f);

        assertEquals(150.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(1, cuenta.getNumeroRetiros());
    }

    @Test
    void testRetirarCuandoNoHayFondosLanzaExcepcion() {
        Cuenta cuenta = new Cuenta(50.0f, 0.05f);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> cuenta.retirar(60.0f)
        );

        assertEquals("Fondos insuficientes", exception.getMessage());
        assertEquals(50.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(0, cuenta.getNumeroRetiros());
    }

    @Test
    void testRetirarCantidadNegativaEsRechazada() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> cuenta.retirar(-10.0f)
        );

        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
        assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
    }

    @Test
    void testCalcularInteresAumentaSaldoSegunTasaMensual() {
        Cuenta cuenta = new Cuenta(1000.0f, 0.12f);

        cuenta.calcularInteres();

        assertEquals(1010.0f, cuenta.getSaldo(), 0.001f);
    }

    @Test
    void testExtractoMensualAplicaInteresYComision() {
        Cuenta cuenta = new Cuenta(1000.0f, 0.12f);
        cuenta.setComisionMensual(20.0f);

        cuenta.extractoMensual();

        assertEquals(1000.0f + (1000.0f * 0.12f / 12.0f) - 20.0f, cuenta.getSaldo(), 0.001f);
    }
}
