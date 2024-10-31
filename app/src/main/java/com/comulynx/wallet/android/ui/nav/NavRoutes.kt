package com.comulynx.wallet.android.ui.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {
    @Serializable
    data object Login : NavRoutes()

    @Serializable
    data object Home : NavRoutes()

    @Serializable
    data object SendMoney : NavRoutes()

    @Serializable
    data object ViewStatement : NavRoutes()

    @Serializable
    data object ViewTransactions : NavRoutes()

    @Serializable
    data object Profile : NavRoutes()
}