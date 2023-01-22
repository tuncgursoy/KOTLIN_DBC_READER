package Source

import Interfaces.IComment
import Interfaces.IMessage
import Interfaces.ISIGNAL

class Message(
    override var ID: Long,
    override var NAME: String,
    override var DLC: Int,
    override var SendingNode: String,
    override var ISIGNAL: ArrayList<ISIGNAL>,
    override var comment: IComment?
) :IMessage {
    override fun toString(): String {
        return "Message(ID=$ID, NAME='$NAME', DLC=$DLC, SendingNode='$SendingNode', ISIGNAL=${ISIGNAL.toString()}, " +
                "IValue:)${comment.toString()}"
    }
}