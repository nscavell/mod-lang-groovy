/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vertx.groovy.core.net

import org.vertx.java.core.ServerSSLSupport
import org.vertx.java.core.ServerTCPSupport


/**
 * Represents a TCP or SSL server
 * <p>
 * This class is a thread safe and can safely be used by different threads.
 * <p>
 * If an instance is instantiated from an event loop then the handlers
 * of the instance will always be called on that same event loop.
 * If an instance is instantiated from some other arbitrary Java thread then
 * and event loop will be assigned to the instance and used when any of its handlers
 * are called.
 * <p>
 * Instances cannot be used from worker verticles
 * @author Peter Ledbrook
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
interface NetServer extends ServerSSLSupport<NetServer>, ServerTCPSupport<NetServer> {
  
  /**
   * Supply a connect handler for this server. The server can only have at most one connect handler at any one time.
   * As the server accepts TCP or SSL connections it creates an instance of {@link org.vertx.groovy.core.net.NetSocket} and passes it to the
   * connect handler.
   * @return a reference to this so multiple method calls can be chained together
   */
  NetServer connectHandler(Closure hndlr)

  /**
   * Instruct the server to listen for incoming connections on the specified {@code port} and {@code host}. {@code host} can
   * be a host name or an IP address.
   */
  NetServer listen(int port)

  /**
   * Instruct the server to listen for incoming connections on the specified {@code port}.
   */
  NetServer listen(int port, String host)

  /**
   * Instruct the server to listen for incoming connections on the specified {@code port} and {@code host}. {@code host} can
   * be a host name or an IP address.
   */
  NetServer listen(int port, Closure bindHandler)

  /**
   * Instruct the server to listen for incoming connections on the specified {@code port}.
   */
  NetServer listen(int port, String host, Closure bindHandler)

  /**
   * Close the server. This will close any currently open connections.
   */
  void close()

  /**
   * Close the server. This will close any currently open connections. The event handler {@code done} will be called
   * when the close is complete.
   */
  void close(Closure hndlr)

  /**
   * The actual port the server is listening on. This is useful if you bound the server specifying 0 as port number
   * signifying an ephemeral port
   */
  int getPort()

  /**
   * The host
   */
  String getHost()
}
