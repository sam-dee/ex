package desmos.ui


import paints.*
import java.awt.Color
import java.awt.Dimension
import java.awt.event.*
import javax.swing.*
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin


class MainFrame : JFrame() {
    private val controlPanel: ControlPanel = ControlPanel()
    private val minDim = Dimension(800, 800)
    val mainPanel: GraphicsPanel

    init {
        minimumSize = minDim
        defaultCloseOperation = EXIT_ON_CLOSE

        with(controlPanel) {
            val plane = CartesianPlane(
                xMin.value as Double, xMax.value as Double,
                yMin.value as Double, yMax.value as Double,
            )

            val polyPainter = PolyPainterContainer(plane)
            polyPainter.pointColor = cjp1.background
            polyPainter.polyColor = cjp2.background
            polyPainter.derivColor = cjp3.background

            polyPainter.paint_points = cb1.isSelected
            polyPainter.paint_poly = cb2.isSelected
            polyPainter.paint_deriv = cb3.isSelected

            val painters = mutableListOf(CartesianPainter(plane), polyPainter)

            mainPanel = GraphicsPanel(painters).apply {
                background = Color.WHITE
            }

            mainPanel.addComponentListener(object : ComponentAdapter() {
                override fun componentResized(e: ComponentEvent?) {
                    plane.width = mainPanel.width
                    plane.height = mainPanel.height
                    mainPanel.repaint()
                }
            })

//            mainPanel.addMouseListener(object : MouseAdapter() {
//                override fun mouseClicked(e: MouseEvent?) {
//                    with(polyPainter) {
//                            if (e != null) {
//                                addPoint(plane.xScr2Crt(e.x) to plane.yScr2Crt(e.y))
//                                mainPanel.repaint()
//                            }
//                    }
//                }
//            })

//            mainPanel.addMouseWheelListener {
//                println(it)
//            }

//            colors mapping
            listOf(cjp1, cjp2).forEach {
                with(it) {
                    addMouseListener(object : MouseAdapter() {
                        override fun mouseClicked(e: MouseEvent?) {
                            background = JColorChooser.showDialog(mainPanel, "Выберите цвет", Color.RED)
                            if (it == cjp1) polyPainter.pointColor = background
                            if (it == cjp2) polyPainter.polyColor = background
                            if (it == cjp3) polyPainter.derivColor = background
                            mainPanel.repaint()
                        }
                    })
                }
            }
//          check-box mapping
            cb1.addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    if (cb1.isSelected) {
                        painters.add(explicitfunctionPainter)
                    } else {
                        painters.remove(explicitfunctionPainter)
                    }
                    painters[0] // threads?..
                    mainPanel.repaint()
                }
            })


            cb2.addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    print(cb2.isSelected)
                    if (cb2.isSelected) {
                        painters.add(explicitfunctionPainter)
                    } else {
                        painters.remove(explicitfunctionPainter)
                    }
                    painters[0] // threads?..
                    mainPanel.repaint()
                }
            })

            xMin.addChangeListener {
                xMaxM.minimum = xMin.value as Double + 0.1
                plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
                mainPanel.repaint()
            }
            xMax.addChangeListener {
                xMinM.maximum = xMax.value as Double - 0.1
                plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
                mainPanel.repaint()
            }
            yMin.addChangeListener {
                yMaxM.minimum = yMin.value as Double + 0.1
                plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
                mainPanel.repaint()
            }
            yMax.addChangeListener {
                yMinM.maximum = yMax.value as Double - 0.1
                plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
                mainPanel.repaint()
            }
            plane.width = mainPanel.width
            plane.height = mainPanel.height
        }

        layout = GroupLayout(contentPane).apply {
            autoCreateGaps = true
            autoCreateContainerGaps = true
            setVerticalGroup(
                createSequentialGroup()
                    .addComponent(mainPanel)
                    .addComponent(controlPanel)
            )
            setHorizontalGroup(
                createParallelGroup()
                    .addComponent(mainPanel)
                    .addComponent(controlPanel)
            )
        }
        pack()
    }
}