import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EquationsTest {

    @Test
    public void findCorrectEquationsReturnsInputEquationWhenAlreadyCorrect() {
        String inputEquation = "0=0";
        List<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(equalTo(inputEquation)));
    }
}
