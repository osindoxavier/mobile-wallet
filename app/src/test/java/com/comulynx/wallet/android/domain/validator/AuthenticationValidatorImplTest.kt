package com.comulynx.wallet.android.domain.validator

import com.comulynx.wallet.android.data.validator.AuthenticationValidator
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class AuthenticationValidatorImplTest {

    private lateinit var authenticationValidator: AuthenticationValidator

    @Before
    fun setUp() {
        authenticationValidator = AuthenticationValidatorImpl()
    }

    @Test
    fun `executeCustomerId with blank customerId should return error`() {
        val result = authenticationValidator.executeCustomerId("")
        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo("Customer ID cannot be empty!")
    }

    @Test
    fun `executeCustomerId with valid customerId should return success`() {
        val result = authenticationValidator.executeCustomerId("12345")
        assertThat(result.successful).isTrue()
        assertThat(result.errorMessage).isNull()
    }

    @Test
    fun `executePin with blank pin should return error`() {
        val result = authenticationValidator.executePin("")
        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo("Pin cannot be empty!")
    }

    @Test
    fun `executePin with pin less than 4 characters should return error`() {
        val result = authenticationValidator.executePin("123")
        assertEquals(false, result.successful)
        assertEquals("Pin cannot be less than 4 characters!", result.errorMessage)
    }

    @Test
    fun `executePin with valid pin should return success`() {
        val result = authenticationValidator.executePin("1234")
        assertThat(result.successful).isTrue()
        assertEquals(true, result.successful)
        assertThat(result.errorMessage).isNull()
    }
}