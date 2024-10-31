package TestNG;

import com.example.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestActividadNG {
    OfertaActividades Activ;
    Actividad Act1;

    @BeforeClass
    public void setUp() throws Exception {
        // Inicializamos las variables de instancia correctamente
        Activ = new OfertaActividades();
        Persona p1 = new Persona("Esteban", "Orellano", "1231231", 21);
        Act1 = new Actividad("asda", p1, 123, 54);
    }

    @Test
    public void testAgregarActividadesNg() throws Exception {
        Activ.NuevaActividad(Act1);
 
        try {
            Activ.NuevaActividad(Act1);  // Intentamos agregar la misma actividad de nuevo
        } catch (Exception e) {
            // Usamos Assert.assertEquals de TestNG

            Assert.assertEquals(Activ.CantidadActividades(), 1, "La cantidad de actividades no es la esperada");

        }
    }
}
