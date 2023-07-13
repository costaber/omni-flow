package costaber.com.github.omniflow.generator;

import costaber.com.github.omniflow.model.Step;
import costaber.com.github.omniflow.model.StepType;

public class StepGenerator {

    public static final String STEP_NAME = "stepNumber";

    public static Step independent(String stepName, int index) {
        return new Step(
                stepName + index,
                "Independent call step example",
                StepType.CALL,
                StepContextGenerator.independentCall()
        );
    }

    public static Step usingVariables(String stepName, int index) {
        return new Step(
                stepName + index,
                "Call step using variables example",
                StepType.CALL,
                StepContextGenerator.callUsingVariables()
        );
    }

    public static Step assign(String stepName, int index) {
        return new Step(
                stepName + index,
                "Assign step example",
                StepType.ASSIGN,
                StepContextGenerator.hardcodedVariables()
        );
    }

    public static Step binaryConditional(String stepName, int index) {
        return new Step(
                stepName + index,
                "Binary condition step example",
                StepType.CONDITIONAL,
                StepContextGenerator.ifElseSwitch(index)
        );
    }

    public static Step multipleDecision(String stepName, int index) {
        return new Step(
                stepName + index,
                "Multiple decision step example",
                StepType.CONDITIONAL,
                StepContextGenerator.multipleSwitch(index)
        );
    }
}