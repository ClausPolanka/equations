import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

@RunWith(ZohhakRunner.class)
public class EquationsTest {

    @TestWith({ "0=0", "1=1", "2=2", "3=3", "4=4", "5=5", "6=6", "7=7", "8=8", "9=9" })
    public void findCorrectEquationsReturnsInputEquationWhenEquationIsAlreadyCorrect(String inputEquation) {
        Set<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(equalTo(inputEquation)));
    }

    @TestWith({
            "0=1"
    })
    public void findCorrectEquationsReturnsEmptyListWhenThereIsNoSolution(String inputEquation) {
        Set<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(0));
    }

    @TestWith({
            "0=6, 0=0, 6=6",
            "0=9, 0=0, 9=9",
            "2=3, 2=2, 3=3",
            "3=2, 2=2, 3=3",
            "3=5, 3=3, 5=5",
            "5=3, 3=3, 5=5",
            "6=0, 0=0, 6=6",
            "6=9, 6=6, 9=9",
            "9=0, 0=0, 9=9",
            "9=6, 6=6, 9=9"
    })
    public void findCorrectEquationsReturnsCorrectEquations(
            String inputEquation,
            String correctEquation1,
            String correctEquation2
    ) {
        Set<String> result = new Equations().findCorrectEquations(inputEquation);

        assertThat(result.size(), is(2));
        assertThat(result, hasItems(correctEquation1, correctEquation2));
    }

}
