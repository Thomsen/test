import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


fun encryptAES(plainText: String, secretKey: ByteArray): String {
    // a. 随机生成IV值（byte数组）
    val iv = ByteArray(16) // AES块大小为16字节
    SecureRandom().nextBytes(iv)

    // b. 使用IV值进行加密得到密文（byte数组）
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val secretKeySpec = SecretKeySpec(secretKey, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(iv))
    val cipherText = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))

    // c. byte数组拼接：IV值+密文得到字节数组
    val combined = iv + cipherText

    // d. 使用Base64编码得到最终字符串
    return Base64.getEncoder().encodeToString(combined)
}

fun decryptAES(base64Encoded: String, secretKey: ByteArray): String {
    // a. 从Base64解码得到字节数组
    val combined = Base64.getDecoder().decode(base64Encoded)

    // b. 提取IV值和密文
    val iv = combined.copyOfRange(0, 16) // 前16字节为IV
    val cipherText = combined.copyOfRange(16, combined.size) // 剩余部分为密文

    // c. 使用IV值进行解密得到明文（byte数组）
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val secretKeySpec = SecretKeySpec(secretKey, "AES")
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(iv))
    val plainText = cipher.doFinal(cipherText)

    // d. 将明文字节数组转换为字符串
    return String(plainText, Charsets.UTF_8)
}

fun main() {
    val aa = encryptAES("hello", "1234abcd43210000".toByteArray(Charsets.UTF_8))
    println(aa)
    println(decryptAES(aa, "1234abcd43210000".toByteArray(Charsets.UTF_8)))
}