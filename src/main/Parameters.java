package src.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Parameters {
    private String errorMessage;
    private boolean isValid = true;

    String[] parameters;

    Parameters(String[] parameters) {
        this.parameters = parameters;
        validate(parameters);
    }

    enum validProperties {
        BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD, SAD, HAPPY
    }

    private void validate(String[] parameters) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> invalidParameters = new ArrayList<>();

        validateFirstParameter(parameters, builder);
        validateSecondParameter(parameters, builder);
        validateProperties(parameters, invalidParameters, builder);
        validateMutuallyExclusiveProperties(parameters, builder, invalidParameters);

        this.errorMessage = builder.toString();
        this.isValid = builder.length() == 0;
    }

    private void validateFirstParameter(String[] parameters, StringBuilder builder) {
        if (Long.parseLong(parameters[0]) < 0) {
            builder.append("The first parameter should be a natural number or zero.");
        }
    }

    private void validateSecondParameter(String[] parameters, StringBuilder builder) {
        if (parameters.length > 1 && Long.parseLong(parameters[1]) <= 0) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append("The second parameter should be a natural number");
        }
    }

    private void validateProperties(String[] parameters, ArrayList<String> invalidParameters, StringBuilder builder) {
        if (parameters.length > 2) {
            for (int i = 2; i < parameters.length; i++) {
                if (!isValidProperty(parameters[i])) {
                    invalidParameters.add(parameters[i]);
                }
            }

            if (invalidParameters.size() == 1) {
                appendErrorMessage(builder, "The property " + invalidParameters.get(0) + " is wrong.");
            } else if (invalidParameters.size() > 1) {
                appendErrorMessage(builder, "The properties " + invalidParameters + " are wrong.");
            }
        }
    }

    private void validateMutuallyExclusiveProperties(String[] parameters, StringBuilder builder, ArrayList<String> invalidParameters) {
        if (parameters.length > 3 && propertiesAreExclusive(parameters)) {
            appendErrorMessage(builder, "The request contains mutually exclusive properties: " + getMutuallyExclusiveProperties(parameters));
        }
    }

    private void appendErrorMessage(StringBuilder builder, String errorMessage) {
        if (builder.length() > 0) {
            builder.append("\n");
        }
        builder.append(errorMessage).append("\nAvailable properties: ").append(Arrays.toString(validProperties.values()));
    }


    public boolean propertiesAreExclusive(String[] parameters) {
        return getMutuallyExclusiveProperties(parameters).length() > 0;
    }

    public String getMutuallyExclusiveProperties(String[] parameters) {
        if (inArray(parameters, "even") && inArray(parameters, "odd")) {
            return "[EVEN, ODD]";
        }

        if (inArray(parameters, "duck") && inArray(parameters, "spy")) {
            return "[DUCK, SPY]";
        }

        if (inArray(parameters, "sunny") && inArray(parameters, "square")) {
            return "[SUNNY, SQUARE]";
        }

        if (inArray(parameters, "sad") && inArray(parameters, "happy")) {
            return "[SAD, HAPPY]";
        }

        if (inArray(parameters, "-even") && inArray(parameters, "-odd")) {
            return "[-EVEN, -ODD]";
        }

        if (inArray(parameters, "-duck") && inArray(parameters, "-spy")) {
            return "[-DUCK, -SPY]";
        }

        if (inArray(parameters, "-sad") && inArray(parameters, "-happy")) {
            return "[-SAD, -HAPPY]";
        }

        for (int i = 0; i < parameters.length; i++) {
            for (int j = i + 1; j < parameters.length; j++) {
                if (parameters[i].equalsIgnoreCase("-" + parameters[j]) ||
                        parameters[j].equalsIgnoreCase("-" + parameters[i])) {
                    return "[" + parameters[i].toUpperCase() + ", " + parameters[j].toUpperCase() + "]";
                }
            }
        }

        return "";
    }

    public boolean inArray(String[] array, String searchString) {
        for (String item : array) {
            if (item.equalsIgnoreCase(searchString)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidProperty(String property) {
        for (validProperties validProperty : validProperties.values()) {
            if (property.equalsIgnoreCase(validProperty.toString()) ||
                    property.equalsIgnoreCase("-" + validProperty.toString())) {
                return true;
            }
        }
        return false;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public long getFirstParameter() {
        return Long.parseLong(parameters[0]);
    }

    public long getSecondParameter() {
        return Long.parseLong(parameters[1]);
    }
}