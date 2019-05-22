import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EquationsTest {

    @Test
    public void findCorrectEquationsReturnsInputEquationWhenEquationIsZeroEqualsZero() {
        String inputEquation = "0=0";
        List<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(equalTo(inputEquation)));
    }

    @Test
    public void findCorrectEquationsReturnsInputEquationWhenEquationIsOneEqualsOne() {
        String inputEquation = "1=1";
        List<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(equalTo(inputEquation)));
    }

    @Test
    public void findCorrectEquationsReturnsEmptyListWhenEquationIsZeroEqualsOne() {
        String inputEquation = "0=1";
        List<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(0));
    }

    @Test
    public void findCorrectEquationsReturnsCorrectEquationsWhenEquationIsTwoEqualsThree() {
        String inputEquation = "2=3";
        List<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(equalTo("2=2")));
        assertThat(result.get(1), is(equalTo("3=3")));
    }
}
