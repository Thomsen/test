package kt.advance

import kt.basic.syntax.PersonThree
import kt.basic.syntax.Superhero
import kt.basic.syntax.SuperheroThree
import kt.basic.syntax.SuperheroTwo
import org.junit.Test
import kotlin.test.assertEquals

class TestPersonDelegate {

    @Test
    fun testSuperheroOne() {
        val hero = Superhero(
            "Sherlock", "Holmes", 28, "US")
        assertEquals("Sherlock", hero.firstName)
        assertEquals(28, hero.age)
    }

    @Test
    fun testSuperheroTwo() {
        val hero = SuperheroTwo(PersonThree(
            "Sherlock", "Holmes", 28), "US")
        assertEquals("Sherlock", hero.firstName)
        assertEquals(28, hero.age)
    }

    @Test
    fun testSuperheroThree() {
        val hero = SuperheroThree(PersonThree(
            "Sherlock", "Holmes", 28), "US")
        assertEquals("Sherlock", hero.firstName)
        assertEquals(28, hero.age)
    }
}