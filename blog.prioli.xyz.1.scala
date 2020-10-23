
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BlogPrioliXyz extends Simulation {

	val httpProtocol = http
                // Standard
		.baseUrl("https://35.212.242.37")
                // Premium
                //.baseUrl("https://35.190.3.150")
		.inferHtmlResources(WhiteList(""".*blog\.prioli\.xyz.*""",""".*35.212.242.37.*""",""".*35.190.3.150.*"""))
                //, BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""))
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.doNotTrackHeader("1")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:79.0) Gecko/20100101 Firefox/79.0")

	val headers_0 = Map(
		"Pragma" -> "no-cache",
		"TE" -> "Trailers",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "image/webp,*/*",
		"Pragma" -> "no-cache",
		"TE" -> "Trailers")



	val scn = scenario("BlogPrioliXyz")
		.exec(http("/")
			.get("/")
			.headers(headers_0))
		.pause(2 seconds, 5 seconds)
		.exec(http("/posts/")
			.get("/posts/")
			.headers(headers_0))
		.pause(2 seconds, 5 seconds)
		.exec(http("/posts/hugo-on-cloud-storage/")
			.get("/posts/hugo-on-cloud-storage/")
			.headers(headers_0))
		.pause(6 seconds, 10 seconds)
		.exec(http("/")
			.get("/")
			.headers(headers_0))
                .pause(6 seconds, 10 seconds)
                .exec(http("/posts/gatling-load-testing-1/")
                        .get("/posts/gatling-load-testing-1/")
                        .headers(headers_0))

	//setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
        setUp(scn.inject(rampUsersPerSec(1) to 30 during (10 minutes) randomized).protocols (httpProtocol))
}
