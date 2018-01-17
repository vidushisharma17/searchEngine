package com.vidushi

import java.io.File
import java.util
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.index.IndexReader
import org.apache.lucene.queryParser.QueryParser
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.TopDocs
import org.apache.lucene.util.Version
import org.apache.lucene.index.Term
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Query
import org.apache.lucene.search.TermQuery
import org.apache.lucene.store.FSDirectory

import org.apache.lucene.search.BooleanClause
import org.apache.lucene.search.BooleanQuery


class Searcher {

  val INDEX_DIRECTORY = "/Users/vidushisharma/IdeaProjects/LuceneProject/src/resources/IndexedFiles"
  private val FIELD_CONTENTS = "filename"

   def searchIndex(instring :String): Unit ={

     println("\nSearching for '" + instring + "' using QueryParser")
     //println("Searching for "+instring)
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

  //------------------------------//
def searchIndexWithTermQuery(searchString: String): Unit =
{
  println("\nSearching for '" + searchString + "' using TermQuery")

  val reader: IndexReader=IndexReader.open(FSDirectory.open(new File(INDEX_DIRECTORY)))
  val searcher:IndexSearcher=new IndexSearcher(reader)
  val term: Term = new Term(FIELD_CONTENTS, searchString)
  val query: Query = new TermQuery(term)
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


  def searchWithBooleanQuery(instring1:String , instring2:String): Unit ={
    println("\nSearching for '" + instring1 + "' and '" +instring2 +" ' using Boolean Query")

    val reader: IndexReader=IndexReader.open(FSDirectory.open(new File(INDEX_DIRECTORY)))
    val searcher:IndexSearcher=new IndexSearcher(reader)

    val term1 = new Term(FIELD_CONTENTS, instring1)
    val query1 = new TermQuery(term1)

    val term2 = new Term(FIELD_CONTENTS, instring2)
    val query2 = new TermQuery(term2)

    val query = new BooleanQuery
    query.add(query1, BooleanClause.Occur.MUST)
    query.add(query2, BooleanClause.Occur.MUST)

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



