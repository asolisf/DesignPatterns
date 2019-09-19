package com.alansolisflores

fun main(args: Array<String>) {
    val chainLogger = LoggerHandler.getChainLogger()
    chainLogger.Process(Log.DEBUG,"var x value is ...")
    chainLogger.Process(Log.INFO,"Task executed successfully")
    chainLogger.Process(Log.ERROR,"Fatal error!")
}

class LoggerHandler{

    companion object{
        fun getChainLogger() : AbstractLogger{
            
            //Build chain logger
            val fileLogger: AbstractLogger = FileLogger()       
            val errorLogger: AbstractLogger = ErrorLogger()       
            val consoleLogger: AbstractLogger = ConsoleLogger()       
            
            fileLogger.SetNext(errorLogger)
            errorLogger.SetNext(consoleLogger)

            return fileLogger
        }
    }
}

interface AbstractLogger{

    fun SetNext(nextLogger: AbstractLogger)

    fun Process(log: Log,message: String)

    fun Write(message: String)
}

class FileLogger : AbstractLogger {

    private lateinit var nextLogger: AbstractLogger

    override fun SetNext(nextLogger: AbstractLogger){
        this.nextLogger = nextLogger
    }

    override fun Process(log: Log,message: String){
        if(log == Log.INFO){
            this.Write(message)
        }else{
            this.nextLogger.Process(log,message)
        }
    }

    override fun Write(message: String){
        println("Message written in file: "+message)
    }
}

class ConsoleLogger : AbstractLogger {
    
    private lateinit var nextLogger: AbstractLogger

    override fun SetNext(nextLogger: AbstractLogger){
        this.nextLogger = nextLogger
    }

    override fun Process(log: Log,message: String){
        if(log == Log.DEBUG){
            this.Write(message)
        }else{
            this.nextLogger.Process(log,message)
        }
    }

    override fun Write(message: String){
        println("Message print in console: "+message)
    }
}

class ErrorLogger : AbstractLogger {

    private lateinit var nextLogger: AbstractLogger

    override fun SetNext(nextLogger: AbstractLogger){
        this.nextLogger = nextLogger
    }

    override fun Process(log: Log,message: String){
        if(log == Log.ERROR){
            this.Write(message)
        }else{
            this.nextLogger.Process(log,message)
        }
    }

    override fun Write(message: String){
        println("Message error: "+message)
    }
}

enum class Log {
    DEBUG,INFO,ERROR
}