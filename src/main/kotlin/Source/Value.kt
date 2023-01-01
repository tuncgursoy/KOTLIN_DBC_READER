package Source

import Interfaces.IValue
import Interfaces.IValueType

class Value(override var ID: Long, override var SignalName: String, override var VALUE: Array<IValueType>) :IValue
{

}
