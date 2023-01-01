package Interfaces

interface IComment {
    enum class CommentType
    {
        COMMENT_MESSAGE,
        COMMENT_SIGNAL
    }

    var COMMENT_TYPE: CommentType

    var ID:Long

    var COMMENT:String

}
