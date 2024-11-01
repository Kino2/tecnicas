package TestNG;

import java.util.ArrayList;
import com.example.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import org.testng.Assert;

public class TestSocio {

    Persona p1;
    Persona p2;
    Persona p3;
    NominaSocios nomina;
    Actividad act;

    @BeforeClass
    void setSocio() throws Exception {
        nomina = new NominaSocios();
        p1 = new Persona("Estebancitoi", "Orellano", "1231231", 21);
        p2 = new Persona("asdasdas", "Orellano", "1231231", 21);
        p3 = new Persona("Estelaputasdasdwban", "Orellano", "1231231", 21);
        act = new Actividad("asd", p2, 45465, 5);
    }

//Comprobar que el método toString() devuelve una cadena con el formato <Apellido>, <Nombre>
    @Test
    public void testToStringSocio() throws Exception {
        try {
            nomina.Asociar(p1);

        } catch (YaExisteSocioException e) {
            Assert.fail();
        }
        
        ArrayList<Socio> nominaToString = nomina.GetNomina();
        String resultadoToString = nominaToString.get(0).toString();

        String esperado = p1.getApellido()+",  "+ p1.getNombre();

        Assert.assertEquals(esperado, resultadoToString);

    }

}
