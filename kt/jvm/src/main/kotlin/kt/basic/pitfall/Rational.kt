package kt.basic.pitfall

import java.io.IOException
import java.io.InvalidObjectException
import java.io.ObjectInputStream

/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ /**
 *
 * An immutable data type representation a rational number.
 *
 *
 * Contains a pair of `int`s representing the numerator and denominator of a
 * Rational number.
 */
class Rational(numerator: Int, denominator: Int) : Number(), Comparable<Rational> {
    /**
     * Gets the numerator of the rational.
     *
     *
     * The numerator will always return `1` if this rational represents
     * infinity (that is, the denominator is `0`).
     */
    /*
         * Do not change the order of these fields or add new instance fields to maintain the
         * Serializable compatibility across API revisions.
         */
    var numerator = 0

    /**
     * Gets the denominator of the rational
     *
     *
     * The denominator may return `0`, in which case the rational may represent
     * positive infinity (if the numerator was positive), negative infinity (if the numerator
     * was negative), or `NaN` (if the numerator was `0`).
     *
     *
     * The denominator will always return `1` if the numerator is `0`.
     */
    var denominator = 0

    /**
     * Indicates whether this rational is a *Not-a-Number (NaN)* value.
     *
     *
     * A `NaN` value occurs when both the numerator and the denominator are `0`.
     *
     * @return `true` if this rational is a *Not-a-Number (NaN)* value;
     * `false` if this is a (potentially infinite) number value
     */
    val isNaN: Boolean
        get() = denominator == 0 && numerator == 0

    /**
     * Indicates whether this rational represents an infinite value.
     *
     *
     * An infinite value occurs when the denominator is `0` (but the numerator is not).
     *
     * @return `true` if this rational is a (positive or negative) infinite value;
     * `false` if this is a finite number value (or `NaN`)
     */
    val isInfinite: Boolean
        get() = numerator != 0 && denominator == 0

    /**
     * Indicates whether this rational represents a finite value.
     *
     *
     * A finite value occurs when the denominator is not `0`; in other words
     * the rational is neither infinity or `NaN`.
     *
     * @return `true` if this rational is a (positive or negative) infinite value;
     * `false` if this is a finite number value (or `NaN`)
     */
    val isFinite: Boolean
        get() = denominator != 0

    /**
     * Indicates whether this rational represents a zero value.
     *
     *
     * A zero value is a [finite][.isFinite] rational with a numerator of `0`.
     *
     * @return `true` if this rational is finite zero value;
     * `false` otherwise
     */
    val isZero: Boolean
        get() = isFinite && numerator == 0
    private val isPosInf: Boolean
        private get() = denominator == 0 && numerator > 0
    private val isNegInf: Boolean
        private get() = denominator == 0 && numerator < 0

    /**
     *
     * Compare this Rational to another object and see if they are equal.
     *
     *
     * A Rational object can only be equal to another Rational object (comparing against any
     * other type will return `false`).
     *
     *
     * A Rational object is considered equal to another Rational object if and only if one of
     * the following holds:
     *  * Both are `NaN`
     *  * Both are infinities of the same sign
     *  * Both have the same numerator and denominator in their reduced form
     *
     *
     *
     * A reduced form of a Rational is calculated by dividing both the numerator and the
     * denominator by their greatest common divisor.
     *
     * <pre>`(new Rational(1, 2)).equals(new Rational(1, 2)) == true   // trivially true
     * (new Rational(2, 3)).equals(new Rational(1, 2)) == false  // trivially false
     * (new Rational(1, 2)).equals(new Rational(2, 4)) == true   // true after reduction
     * (new Rational(0, 0)).equals(new Rational(0, 0)) == true   // NaN.equals(NaN)
     * (new Rational(1, 0)).equals(new Rational(5, 0)) == true   // both are +infinity
     * (new Rational(1, 0)).equals(new Rational(-1, 0)) == false // +infinity != -infinity
    `</pre> *
     *
     * @param obj a reference to another object
     *
     * @return A boolean that determines whether or not the two Rational objects are equal.
     */
    override fun equals(obj: Any?): Boolean {
        return obj is Rational && equals(obj)
    }

    private fun equals(other: Rational): Boolean {
        return numerator == other.numerator && denominator == other.denominator
    }

    /**
     * Return a string representation of this rational, e.g. `"1/2"`.
     *
     *
     * The following rules of conversion apply:
     *
     *  * `NaN` values will return `"NaN"`
     *  * Positive infinity values will return `"Infinity"`
     *  * Negative infinity values will return `"-Infinity"`
     *  * All other values will return `"numerator/denominator"` where `numerator`
     * and `denominator` are substituted with the appropriate numerator and denominator
     * values.
     *
     */
    override fun toString(): String {
        return if (isNaN) {
            "NaN"
        } else if (isPosInf) {
            "Infinity"
        } else if (isNegInf) {
            "-Infinity"
        } else {
            numerator.toString() + "/" + denominator
        }
    }

    /**
     *
     * Convert to a floating point representation.
     *
     * @return The floating point representation of this rational number.
     * @hide
     */
    override fun toFloat(): Float {
        // TODO: remove this duplicate function (used in CTS and the shim)
        return toFloat()
    }

    /**
     * {@inheritDoc}
     */
    override fun hashCode(): Int {
        // Bias the hash code for the first (2^16) values for both numerator and denominator
        val numeratorFlipped = numerator shl 16 or numerator ushr 16
        return denominator xor numeratorFlipped
    }

    override fun toByte(): Byte {
        return (numerator / denominator).toByte()
    }

    override fun toChar(): Char {
        return (numerator / denominator).toChar()
    }

    /**
     * Returns the value of the specified number as a `double`.
     *
     *
     * The `double` is calculated by converting both the numerator and denominator
     * to a `double`; then returning the result of dividing the numerator by the
     * denominator.
     *
     * @return the divided value of the numerator and denominator as a `double`.
     */
    override fun toDouble(): Double {
        val num = numerator.toDouble()
        val den = denominator.toDouble()
        return num / den
    }

    /**
     * Returns the value of the specified number as a `float`.
     *
     *
     * The `float` is calculated by converting both the numerator and denominator
     * to a `float`; then returning the result of dividing the numerator by the
     * denominator.
     *
     * @return the divided value of the numerator and denominator as a `float`.
     */
//    override fun toFloat(): Float {
//        val num = numerator.toFloat()
//        val den = denominator.toFloat()
//        return num / den
//    }

    /**
     * Returns the value of the specified number as a `int`.
     *
     *
     * [Finite][.isInfinite] rationals are converted to an `int` value
     * by dividing the numerator by the denominator; conversion for non-finite values happens
     * identically to casting a floating point value to an `int`, in particular:
     *
     *
     *
     *
     *  * Positive infinity saturates to the largest maximum integer
     * [Integer.MAX_VALUE]
     *  * Negative infinity saturates to the smallest maximum integer
     * [Integer.MIN_VALUE]
     *  * *Not-A-Number (NaN)* returns `0`.
     *
     *
     *
     * @return the divided value of the numerator and denominator as a `int`.
     */
    override fun toInt(): Int {
        // Mimic float to int conversion rules from JLS 5.1.3
        return if (isPosInf) {
            Int.MAX_VALUE
        } else if (isNegInf) {
            Int.MIN_VALUE
        } else if (isNaN) {
            0
        } else { // finite
            numerator / denominator
        }
    }

    /**
     * Returns the value of the specified number as a `long`.
     *
     *
     * [Finite][.isInfinite] rationals are converted to an `long` value
     * by dividing the numerator by the denominator; conversion for non-finite values happens
     * identically to casting a floating point value to a `long`, in particular:
     *
     *
     *
     *
     *  * Positive infinity saturates to the largest maximum long
     * [Long.MAX_VALUE]
     *  * Negative infinity saturates to the smallest maximum long
     * [Long.MIN_VALUE]
     *  * *Not-A-Number (NaN)* returns `0`.
     *
     *
     *
     * @return the divided value of the numerator and denominator as a `long`.
     */
    override fun toLong(): Long {
        // Mimic float to long conversion rules from JLS 5.1.3
        return if (isPosInf) {
            Long.MAX_VALUE
        } else if (isNegInf) {
            Long.MIN_VALUE
        } else if (isNaN) {
            0
        } else { // finite
            (numerator / denominator).toLong()
        }
    }

    /**
     * Returns the value of the specified number as a `short`.
     *
     *
     * [Finite][.isInfinite] rationals are converted to a `short` value
     * identically to [.intValue]; the `int` result is then truncated to a
     * `short` before returning the value.
     *
     * @return the divided value of the numerator and denominator as a `short`.
     */
    override fun toShort(): Short {
        return toInt().toShort()
    }

    /**
     * Compare this rational to the specified rational to determine their natural order.
     *
     *
     * [.NaN] is considered to be equal to itself and greater than all other
     * `Rational` values. Otherwise, if the objects are not [equal][.equals], then
     * the following rules apply:
     *
     *
     *  * Positive infinity is greater than any other finite number (or negative infinity)
     *  * Negative infinity is less than any other finite number (or positive infinity)
     *  * The finite number represented by this rational is checked numerically
     * against the other finite number by converting both rationals to a common denominator multiple
     * and comparing their numerators.
     *
     *
     * @param another the rational to be compared
     *
     * @return a negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified rational.
     *
     * @throws NullPointerException if `another` was `null`
     */
    override fun compareTo(another: Rational): Int {
        if (equals(another)) {
            return 0
        } else if (isNaN) { // NaN is greater than the other non-NaN value
            return 1
        } else if (another.isNaN) { // the other NaN is greater than this non-NaN value
            return -1
        } else if (isPosInf || another.isNegInf) {
            return 1 // positive infinity is greater than any non-NaN/non-posInf value
        } else if (isNegInf || another.isPosInf) {
            return -1 // negative infinity is less than any non-NaN/non-negInf value
        }

        // else both this and another are finite numbers

        // make the denominators the same, then compare numerators
        val thisNumerator = numerator.toLong() * another.denominator // long to avoid overflow
        val otherNumerator = another.numerator.toLong() * denominator // long to avoid overflow

        // avoid underflow from subtraction by doing comparisons
        return if (thisNumerator < otherNumerator) {
            -1
        } else if (thisNumerator > otherNumerator) {
            1
        } else {
            // This should be covered by #equals, but have this code path just in case
            0
        }
    }
    /*
     * Serializable implementation.
     *
     * The following methods are omitted:
     * >> writeObject - the default is sufficient (field by field serialization)
     * >> readObjectNoData - the default is sufficient (0s for both fields is a NaN)
     */
    /**
     * writeObject with default serialized form - guards against
     * deserializing non-reduced forms of the rational.
     *
     * @throws InvalidObjectException if the invariants were violated
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: ObjectInputStream) {
        `in`.defaultReadObject()

        /*
         * Guard against trying to deserialize illegal values (in this case, ones
         * that don't have a standard reduced form).
         *
         * - Non-finite values must be one of [0, 1], [0, 0], [0, 1], [0, -1]
         * - Finite values must always have their greatest common divisor as 1
         */if (numerator == 0) { // either zero or NaN
            if (denominator == 1 || denominator == 0) {
                return
            }
            throw InvalidObjectException(
                "Rational must be deserialized from a reduced form for zero values"
            )
        } else if (denominator == 0) { // either positive or negative infinity
            if (numerator == 1 || numerator == -1) {
                return
            }
            throw InvalidObjectException(
                "Rational must be deserialized from a reduced form for infinity values"
            )
        } else { // finite value
            if (gcd(numerator, denominator) > 1) {
                throw InvalidObjectException(
                    "Rational must be deserialized from a reduced form for finite values"
                )
            }
        }
    }

    companion object {
        /**
         * Constant for the *Not-a-Number (NaN)* value of the `Rational` type.
         *
         *
         * A `NaN` value is considered to be equal to itself (that is `NaN.equals(NaN)`
         * will return `true`; it is always greater than any non-`NaN` value (that is
         * `NaN.compareTo(notNaN)` will return a number greater than `0`).
         *
         *
         * Equivalent to constructing a new rational with both the numerator and denominator
         * equal to `0`.
         */
        val NaN = Rational(0, 0)

        /**
         * Constant for the positive infinity value of the `Rational` type.
         *
         *
         * Equivalent to constructing a new rational with a positive numerator and a denominator
         * equal to `0`.
         */
        val POSITIVE_INFINITY = Rational(1, 0)

        /**
         * Constant for the negative infinity value of the `Rational` type.
         *
         *
         * Equivalent to constructing a new rational with a negative numerator and a denominator
         * equal to `0`.
         */
        val NEGATIVE_INFINITY = Rational(-1, 0)

        /**
         * Constant for the zero value of the `Rational` type.
         *
         *
         * Equivalent to constructing a new rational with a numerator equal to `0` and
         * any non-zero denominator.
         */
        val ZERO = Rational(0, 1)

        /**
         * Unique version number per class to be compliant with [java.io.Serializable].
         *
         *
         * Increment each time the fields change in any way.
         */
        private const val serialVersionUID = 1L

        /**
         * Calculates the greatest common divisor using Euclid's algorithm.
         *
         *
         * *Visible for testing only.*
         *
         * @param numerator the numerator in a fraction
         * @param denominator the denominator in a fraction
         *
         * @return An int value representing the gcd. Always positive.
         * @hide
         */
        fun gcd(numerator: Int, denominator: Int): Int {
            /*
         * Non-recursive implementation of Euclid's algorithm:
         *
         *  gcd(a, 0) := a
         *  gcd(a, b) := gcd(b, a mod b)
         *
         */
            var a = numerator
            var b = denominator
            while (b != 0) {
                val oldB = b
                b = a % b
                a = oldB
            }
            return Math.abs(a)
        }

        private fun invalidRational(s: String): NumberFormatException {
            throw NumberFormatException("Invalid Rational: \"$s\"")
        }

        /**
         * Parses the specified string as a rational value.
         *
         * The ASCII characters `\``u003a` (':') and
         * `\``u002f` ('/') are recognized as separators between
         * the numerator and denumerator.
         *
         *
         * For any `Rational r`: `Rational.parseRational(r.toString()).equals(r)`.
         * However, the method also handles rational numbers expressed in the
         * following forms:
         *
         *
         * "*num*`/`*den*" or
         * "*num*`:`*den*" `=> new Rational(num, den);`,
         * where *num* and *den* are string integers potentially
         * containing a sign, such as "-10", "+7" or "5".
         *
         * <pre>`Rational.parseRational("3:+6").equals(new Rational(1, 2)) == true
         * Rational.parseRational("-3/-6").equals(new Rational(1, 2)) == true
         * Rational.parseRational("4.56") => throws NumberFormatException
        `</pre> *
         *
         * @param string the string representation of a rational value.
         * @return the rational value represented by `string`.
         *
         * @throws NumberFormatException if `string` cannot be parsed
         * as a rational value.
         * @throws NullPointerException if `string` was `null`
         */
        @Throws(NumberFormatException::class)
        fun parseRational(string: String): Rational {
            if (string == "NaN") {
                return NaN
            } else if (string == "Infinity") {
                return POSITIVE_INFINITY
            } else if (string == "-Infinity") {
                return NEGATIVE_INFINITY
            }
            var sep_ix = string.indexOf(':')
            if (sep_ix < 0) {
                sep_ix = string.indexOf('/')
            }
            if (sep_ix < 0) {
                throw invalidRational(string)
            }
            return try {
                Rational(string.substring(0, sep_ix).toInt(), string.substring(sep_ix + 1).toInt())
            } catch (e: NumberFormatException) {
                throw invalidRational(string)
            }
        }
    }

    /**
     *
     * Create a `Rational` with a given numerator and denominator.
     *
     *
     * The signs of the numerator and the denominator may be flipped such that the denominator
     * is always positive. Both the numerator and denominator will be converted to their reduced
     * forms (see [.equals] for more details).
     *
     *
     * For example,
     *
     *  * a rational of `2/4` will be reduced to `1/2`.
     *  * a rational of `1/-1` will be flipped to `-1/1`
     *  * a rational of `5/0` will be reduced to `1/0`
     *  * a rational of `0/5` will be reduced to `0/1`
     *
     *
     *
     * @param numerator the numerator of the rational
     * @param denominator the denominator of the rational
     *
     * @see .equals
     */
    init {
        var numerator = numerator
        var denominator = denominator
        if (denominator < 0) {
            numerator = -numerator
            denominator = -denominator
        }

        // Convert to reduced form
        if (denominator == 0 && numerator > 0) {
            this.numerator = 1 // +Inf
            this.denominator = 0
        } else if (denominator == 0 && numerator < 0) {
            this.numerator = -1 // -Inf
            this.denominator = 0
        } else if (denominator == 0 && numerator == 0) {
            this.numerator = 0 // NaN
            this.denominator = 0
        } else if (numerator == 0) {
            this.numerator = 0
            this.denominator = 1
        } else {
            val gcd = gcd(numerator, denominator)
            this.numerator = numerator / gcd
            this.denominator = denominator / gcd
        }
    }
}