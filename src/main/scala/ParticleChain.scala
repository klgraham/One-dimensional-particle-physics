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
	require(length % 2 == 0)
	val chain: ArrayBuffer[Int] = new ArrayBuffer[Int]
	val random: Random = new Random(Calendar.getInstance().getTimeInMillis)
	val K: Int = 1 + random.nextInt(length-1)

	def initialize() = {
		if (chain.isEmpty)
			for (n <- 0 until length) chain.append(if (n < length/2) 1 else 0)
		else
			for (n <- 0 until length) chain(n) = if (n < length/2) 1 else 0
	}
	initialize()

	//swap the ptcls at n and n-1
	def swap(n: Int) {
		val p1 = chain(n - 1)
		val p2 = chain(n)
		chain(n - 1) = p2
		chain(n) = p1
	}

	// randomly select a pair and swap them if they're out of order
	def step() {
		val pairToSwap = 1 + random.nextInt(length-1)
		if (isOutOfOrder(pairToSwap)){
			swap(pairToSwap)
		}
	}

	def equilibrate() {
		var steps = 0
		var isSorted = false
		println(this)

		while (!isSorted) {
			step()
			steps += 1
			println(this)
			println("R_" + K + " = " + numParticlesToRightOf(K) + ", L_" + K + " = " + numVacanciesToLeftOf(K))
			println("dR_" + K + " = " + numParticlesToRightOf(K).toFloat/steps.toFloat + ", dL_" + K + " = " + numVacanciesToLeftOf(K).toFloat/steps.toFloat + "\n")
			isSorted = isOrdered()
		}
		if (isSorted) println("Sorted in " + steps + " swaps.")
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

	// r_k, the # of 1s to the right of site k, inclusive
	def numParticlesToRightOf(k: Int): Int = (for (i <- k until length) yield chain(i)).sum

	// l_k, the # of 0s to the left of site k, exclusive
	def numVacanciesToLeftOf(k: Int): Int = (for (i <- 0 until k) yield if (chain(i) == 1) 0 else 1).sum
}
