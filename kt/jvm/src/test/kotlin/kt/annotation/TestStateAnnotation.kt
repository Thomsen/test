package kt.annotation

import org.junit.Test
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.test.assertEquals

class TestStateAnnotation {

    @Test
    fun `test annotation`() {
        val myData = StateAnnotation(1, "Alice")
        var propertyValue = 0
        val properties = myData::class.declaredMemberProperties
        for (property in properties) {
            val fieldAnnotations = property.annotations.filterIsInstance<StatePrimaryKey>()
            if (fieldAnnotations.isNotEmpty()) {
                val primaryKeyAnnotation = fieldAnnotations.first()
                val primaryKeyValue = primaryKeyAnnotation.value
//            val propertyValue = property.get(myData)
                propertyValue = property.call(myData) as Int


                println("Field: ${property.name}, Primary Key Value: $primaryKeyValue, Property Value: $propertyValue")
            }
        }
        assertEquals(myData.id, propertyValue)
    }

    @Test
    fun `test annotation java fields`() {
        val myData = StateAnnotation(1, "Alice")
        var propertyValue = 0
        val properties = myData::class.java.declaredFields
        for (field in properties) {
            val fieldAnnotations = field.getAnnotationsByType(StatePrimaryKey::class.java)
            if (fieldAnnotations.isNotEmpty()) {
                val primaryKeyAnnotation = fieldAnnotations.first()
                val primaryKeyValue = primaryKeyAnnotation.value
                field.isAccessible = true
                propertyValue = field.get(myData) as Int

                println("Field: ${field.name}, Primary Key Value: $primaryKeyValue, Field Value: $propertyValue")
            }
        }
        assertEquals(myData.id, propertyValue)
    }

    @Test
    fun `test annotation java fields firstOrNull`() {
        val myData = StateAnnotation(1, "Alice")
        val properties = myData::class.java.declaredFields
        val propertyValue = properties.firstOrNull {
            it.isAccessible = true
            it.annotations.any { annotation ->
                annotation is StatePrimaryKey
            }
        }?.get(myData) as Int
        assertEquals(myData.id, propertyValue)
    }

    @Test
    fun `test annotation field`() {
        val myData = StateAnnotationField(1, "Alice")
        var propertyValue = 0
        val properties = myData::class.declaredMemberProperties
        for (property in properties) {
            val fieldAnnotations = property.annotations.filterIsInstance<StatePrimaryKey>()
            if (fieldAnnotations.isNotEmpty()) {
                val primaryKeyAnnotation = fieldAnnotations.first()
                val primaryKeyValue = primaryKeyAnnotation.value
//            val propertyValue = property.get(myData)
                propertyValue = property.call(myData) as Int

                println("Field: ${property.name}, Primary Key Value: $primaryKeyValue, Property Value: $propertyValue")
            }
        }
        assertEquals(myData.id, propertyValue)
    }
}