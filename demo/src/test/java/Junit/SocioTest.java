package Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.NominaSocios;
import com.example.Persona;
import com.example.Actividad;
import com.example.EdadInsuficienteException;
import com.example.Socio;
import com.example.YaExisteSocioException;

public class SocioTest {

    // Variables de instancia, sin `static`
    Persona p1;
    Persona p2;
    Persona p3;
    NominaSocios nomina;
    Actividad act;

    @BeforeEach
    void setUp() throws Exception {
        nomina = new NominaSocios();
        p1 = new Persona("Estebancito", "Orellano", "1231231", 21);
        p2 = new Persona("asdasdas", "Orellano", "1231231", 21);
        p3 = new Persona("Estela", "Orellano", "1231231", 21);
        act = new Actividad("asd", p2, 45465, 5);
    }

    @Test
    void check() throws Exception{
        // Uso de la instancia `nomina` en lugar de una referencia estática
        nomina.Asociar(p1);
        try {
            nomina.Asociar(p1); // Intento de asociar la misma persona nuevamente
        } catch (YaExisteSocioException e) {
            assertEquals(1, nomina.ContarSocios(), "La cantidad de socios no es la esperada");
        }
    }

    @Test
    void encargadoEsSocio() throws Exception {
        // Arrange: Asociamos varios socios a la nómina
        nomina.Asociar(p1);
        nomina.Asociar(p2);
        nomina.Asociar(p3);

        // Act: Obtenemos la lista de socios en la nómina
        ArrayList<Socio> list = nomina.GetNomina();
        
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


