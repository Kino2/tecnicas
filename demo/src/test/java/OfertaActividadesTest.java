import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.*;

public class OfertaActividadesTest {
    static OfertaActividades Activ;
    static Actividad Act1;

    @BeforeAll
    static void setUp() throws Exception {
        OfertaActividades Activ = new OfertaActividades();
        Persona p1 = new Persona("Esteban", "Orellano", "1231231", 21);
        Actividad Act1 = new Actividad("asda", p1, 123, 54);
    }

    @Test
    void testAgregarActividades() throws Exception {
        OfertaActividades.NuevaActividad(Act1);
        try {
            OfertaActividades.NuevaActividad(Act1);
        } catch (Exception e) {
            assertEquals(OfertaActividades.CantidadActividades(), 1);
        }
    }
}
