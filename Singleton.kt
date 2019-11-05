package com.alansolisflores

fun main(args: Array<String>) {
    LoggerService.getInstance().Error("LoggerService: Warning!")
}

class LoggerService private constructor() : ILogger{

    companion object{
        
        private var INSTANCE: LoggerService? = null

        fun getInstance() : LoggerService{
            val tmpInstance = INSTANCE

            if(tmpInstance != null){
                return tmpInstance
            }
            
            //Thread safe
            synchronized(this) { 
                val instance = LoggerService()
                INSTANCE = instance
                return instance
            }
        }

    }

    override fun Error(message: String){
        println(message)
    }
}

interface ILogger {
    fun Error(message: String)
}