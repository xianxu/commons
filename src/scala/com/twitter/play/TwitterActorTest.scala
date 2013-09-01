package com.twitter.play

import com.twitter.util._
import com.twitter.concurrent._
import java.util.concurrent.CountDownLatch

/*
 * Tessting performance of Twitter Future. We do it this way:
 *
 * Create N actors, connected into a ring. We random select a node and starting passing messages.
 * Each actor will pass the message to it's next neighbor in the ring. When message's received by
 * the same actor that sends it, it's removed from further propogation. We do this M times.
 */
object TwitterActorTest {
  def main(args: Array[String]) {
    val tick = {
      val t = Time.now
      () => Time.now - t
    }
    val N = 10000
    val M = 1000
    val R = 3
    //val N = 3
    //val M = 1
    //val R = 1
    // [node 0] === pipe 0 ==> [node 1] ... [node n-1] === pipe n-1 ==> [node 0]
    val edges = (0 until N) map { _ => new Broker[Int] } toArray
    val latch = new CountDownLatch(M * R)

    def act(i: Int) {
      val prev = edges((N + i - 1) % N)
      val next = edges(i)
      def loop {
        prev.recv.sync() foreach { msg: Int =>
          if (msg == i) {
            //println(s"$i skipping, msg: $msg")
            latch.countDown
          } else {
            //println(s"$i passing $msg to ${(N + i + 1) % N}")
            next.send(msg).sync()
          }
          loop
        }
      }
      loop
    }

    (0 until N) foreach { i => act(i) }
    def fire() = (0 until M) foreach { i => edges(i).send(i).sync() }
    (0 until R) foreach { _ => fire() }

    latch.await
    println(s"time took: ${tick()}")
  }
}
