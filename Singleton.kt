package com.alansolisflores

fun main(args: Array<String>) {
    LoggerService.getInstance().Error("FATAL ERROR!")
}

class LoggerService private constructor() : ILogger{

    companion object{
        
        private val instance = LoggerService()

        fun getInstance() : LoggerService{
            return this.instance
        }

    }

    override fun Error(message: String){
        println(message)
    }
}

interface ILogger {
    fun Error(message: String)
}