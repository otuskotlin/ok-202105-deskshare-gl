package user

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class UserClassTest: FreeSpec({
    "validate user instance" {
        listOf(
            UserTestBuilder.validUser() to true,
            UserTestBuilder.validUser(username = "Grischa") to true,
            UserTestBuilder.validUser(password = "fgh") to true,
            UserTestBuilder.invalidUser() to false,
            UserTestBuilder.invalidUser() to false
        ).forEach { (user: User, expectedValidation: Boolean) ->
            "user $user expected to be $expectedValidation" {
                user.isValid() shouldBe expectedValidation
            }
        }
    }

    "validate user instance by roles" {
        listOf(
            UserTestBuilder.userAdmin() to UserRole.ADMIN,
            UserTestBuilder.userWrite() to UserRole.WRITE,
            UserTestBuilder.userRead() to UserRole.READ,
        ).forEach { (user: User, expectedRolle: UserRole) ->
            "user $user expected to be $expectedRolle" {
                user.role shouldBe expectedRolle
            }
        }
    }
})
