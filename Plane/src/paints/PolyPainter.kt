package paints

import math.Newton
import java.awt.Color
import java.awt.Graphics

class PolyPainterContainer(private val plane: CartesianPlane) : Painter {

    var polyColor: Color = Color.BLACK
        set(value) {
            poly_painter.funColor = value
            field = value
        }

    var derivColor: Color = Color.BLACK
        set(value) {
            deriv_painter.funColor = value
            field = value
        }

    var pointColor: Color = Color.BLACK

    val point_width = 7

    var poly: Newton = Newton()

    val poly_painter = FunctionPainter(plane)
    val deriv_painter = FunctionPainter(plane)

    var paint_poly = false
    var paint_deriv = false
    var paint_points = false

    init {
        poly_painter.funColor = polyColor
        deriv_painter.funColor = derivColor

        poly_painter.function = poly
    }

    override fun paint(g: Graphics) {
        if (poly.coeff.isNotEmpty()){
            if (paint_poly) poly_painter.paint(g)

            if (paint_points) paint_points(g)

            if (paint_deriv) deriv_painter.paint(g)
        }
    }

    fun paint_points(g: Graphics) {
        g.color = pointColor
        poly.points.forEach { (x, y) ->
            g.fillOval(plane.xCrt2Scr(x) - point_width/2, plane.yCrt2Scr(y) - point_width/2, point_width, point_width)
        }
        g.color = Color.BLACK
    }

    fun addPoint(pair: Pair<Double, Double>) {
        poly.add_point(pair)
        deriv_painter.function = poly.derivative()
    }
}