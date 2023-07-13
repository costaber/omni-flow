package costaber.com.github.omniflow.generator;

import costaber.com.github.omniflow.model.*;

import java.util.*;

import static costaber.com.github.omniflow.generator.StepGenerator.STEP_NAME;

public class StepContextGenerator {

    private static final String VARIABLE_NAME_1 = "number1";
    private static final String VARIABLE_NAME_2 = "number2";

    public static StepContext independentCall() {
        Map<String, Term<?>> headers = new HashMap<>();
        headers.put("Content-Type", new Value<>("application/json"));
        Map<String, Term<?>> queries = new HashMap<>();
        queries.put("number", new Value<>(1));
        return new CallContext(
                HttpMethod.GET,
                "example.com",
                "/example",
                null,
                new Body("John", "Johnson"),
                headers,
                queries,
                5L,
                "result"
        );
    }

    public static StepContext callUsingVariables() {
        Map<String, Term<?>> queries = new HashMap<>();
        queries.put("number1", new Variable(VARIABLE_NAME_1));
        queries.put("number2", new Variable(VARIABLE_NAME_2));
        return new CallContext(
                HttpMethod.GET,
                "example.com",
                "/example",
                null,
                new Body("John", "Johnson"),
                new HashMap<>(),
                queries,
                5L,
                "result"
        );
    }

    public static StepContext hardcodedVariables() {
        Random random = new Random();
        int random1 = random.nextInt();
        int random2 = random.nextInt();
        List<VariableInitialization<?>> variables = new ArrayList<>();
        variables.add(new VariableInitialization<>(new Variable(VARIABLE_NAME_1), new Value<>(random1)));
        variables.add(new VariableInitialization<>(new Variable(VARIABLE_NAME_1), new Value<>(random2)));
        return new AssignContext(variables);
    }

    public static StepContext ifElseSwitch(int index) {
        List<Condition> conditions = new ArrayList<>();
        BinaryExpression<Integer> greaterThanOrEqual = new GreaterThanExpression<>(
                new Variable("result.number"),
                new Value<>(123)
        );
        conditions.add(new Condition(greaterThanOrEqual, STEP_NAME + (index + 1)));
        return new ConditionalContext(conditions, STEP_NAME + (index + 2));
    }

    public static StepContext multipleSwitch(int index) {
        List<Condition> conditions = new ArrayList<>();
        BinaryExpression<Integer> equalTo = new EqualToExpression<>(
                new Variable("result"),
                new Value<>(123)
        );
        conditions.add(new Condition(equalTo, STEP_NAME + (index + 1)));
        BinaryExpression<Integer> greaterThan = new GreaterThanExpression<>(
                new Variable("result"),
                new Value<>(123)
        );
        conditions.add(new Condition(greaterThan, STEP_NAME + (index + 2)));
        BinaryExpression<Integer> lessThan = new LessThanExpression<>(
                new Variable("result"),
                new Value<>(123)
        );
        conditions.add(new Condition(lessThan, STEP_NAME + (index + 3)));
        return new ConditionalContext(conditions, STEP_NAME + (index + 4));
    }

    static class Body {

        private String firstName;
        private String lastName;

        public Body(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}