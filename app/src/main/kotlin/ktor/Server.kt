package ktor

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import ktor.plugins.*
import ktor.rpc.connectToNode
import net.corda.client.jackson.JacksonSupport
import net.corda.client.rpc.CordaRPCConnection
import net.corda.core.messaging.CordaRPCOps

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    val connection: CordaRPCConnection = connectToNode("localhost", 10006, "user1", "test")
    install(ContentNegotiation) { cordaJackson(connection.proxy) }
    configureRouting(connection.proxy)
}

fun ContentNegotiationConfig.cordaJackson(proxy: CordaRPCOps) {
    val mapper: ObjectMapper = JacksonSupport.createDefaultMapper(proxy)
    mapper.apply {
        setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
            indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
            indentObjectsWith(DefaultIndenter("  ", "\n"))
        })
    }
    val converter = JacksonConverter(mapper)

    register(ContentType.Application.Json, converter)
}
