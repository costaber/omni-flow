package costaber.com.github.omniflow.cloud.provider.aws

const val AMAZON_COMMENT = "\"Comment\""
const val AMAZON_START_AT = "\"StartAt\""
const val AMAZON_STATES = "\"States\""
const val AMAZON_TYPE = "\"Type\": "
const val AMAZON_TASK = "\"Task\""
const val AMAZON_PASS = "\"Pass\""
const val AMAZON_CHOICE = "\"Choice\""
const val AMAZON_TASK_TYPE = "$AMAZON_TYPE$AMAZON_TASK,"
const val AMAZON_PASS_TYPE = "$AMAZON_TYPE$AMAZON_PASS,"
const val AMAZON_CHOICE_TYPE = "$AMAZON_TYPE$AMAZON_CHOICE,"
const val AMAZON_RESOURCE = "\"Resource\": \"arn:aws:states:::apigateway:invoke\","
const val AMAZON_INPUT_PATH = "\"InputPath\": "
const val AMAZON_START_PARAMETERS = "\"Parameters\": {"
const val AMAZON_API_ENDPOINT = "\"ApiEndpoint\": "
const val AMAZON_METHOD = "\"Method\": "
const val AMAZON_PATH = "\"Path\": "
const val AMAZON_QUERY_PARAMETERS = "\"QueryParameters\": {"
const val AMAZON_AUTH_TYPE = "\"AuthType\": "
const val AMAZON_START_RESULT_SELECTOR = "\"ResultSelector\": {"
const val AMAZON_START_RESULT = "\"Result\": {"
const val AMAZON_RESPONSE_BODY = "ResponseBody"
const val AMAZON_END = "\"End\": true"
const val AMAZON_NEXT = "\"Next\": "
const val AMAZON_CLOSE_OBJECT = "},"
const val AMAZON_START_CHOICES = "\"Choices\": ["
const val AMAZON_CLOSE_ARRAY = "],"
const val AMAZON_DEFAULT = "\"Default\": "
const val AMAZON_VARIABLE = "\"Variable\": "
const val AMAZON_NOT = "\"Not\": {"
const val AMAZON_STRING_EQUALS_PATH = "\"StringEqualsPath\": "
const val AMAZON_NUMERIC_EQUALS = "\"NumericEquals\": "
const val AMAZON_STRING_EQUALS = "\"StringEquals\": "
const val AMAZON_BOOLEAN_EQUALS = "\"BooleanEquals\": "