package Source

import Interfaces.IComment

class Comment(override var COMMENT_TYPE: IComment.CommentType, override var ID: Long, override var COMMENT: String,override var commentedSignal: String) : IComment
{
    override fun toString(): String {
        var result = "Comment ";
        if(COMMENT_TYPE == IComment.CommentType.COMMENT_SIGNAL)
        {
            result += "Signal "
        }else
        {
            result+= "Message "
        }

        result += "$ID $COMMENT $commentedSignal"
        return result
    }

}