package com.vidushi
import com.vidushi.Searcher
object mainSearcher extends App {
  override def main(args:Array[String]): Unit ={
    //val pt=new Searcher().searchIndex("txt")
    val pt2=new Searcher().searchWithBooleanQuery("1","txt")


  }

}
