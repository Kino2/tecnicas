package Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.OfertaActividades;
import com.example.Actividad;
import com.example.Persona;

public class OfertaActividadesTest {
    static OfertaActividades Activ;  // Variables de clase
    static Actividad Act1;

    @BeforeAll
    static void setUp() throws Exception {
        Activ = new OfertaActividades();  // Inicializamos las variables de clase
        Persona p1 = new Persona("Esteban", "Orellano", "1231231", 21);
        Act1 = new Actividad("asda", p1, 123, 54);
    }

    @Test
    void testAgregarActividades() throws Exception {
        Activ.NuevaActividad(Act1);  // Llamada a través de la instancia "Activ"
        try {
            Activ.NuevaActividad(Act1);  // Intento de agregar la misma actividad
        } catch (Exception e) {
            assertEquals(1, Activ.CantidadActividades(), "La cantidad de actividades no es la esperada");
        }
    }
}
