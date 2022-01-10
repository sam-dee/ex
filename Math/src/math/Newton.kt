package math

class Newton(coeff_p: Iterable<Double>) : Polynomial(), (Double) -> Double {

    var points: MutableMap<Double, Double> = mutableMapOf()

    init {
        _coeff.addAll(coeff_p)
    }

    constructor() : this(emptyList())

    constructor(_points: MutableMap<Double, Double>) : this(emptyList()) {
        this.points = _points
        val p = Polynomial()
        for (i in points.keys.indices) {
            p += omega(i) * divdiff_nth(i)
        }
        coeff = p.coeff
    }

    private var cachedOmega = Polynomial(1.0)


    fun add_point(point: Pair<Double, Double>) {
        points += point
        val deg = points.size - 1
        this += cachedOmega * divdiff_nth(deg)
        cachedOmega = cachedOmega * Polynomial(-point.first, 1.0)
    }

    fun omega(n: Int): Polynomial {
        val p = Polynomial(1.0)
        points.keys.take(n).forEach {
            p *= Polynomial(-it, 1.0)
        }
        cachedOmega = p
        return p
    }


    fun divdiff_nth(n: Int): Double {
        var sum = 0.0

        points.keys.take(n + 1).forEach { xj ->
            var prod = 1.0
            points.keys.take(n + 1).forEach { xi ->
                if (xi != xj) prod *= (xj - xi)
            }
            sum += points[xj]!! / prod
        }
        return sum
    }

    override operator fun invoke(x: Double): Double {
        return super.invoke(x)
    }
}