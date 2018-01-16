package com.vidushi
import com.vidushi.Indexer
object MainIndexer extends App {
  override def main(arg: Array[String]): Unit = {
    try
      {
        val pt=new Indexer()
        pt.Indexer()

      }

    catch {
      case ex: Exception =>
        System.out.println("Cannot Start :(")
    }
  }

}
