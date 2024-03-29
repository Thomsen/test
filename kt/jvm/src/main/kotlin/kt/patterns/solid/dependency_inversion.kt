package kt.patterns.solid

// dependency inversion principle

class AndroidDeveloper0 {
    fun developMobileApp(){
        println("Developing Android Application by using Kotlin")
    }
}

class IosDeveloper0 {
    fun developMobileApp(){
        println("Developing iOS Application by using Swift")
    }
}

// ->

interface MobileDeveloper {
    fun developMobileApp()
}

class AndroidDeveloper(var mobileService: MobileServices): MobileDeveloper {

    override fun developMobileApp() {
        println("Developing Android Application by using Kotlin. " +
                "Application will work with ${mobileService.serviceName}")
    }

    enum class MobileServices(var serviceName: String){
        HMS("Huawei Mobile Services"),
        GMS("Google Mobile Services"),
        BOTH("Huawei Mobile Services and Google Mobile Services")
    }
}

class IosDeveloper: MobileDeveloper {
    override fun developMobileApp(){
        println("Developing iOS Application by using Swift")
    }
}