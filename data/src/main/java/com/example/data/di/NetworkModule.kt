import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }

            defaultRequest {
                url(BASE_URL)
                header(KEY_API_VERSION, VALUE_API_VERSION)
            }
        }
    }
}

private const val BASE_URL = "https://api.github.com/"
private const val KEY_API_VERSION = "X-GitHub-Api-Version"
private const val VALUE_API_VERSION = "2022-11-28"