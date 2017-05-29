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

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate): Boolean {
        return date > start && date < endInclusive
    }

}
