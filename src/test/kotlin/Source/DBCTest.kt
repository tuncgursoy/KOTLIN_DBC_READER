package Source

import Interfaces.IComment
import Interfaces.ISIGNAL
import Interfaces.IValueType
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.io.File

internal class DBCTest {

    @org.junit.jupiter.api.Test
    fun `test-findVersion`()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result =  dbc.findVersion(file)
        Assertions.assertEquals("TestVersion1", result)
    }

    @Test
    fun testfindSendingNodes()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result = dbc.findSendingNodes(file);
        val expectedAnswer:ArrayList<String> = ArrayList()
        expectedAnswer.add("SendingNODE1")
        expectedAnswer.add("SendingNODE2")
        Assert.assertEquals(expectedAnswer,result)
    }

    @Test
    fun testfindMessages()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result = dbc.findMessages(file)
        val expectedAnswer:ArrayList<Message>  = ArrayList()
        expectedAnswer.add(Message(1800,"Msg1Txt",8,"SendingNODE1", ArrayList(),null))
        expectedAnswer.add(Message(1801,"Msg2Txt",8,"SendingNODE2", ArrayList(),null))
        Assert.assertEquals(expectedAnswer.toString(),result.toString())
    }

    @Test
    fun testfindSignals()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result = dbc.findSignals(file)
        val expectedAnswer: ArrayList<SIGNAL> = ArrayList()
        expectedAnswer.add(SIGNAL("sg1",0,1,true,1.0,0.0,0.0,1.0,"",null,null))
        expectedAnswer.add(SIGNAL("sg2",1,1,true,1.0,0.0,0.0,1.0,"",null,null))
        expectedAnswer.add(SIGNAL("sg3",0,2,true,1.0,0.0,0.0,3.0,"",null,null))
        Assert.assertEquals(expectedAnswer.toString(),result.toString())
    }

    @Test
    fun testfindComments()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result = dbc.findComments(file)
        val expectedAnswer : ArrayList<Comment> = ArrayList()
        expectedAnswer.add(Comment(IComment.CommentType.COMMENT_SIGNAL,1800,"Test1 Message","sg1"))
        expectedAnswer.add(Comment(IComment.CommentType.COMMENT_MESSAGE,1800,"SendingNode1 Msg",""))
        expectedAnswer.add(Comment(IComment.CommentType.COMMENT_MESSAGE,1801,"SendingNode2 Msg",""))
        expectedAnswer.add(Comment(IComment.CommentType.COMMENT_SIGNAL,1801,"Test3 Message","sg3"))
        Assert.assertEquals(expectedAnswer.toString(),result.toString())
    }

    @Test
    fun testfindValues()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result = dbc.findValues(file)
        val expectedAnswer : ArrayList<Value> = ArrayList()
        val expectedAnswerValues1 = ArrayList<IValueType>()
        expectedAnswerValues1.add(ValueType("Test Value 1",0.0))
        expectedAnswerValues1.add(ValueType("Test Value 2",1.0))
        expectedAnswer.add(Value(1800,"sg1",expectedAnswerValues1))
        val expectedAnswerValues2 = ArrayList<IValueType>()
        expectedAnswerValues2.add(ValueType("Test Value Test 1",0.0))
        expectedAnswerValues2.add(ValueType("Test Value Test2",1.0))
        expectedAnswerValues2.add(ValueType("TestValue3",2.0))
        expectedAnswerValues2.add(ValueType("TestValue 4",3.0))
        expectedAnswer.add(Value(1801,"sg3",expectedAnswerValues2))

        Assert.assertEquals(expectedAnswer.toString(),result.toString())
    }

    @Test
    fun testMapping()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val values = dbc.findValues(file)
        val comments = dbc.findComments(file)
        val signals = dbc.findSignals(file)
        val messages = dbc.findMessages(file)
        val expectedAnswer:ArrayList<Message>  = ArrayList()
        val sendingNode1 = (Message(1800,"Msg1Txt",8,"SendingNODE1", ArrayList(),null))
        val sendingNode2 = (Message(1801,"Msg2Txt",8,"SendingNODE2", ArrayList(),null))
        val sg1 = SIGNAL("sg1",0,1,true,1.0,0.0,0.0,1.0,"",null,null)
        val sg2 = SIGNAL("sg2",1,1,true,1.0,0.0,0.0,1.0,"",null,null)
        val sg3 = SIGNAL("sg3",0,2,true,1.0,0.0,0.0,3.0,"",null,null)
        val cm1 = Comment(IComment.CommentType.COMMENT_SIGNAL,1800,"Test1 Message","sg1")
        val cm2 = Comment(IComment.CommentType.COMMENT_MESSAGE,1800,"SendingNode1 Msg","")
        val cm3 = Comment(IComment.CommentType.COMMENT_MESSAGE,1801,"SendingNode2 Msg","")
        val cm4 = Comment(IComment.CommentType.COMMENT_SIGNAL,1801,"Test3 Message","sg3")
        val value1 = Value(1800,"sg1",ArrayList())
        value1.VALUE.add(ValueType("Test Value 1",0.0))
        value1.VALUE.add(ValueType("Test Value 2",1.0))
        val value2 = Value(1801,"sg3",ArrayList())
        value2.VALUE.add(ValueType("Test Value Test 1",0.0))
        value2.VALUE.add(ValueType("Test Value Test2",1.0))
        value2.VALUE.add(ValueType("TestValue3",2.0))
        value2.VALUE.add(ValueType("TestValue 4",3.0))

        sg1.ICOMMENT = cm1
        sendingNode1.comment = cm2
        sendingNode2.comment = cm3
        sg3.ICOMMENT = cm4

        sg1.IValue = value1
        sg3.IValue = value2

        sendingNode1.ISIGNAL.add(sg1)
        sendingNode1.ISIGNAL.add(sg2)

        sendingNode2.ISIGNAL.add(sg3)

        expectedAnswer.add(sendingNode1)
        expectedAnswer.add(sendingNode2)


        dbc.mapDBC(messages,signals,comments,values)
        Assert.assertEquals(expectedAnswer.toString(),messages.toString())


    }


}