package Interfaces

interface DBC
{
    var VERSION:String

    var NODES:Array<String>

    var MESSAGE:Array<Message>

    var COMMENT:Array<Comment>

    var VALUES:Array<Value>

}