package Interfaces

interface IMessage
{
    var ID:Long

    var NAME:String

    var DLC:Int

    var SendingNode:String

    var ISIGNAL:Array<ISIGNAL>
}