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

            val explicitfunctionPainter = FunctionPainter(plane)
            explicitfunctionPainter.function = { x: Double -> 1 + (x + 2) * (x + 2) }
            explicitfunctionPainter.funColor = cjp1.background
            val implicitfunctionPainter = ImplicitFunctionPainter(
                plane,
                tMin.value as Double,
                tMax.value as Double
            )
            implicitfunctionPainter.function_x = { t: Double -> asin(sin(t)) }
            implicitfunctionPainter.function_y = { t: Double -> acos(cos(t)) }
            implicitfunctionPainter.funColor = cjp2.background

            val painters = mutableListOf(CartesianPainter(plane), explicitfunctionPainter, implicitfunctionPainter)

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

//            colors mapping
            listOf(cjp1, cjp2).forEach {
                with(it) {
                    addMouseListener(object : MouseAdapter() {
                        override fun mouseClicked(e: MouseEvent?) {
                            background = JColorChooser.showDialog(mainPanel, "Выберите цвет", Color.RED)
                            if (it == cjp1) explicitfunctionPainter.funColor = background
                            if (it == cjp2) implicitfunctionPainter.funColor = background
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

                    if (cb2.isSelected) {
                        painters.add(implicitfunctionPainter)
                    } else {
                        painters.remove(implicitfunctionPainter)
                    }
                    painters[0] // threads?..
                    println(painters)
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
            tMin.addChangeListener {
                tMinM.minimum = tMin.value as Double - 0.1
                implicitfunctionPainter.t_min = tMin.value as Double
                mainPanel.repaint()
            }
            tMax.addChangeListener {
                tMaxM.maximum = tMax.value as Double + 0.1
                implicitfunctionPainter.t_max = tMax.value as Double
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