import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESCipher(private val key: String) {
    companion object {
        private const val ALGORITHM = "AES/CBC/PKCS5Padding"
        private const val IV_LENGTH = 16
    }

    // 加密并构建RequestBody
    fun encryptToRequestBody(data: String): RequestBody? {
        val encryptedBytes = encrypt(data) ?: return null
        return encryptedBytes.toRequestBody("application/octet-stream".toMediaType())
    }

    // 加密
    fun encrypt(data: String): String? {
        // 1. 创建加密器
        val cipher = Cipher.getInstance(ALGORITHM)

        // 2. 生成随机IV
        val iv = ByteArray(IV_LENGTH).apply {
            SecureRandom().nextBytes(this)
        }

        // 3. 初始化加密器
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))

        // 4. 加密
        val encrypted = cipher.doFinal(data.toByteArray())

        // 5. 拼接IV和加密数据
        val combined = ByteArray(iv.size + encrypted.size).apply {
            System.arraycopy(iv, 0, this, 0, iv.size)
            System.arraycopy(encrypted, 0, this, iv.size, encrypted.size)
        }

        // 6. Base64编码
        return Base64.getEncoder().encodeToString(combined)
    }

    // 解密
    fun decrypt(encryptedData: String): String {
        // 1. Base64解码
        val combined = Base64.getDecoder().decode(encryptedData)

        // 2. 分离IV和加密数据
        val iv = ByteArray(IV_LENGTH)
        val encrypted = ByteArray(combined.size - IV_LENGTH)
        System.arraycopy(combined, 0, iv, 0, IV_LENGTH)
        System.arraycopy(combined, IV_LENGTH, encrypted, 0, encrypted.size)

        // 3. 创建解密器
        val cipher = Cipher.getInstance(ALGORITHM)

        // 4. 初始化解密器
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))

        // 5. 解密
        val decrypted = cipher.doFinal(encrypted)

        return String(decrypted)
    }
}

fun main() {
    try {
        // 创建AES加解密器实例（密钥必须是16字节）
        val aesCipher = AESCipher("your16byteskey12")

        // 原始数据
        val originalData = "Hello, World!"

        // 加密
        val encrypted = aesCipher.encrypt(originalData)
        println("加密后的Base64字符串: ${encrypted}")

        // 构建RequestBody
        val requestBody = aesCipher.encryptToRequestBody(originalData)

        // 解密（这里使用String(encrypted)模拟接收到的加密数据）
        val decrypted = aesCipher.decrypt(encrypted.toString())
        println("解密后的数据: $decrypted")

        // 验证
        println("解密是否成功: ${originalData == decrypted}")

    } catch (e: Exception) {
        e.printStackTrace()
    }
}