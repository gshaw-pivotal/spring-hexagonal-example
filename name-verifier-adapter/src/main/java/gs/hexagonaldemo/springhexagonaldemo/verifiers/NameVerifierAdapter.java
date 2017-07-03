package gs.hexagonaldemo.springhexagonaldemo.verifiers;

import gs.hexagonaldemo.springhexagonaldemo.ports.NameVerifierService;

public class NameVerifierAdapter implements NameVerifierService{

    @Override
    public boolean verifyName(String name) {
        name = name.trim();
        if (name.length() != 0 && checkNameIsAlphabeticOnly(name)) {
            return true;
        }

        return false;
    }

    private boolean checkNameIsAlphabeticOnly(String name) {
        return name.matches("[a-zA-Z ]+");
    }
}
