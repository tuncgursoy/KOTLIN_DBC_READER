package Source

import Interfaces.IMessage
import Interfaces.ISIGNAL

class Message(
    override var ID: Long,
    override var NAME: String,
    override var DLC: Int,
    override var SendingNode: String,
    override var ISIGNAL: Array<ISIGNAL>
) :IMessage {
    override fun toString(): String {
        return "Message(ID=$ID, NAME='$NAME', DLC=$DLC, SendingNode='$SendingNode', ISIGNAL=${ISIGNAL.contentToString()})"
    }
}