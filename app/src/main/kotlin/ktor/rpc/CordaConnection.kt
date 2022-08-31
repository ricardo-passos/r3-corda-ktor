package ktor.rpc

import net.corda.client.rpc.CordaRPCClient
import net.corda.client.rpc.CordaRPCConnection
import net.corda.core.utilities.NetworkHostAndPort

fun connectToNode(
    host: String = System.getProperty("config.rpc.host"),
    rpcPort: Int = System.getProperty("config.rpc.port").toInt(),
    username: String = System.getProperty("config.rpc.username"),
    password: String = System.getProperty("config.rpc.password")
): CordaRPCConnection {
    val rpcAddress = NetworkHostAndPort(host, rpcPort)
    val rpcClient = CordaRPCClient(rpcAddress)
    return rpcClient.start(username, password)
}