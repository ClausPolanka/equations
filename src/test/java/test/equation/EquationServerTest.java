package test.equation;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

public class EquationServerTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void exampleTest() throws IOException {
        stubFor(get(urlEqualTo("/assignment/stage/1/testcase/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n  equation: \"8=8\"\n}")));

//        TestRunner.runTestCase(1);

        //        assertTrue(result.wasSuccessful());
        //
        verify(postRequestedFor(urlEqualTo("/assignment/stage/1/testcase/1"))
                .withRequestBody(matching("{ \"correctedEquations\": [\"8=8\"]}"))
                .withHeader("Content-Type", matching("application/json; utf-8")));
    }

}
