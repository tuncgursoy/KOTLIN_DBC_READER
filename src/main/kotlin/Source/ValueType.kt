package Source

import Interfaces.IValueType

class ValueType(override var NAME: String, override var VALUE: Double) :IValueType
{
    override fun toString(): String {
        return "NAME: \"$NAME\" Value: $VALUE"
    }
}