package Interfaces

interface Message
{
    var ID:Long

    var NAME:String

    var DLC:Int

    var SendingNode:String

    var SIGNAL:Array<SIGNAL>
}