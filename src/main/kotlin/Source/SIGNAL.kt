package Source

import Interfaces.IComment
import Interfaces.ISIGNAL

class SIGNAL(
    override var NAME: String,
    override var SIGNAL_LOC: Int,
    override var SIGNAL_SIZE: Int,
    override var IS_UNSIGNED: Boolean,
    override var FACTOR: Double,
    override var OFFSET: Double,
    override var MINIMUM: Double,
    override var MAXIMUM: Double,
    override var UNIT: String,
    override var ICOMMENT: IComment?
) :ISIGNAL {
    private var MSG_ID:Long = 0
    override fun toString(): String {
        return "SIGNAL(NAME='$NAME', SIGNAL_LOC=$SIGNAL_LOC, SIGNAL_SIZE=$SIGNAL_SIZE, IS_UNSIGNED=$IS_UNSIGNED, FACTOR=$FACTOR, OFFSET=$OFFSET, MINIMUM=$MINIMUM, MAXIMUM=$MAXIMUM, UNIT='$UNIT', ICOMMENT=$ICOMMENT)"
    }

    fun setMessageID(ID:Long)
    {
        MSG_ID = ID
    }

    fun getMessageID():Long
    {
        return MSG_ID
    }
}