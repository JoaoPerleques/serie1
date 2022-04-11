/**This file is just to keep parts of old code
 *
 *
 * --------- Start -------------
 * fun main(args: Array<String>) {

val min = readLineStr("upper limit")
val max = readLineStr("down limit")
val limits = Pair(min,max)



var files = Array<Files>(10) {i   -> Files(createReader("dummy"),true)}
for (i in 0..args.size - 1) {
files[i] = Files(createReader(args[i]),files[i].status)
}


var arrWord : Array<Word?> = emptyArray()

var sort = Sort(files,arrWord,arrWord,false,limits,Word("",0))

sort = sort.init()

println(sort.finish)


sort.word.forEach {
if (it != null)
println(it.w)
}
//AED library start
var pw = createWriter("output.txt")

val comp = { x: Word?, y: Word? ->
if (x != null && y != null) x.w.compareTo(y.w)
else if (x != null) 1
else if (y != null) -1
else 0

}

var pq = AEDPriorityQueue(sort.word,sort.word.size,comp)
var newRead :Word? = null



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


//Java framework start
var filesJava = Array<Files>(10) {i   -> Files(createReader("dummyJava"),true)}
for (i in 0..args.size - 1) {
filesJava[i] = Files(createReader(args[i]),files[i].status)
}

println("Java framework start---------------------")
//Writer

var pwJava  = createWriter("outputJava.txt")

var newReadJava :Word? = null
sort = Sort(filesJava,arrWord,arrWord,false,limits,Word("",0))
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

newRead = sort.NewRead//substituir pela primeira


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

for (i in sort.word.indices-1){
resto = pqJava.poll()
if (resto != null )
pw.println(resto.w)
else
break
}

pwJava.close()


}
val comp = { x: Word?, y: Word? ->
if (x != null && y != null) x.w.compareTo(y.w)
else if (x != null) 1
else if (y != null) -1
else 0

}
 * ---------------End---------------------
 * */

