package com.github.zjiajun.akka.forward

import akka.actor.{Actor, ActorLogging, Props}

/**
  * @author zhujiajun
  * @since 2018/2/4
  *
  *  支票账户
  */
class ChequingAccountActor extends Actor with ActorLogging {

  override def receive: Receive = {

    case ChequingAccountActor.ChequingAccount => {
      log.info(s"${sender()}")
      sender() ! AccountSystem.AccountResult(12345)
    }

  }


}

object ChequingAccountActor {

  def props: Props = Props(classOf[ChequingAccountActor])

  case class ChequingAccount()
}
