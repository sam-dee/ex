package desmos.ui

import paints.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(private val painters: MutableList<Painter>) : JPanel() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        painters.forEach {
            if (g != null) {
                it.paint(g)
            }
        }
    }
}