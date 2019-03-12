dependencies {
	compile("com.squareup.okhttp3:okhttp:3.13.1")
	compile("com.squareup.okhttp3:logging-interceptor:3.13.1")
	
	compile("com.google.code.gson:gson:2.8.5")
	
	compile("io.reactivex.rxjava2:rxjava:2.2.7")
	compile("io.reactivex.rxjava2:rxandroid:2.1.1")
	compile("io.reactivex.rxjava2:rxkotlin:2.3.0") {
		exclude(group = "org.jetbrains.kotlin")
	}
	testCompile("com.squareup.okhttp3:mockwebserver:3.13.1")
}