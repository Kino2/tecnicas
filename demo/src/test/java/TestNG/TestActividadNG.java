package TestNG;

import com.example.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class TestActividadNG {
    Persona p1;
    Persona p2;
    OfertaActividades Activ;
    Actividad Act1;
    NominaSocios nomina;
    private static final Faker faker = new Faker();

    @BeforeClass
    public void setUp() throws Exception {
        // Inicializamos las variables de instancia correctamente
        nomina = new NominaSocios();
        Activ = new OfertaActividades();
        p1 = new Persona("Esteban", "Orellano", "1231231", 20);
        p2 = new Persona("Gonzalo", "Lucio", "21412", 21);
        Act1 = new Actividad("asda", p2, 1, 18);
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

    @Test
    public void testAgregarActividadesNg() throws Exception {
        Activ.NuevaActividad(Act1);

        try {
            Activ.NuevaActividad(Act1); // Intentamos agregar la misma actividad de nuevo
        } catch (Exception e) {
            // Usamos Assert.assertEquals de TestNG

            Assert.assertEquals(Activ.CantidadActividades(), 1, "La cantidad de actividades no es la esperada");

        }
    }

    // Comprobar que el método toString() de Actividad devuelve una cadena con el
    // formato "<NombreActividad> a cargo de <Apellido>"
    @Test
    public void testToStringActividad() throws Exception {

        // Act: Llamamos al método toString de Actividad
        String resultadoToString = Act1.toString();

        // Assert: Verificamos que el formato devuelto sea el esperado
        String esperado = Act1.getNombre() + "  a cargo de " + Act1.getEncargado().getNombre();

        Assert.assertEquals(resultadoToString, esperado,
                "El método toString() de Actividad no devuelve el formato esperado");
    }

    // Comprobar que al intentar inscribir un socio con una edad no permitida se
    // dispara la excepción EdadInsuficieneException
    @Test
    public void socioMenor() throws Exception {
        try {
            nomina.Asociar(p1);

        } catch (YaExisteSocioException e) {
            Assert.fail("Se esperaba que se lanzara YaExisteSocioException, pero no se lanzó");
        }
        try {
            ArrayList<Socio> lista = nomina.GetNomina();
            Socio persona = lista.get(0);
            Act1.inscribirSocio(persona);
        } catch (EdadInsuficienteException e) {
            Assert.fail("Se esperaba que se lanzara EdadInsuficienteException, pero no se lanzó");
        }
    }

    // Comprobar que al intentar inscribir más usuarios del cupo permitido, se
    // dispara la excepción CupoExcedidoException
    @Test
    public void cupoExcedido() throws Exception {
        try {
            nomina.Asociar(p1);

        } catch (YaExisteSocioException e) {
            Assert.fail("Ya existe el socio");
        }
        try {
            ArrayList<Socio> lista = nomina.GetNomina();
            Socio persona = lista.get(0);
            Act1.inscribirSocio(persona);
        } catch (CupoExcedidoException e) {
            Assert.fail("Se excedió el cupo");
        }
    }
}
