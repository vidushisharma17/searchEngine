package com.vidushi

import java.io.File
import java.io.IOException
import java.util
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.index.IndexReader
import org.apache.lucene.queryParser.ParseException
import org.apache.lucene.queryParser.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Query
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.TopDocs
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.util.Version


class Searcher {

  val INDEX_DIRECTORY = "/Users/vidushisharma/IdeaProjects/LuceneProject/src/resources/IndexedFiles"
  private val FIELD_CONTENTS = "content"

   def searchIndex(instring :String): Unit ={
     println("Searching for "+instring)
     val reader: IndexReader=IndexReader.open(FSDirectory.open(new File(INDEX_DIRECTORY)))
     val searcher:IndexSearcher=new IndexSearcher(reader)
     val analyzer:Analyzer=new StandardAnalyzer(Version.LUCENE_34)
     val queryParser:QueryParser=new QueryParser(Version.LUCENE_34,FIELD_CONTENTS,analyzer) //Field Contents is defined above

     val query :Query=queryParser.parse(instring)
     val hits:TopDocs=searcher.search(query,5)
     val document:Array[ScoreDoc]=hits.scoreDocs

     println("Total Number of Hits according to content :"+hits.totalHits)
     val urls:util.List[String]=new util.ArrayList[String] // To use ArrayList import util
     var i:Int =0
     while ({i<document.length})
       {
         val doc:Document=searcher.doc(document(i).doc)
         val filename:String=doc.get("filename")
         println(filename)
        i+=1
       }




   }


}
