package kt.regex

import java.util.regex.Pattern
import kotlin.test.Test
import kotlin.test.assertTrue

class TestUrl {

    val url_edit = "https://www.example.com/social/post-review/edit-review?product_id=1&order_id=4&review_source=free_sample&back_url=/social/post-review/review-product"
    val url_review = "https://www.example.com/social/post-review/review-product?review_source=free_sample"

    val regex_edit = "^(https?://[^?]+/social/post-review/edit-review)(?:[/?#]|$)"
    val regex_review = "^(https?://[^?]+/social/post-review/review-product)(?:[/?#]|$)"

    val regex_edit_2 = "https?://(.+/)+social/post-review/edit-review(\\?.*)?"
    val regex_review_2= "https?://(.+/)+social/post-review/review-product(\\?.*)?"

    @Test
    fun `test url edit find by edit 1`() {
        val pattern = Pattern.compile(regex_edit)
        val matcher = pattern.matcher(url_edit)
        assertTrue(matcher.find())
    }

    @Test
    fun `test url edit find by review 1`() {
        val pattern = Pattern.compile(regex_review)
        val matcher = pattern.matcher(url_edit)
        assertTrue(matcher.find())
    }

    @Test
    fun `test url edit find by edit 2`() {
        val pattern = Pattern.compile(regex_edit_2)
        val matcher = pattern.matcher(url_edit)
        assertTrue(matcher.find())
    }

    @Test
    fun `test url edit find by review 2`() {
        val pattern = Pattern.compile(regex_review_2)
        val matcher = pattern.matcher(url_edit)
        assertTrue(matcher.find())
    }

    @Test
    fun `test url edit match by edit 1`() {
        assertTrue(Pattern.matches(regex_edit, url_edit))
    }

    @Test
    fun `test url edit match by review 1`() {
        assertTrue(Pattern.matches(regex_review, url_edit))
    }

    @Test
    fun `test url edit match by edit 2`() {
        assertTrue(Pattern.matches(regex_edit_2, url_edit))
    }

    @Test
    fun `test url edit match by review 2`() {
        assertTrue(Pattern.matches(regex_review_2, url_edit))
    }


    //// review url test

    @Test
    fun `test url review find by review 1`() {
        val pattern = Pattern.compile(regex_review)
        val matcher = pattern.matcher(url_review)
        assertTrue(matcher.find())
    }

    @Test
    fun `test url review find by review 2`() {
        val pattern = Pattern.compile(regex_review_2)
        val matcher = pattern.matcher(url_review)
        assertTrue(matcher.find())
    }

    @Test
    fun `test url review match by review 1`() {
        assertTrue(Pattern.matches(regex_review, url_review))
    }

    @Test
    fun `test url review match by review 2`() {
        assertTrue(Pattern.matches(regex_review_2, url_review))
    }

}