package costaber.com.github.omniflow.model

enum class CloudProvider {
    GoogleCloudPlatform,
    Azure,
    AmazonWebServices
}

fun getCloudProvider(value: String): CloudProvider =
    when (value) {
        "GCP" -> CloudProvider.GoogleCloudPlatform
        "Azure" -> CloudProvider.Azure
        "AWS" -> CloudProvider.AmazonWebServices
        else -> throw IllegalStateException("Invalid ${CloudProvider::class.simpleName}: $value")
    }