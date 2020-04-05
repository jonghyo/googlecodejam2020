fun main(args: Array<String>) {
    val t = Integer.parseInt(readLine())
    repeat(t) { index ->
        val s = readLine()!!.toCharArray()
        var tempList = mutableListOf<String>()
        var result:String = ""
        for (i in 0 until s.size){
            var sameString:String = s[i].toString()
            for(j in i+1 until s.size){
                if(s[i].equals(s[j])){
                    sameString += s[j].toString()
                } else {
                    if(i == 0){
                        tempList.add(sameString)
                    }else{
                        var preMember = tempList.lastOrNull() ?: s[i].toString()
                        if(!preMember.contains(s[i]))
                            tempList.add(sameString)
                    }
                    break
                }
            }
            var preMember = tempList.lastOrNull() ?: s[i].toString()
            if(tempList.size == 0 ){
                tempList.add(sameString)
            }else if(!preMember.contains(s[i])){
                tempList.add(sameString)
            }

        }
        for(i in 0 until tempList.size){
            if(i == 0){
                if(tempList.size != 1){
                    result += addParent("0",tempList[i], tempList[i+1])
                }else {
                    result += addParent("0",tempList[i], "0")
                }
            }else if(i != tempList.size - 1){
                result += addParent(tempList[i-1], tempList[i],tempList[i+1])
            } else{
                result += addParent(tempList[i-1], tempList[i],"0")
            }

        }
        println("Case #${index+1}: ${result}")
    }
}

inline fun addParent(left:String, current:String, right:String): String {
    val leftdiff = Character.getNumericValue(current[0]) - Character.getNumericValue(left[0])
    val rightdiff = Character.getNumericValue(current[0]) - Character.getNumericValue(right[0])
    return "${addLeft(leftdiff)}${current}${addRight(rightdiff)}"
}

inline fun addLeft(s:Int): String {
    var leftParent:String = ""
    if(s >= 0){
        repeat(s){
            leftParent += "("
        }
    }
    return leftParent
}

inline fun addRight(s:Int): String {
    var rightParent:String = ""
    if(s >= 0){
        repeat(s){
            rightParent += ")"
        }
    }
    return rightParent
}

