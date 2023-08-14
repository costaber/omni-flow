package costaber.com.github.omniflow.generator;

import costaber.com.github.omniflow.model.Step;
import costaber.com.github.omniflow.model.Workflow;

import java.util.ArrayList;
import java.util.List;

import static costaber.com.github.omniflow.generator.StepGenerator.STEP_NAME;

public class WorkflowGenerator {

    private static final String WORKFLOW_NAME = "testWorkflow";
    private static final String WORKFLOW_DESCRIPTION = "Workflow Example";
    private static final String WORKFLOW_INPUT = "args";
    private static final String WORKFLOW_RESULT = "result";

    /**
     * Generates a workflow with @stepsNumber steps
     * without any relation, independent.
     *
     * @param stepsNumber number of steps to generate
     * @return a workflow with independent steps
     */
    public static Workflow withIndependentSteps(int stepsNumber) {
        List<Step> steps = new ArrayList<>();
        for (int idx = 0; idx < stepsNumber; idx++) {
            Step step = StepGenerator.independent(STEP_NAME, idx);
            steps.add(step);
        }
        return new Workflow(
                WORKFLOW_NAME,
                WORKFLOW_DESCRIPTION,
                WORKFLOW_INPUT,
                steps,
                WORKFLOW_RESULT
        );
    }

    /**
     * Generates a workflow with at least @stepsNumber
     * steps, where the maximum steps generated are
     * <code>stepsNumber</code> + 1. It will create steps,
     * where half are calls and the other half are assigns.
     * The last step is always a call.
     *
     * @param stepsNumber number of steps to generate
     * @return a workflow with steps that use variables
     */
    public static Workflow usingVariables(int stepsNumber) {
        List<Step> steps = new ArrayList<>();
        for (int idx = 0; idx < stepsNumber; ) {
            steps.add(StepGenerator.assign(STEP_NAME, idx++));
            steps.add(StepGenerator.usingVariables(STEP_NAME, idx++));
        }
        return new Workflow(
                WORKFLOW_NAME,
                WORKFLOW_DESCRIPTION,
                WORKFLOW_INPUT,
                steps,
                WORKFLOW_RESULT
        );
    }

    /**
     * Generates a workflow with at least @stepsNumber
     * steps, where the maximum steps generated are
     * <code>stepsNumber</code> + 2. It will create steps,
     * where 1/3 are binary conditions and the rest
     * 2/3 are calls. The last 2 steps always calls
     *
     * @param stepsNumber number of steps to generate
     * @return a workflow with steps that use binary conditions
     */
    public static Workflow withBinaryConditions(int stepsNumber) {
        List<Step> steps = new ArrayList<>();
        Step firstStep = StepGenerator.independent(STEP_NAME, 0);
        steps.add(firstStep);
        for (int idx = 1; idx < stepsNumber; ) {
            steps.add(StepGenerator.binaryConditional(STEP_NAME, idx++));
            steps.add(StepGenerator.independent(STEP_NAME, idx++));
            steps.add(StepGenerator.independent(STEP_NAME, idx++));
        }
        return new Workflow(
                WORKFLOW_NAME,
                WORKFLOW_DESCRIPTION,
                WORKFLOW_INPUT,
                steps,
                WORKFLOW_RESULT
        );
    }

    /**
     * Generates a workflow with at least @stepsNumber
     * steps, where the maximum steps generated are
     * <code>stepsNumber</code> + 4. It will create steps,
     * where 1/5 are switch conditions and the rest
     * 4/5 are calls. The last 2 steps always calls
     *
     * @param stepsNumber number of steps to generate
     * @return a workflow with steps that use binary conditions
     */
    public static Workflow withMultipleDecisions(int stepsNumber) {
        List<Step> steps = new ArrayList<>();
        Step firstStep = StepGenerator.independent(STEP_NAME, 0);
        steps.add(firstStep);
        for (int idx = 1; idx < stepsNumber; ) {
            steps.add(StepGenerator.multipleDecision(STEP_NAME, idx++));
            steps.add(StepGenerator.independent(STEP_NAME, idx++));
            steps.add(StepGenerator.independent(STEP_NAME, idx++));
            steps.add(StepGenerator.independent(STEP_NAME, idx++));
            steps.add(StepGenerator.independent(STEP_NAME, idx++));
        }
        return new Workflow(
                WORKFLOW_NAME,
                WORKFLOW_DESCRIPTION,
                WORKFLOW_INPUT,
                steps,
                WORKFLOW_RESULT
        );
    }

    public static Workflow textTranslator() {
        List<Step> steps = new ArrayList<>();
        steps.add(StepGenerator.newTranslation());
        steps.add(StepGenerator.assignTranslation());
        return new Workflow(
                WORKFLOW_NAME,
                WORKFLOW_DESCRIPTION,
                WORKFLOW_INPUT,
                steps,
                "translation_result"
        );
    }

    public static Workflow saveAndGetPetFromStore() {
        List<Step> steps = new ArrayList<>();
        steps.add(StepGenerator.addPetToStore());
        steps.add(StepGenerator.checkSuccess());
        steps.add(StepGenerator.getPetsFromStore());
        steps.add(StepGenerator.notifyWatchers());
        return new Workflow(
                WORKFLOW_NAME,
                "Calling APIGW HTTP Endpoint",
                WORKFLOW_INPUT,
                steps,
                "NotificationStatus"
        );
    }
}