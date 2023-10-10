package kt.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class StatePrimaryKey(val value: String)

data class StateAnnotation(
    @StatePrimaryKey("id")
    val id: Int,
    val name: String
)

data class StateAnnotationField(
    @field:StatePrimaryKey("id")
    val id: Int,
    val name: String
)

