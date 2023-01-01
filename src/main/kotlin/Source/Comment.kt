package Source

import Interfaces.IComment

class Comment(override var COMMENT_TYPE: IComment.CommentType, override var ID: Long, override var COMMENT: String) : IComment {
}