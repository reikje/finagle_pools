package com.github.reikje

import java.net.URL

import com.twitter.conversions.time._
import com.twitter.finagle.client.Transporter
import com.twitter.finagle.factory.TimeoutFactory
import com.twitter.finagle.http.Request
import com.twitter.finagle.service.FailFastFactory.FailFast
import com.twitter.finagle.{Http, param}
import com.twitter.io.Buf
import com.twitter.util.Await

object Test {
  def main(args: Array[String]): Unit = {
    val url = new URL("https://github.com")

    println(s"Creating client using inet!${url.getHost}:443")

    val client = Http.client
      .configured(param.Label("finagle_pools"))
      .configured(TimeoutFactory.Param(10.seconds))
      .configured(Transporter.ConnectTimeout(10.seconds))
      .configured(FailFast(enabled = false))
      .withTransport.tls(hostname = url.getHost)
      .withTransport.verbose
      .withSessionPool.minSize(1)
      .withSessionPool.maxSize(2)
      .withSessionPool.maxWaiters(250)
      .newClient(s"inet!${url.getHost}:443")
      .toService

    val request = Request("https://github.com/twitter/finagle")
    val response = Await.result(client(request))
    println(new String(Buf.ByteArray.Owned.extract(response.content), "UTF-8"))
  }
}
