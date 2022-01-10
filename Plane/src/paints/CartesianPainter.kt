package paints

import java.awt.*
import kotlin.math.roundToInt

class CartesianPainter(private val plane: CartesianPlane) : Painter {

    override fun paint(g: Graphics) {
        paintAxis(g)
        PaintXs(g)
        PaintYs(g)
    }

    private fun paintAxis(g: Graphics) {
        with(plane) {
            (g as Graphics2D).apply {
                stroke = BasicStroke(2F)
                if (xMin > 0 || xMax < 0) {
                    drawLine(width, 0, width, height)
                    drawLine(0, 0, 0, height)
                } else drawLine(xCrt2Scr(0.0), 0, xCrt2Scr(0.0), height)
                if (yMin > 0 || yMax < 0) {
                    drawLine(0, 0, width, 0)
                    drawLine(0, height, width, height)
                } else drawLine(0, yCrt2Scr(0.0), width, yCrt2Scr(0.0))
            }
        }
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this).toDouble()

    fun PaintXs(g: Graphics) {
        with(plane) {
            (g as Graphics2D).apply {
                val scale = 0.2
                val tSize = 10
                var coeff = 10
                if ((Math.abs(xMin) + Math.abs(xMax)) >= 20) coeff = 50
                if ((Math.abs(xMin) + Math.abs(xMax)) <= 3) coeff = 1
                stroke = BasicStroke(1F)
                var n = (xMin * 10).format(1)
                font = Font("Consolas", Font.BOLD, 12)
                while (n < xMax * 10) {
                    val (tW, tH) = fontMetrics.getStringBounds((n / 10).toString(), g)
                        .run { Pair(width.toFloat(), height.toFloat()) }
                    if (n % coeff == 0.0) {
                        if (n != 0.0) {
                            color = Color.RED
                            drawLine(xCrt2Scr(n / 10), yCrt2Scr(-scale), xCrt2Scr(n / 10), yCrt2Scr(scale))
                            color = Color.BLACK
                        }
                        var minus_amend = 0.0f
                        if (n / 10 <= 0) minus_amend = fontMetrics.getStringBounds("-", g).width.toFloat()
                        var str = (n / 10).toString()
                        if (n == 0.0) str = "0"
                        drawString(
                            str,
                            xCrt2Scr(n / 10) - (tW + minus_amend) / 2,
                            yCrt2Scr(0.0) + tH + tSize
                        )
                    } else if ((n % 5 == 0.0) and (coeff != 1) and (n != 0.0)) {
                        color = Color.BLUE
                        drawLine(xCrt2Scr(n / 10), yCrt2Scr(-scale / 2), xCrt2Scr(n / 10), yCrt2Scr(scale / 2))
                    } else if (coeff != 50) {
                        color = Color.BLACK
                        drawLine(xCrt2Scr(n / 10), yCrt2Scr(-scale / 3), xCrt2Scr(n / 10), yCrt2Scr(scale / 3))
                    }
                    n += 1
                }
            }
        }
    }

    fun PaintYs(g: Graphics) {
        with(plane) {
            (g as Graphics2D).apply {
                val scale = 0.2
                val tSize = 10
                var coeff = 10
                if (Math.abs(yMin) + Math.abs(yMax) >= 15) coeff = 50
                if (Math.abs(yMin) + Math.abs(yMax) <= 2) coeff = 1
                var n = (yMin * 10).format(1)
                stroke = BasicStroke(1F)
                font = Font("Consolas", Font.BOLD, 12)

                while (n < yMax * 10) {
                    val (tW, tH) = fontMetrics.getStringBounds((n / 10).toString(), g)
                        .run { Pair(width.toFloat(), height.toFloat()) }
                    if ((n % coeff == 0.0) and (n != 0.0)) {
                        color = Color.RED
                        drawLine(xCrt2Scr(-scale), yCrt2Scr(n / 10), xCrt2Scr(scale), yCrt2Scr(n / 10))
                        color = Color.BLACK
                        drawString((n / 10).toString(), xCrt2Scr(0.0) + tH + tSize, yCrt2Scr(n / 10) + tH / 4)

                    } else if ((n % 5 == 0.0) and (coeff != 1) and (n != 0.0)) {
                        color = Color.BLUE
                        drawLine(xCrt2Scr(-scale / 2), yCrt2Scr(n / 10), xCrt2Scr(scale / 2), yCrt2Scr(n / 10))
                    } else if (coeff != 50) {
                        color = Color.BLACK
                        drawLine(xCrt2Scr(-scale / 4), yCrt2Scr(n / 10), xCrt2Scr(scale / 4), yCrt2Scr(n / 10))
                    }
                    n += 1
                }
            }
        }
    }
}