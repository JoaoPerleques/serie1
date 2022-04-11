package serie1.problem

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.PrintWriter
import java.util.*


data class Word (val w : String, val reader : Int)

fun createReader(fileName: String): BufferedReader {
    return BufferedReader(FileReader(fileName))
}

fun createWriter(fileName: String?): PrintWriter {
    return PrintWriter(fileName)
}


public data class Sort(val file : Array<Files>, val word: Array<Word?> , val lw : Array<Word?>, val finish: Boolean, val limits : Pair<String,String>,val NewRead : Word)

public data class Files (val br : BufferedReader ,val status : Boolean)

fun String.range(limits: Pair<String,String>) : Pair<Boolean,Boolean>{

    var bellow = true
    var greater = false

    var first = this.compareTo(limits.first)
    if(first <= 0)
        bellow = false

    var second = this.compareTo(limits.second)
    if(second >= 0)
        greater = true

    return Pair(bellow,greater)


}

//initialize the program - Works
fun Sort.init() : Sort{
    var sortTemp = this
    var arr : Array<Word?> = emptyArray()
    var arrFiles = file
    var rd : String? = ""
    var last = lw
    var rp = false
    var rng :Pair<Boolean,Boolean> = Pair(false,true)

    if (arrFiles.isNotEmpty()){
        for (i in arrFiles.indices){
                do {
                    //lê do ficheiro
                    rd = arrFiles[i].br.readLine()
                    //verifica se não é null
                    if (!rd.isNullOrEmpty()){
                        rng = rd.range(limits)
                        //verifica os limites
                        if (rng.second){//se for maior que o limite inferior remove o file e passa para o proximo
                            sortTemp = copy(file = arrFiles).removeFile(i)
                            if (!sortTemp.finish)
                                arrFiles = sortTemp.file
                            else
                                return sortTemp

                            rp =false
                        }else if(!rng.first){ //se for menor que o limite superior continua a ler desse ficheiro
                            rp = true
                        }else{//se bellow && !greater verifica se esta repetida
                            if (i==0){//se for a primeira iteração não precisa de verificar
                                arr += Word(rd,i)
                                rp= false
                            }else if (rd.repetida(Sort(file, word,arr,finish,limits,NewRead))){//se for repetida continua a ler
                                rp = true
                            }else{//se não for repetida guarda a palavra e passa para o próximo file
                                arr += Word(rd,i)
                                rp= false
                            }
                        }
                    }else{//se for null remove o file e passa para o proximo file
                        sortTemp = copy(file = arrFiles).removeFile(i)
                        if (!sortTemp.finish)
                            arrFiles = sortTemp.file
                        else
                            return sortTemp
                        rp =false
                    }


                }while(rp)


            }

        }



    return copy(file=arrFiles,word=arr, lw = arr)


}

fun Sort.removeFile (e : Int) : Sort {

    var arr : Array<Files> = emptyArray()
    for (i in file.indices)
    {
        if (i != e)
            arr += file[i]
        else{
            arr += Files(file[i].br,false)
            println("remove file $i")
        }

    }


    if( copy(file = arr).Finish())
       return  copy(finish=true)

    return copy(file=arr)
}

fun Sort.read(w : Word) : Sort{

    var sortTemp = this
    var arr : Array<Word?> = word
    var arrFiles = file
    var new_read : Word? = null
    var afile = w.reader
    var rd : String? = ""
    var rp = false
    var rng :Pair<Boolean,Boolean> = Pair(false,true)

    if (arrFiles.isNotEmpty()){
        do {
            if (arrFiles[afile].status){//verifica se o status do file esta a true (legível)
                rd = arrFiles[afile].br.readLine()//lê do ficheiro
                if (!rd.isNullOrEmpty()){//verifica se o que foi lido é nulo
                    rng = rd.range(limits)
                    if (rng.second){
                        sortTemp = copy(file = arrFiles).removeFile(afile)
                        if (!sortTemp.finish)
                            arrFiles = sortTemp.file
                        else
                            return sortTemp
                        rp =true
                    }else if(!rng.first){
                        rp = true
                    }else{
                        //vê se é repetido
                        if (rd.repetida(this)){
                            rp = true
                        }else{//verify  if the last position is null
                            rp = false
                            new_read = Word(rd,afile)
                        }
                    }

                }else{ //se for null altera o status do file
                    sortTemp = copy(file = arrFiles).removeFile(afile)
                    if (!sortTemp.finish)
                        arrFiles = sortTemp.file
                    else
                        return sortTemp
                    rp =true
                }
            }else{
                if (afile < arrFiles.size-1)
                    afile++
                else
                    afile = 0
            }
        } while (rp)
    }

    if (new_read != null)
        return copy(file = arrFiles, NewRead = new_read)
    else
        return copy(file = arrFiles)

}

fun String.repetida (sort: Sort) : Boolean{
    var rep = false
    var i = -1
    do {
        i++
        var word :Word? = sort.lw[i]
        if (word != null && this == word.w )
            rep = true

    }while (!rep && i < sort.lw.size-1)
    return rep
}

fun Sort.Finish() :Boolean{

    var f = true
    file.forEach {
        if (it.status)
            return false
    }
    return f

}

fun cmp(x: String, y: String): Int {
    return if (x != null && y != null) x.compareTo(y)
    else if (x != null) 1
    else if (y != null) -1
    else 0
}

fun readLineStr (msg : String) : String {
    var str = true
    var finalStr = ""
    do {
        str = true
        print("$msg :")
        var read = readLine()
        if (read != null){
            //Remove spaces from string
            read = read.trim()
            //Upper case all char
            read = read.lowercase()
            //Verify if it is all letters
            read.forEach {
                if (it.code !in 97 .. 122 && str){
                    str = false }
            }
        }
        else
            str = false
        if (str)
            finalStr = read!!
    }while (!str)
    return finalStr
}
/**
 * To do
 *  Limitar por palavras introduzidas pelo utilizador



fun result(x: Word?, y: Word?): Int {
    return if (x != null && y != null) x.w.compareTo(y.w)
    else if (x != null) 1
    else if (y != null) -1
    else 0
}

if (rd.range(limits)){
if (i==0){
arr += Word(rd,i)
rp= false
}else if (rd.repetida(Sort(file, word,arr,finish,limits,NewRead))){
rd = arrFiles[i].br.readLine()
rp = true
}else{
arr += Word(rd,i)
rp= false
}
}else{
sortTemp = copy(file = arrFiles).removeFile(i)
if (!sortTemp.finish)
arrFiles = sortTemp.file
else
return sortTemp

rp =false
}
}else{
sortTemp = copy(file = arrFiles).removeFile(i)
if (!sortTemp.finish)
arrFiles = sortTemp.file
else
return sortTemp
rp =true
}
*/
