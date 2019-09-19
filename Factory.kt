package com.alansolisflores

import kotlin.reflect.KClass

fun main(args: Array<String>) {
    var rectangle = ShapeFactory.getShape(Rectangle::class)
    //? for safe use of nullable object
    rectangle?.Draw()
}

interface Shape{
    fun Draw()
}

class Rectangle : Shape {
    override fun Draw(){
        println("Draw rectangle")
    }
}

class Circle : Shape {
    override fun Draw(){
        println("Draw circle")
    }
}

class ShapeFactory {
    companion object {
        fun<T: Shape> getShape(shapeType: KClass<T>) : Shape? {
            if(shapeType == Rectangle::class){
                return Rectangle()
            }else if(shapeType == Circle::class){
                return Circle()
            }else{
                return null
            }
        }
    }
}

