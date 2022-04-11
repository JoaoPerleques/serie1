package serie1.problem

/* Fila prioritária Q implementada através de um min-heap de dimensão fixa size; */
data class AEDPriorityQueue(val heap: Array<Word?>,
                            var size:Int,
                            val cmp:(a: Word?, b: Word?) -> Int)


/* OFFER, adiciona um novo elemento a Q de dimensão size, organizado
segundo o critério de comparação; */



fun AEDPriorityQueue.offer(element: Word): Boolean {
    if (heap[size] != null) return false
    heap[size] = element
    size++
    minHeapify(0)

    return true
}


/* PEEK, retorna o elemento de Q com mais prioritário; */


fun AEDPriorityQueue.peek(): Word? {
    minHeapify(0)
    return if (size == 0) null else heap[0]
}

/* POLL remove e retorna o elemento de Q mais prioritário;*/


fun AEDPriorityQueue.poll(): Word? {
    if (size == 0) return null
    minHeapify(0)
    val element = heap[0]
    size--
    heap[0] = heap[size]
    heap[size] = null
    return element
}

private fun AEDPriorityQueue.minHeapify(i: Int) {
    var smallest = i
    val l = left(i)
    val r= right(i)
    if (l < size && cmp(heap[l], heap[i]) < 0) smallest = l
    if (r < size && cmp(heap[r], heap[smallest]) < 0) smallest = r
    if (smallest != i) {
        exchange(i, smallest)
            if (heap[i] != null && heap[smallest] != null )
                exchangePositions(heap[i]!!, heap[smallest]!!)
        minHeapify(smallest)
    }
}


private fun AEDPriorityQueue.exchange(i: Int, j: Int) {
    val x = heap[i]
    heap[i] =heap[j]
    heap[j] = x
}

private fun AEDPriorityQueue.exchangePositions(i: Word, j: Word) {

    var word = i
    var wordSmal = j

    val x = word
    word = wordSmal
    wordSmal = x
}

private fun AEDPriorityQueue.left(i: Int): Int {
    return 2 * i + 1
}

private fun AEDPriorityQueue.right(i: Int): Int {
    return 2 * i + 2
}