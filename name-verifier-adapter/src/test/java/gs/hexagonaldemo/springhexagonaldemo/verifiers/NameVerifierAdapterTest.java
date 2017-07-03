package gs.hexagonaldemo.springhexagonaldemo.verifiers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameVerifierAdapterTest {

    private NameVerifierAdapter nameVerifierAdapter = new NameVerifierAdapter();

    @Test
    public void givenAZeroLengthName_returnsFalse() {
        assertFalse(nameVerifierAdapter.verifyName(""));
    }

    @Test
    public void givenANameOfWhitespaceOnly_returnsFalse() {
        assertFalse(nameVerifierAdapter.verifyName("    "));
    }

    @Test
    public void givenANameWithNonAlphabeticCharacters_returnsFalse() {
        assertFalse(nameVerifierAdapter.verifyName("Bob9"));
        assertFalse(nameVerifierAdapter.verifyName("11Bob"));
        assertFalse(nameVerifierAdapter.verifyName("123Bob9"));
        assertFalse(nameVerifierAdapter.verifyName("Hello my 7 name is"));
    }

    @Test
    public void givenANameWithOnlyAlphabeticCharacters_returnsTrue() {
        assertTrue(nameVerifierAdapter.verifyName("hello"));
        assertTrue(nameVerifierAdapter.verifyName("hello my name is"));
    }
}