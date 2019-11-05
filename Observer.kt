package com.alansolisflores

fun main(args: Array<String>) {
    val publisher = DailyNews()
    val consumer = Consumer(publisher)
    publisher.Notify()
    consumer.Dispose()
}

public interface ISuscriber{
    fun Listen(message: String)
    fun Dispose()
}

public interface IPublisher{

    fun Suscribe(suscriber: ISuscriber)
    fun UnSuscribe()
    fun Notify()
}

public class DailyNews : IPublisher{

    private var suscriber: ISuscriber? = null

    override fun Suscribe(suscriber: ISuscriber){
        this.suscriber = suscriber
    }

    override fun UnSuscribe(){
        this.suscriber = null
    }

    override fun Notify(){
        this.suscriber?.Listen("New magazine!")        
    }
}

public class Consumer : ISuscriber{

    private val publisher: DailyNews

    constructor(publisher: DailyNews){
        this.publisher = publisher
        this.publisher.Suscribe(this)
    }

    override fun Listen(message: String){
        println(message)
    }

    override fun Dispose(){
        this.publisher.UnSuscribe()
    }
}
