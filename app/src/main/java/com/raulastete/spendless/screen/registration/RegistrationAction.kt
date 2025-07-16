package com.raulastete.spendless.screen.registration

sealed interface RegistrationAction {

    data class OnUsernameUpdate(val username: String) : RegistrationAction

}