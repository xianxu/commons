package com.twitter.play

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

class AkkaMain extends Actor {
  override def preStart(): Unit = {
    val theRing = context.actorOf(Props[World.Ring])
    theRing ! World.Go
  }

  def receive = {
    case World.Done =>
      println(s"time elapsed ${World.tick()} ms")
      context.stop(self)
  }
}

object World {
  case object Go
  case object Done
  case object Count

  val N = 10000
  val M = 1000
  val R = 3
  //val N = 3
  //val M = 1
  //val R = 1

  val t = System.currentTimeMillis
  def tick() = {
    System.currentTimeMillis - t
  }

  var root: ActorRef = null
  var ring: ActorRef = null
  var nodes: Array[ActorRef] = null
  var finished = 0

  class Ring extends Actor {
    def receive = {
      case Go =>
        ring = self
        root = sender

        nodes = (0 until N).map { i: Int =>
          context.actorOf(Node.props(i))
        } .toArray

        def fire() {
          (0 until M) foreach { i =>
            nodes(i) ! i
          }
        }

        (0 until R) foreach { _ => fire() }

      case Count =>
        finished += 1
        if (finished == M * R)
          root ! Done
    }
  }

  object Node {
    def props(i: Int): Props = Props(classOf[Node], i)
  }

  class Node(i: Int) extends Actor {
    def receive = {
      case msg: Int if msg == (N + i + 1) % N =>
        //println(s"$i skipping, msg: $msg")
        ring ! Count
      case msg =>
        //println(s"$i passing $msg to ${(N+i+1)%N}")
        nodes((N+i+1)%N) ! msg
    }
  }
}
