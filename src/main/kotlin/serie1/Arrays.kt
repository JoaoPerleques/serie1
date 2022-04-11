package serie1

import kotlin.math.abs

data class Point(var x:Int, var y:Int)

fun upperBound(a: IntArray, l: Int, r: Int, element: Int): Int {
    var i = 0

    if (r<l || l<0 || r<0 || a.isEmpty())return -1

    var mid = (l+r)/2
    if (a[r] < element)return r
    if (a[l] > element) return l-1

    if (element > a[mid])
        return upperBound(a,mid +1, r, element)
    else if (element < a[mid])
        return upperBound(a,l, mid-1, element)
    else{
        while(mid+i<a.size-1 && a[mid+i]==element){
            i++
        }
        if (i == 0)
            return mid
        else if(mid+i == a.size-1)
            return mid+i
        else
            return mid+i-1

    }
}

fun countIncreasingSubArrays(arr: IntArray): Int {
    var count = 0  //var que conta o numero de subArrays crescentes que existem
    var i = 0 //var que serve de iterador
    var j = 0
    while (j<arr.size){
        if (i in 1 until arr.size && arr[i] > arr[i-1]){
            count++
            i++
        }else {
            j++
            i = j
        }
    }
    return count
}

fun countEquals(points1 : Array<Point>,points2: Array<Point>,cmp:(p1:Point, p2:Point)->Int):Int{

    //Variables
    val size1 = points1.size;val size2 = points2.size;var i = 0;var j = 0;var cnt = 0; var cmp = 0
    if (size1>0 && size2>0){
        while (i < size1 || j < size2){
            cmp = cmp(points1[i],points2[j])
            if (cmp<0){
                if (i<size1-1) i++
                else break;
            }
            else if(cmp>0){
                if (j<size2-1)j++
                else break;
            }
            else if (cmp == 0){
                cnt++
                if (i == size1-1) break
                else i++
                if (j == size2-1) break
                else j++
            }
        }
        return cnt
    }else
        return 0

}

fun mostLonely(arr : IntArray) :Int{

    var edgeR= false
    var edgeL=false
    var maior = -1
    var menor = 0
    var left = 0
    var right = 0
    var lonely = 0

    mergeSort(arr,0,arr.size-1)
    println("---------Array sorted---------")
    arr.forEach { println("$it") }
    for (i in 0 until arr.size){
        if (i>0){
            left = abs(abs((arr[i]-arr[i-1])))
        }
        else
            edgeL=true

        if (i<arr.size-1){
            right = abs(abs((arr[i]-arr[i+1])))
        }
        else
            edgeR=true

        if (!edgeR&&!edgeL){
            if (right<left)
                menor=right
            else
                menor=left

            if (menor>maior){
                lonely=i
                maior=menor
            }
        }
        else if(edgeL){
            if (left>maior){
                lonely=i
                maior=right
            }
        }
        else{
            if (right>maior){
                lonely=i
                maior=left
            }
        }

        edgeR=false
        edgeL=false
    }
    return arr[lonely]
}

fun mergeSort(a:IntArray, l:Int,r:Int){
    if (l<r){

        val mid = (l+r)/2

        mergeSort(a,l,mid)
        mergeSort(a,mid+1,r)

        merge(a,l,r,mid)
    }
}

fun merge (t:IntArray,l: Int, r:Int,mid:Int){

    val n1 = mid-l
    val n2 = r - (mid+1)


    var tleft = IntArray(n1+1)
    var tright = IntArray(n2+1)

    for (i in 0 .. n1)
        tleft[i] = t[l+i]
    for (i in 0..n2)
        tright[i] = t[mid+1+i]

    var i = 0
    var j = 0
    var k = l

    while (i < tleft.size && j < tright.size){
        if (tleft[i] <= tright[j])
            t[k++] = tleft[i++]
        else
            t[k++] = tright[j++]
    }
    while (i < tleft.size)
        t[k++] = tleft[i++]
    while (j < tright.size)
        t[k++] = tright[j++]
}
