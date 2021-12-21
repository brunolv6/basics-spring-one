package com.basic.springone.adapter.validators

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [IsValidPasswordValidator::class])
@MustBeDocumented
annotation class IsValidPassword(
    val message: String = "Not valid password",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class IsValidPasswordValidator : ConstraintValidator<IsValidPassword, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean =
        value != null && value.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$".toRegex())

}
