package com.alansolisflores

fun main(args: Array<String>) {
    val publisher = DailyNews()
    val consumer1 = Consumer(publisher)
    val consumer2 = Consumer(publisher)
    val consumer3 = Consumer(publisher)
    publisher.Notify()
    consumer1.Dispose()
    consumer2.Dispose()
    consumer3.Dispose()
}

public interface ISubscriber{
    fun MailBox(message: String)
    fun Dispose()
}

public interface IPublisher{

    fun Subscribe(subscriber: ISubscriber)
    fun UnSubscribe(subscriber: ISubscriber)
    fun Notify()
}

public class DailyNews : IPublisher{

    private var subscribers= mutableListOf<ISubscriber>()

    override fun Subscribe(subscriber: ISubscriber){
        this.subscribers.add(subscriber)
    }

    override fun UnSubscribe(subscriber: ISubscriber){
        this.subscribers.remove(subscriber)
    }

    override fun Notify(){
        this.subscribers.forEach({it.MailBox("New magazine!")})
    }
}

public class Consumer : ISubscriber{

    private val publisher: DailyNews

    constructor(publisher: DailyNews){
        this.publisher = publisher
        this.publisher.Subscribe(this)
    }

    override fun MailBox(message: String){
        println(message)
    }

    override fun Dispose(){
        this.publisher.UnSubscribe(this)
    }
}
