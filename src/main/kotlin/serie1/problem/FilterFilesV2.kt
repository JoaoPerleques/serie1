package serie1.problema

import serie1.problem.*
import java.util.*

fun main (args: Array<String>) {

    val start = System.currentTimeMillis()
    //Java framework start

    val limits = Pair(args[0].toString(),args[1].toString())
    val outputFile = args[2]
    var files = Array<Files>(10) { i   -> Files(createReader("dummy"),true) }

    for (i in 3..args.size - 1) {
        files[i-3] = Files(createReader(args[i]),files[i].status)
    }
    var arrWord : Array<Word?> = emptyArray()
    var sort = Sort(files,arrWord,arrWord,false,limits, Word("",0))

    val comp = { x: Word?, y: Word? ->
        if (x != null && y != null) x.w.compareTo(y.w)
        else if (x != null) 1
        else if (y != null) -1
        else 0
    }


    println("Java framework start---------------------")
    //Writer

    var pwJava  = createWriter("$outputFile")

    var newReadJava : Word? = null
    sort = Sort(files,arrWord,arrWord,false,limits, Word("",0))
    sort = sort.init()
    //initialize pq
    var pqJava = PriorityQueue<Word?>(sort.word.size,comp)
    //Adding new word to pq
    for (i in sort.word.indices){
        pqJava.add(sort.word[i])
    }

    var arrTemp : Array<Word?> = emptyArray()


    while(!sort.finish){
        //Select the head element from heap
        var newInputJava = pqJava.peek()

        //Write the element from peek if it isn't null
        if (newInputJava != null){
            pwJava.println(newInputJava.w)
            println(newInputJava.w)
        }


        //copies the priority queue array to another array temporary and then equals this array to sort.word
        pqJava.toArray(arrTemp)
        arrTemp.forEach { if (it != null) println(it.w) else println("null") }
        sort = sort.copy(word = pqJava.toArray(arrTemp),lw=pqJava.toArray(arrTemp))


        //read a new word
        if (newInputJava != null)
            sort = sort.read(newInputJava)

        var newRead = sort.NewRead//substituir pela primeira


        if (!sort.finish){

            if (newRead != null){
                //Remove the element from the pq
                pqJava.poll()

                //Insert a new element in the pq
                pqJava.offer(newRead)

                //copies the new pq
                //This peace of code maybe is not necessary
                pqJava.toArray(arrTemp)
                sort = sort.copy(word = arrTemp,lw=arrTemp)

            }

        }else
            pqJava.poll()
    }

    var resto : Word?= null
    for (i in sort.word.indices-1){
        resto = pqJava.poll()
        if (resto != null )
            pwJava.println(resto.w)
        else
            break
    }

    pwJava.close()


    val end = System.currentTimeMillis()
    val dif = end - start
}