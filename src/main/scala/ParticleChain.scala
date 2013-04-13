import collection.mutable.ArrayBuffer
import java.util.Calendar
import util.Random

/**
 * Created with IntelliJ IDEA.
 * User: ken
 * Date: 4/13/13
 * Time: 7:39 AM
 * To change this template use File | Settings | File Templates.
 */
class ParticleChain(val length: Int = 80) {
	val chain = new ArrayBuffer[Int]
	val random = new Random//(Calendar.getInstance().getTimeInMillis)

	def initialize() = for (n <- 0 until length) chain.append(if (n < length/2) 1 else 0)
	initialize()

	//swap the ptcls at n and n-1
	def swap(n: Int) {
		val p1 = chain(n - 1)
		val p2 = chain(n)
		chain(n - 1) = p2
		chain(n) = p1
	}

	// allow particles to move
	def step() {
		// scan through chain and randomly swap out of order pairs
		for (n <- 1 until length if isOutOfOrder(n) && (random.nextInt < 0.5)) swap(n)
	}

	def equilibrate() {
		var steps = 0
		var isSorted = false
		println(this)

		while (!isSorted) {
			step()
			steps += 1
			println(this)
			isSorted = isOrdered()
		}
		if (isSorted) println("Sorted in " + steps + " steps")
	}

	// determine if n-1, n pair is out of order
	def isOutOfOrder(n: Int): Boolean = chain(n - 1) > chain(n)

	override def toString(): String = {
		val s = new StringBuilder
		for (c <- chain) s.append(c)
		s.toString()
	}

	def isOrdered(): Boolean = {
		var isOrdered = true
		for (n <- 1 until length) isOrdered &= !isOutOfOrder(n)
		isOrdered
	}
}
