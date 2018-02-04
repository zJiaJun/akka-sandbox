package com.github.zjiajun.akka.forward

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.concurrent.duration.Duration

/**
  * @author zhujiajun
  * @since 2018/2/4
  */

class AccountSystem extends Actor with ActorLogging {

  override def receive: Receive = {

    case AccountSystem.QueryHistoryAccount => {
      context.actorSelection("akka://account/user/historyAccountActor") ! HistoryAccountActor.HistoryAccount(1)
    }
    case AccountSystem.AccountResult(amount) => {
      log.info(s"account amount is $amount from ${sender()}")
    }

  }

}
object AccountSystem {

  def props: Props = Props(classOf[AccountSystem])

  case class QueryHistoryAccount()

  case class AccountResult(amount: Long)

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("account")
    val accountActor = system.actorOf(AccountSystem.props, "accountActor")
    system.actorOf(HistoryAccountActor.props, "historyAccountActor")
    system.actorOf(ChequingAccountActor.props, "chequingAccountActor")
    system.actorOf(CreditCardAccountActor.props, "creditCardAccountActor")

    import concurrent.ExecutionContext.Implicits.global
    system.scheduler.scheduleOnce(Duration(1, TimeUnit.SECONDS), accountActor, AccountSystem.QueryHistoryAccount)

  }

}
