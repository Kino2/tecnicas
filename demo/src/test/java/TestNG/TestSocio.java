package TestNG;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.example.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import org.testng.Assert;

public class TestSocio {

    Persona p1;
    Persona p2;
    Persona p3;
    Persona p4;
    NominaSocios nomina;
    Actividad act;
    private static final Faker faker = new Faker();

    @BeforeClass
    void setSocio() throws Exception {
        nomina = new NominaSocios();
        p1 = new Persona("Estebancitoi", "Orellano", "1231231", 21);
        p2 = new Persona("asdasdas", "Orellano", "1231231", 21);
        p3 = new Persona("Estelaputasdasdwban", "Orellano", "1231231", 21);
        p4 = new Persona("Caramelito", "Divino", "412313", 15);
        act = new Actividad("asd", p2, 45465, 5);
    }

    @DataProvider
    public Object[] GeneradorPersonasTest() {
        Object[] result = new Object[10];
        for (int i = 0; i < 10; i++) {
            result[i] = new Persona(faker.name().firstName(), faker.name().lastName(), faker.number().digits(8),
                    faker.number().numberBetween(18, 85));
        }
        return result;
    }

    // Comprobar que el método toString() devuelve una cadena con el formato
    // <Apellido>, <Nombre>
    @Test
    public void testToStringSocio() throws Exception {
        try {
            nomina.Asociar(p1);

        } catch (YaExisteSocioException e) {
            Assert.fail();
        }

        ArrayList<Socio> nominaToString = nomina.GetNomina();
        String resultadoToString = nominaToString.get(0).toString();

        String esperado = p1.getApellido() + ",  " + p1.getNombre();

        Assert.assertEquals(esperado, resultadoToString);
    }


//Crear un generador de datos de Socios para comprobar que el método toString() devuelve una cadena en formato <Nombre><Apellido>
    @Test(dataProvider = "GeneradorPersonasTest")
    public void testToStringSocioConGenerador(Persona p) throws Exception {
        try {
            nomina.Asociar(p);

        } catch (YaExisteSocioException e) {
            Assert.fail();
        }

        ArrayList<Socio> nominaToString = nomina.GetNomina();
        String resultadoToString = nominaToString.get(0).toString();

        String esperado = p.getNombre() + " " + p.getApellido();
        nomina.Baja(nominaToString.get(0));
        Assert.assertEquals(esperado, resultadoToString);
    }
}
