package com.github.zjiajun.akka.become

import akka.actor.{Actor, ActorLogging, Props}

/**
  * @author zhujiajun
  * @since 2018/2/7
  */
class BecomeActor extends Actor with ActorLogging {

  override def receive: Receive = {

    case BecomeActor.BecomeMsg => {
      log.info("Become msg")
      context.become(becomeReceive)
    }
    case BecomeActor.UnBecomeMsg => {
      log.info("UnBecome msg")
      context.unbecome()
    }
    case _ => log.info("Become unKnown msg")
  }

  def becomeReceive: Receive = {
    case BecomeActor.BecomeMsg => {
      log.info("Become ")
    }
    case s: String => log.info(s"$s")
    case _ => log.info("BecomeReceive unKnown msg")
  }

  def happy: Receive = {
    case BecomeActor.UnBecomeMsg => {
      log.info("UnBecome msg in happy")
      context.unbecome()
    }

  }

}

object BecomeActor {

  def props: Props = Props(classOf[BecomeActor])

  case class BecomeMsg()

  case class UnBecomeMsg()
}
