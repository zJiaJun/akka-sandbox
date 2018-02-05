package com.github.zjiajun.akka.lifecycle

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Kill, PoisonPill, Props, Terminated}
import com.github.zjiajun.akka.lifecycle.WatchSystem.Watch

import scala.concurrent.duration.Duration

/**
  * @author zhujiajun
  * @since 2018/2/5
  */
class WatchSystem extends Actor with ActorLogging {

  override def receive: Receive = {

    case Watch(watchRef) => {
      log.info(s"$context  watch $watchRef")
      watchRef ! "someMsg"
      context.watch(watchRef)
    }
    case Terminated(ref) => {
      log.info(s"$ref is Terminated")
    }
  }


}

object WatchSystem {

  def props: Props = Props(classOf[WatchSystem])

  case class Watch(ref: ActorRef)


  def main(args: Array[String]): Unit = {

    val system = ActorSystem("watch")
    val watch = system.actorOf(WatchSystem.props, "watch")
    val watchRef = system.actorOf(WatchActor.props, "watchRef")
    watch ! Watch(watchRef)

    import concurrent.ExecutionContext.Implicits.global
    system.scheduler.scheduleOnce(Duration(1, TimeUnit.SECONDS), watchRef, PoisonPill)

  }


}
