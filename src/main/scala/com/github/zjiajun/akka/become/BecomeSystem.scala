package com.github.zjiajun.akka.become

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem

import scala.concurrent.duration.Duration

/**
  * @author zhujiajun
  * @since 2018/2/7
  */
object BecomeSystem {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("become")
    val becomeActor = system.actorOf(BecomeActor.props, "becomeActor")

    becomeActor ! BecomeActor.BecomeMsg

    import concurrent.ExecutionContext.Implicits.global
    system.scheduler.scheduleOnce(Duration(2, TimeUnit.SECONDS), becomeActor, 100)
    system.scheduler.scheduleOnce(Duration(4, TimeUnit.SECONDS), becomeActor, "hello become")
    system.scheduler.scheduleOnce(Duration(4, TimeUnit.SECONDS), becomeActor, 0.2)

  }

}
