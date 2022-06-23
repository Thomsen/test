package kt.patterns.creational.factory

interface Currency {

    fun symbol(): String
    fun code(): String

}

enum class Country {
    UnitedState, Spain
}

class USDollar: Currency {
    override fun symbol(): String {
        return "$"
    }

    override fun code(): String {
        return "USD"
    }

}

class Euro: Currency {
    override fun symbol(): String {
        return "€"
    }

    override fun code(): String {
        return "EUR"
    }

}

class CurrencyFactory {
    fun currency(country: Country): Currency {
        return when (country) {
            Country.UnitedState -> {
                USDollar()
            }
            Country.Spain -> {
                Euro()
            }
        }
    }
}