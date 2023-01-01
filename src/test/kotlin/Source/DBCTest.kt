package Source

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
        expectedAnswer.add(Message(1800,"Msg1Txt",8,"SendingNODE1", emptyArray()))
        expectedAnswer.add(Message(1801,"Msg2Txt",8,"SendingNODE2", emptyArray()))
        Assert.assertEquals(expectedAnswer.toString(),result.toString())
    }

    @Test
    fun testfindSignals()
    {
        val dbc:DBC = DBC()
        val file:File = File("src/test/resources/Test.dbc")
        val result = dbc.findSignals(file)
        val expectedAnswer: ArrayList<SIGNAL> = ArrayList()
        expectedAnswer.add(SIGNAL("sg1",0,1,true,1.0,0.0,0.0,1.0,"",null))
        expectedAnswer.add(SIGNAL("sg2",1,1,true,1.0,0.0,0.0,1.0,"",null))
        expectedAnswer.add(SIGNAL("sg3",0,2,true,1.0,0.0,0.0,3.0,"",null))
        Assert.assertEquals(expectedAnswer.toString(),result.toString())
    }
}