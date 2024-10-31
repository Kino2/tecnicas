import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.example.NominaSocios;
import com.example.Persona;
import com.example.Socio;
import com.example.YaExisteSocioException;

public class SocioTest {
    static Persona p1;
    static NominaSocios nomina;

    @BeforeAll
    public void setSocio() {
        p1 = new Persona("Esteban", "Orellano", "1231231", 21);
        nomina = new NominaSocios();
    }

    @Test
    public void check() throws YaExisteSocioException {
        this.nomina.Asociar(p1);
        try {
            this.nomina.Asociar(p1);
        } catch (YaExisteSocioException E) {
            assertEquals(this.nomina.ContarSocios(), 1);
        }
    }
}
