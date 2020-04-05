fun main(args: Array<String>) {
    val t = Integer.parseInt(readLine())
    repeat(t) { index ->
        val n = Integer.parseInt(readLine())
        val array = Array(n, {arrayOfNulls<Int>(n)})
        repeat(n){
            val m = readLine()!!.split(" ").map(String::toInt)
            array[it] = toArray<Int?>(m)
        }
        val k = sumTrace(array, n)
        val r = checkRow(array)
        val c = checkColumn(array, n)

        println("Case #${index+1}: ${k} ${r} ${c}")
    }
}

inline fun <reified T> toArray(list: List<*>): Array<T> {
    return (list as List<T>).toTypedArray()
}

inline fun sumTrace(array:Array<Array<Int?>>, index: Int): Int {
    var k:Int = 0
    repeat(index){
        k += array[it][it]!!
    }
    return k
}

inline fun checkRow(array:Array<Array<Int?>>): Int {
    var r:Int = 0
    array.forEach {
        if(it.size != it.distinct().size)
            r++
    }
    return r
}

inline fun checkColumn(array:Array<Array<Int?>>, index: Int): Int {
    var c:Int = 0
    var trans = array.transpose()
    trans.forEach {
        if(it.size != it.distinct().size)
            c++
    }
    return c
}

typealias Matrix = Array<Array<Int?>>

fun Matrix.transpose(): Matrix {
    val n = this.size
    val trans = Array(n, {arrayOfNulls<Int>(n)})
    for (i in 0 until n) {
        for (j in 0 until n) trans[i][j] = this[j][i]
    }
    return trans
}
