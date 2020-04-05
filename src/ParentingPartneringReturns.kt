fun main(args: Array<String>) {
    val t = Integer.parseInt(readLine())
    repeat(t) { index ->
        val n = Integer.parseInt(readLine())
        val array = Array(n, {arrayOfNulls<Int>(n)})
        var result :String = ""
        var iscameronFirst:Boolean = true
        var isjamesFirst:Boolean = true
        var cameron = mutableMapOf("startTime" to 0, "endTime" to 0)
        var james = mutableMapOf("startTime" to 0, "endTime" to 0)
        var templist = mutableListOf<List<Int>>()
        repeat(n){
            val job = readLine()!!.split(" ").map(String::toInt)
            val jobMap = mutableMapOf("original" to n, "startTime" to job[0], "endTime" to job[1])
            templist.add(job)
        }

        val sortedList = templist.sortedWith(Comparator {
                a,b ->  a[0].compareTo(b[0])
        })

        for ((index, job) in sortedList.withIndex()) {
            if("IMPOSSIBLE".equals(result))
                break
            if(iscameronFirst){
                result += "C"
                cameron.set("startTime", job[0])
                cameron.set("endTime", job[1])
                iscameronFirst = false
            } else {
                if(isjamesFirst){
                    result += "J"
                    james.set("startTime", job[0])
                    james.set("endTime", job[1])
                    isjamesFirst = false
                } else {
                    if(cameron.get("endTime")!!.toInt() <= job[0] && cameron.get("startTime")!!.toInt() != job[0]){
                        result += "C"
                        cameron.set("startTime", job[0])
                        cameron.set("endTime", job[1])
                    } else if(james.get("endTime")!!.toInt() <= job[0] && james.get("startTime")!!.toInt() != job[0]){
                        result += "J"
                        james.set("startTime", job[0])
                        james.set("endTime", job[1])
                    } else{
                        result = "IMPOSSIBLE"
                    }
                }
            }
        }
        if(!"IMPOSSIBLE".equals(result) && !sortedList.equals(templist)){
            var truersult = CharArray(sortedList.size)
            for ((index, job) in sortedList.withIndex()) {
                for((i, item) in templist.withIndex()){
                    if(job.equals(item) && !truersult[i].equals('C')){
                        truersult[i] = result[index]
                        break
                    }
                }
            }
            result = String(truersult)
        }

        println("Case #${index+1}: ${result}")
    }
}

inline fun sortList(jobList:List<List<String>>): List<List<String>> {

    return jobList
}
