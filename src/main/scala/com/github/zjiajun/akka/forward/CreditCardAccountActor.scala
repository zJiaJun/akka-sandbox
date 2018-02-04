package com.github.zjiajun.akka.forward

import akka.actor.{Actor, ActorLogging, Props}

/**
  * @author zhujiajun
  * @since 2018/2/4
  *
  *  信用卡账户
  */
class CreditCardAccountActor extends Actor with ActorLogging {

  override def receive: Receive = {

    case CreditCardAccountActor.CreditCardAccount => {
      log.info(s"${sender()}")
    }

  }


}

object CreditCardAccountActor {

  def props: Props = Props(classOf[CreditCardAccountActor])

  case class CreditCardAccount()
}