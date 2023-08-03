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
                new PersonNameBody("John", "Johnson"),
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
                new PersonNameBody("John", "Johnson"),
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

    public static StepContext callTranslationApi() {
        return new CallContext(
                HttpMethod.POST,
                "https://translation.googleapis.com",
                "/v3/projects/19823573:translateText",
                new Authentication("OAuth2", null, null, null),
                new TranslationApiBody("Hello, my name is John!", "en-US", "ru-RU"),
                new HashMap<>(),
                new HashMap<>(),
                null,
                "translate_response"
        );
    }

    public static StepContext assignTranslationResult() {
        List<VariableInitialization<?>> variables = new ArrayList<>();
        Variable variableAssigned = new Variable("translation_result");
        Variable variableNewValue = new Variable("translate_response.translations[0].translatedText");
        VariableInitialization<String> translationResultVariable =
                new VariableInitialization<>(variableAssigned, variableNewValue);
        variables.add(translationResultVariable);
        return new AssignContext(variables);
    }

    public static StepContext addPetToStoreCall() {
        return new CallContext(
                HttpMethod.POST,
                "<POST_PETS_API_ENDPOINT>",
                "pets",
                new Authentication("IAM_ROLE", null, null, null),
                "$.NewPet",
                new HashMap<>(),
                new HashMap<>(),
                null,
                "ResponseBody"
        );
    }

    public static StepContext responseStatusCodeIs200() {
        List<Condition> conditions = new ArrayList<>();
        EqualToExpression<Integer> is200 = new EqualToExpression<>(
                new Variable("StatusCode"),
                new Value<>(200)
        );
        conditions.add(new Condition(is200, "Notify Success"));
        return new ConditionalContext(conditions, "Notify Result");
    }

    public static StepContext getPetsCall() {
        return new CallContext(
                HttpMethod.GET,
                "<GET_PETS_API_ENDPOINT>",
                "pets",
                new Authentication("IAM_ROLE", null, null, null),
                null,
                new HashMap<>(),
                new HashMap<>(),
                null,
                "Pets"
        );
    }

    public static StepContext callNotifyApi() {
        return new CallContext(
                HttpMethod.GET,
                "<API_ENDPOINT>",
                "/",
                new Authentication("IAM_ROLE", null, null, null),
                null,
                new HashMap<>(),
                new HashMap<>(),
                null,
                "NotificationStatus"
        );
    }

    static class PersonNameBody {

        private String firstName;
        private String lastName;

        public PersonNameBody(String firstName, String lastName) {
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

    static class TranslationApiBody {

        private String contents;
        private String sourceLanguageCode;
        private String targetLanguageCode;

        public TranslationApiBody(String contents, String sourceLanguageCode, String targetLanguageCode) {
            this.contents = contents;
            this.sourceLanguageCode = sourceLanguageCode;
            this.targetLanguageCode = targetLanguageCode;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getSourceLanguageCode() {
            return sourceLanguageCode;
        }

        public void setSourceLanguageCode(String sourceLanguageCode) {
            this.sourceLanguageCode = sourceLanguageCode;
        }

        public String getTargetLanguageCode() {
            return targetLanguageCode;
        }

        public void setTargetLanguageCode(String targetLanguageCode) {
            this.targetLanguageCode = targetLanguageCode;
        }
    }
}