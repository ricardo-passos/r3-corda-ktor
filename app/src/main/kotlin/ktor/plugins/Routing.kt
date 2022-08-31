package ktor.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import ktor.routes.*
import net.corda.core.messaging.CordaRPCOps

fun Application.configureRouting(proxy: CordaRPCOps) {
    routing {
        customerRouting(proxy)
        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}