package Source

import Interfaces.IValue
import Interfaces.IValueType

class Value(override var ID: Long, override var SignalName: String, override var VALUE: ArrayList<IValueType>) :IValue
{
    override fun toString(): String {
        var result = "ID: $ID SignalName: $SignalName "

        for(i in VALUE)
        {
            result += "$i "
        }
        return result
    }
}
