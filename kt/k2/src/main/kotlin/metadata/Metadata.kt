package metadata

import kotlin.metadata.KmClass
import kotlin.metadata.jvm.KotlinClassMetadata
import kotlin.Metadata
import kotlin.metadata.KmClassifier
import kotlin.metadata.KmConstructor
import kotlin.metadata.KmFunction
import kotlin.metadata.KmProperty
import kotlin.metadata.KmPropertyAccessorAttributes
import kotlin.metadata.KmType
import kotlin.metadata.Visibility
import kotlin.metadata.isSecondary
import kotlin.metadata.jvm.JvmMetadataVersion
import kotlin.metadata.jvm.JvmMethodSignature
import kotlin.metadata.jvm.signature
import kotlin.metadata.visibility

class MyClass {
    val myProperty: String? = null

    fun myFunction() {
    }
}

internal fun Class<*>.getMetadata(): Metadata {
    return getAnnotation(Metadata::class.java)
}

internal fun Metadata.readAsKmClass(): KmClass {
    val clazz = KotlinClassMetadata.readStrict(this) as? KotlinClassMetadata.Class
    return clazz?.kmClass ?: error("Not a KotlinClassMetadata.Class: $clazz")
}

internal fun Class<*>.readMetadataAsKmClass(): KmClass = getMetadata().readAsKmClass()


fun main() {
    val myClass = MyClass::class.java.readMetadataAsKmClass()

    val sampleClass = KmClass().apply {
        name = "SampleClass"
        visibility = Visibility.PUBLIC

        constructors += KmConstructor().apply {
            visibility = Visibility.PUBLIC
            isSecondary = false
            // Setting the JVM signature (for example, to be used by kotlin-reflect)
            signature = JvmMethodSignature("<init>", "()V")
        }

        properties += KmProperty("myProperty").apply {
            visibility = Visibility.PUBLIC
            returnType = KmType().apply {
                classifier = KmClassifier.Class("kotlin/String")
            }
        }

        functions += KmFunction("myFunction").apply {
            visibility = Visibility.PUBLIC
            returnType = KmType().apply {
//                classifier = KmClassifier.Class("Void")
                classifier = KmClassifier.Class("kotlin/String")
            }
            signature = JvmMethodSignature("myFunction", "()Ljava/lang/String;")
        }

    }


    // Convert KmClass to KotlinClassMetadata
    val classMetadata = KotlinClassMetadata.Class(myClass, JvmMetadataVersion.LATEST_STABLE_SUPPORTED, 0)

    // Generate the Metadata annotation
    val metadataAnnotation = classMetadata.write()

    // Read the metadata
    val metadata = KotlinClassMetadata.readStrict(metadataAnnotation) as KotlinClassMetadata.Class
    val kmClass: KmClass = metadata.kmClass

    // Print class name
    println("Class name: ${kmClass.name}")

    // Print properties
    kmClass.properties.forEach { property ->
        println("Property: ${property.name}, Type: ${property.returnType.classifier}")
    }

    // Print functions
    kmClass.functions.forEach { function ->
        println("Function: ${function.name}")
    }
}