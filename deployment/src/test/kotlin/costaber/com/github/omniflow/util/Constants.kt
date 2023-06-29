package costaber.com.github.omniflow.util

import costaber.com.github.omniflow.model.*
import costaber.com.github.omniflow.renderer.RenderingContext

const val VARIABLE_NAME_1 = "myVariable123"
const val STEP_NAME_1 = "step_325"
const val STEP_DESCRIPTION_1 = "Step call example"
const val STEP_DESCRIPTION_2 = "Step assign example"
const val STEP_NAME_2 = "step_479"
const val CONDITION_CONTEXT_DEFAULT_1 = "default1"
const val VARIABLE_NAME_2 = "xpto"
const val WORKFLOW_NAME_1 = "myWorkflow134"
const val WORKFLOW_DESCRIPTION_1 = "The Workflow Description"
const val WORKFLOW_INPUT_1 = "input"
const val WORKFLOW_RESULT_1 = "myResult"

val VARIABLE_1 = Variable(VARIABLE_NAME_1)
val VARIABLE_2 = Variable(VARIABLE_NAME_2)

val VALUE_1 = Value("Mr.Robot")
val VALUE_2 = Value(999)

val CONDITION_CONTEXT_1 = ConditionalContext(
    conditions = listOf(),
    default = CONDITION_CONTEXT_DEFAULT_1,
)

val EQUAL_TO_EXPRESSION_1 = EqualToExpression(
    left = VARIABLE_1,
    right = VALUE_1,
)

val GREATER_THAN_EXPRESSION_1 = GreaterThanExpression(
    left = VARIABLE_1,
    right = VALUE_1,
)

val GREATER_THAN_OR_EQUAL_EXPRESSION_1 = GreaterThanOrEqualExpression(
    left = VARIABLE_1,
    right = VALUE_1,
)

val LESS_THAN_EXPRESSION_1 = LessThanExpression(
    left = VARIABLE_1,
    right = VALUE_1,
)

val LESS_THAN_OR_EQUAL_EXPRESSION_1 = LessThanOrEqualExpression(
    left = VARIABLE_1,
    right = VALUE_1,
)

val NOT_EQUAL_TO_EXPRESSION_1 = NotEqualToExpression(
    left = VARIABLE_1,
    right = VALUE_1,
)

val VARIABLE_INITIALIZATION_1 = VariableInitialization(
    variable = VARIABLE_1,
    term = VALUE_1,
)
val VARIABLE_INITIALIZATION_2 = VariableInitialization(
    variable = VARIABLE_1,
    term = VARIABLE_2,
)

val CONDITION_1 = Condition(
    expression = EQUAL_TO_EXPRESSION_1,
    jump = "stepX",
)

val ASSIGN_CONTEXT_1 = AssignContext(
    variables = listOf(
        VARIABLE_INITIALIZATION_1,
        VARIABLE_INITIALIZATION_2
    )
)

val CALL_CONTEXT_1 = CallContext(
    method = HttpMethod.GET,
    host = "example.com",
    path = "/",
    timeoutInSeconds = 5,
    result = "callExample",
)

val STEP_1 = Step(
    name = STEP_NAME_1,
    description = STEP_DESCRIPTION_1,
    type = StepType.CALL,
    context = CALL_CONTEXT_1,
)
val STEP_2 = Step(
    name = STEP_NAME_2,
    description = STEP_DESCRIPTION_2,
    type = StepType.ASSIGN,
    context = ASSIGN_CONTEXT_1,
)

val WORKFLOW_1 = Workflow(
    name = WORKFLOW_NAME_1,
    description = WORKFLOW_DESCRIPTION_1,
    input = WORKFLOW_INPUT_1,
    steps = listOf(STEP_1, STEP_2),
    result = WORKFLOW_RESULT_1,
)

val DUMMY_RENDERING_CONTEXT = object : RenderingContext {}