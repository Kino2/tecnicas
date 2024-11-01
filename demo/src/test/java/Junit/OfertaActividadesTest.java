package Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.OfertaActividades;
import com.example.Actividad;
import com.example.Persona;

public class OfertaActividadesTest {
    OfertaActividades Activ;  // Variable de instancia
    Actividad Act1;           // Variable de instancia

    @BeforeEach
    void setUp() throws Exception {
        Activ = new OfertaActividades();  // Inicializamos las variables de instancia
        Persona p1 = new Persona("Esteban", "Orellano", "1231231", 21);
        Act1 = new Actividad("asda", p1, 123, 54);
    }

    @Test
    void testAgregarActividades() throws Exception {
        // Agregamos la actividad por primera vez
        Activ.NuevaActividad(Act1);
        
        // Verificamos que agregar la misma actividad lanza la excepción esperada
        Exception exception = assertThrows(Exception.class, () -> {
            Activ.NuevaActividad(Act1);
        });

        // Verificamos que solo hay una actividad registrada después del intento duplicado
        assertEquals(1, Activ.CantidadActividades(), "La cantidad de actividades no es la esperada");
    }
}

