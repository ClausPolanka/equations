import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ZohhakRunner.class)
public class EquationsTest {

    @TestWith({ "0=0", "1=1", "2=2", "3=3", "4=4", "5=5", "6=6", "7=7", "8=8", "9=9" })
    public void findCorrectEquationsReturnsInputEquationWhenEquationIsAlreadyCorrect(String inputEquation) {
        Set<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(equalTo(inputEquation)));
    }

    @Test
    public void findCorrectEquationsReturnsEmptyListWhenEquationIsZeroEqualsOne() {
        String inputEquation = "0=1";
        Set<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(0));
    }

    @Test
    public void findCorrectEquationsReturnsCorrectEquationsWhenEquationIsTwoEqualsThree() {
        String inputEquation = "2=3";
        Set<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(2));
        Iterator<String> it = result.iterator();
        assertThat(it.next(), is(equalTo("2=2")));
        assertThat(it.next(), is(equalTo("3=3")));
    }

}
