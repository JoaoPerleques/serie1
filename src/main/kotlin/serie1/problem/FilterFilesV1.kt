package serie1.problema

import serie1.problem.*

fun main (args: Array<String>) {

    val start = System.currentTimeMillis()

    val limits = Pair(args[0].toString(),args[1].toString())
    var files = Array<Files>(10) { i   -> Files(createReader("dummy"),true) }
    val outputFile = args[2]
    for (i in 3..args.size - 1) {
        files[i-3] = Files(createReader(args[i]),files[i].status)
    }
    var arrWord : Array<Word?> = emptyArray()

    var sort = Sort(files,arrWord,arrWord,false,limits, Word("",0))

    sort = sort.init()

    println(sort.finish)

    sort.word.forEach {
        if (it != null)
            println(it.w)
    }
    //AED library start
    var pw = createWriter("$outputFile")

    val comp = { x: Word?, y: Word? ->
        if (x != null && y != null) x.w.compareTo(y.w)
        else if (x != null) 1
        else if (y != null) -1
        else 0
    }

    var pq = AEDPriorityQueue(sort.word,sort.word.size,comp)
    var newRead : Word? = null



    println("init finish -----------------")
    while (!sort.finish){

        /*println("heap before peek------------------")
        pq.heap.forEach { println(it) }
        println("size : ${pq.size}")*/

        //Write in the file the element get by poll method
        var newInput = pq.peek()

        sort = sort.copy(word = pq.heap,lw=pq.heap)

        /*println("heap after peek------------------")
        pq.heap.forEach { println(it) }*/

        //println("size : ${pq.size}")

        if (newInput!= null)
        {
            pw.println(newInput.w)
            println("Wirte : $newInput")
        }



        /*println("Array Word before read -----------------------")
        sort.word.forEach { println(it) }*/

        //Read new word
        if (newInput != null)
            sort = sort.read(newInput)

        newRead = sort.NewRead//substituir pela primeira


        println("Finish : ${sort.finish}")

        if (!sort.finish){
            /*println("Array Word after read -----------------------")
            sort.word.forEach { println(it) }*/

            if (newRead != null){
                pq.poll()
                /*println("heap after poll------------------")
                pq.heap.forEach { println(it) }*/
                pq.offer(newRead)
                sort = sort.copy(word = pq.heap,lw = pq.heap)
                println("Read: $newRead")
            }
            /*println("heap after offer------------------")
            pq.heap.forEach { println(it) }*/

            //println("size : ${pq.size}")
        }else
            pq.poll()

    }


    println("Resto")
    sort.word.forEach { if (it != null )println(it!!.w) else println("null") }

    var resto : Word? = null
    for (i in sort.word.indices-1){
        resto = pq.poll()
        if (resto != null )
            pw.println(resto.w)
        else
            break
    }

    pw.close()
    println("Finish")
    //AED library end

    val end = System.currentTimeMillis()
    val dif = end - start

    println(dif)
}