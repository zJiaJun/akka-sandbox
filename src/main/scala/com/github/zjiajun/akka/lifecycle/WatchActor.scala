package com.github.zjiajun.akka.lifecycle

import akka.actor.{Actor, ActorLogging, Props}

/**
  * @author zhujiajun
  * @since 2018/2/5
  */
class WatchActor extends Actor with ActorLogging {

  override def receive: Receive = {

    case x => {
      log.info(x.toString)
    }
  }

}

object WatchActor {

  def props: Props = Props[WatchActor]
}
