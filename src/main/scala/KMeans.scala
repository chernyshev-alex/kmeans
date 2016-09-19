import Stream._
import PartialFunction._
import scala.collection.immutable._

object KMeansApp extends App {

  def kmeans(data : Seq[Int], start_points : Seq[Int], it_count : Int) = {
    
      val average = (ls : Seq[Int]) => ls.sum / ls.size 

      val distance = (a : Int, b : Int) => if (a < b) (b - a) else (a - b)
    
      val closest = (point : Int, means : Seq[Int]) => means.sortBy(x => distance(x, point)).head
            
      val point_groups = (means : Seq[Int], ls : Seq[Int]) => ls.groupBy(x => closest(x, means))
      
      val new_means = (pg : Map[Int, Seq[Int]], prev_means : Seq[Int])  
            => for (m <- prev_means if pg.contains(m)) yield average(pg.get(m).get)
          
      val iterate_means = (means : Seq[Int]) => new_means(point_groups(means, data), means)
          
      val itm = Stream.iterate(start_points)(iterate_means)
      
      val means = itm.zip(itm.tail).takeWhile { case (a, b) => a != b }

      means.take(it_count).last._2
  }
            
  val data = Seq(2, 3, 5, 6, 10, 11, 54, 50, 41)
  println(kmeans(data, Seq(5, 30), 100))
  
}
