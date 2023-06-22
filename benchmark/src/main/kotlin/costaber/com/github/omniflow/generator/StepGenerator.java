package costaber.com.github.omniflow.generator;

import costaber.com.github.omniflow.model.Step;
import costaber.com.github.omniflow.model.StepType;

public class StepGenerator {

    public static final String STEP_NAME = "stepNumber";

    public static Step independent(String stepName, int index) {
        return new Step(
                stepName + index,
                "Call Step Example",
                StepType.CALL,
                StepContextGenerator.independentCall()
        );
    }

    public static Step usingVariables(String stepName, int index) {
        return new Step(
                stepName + index,
                "Call Step Example",
                StepType.CALL,
                StepContextGenerator.callUsingVariables()
        );
    }

    public static Step assign(String stepName, int index) {
        return new Step(
                stepName + index,
                "Call Step Example",
                StepType.ASSIGN,
                StepContextGenerator.hardcodedVariables()
        );
    }

    public static Step binaryConditional(String stepName, int index) {
        return new Step(
                stepName + index,
                "Call Step Example",
                StepType.CONDITIONAL,
                StepContextGenerator.ifElseSwitch(index)
        );
    }

    public static Step multipleDecision(String stepName, int index) {
        return new Step(
                stepName + index,
                "Call Step Example",
                StepType.CONDITIONAL,
                StepContextGenerator.multipleSwitch(index)
        );
    }
}
