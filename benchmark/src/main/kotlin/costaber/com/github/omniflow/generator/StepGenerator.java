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

    public static Step newTranslation() {
        return new Step(
                "new_translation",
                "Makes an HTTP POST request to the Cloud Translation API to translate text from English to Russian",
                StepType.CALL,
                StepContextGenerator.callTranslationApi()
        );
    }

    public static Step assignTranslation() {
        return new Step(
                "assign_translation",
                "Assign the translated text to the translation_result variable",
                StepType.ASSIGN,
                StepContextGenerator.assignTranslationResult()
        );
    }

    public static Step addPetToStore() {
        return new Step(
                "Add Pet to Store",
                "Add Pet to store by calling APIGW Rest endpoint",
                StepType.CALL,
                StepContextGenerator.addPetToStoreCall()
        );
    }

    public static Step checkSuccess() {
        return new Step(
                "Pet was Added Successfully?",
                "Checks if the response was not successful",
                StepType.CONDITIONAL,
                StepContextGenerator.responseStatusCodeIs200()
        );
    }

    public static Step getPetsFromStore() {
        return new Step(
                "Retrieve Pet Store Data",
                "Get all data about the pet store",
                StepType.CALL,
                StepContextGenerator.getPetsCall()
        );
    }

    public static Step notifyWatchers() {
        return new Step(
                "Notify Result",
                "Call Notify Api to notify watchers with the result",
                StepType.CALL,
                StepContextGenerator.callNotifyApi()
        );
    }
}