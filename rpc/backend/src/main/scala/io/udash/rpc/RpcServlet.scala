package io.udash.rpc

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import org.atmosphere.cpr._

/**
  * Servlet for RPC endpoint.
  *
  * @param framework Instance of initialized AtmosphereFramework, which handle RPC requests.
  */
class RpcServlet(framework: AtmosphereFramework) extends HttpServlet {
  override def doTrace(req: HttpServletRequest, resp: HttpServletResponse): Unit = doPost(req, resp)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = doPost(req, resp)

  override def doPut(req: HttpServletRequest, resp: HttpServletResponse): Unit = doPost(req, resp)

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    framework.doCometSupport(AtmosphereRequestImpl.wrap(req), AtmosphereResponseImpl.wrap(resp))
  }
}
