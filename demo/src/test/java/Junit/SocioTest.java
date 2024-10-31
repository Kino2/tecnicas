package Junit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.*;

public class SocioTest {
    static Persona p1;
    static Persona p2;
    static Persona p3;
    static NominaSocios nomina;
    static Actividad act;

    @BeforeAll
    static void setSocio() throws Exception {
        nomina = new NominaSocios();
        p1 = new Persona("Estebancitoi", "Orellano", "1231231", 21);
        p2 = new Persona("asdasdas", "Orellano", "1231231", 21);
        p3 = new Persona("Estelaputasdasdwban", "Orellano", "1231231", 21);
        act = new Actividad("asd", p2, 45465, 5);
    }

    @Test
    void check() throws YaExisteSocioException{
        NominaSocios.Asociar(p1);
        try {
            NominaSocios.Asociar(p1);
        } catch (YaExisteSocioException e) {
            assertEquals(NominaSocios.ContarSocios(), 1);
        }
    }

    /* @Test
    void encargadoEsSocio() throws YaExisteSocioException {
        NominaSocios.Asociar(p1);
        NominaSocios.Asociar(p2);
        NominaSocios.Asociar(p3);
        ArrayList<Socio> list = NominaSocios.GetNomina();
        for (Socio soc : list) {
            assertNotEquals(soc.getPersona(), act.getEncargado());
        }
    } */
   @Test
    void encargadoEsSocio() throws YaExisteSocioException {
        // Arrange: Asociamos varios socios a la nómina
        NominaSocios.Asociar(p1);
        NominaSocios.Asociar(p2);
        NominaSocios.Asociar(p3);

        // Act: Obtenemos la lista de socios en la nómina
        ArrayList<Socio> list = NominaSocios.GetNomina();
        
        // Verificamos que el encargado de la actividad está en la lista de socios
        boolean encargadoEsSocio = false;
        for (Socio soc : list) {
            if (soc.getPersona().equals(act.getEncargado())) {
                encargadoEsSocio = true;
                break;
            }
        }
        
        // Assert: Confirmamos que el encargado está en la nómina de socios
        assertTrue(encargadoEsSocio, "El encargado de la actividad debe ser un socio de la nómina");
    }

}
