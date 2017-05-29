package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when(this.year - other.year) {
            0 -> return when(this.month - other.month) {
                0 -> return this.dayOfMonth - other.dayOfMonth
                else -> this.month - other.month
            }
            else -> this.year - other.year
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this,other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return DateIterator(start,endInclusive)
    }

    operator fun contains(date: MyDate): Boolean {
        return date > start && date < endInclusive
    }

}

class DateIterator(start: MyDate, endInclusive: MyDate) : Iterator<MyDate> {
    var current = start
    val last = endInclusive
    override fun hasNext(): Boolean {
        return current <= last
    }

    override fun next(): MyDate {
        val result = current
        current = current.addTimeIntervals(TimeInterval.DAY,1)
        return result
    }

}
