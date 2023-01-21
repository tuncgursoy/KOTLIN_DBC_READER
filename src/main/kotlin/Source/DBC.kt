package Source

import Interfaces.IComment
import Interfaces.IValueType
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class DBC(){

    fun findVersion(file:File):String
    {
        var result="-"
        var line: String
        var splintedLine:List<String>
        try {
            val file  = BufferedReader(FileReader(file))
            do {
                line = file.readLine()
                splintedLine = line.split(' ')
                if(splintedLine[0] == "VERSION")
                {
                    result = splintedLine[1].removePrefix("\"").removeSuffix("\"")
                    break
                }else
                {
                    continue
                }

            }
            while(line != null)


        }catch (_:Exception) {}

        return result

    }

    fun findSendingNodes(file:File):ArrayList<String>
    {
        val result= ArrayList<String>()
        var line: String
        var splintedLine:List<String>
        try {
            val file  = BufferedReader(FileReader(file))
            do {
                line = file.readLine()
                splintedLine = line.split(' ')
                if(splintedLine[0]   == "BU_:")
                {
                    for (node in 1..splintedLine.size)
                    {
                        result.add(splintedLine[node])
                    }
                    break
                }else
                {
                    continue
                }

            }
            while(line != null)


        }catch (_:Exception) {}

        return  result
    }

    fun findMessages(file:File):ArrayList<Message>
    {
        val result= ArrayList<Message>()
        var line: String
        var splintedLine:List<String>
        try {
            val file  = BufferedReader(FileReader(file))
            do {
                line = file.readLine()
                splintedLine = line.split(' ')

                if(splintedLine[0]   == "BO_")
                {
                    val msg = Message(splintedLine[1].toLong(),splintedLine[2].removeSuffix(":"),splintedLine[3].toInt(),splintedLine[4],
                        emptyArray())
                    result.add(msg)
                }else
                {
                    continue
                }

            }
            while(line != null)


        }catch (_:Exception) { }

        return  result
    }

    fun findSignals(file:File):ArrayList<SIGNAL>
    {
        val result = ArrayList<SIGNAL>()
        var line: String
        var splintedLine:List<String>
        try {
            val file  = BufferedReader(FileReader(file))
            var lastmsgID:Long = 0
            do {
                line = file.readLine()
                splintedLine = line.split(' ')
                splintedLine = splintedLine.filter { x-> x.isNotEmpty() }

                if(splintedLine.isNotEmpty() && splintedLine[0]   == "SG_")
                {
                  val sgn = SIGNAL(splintedLine[1],(splintedLine[3].subSequence(0,splintedLine[3].indexOf('|'))).toString().toInt(),
                        splintedLine[3].subSequence(splintedLine[3].indexOf('|')+1,splintedLine[3].indexOf('@')).toString().toInt(),
                        '+'== splintedLine[3].get(splintedLine[3].lastIndex),
                         splintedLine[4].subSequence(1,splintedLine[4].indexOf(',')).toString().toDouble(),
                        splintedLine[4].subSequence(splintedLine[4].indexOf(',')+1,splintedLine[4].lastIndex).toString().toDouble(),
                        splintedLine[5].subSequence(1,splintedLine[5].indexOf('|')).toString().toDouble(),
                        splintedLine[5].subSequence(splintedLine[5].indexOf('|')+1,splintedLine[5].lastIndex).toString().toDouble(),
                        splintedLine[6].removePrefix("\"").removeSuffix("\""),null
                        )
                    sgn.setMessageID(lastmsgID)
                    result.add(sgn)
                }else if(splintedLine.isNotEmpty() && splintedLine[0]   == "BO_")
                {
                   lastmsgID = splintedLine[1].toLong()
                }else
                {
                    continue
                }

            }
            while(line != null)


        }catch (_:Exception) { }

        return  result
    }


    fun findComments(file:File):ArrayList<Comment>
    {
        var result =  ArrayList<Comment>()
        var line: String
        var splintedLine:List<String>


        try {
            val file  = BufferedReader(FileReader(file))
            do {
                line = file.readLine()
                splintedLine = line.split(' ')
                splintedLine = splintedLine.filter { x-> x.isNotEmpty() }

                if(splintedLine.isNotEmpty() && splintedLine[0]   == "CM_")
                {
                    val commentStartLoc = line.indexOf('"')
                    val commentEndLoc = line.indexOf(';')
                    val cmm: Comment
                    if(splintedLine[1].equals("SG_"))
                    {

                        cmm = Comment(IComment.CommentType.COMMENT_SIGNAL,splintedLine[2].toLong(),line.substring(commentStartLoc,commentEndLoc).removePrefix("\"").removeSuffix(";").removeSuffix("\""),splintedLine[3])
                    }else
                    {

                        cmm = Comment(IComment.CommentType.COMMENT_MESSAGE,splintedLine[2].toLong(),line.substring(commentStartLoc,commentEndLoc).removePrefix("\"").removeSuffix(";").removeSuffix("\""),"")
                    }
                    result.add(cmm)
                }else
                {
                    continue
                }

            }
            while(line != null)


        }catch (_:Exception) { }


        return result;
    }

    fun findValues(file:File):ArrayList<Value>
    {
        var result = ArrayList<Value>()
        var line: String
        var splintedLine:List<String>


        try {
            val file  = BufferedReader(FileReader(file))
            do {
                line = file.readLine()
                splintedLine = line.split(' ')
                splintedLine = splintedLine.filter { x-> x.isNotEmpty() }

                if(splintedLine.isNotEmpty() && splintedLine[0]   == "VAL_")
                {
                    val messageID = splintedLine[1].toLong()
                    val signalName = splintedLine[2]
                    val valueList = ArrayList<IValueType>()


                    var valuesString = ""
                    for(i in 3 until splintedLine.size)
                    {
                       valuesString+=(" "+splintedLine[i])
                    }
                    valuesString.trim();
                    valuesString.removeSuffix(";")
                    val splitedValues = valuesString.split('"');
                    for(i in 0 until splitedValues.size-1 step 2)
                    {
                        valueList.add(ValueType(splitedValues[i+1],splitedValues[i].toDouble()))
                    }
                     result.add(Value(messageID,signalName,valueList));
                }else
                {
                    continue
                }

            }
            while(line != null)


        }catch (_:Exception) { }


        return  result
    }


}