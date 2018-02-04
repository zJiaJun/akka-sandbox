package com.github.zjiajun.akka.forward

import akka.actor.{Actor, ActorLogging, Props}

/**
  * @author zhujiajun
  * @since 2018/2/4
  *
  * 查询历史账户
  */
class HistoryAccountActor extends Actor with ActorLogging {

  override def receive: Receive = {

    case HistoryAccountActor.HistoryAccount(accountType) => {
      log.info(s"query history account type is $accountType from ${sender()}")
      accountType match {
        case 1 => {
          context.actorSelection("akka://account/user/chequingAccountActor").forward(ChequingAccountActor.ChequingAccount)
        }
        case 2 => {
          context.actorSelection("akka://account/user/creditCardAccountActor").forward(CreditCardAccountActor.CreditCardAccount)
        }
        case _ => log.error("unKnown account type")
      }

    }
    case _ => log.error("unKnown msg")

  }

}

object HistoryAccountActor {

  def props: Props = Props(classOf[HistoryAccountActor])

  case class HistoryAccount(accountType: Int)

}
