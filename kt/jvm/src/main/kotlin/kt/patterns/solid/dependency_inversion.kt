package kt.patterns.solid

// dependency inversion principle (DIP)

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


// ----------------------------------------------------------------- //

data class Seller(
    val name: String
    // other
)


class MySQLDatabase0 {
    fun saveSeller(seller: Seller) {
        print("Saving ${seller.name} to MySQL database...")
    }
}

class SellerService0 {
    lateinit var database: MySQLDatabase0

    constructor(database: MySQLDatabase0) {
        this.database = database
    }

}

// ->

abstract class Database {
    open fun saveSeller(seller: Seller) {}
}

class MySQLDatabase : Database() {
    override fun saveSeller(seller: Seller) {
        print("Saving ${seller.name} to MySQL database...")
    }
}

class PostgreSQLDatabase : Database() {
    override fun saveSeller(seller: Seller) {
        print("Saving ${seller.name} to PostgreSQL database...")
    }
}

class SellerService {
    lateinit var database: Database

    constructor(database: Database) {
        this.database = database // dependency injection
    }

    fun saveSeller(seller: Seller) {
        database.saveSeller(seller)
    }
}