package Interfaces

interface Comment {
    enum class CommentType
    {
        COMMENT_MESSAGE,
        COMMENT_SIGNAL
    }

    var COMMENT_TYPE: CommentType

    var ID:Long

    var COMMENT:String

}
